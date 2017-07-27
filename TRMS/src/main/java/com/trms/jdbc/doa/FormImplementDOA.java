package com.trms.jdbc.doa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;

import org.apache.log4j.Logger;

import com.trms.jdbc.util.ConnectionFactory;

public class FormImplementDOA implements FormDOA {
	private static Logger log = Logger.getRootLogger();

	Connection conn = null;

	public FormImplementDOA() {
		setup();
	}

	public void setup() {
		// log.debug("Attempting to use Connection Factory");
		conn = ConnectionFactory.getInstance().getConnection();

	}

	/**
	 * This method takes a formID and returns the cost (converted according to
	 * grade scale) of the reimbursement to the company.
	 * @Return Returns 0if it fails to retrieve a reimbursementcost, otherwise it retrieves the converted cost
	 */
	public double getReimbursementCostOnFormID(Integer formID) throws SQLException {
		//SQL Statement
		String sql = "SELECT Full_Cost, GRADE_PERCENT FROM Form_Submissions INNER JOIN GRADE_FORMAT ON  Form_Subbmissions.GRADE_FORMAT_ID = GRADE_FORMAT.GRADEID WHERE formID = ?";
		//Prep statement and add the ID
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, formID);
		//Store ResultSet
		ResultSet rs = pstmt.executeQuery();
		double totalCost = 0;
		double percentage;
		if (rs.next()) {
			//Percentage is drawn from the GRADE_FORMAT table
			totalCost = rs.getDouble(1);
			percentage = rs.getDouble(2);
			// Convert to actual reinbursement cost
			totalCost = totalCost * percentage;
		} else {
			log.error("Failed to locate a form with that ID. Returning 0.");
		}
		return totalCost;
	}

	/**
	 * 
	 * @param formID ID number of the form stored in our Form_Submissions Table
	 * @return Returns the full, unconverted cost of the reimbursement (what the employee submitted)
	 */
	public double getFullCostOnFormID(Integer formID) throws SQLException{
		//SQL Statement
		String sql = "SELECT Full_Cost FROM Form_Submissions WHERE formID = ?";
		//Prepare with formID number
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, formID);
		ResultSet rs = pstmt.executeQuery();
		double totalCost = 0;

		if (rs.next()) {
			totalCost = rs.getDouble(1);
		} else {
			log.error("Failed to locate a form with that ID Returning 0.");
		}
		return totalCost;
	}
	/**
	 * This allows one to set a new cost via form ID, note this is NOT the converted amount, use setReimbursementCostOnFormID to auto convert to full amount
	 * @param formID form to be modified
	 * @param newCost New, full cost of reimbursement to be set
	 * @return returns the value given to the database if successful, otherwise returns 0.0;
	 */
	public double setFullCostOnFormID(Integer formID, Double newCost) throws SQLException{
		if(newCost <= 0.0)
		{
			log.warn("Attempted to set Reimbursement amount to a zero or negative value");
			return 0.0;
		}
		conn.setAutoCommit(false);
		//SQL statement and preparation
		String sql = "UPDATE Form_Submissions SET  FULL_COST = ? WHERE FormID = ? ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, formID);

		Savepoint s = conn.setSavepoint();
		
		int num = pstmt.executeUpdate();
		
		//Honestly I don't know why we do this or how we know to roll back here;
		if (num > 1) {

			conn.rollback(s);
		}
		conn.commit();

		conn.setAutoCommit(true);
		return newCost;
	}

	/**
	 * This allows one to set a new cost via form ID, note this is the converted amount, use setFullCostOnFormID for full cost instead
	 * @param formID form to be modified
	 * @param newCost New, full cost of reimbursement to be set
	 * @return returns the value given to the database if successful, otherwise returns 0.0;
	 */
	public double setReimbursementCostOnFormID(Integer formID, Double newCost) throws SQLException {
		if(newCost <= 0.0)
		{
			log.warn("Attempted to set Reimbursement amount to a zero or negative value");
			return 0.0;
		}
		//SQL PREP, selects the percentage to calculate proper adjustment
		String sql = "SELECT GRADE_PERCENT FROM Form_Submissions INNER JOIN GRADE_FORMAT ON Form_Subbmissions.GRADE_FORMAT_ID = GRADE_FORMAT.GRADEID WHERE formID = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, formID);
		ResultSet rs = pstmt.executeQuery();
		double percentage;
		double newValue = 0;
		if (rs.next()) {
			// Convert to Full Cost
			percentage = rs.getDouble(1);
			newValue = newCost/percentage;

		}
		else{
			log.error("failed to retrieve a related Grade");
			return 0.0;
		}
		//S
		String sql2 = "UPDATE Form_Submissions SET FULL_COST = ? WHERE FormID = ? ";
		PreparedStatement pstmt2 = conn.prepareStatement(sql);
		pstmt.setDouble(1, newValue);
		pstmt.setInt(1, formID);

		pstmt.executeUpdate();
		return newValue;
	}

}
