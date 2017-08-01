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
	public double getReimbursementCostOnFormID(int formID) throws SQLException {
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
			totalCost = rs.getDouble("Full_Cost");
			percentage = rs.getDouble("Grade_Percent");
			// Convert to actual reinbursement cost
			totalCost = totalCost * percentage;
		} else {
			log.error("Failed to locate a form with that ID. Returning 0.");
		}
		return totalCost;
	}
	/**
	 * 
	 * @param formID
	 * @return Returns the string representation of the form(s) associated with the id
	 * @throws SQLEXception
	 */
	public String getFormsOnEmpID(int empID) throws SQLException{
		String output = "";
		
		String sql = "select employee.firstname, employee.lastname, form_submissions.date_made, form_submissions.status, form_submissions.grade_score from employee inner join form_submissions on employee.employeeid = form_submissions.employeeid where employee.employeeid = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, empID);
		
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()){
			output = output + rs.getString("firstname");
			output = output + ";" + rs.getString("lastname");
			output = output + ";" + rs.getString("date_made");
			output = output + ";" + rs.getString("status");
			output = output + ";" + rs.getString("grade_score");
			
		}
		
		return output;
		
		
	}

	/**
	 * 
	 * @param formID ID number of the form stored in our Form_Submissions Table
	 * @return Returns the full, unconverted cost of the reimbursement (what the employee submitted)
	 */
	public double getFullCostOnFormID(int formID) throws SQLException{
		//SQL Statement
		String sql = "SELECT Full_Cost FROM Form_Submissions WHERE formID = ?";
		//Prepare with formID number
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, formID);
		ResultSet rs = pstmt.executeQuery();
		double totalCost = 0;

		if (rs.next()) {
			totalCost = rs.getDouble("Full_Cost");
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
	public double setFullCostOnFormID(int formID, Double newCost) throws SQLException{
		if(newCost <= 0.0)
		{
			log.warn("Attempted to set Reimbursement amount to a zero or negative value");
			return 0.0;
		}
		conn.setAutoCommit(false);
		//SQL statement and preparation
		String sql = "UPDATE Form_Submissions SET  FULL_COST = ? WHERE FormID = ? ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setDouble(1, newCost);
		pstmt.setInt(2, formID);

		Savepoint s = conn.setSavepoint();
		
		int num = pstmt.executeUpdate();
		
		//If somehow I've changed more than one row, rollback
		if (num > 1) {

			conn.rollback(s);
			log.warn("This should never happen: updated multiple rows on setFullCost method");
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
	public double setReimbursementCostOnFormID(int formID, Double newCost) throws SQLException {
		if(newCost <= 0.0)
		{
			log.warn("Attempted to set Reimbursement amount to a zero or negative value");
			return 0.0;
		}
		//SQL PREP, selects the percentage to calculate proper adjustment
		String sql = "SELECT GRADE_PERCENT FROM Form_Submissions INNER JOIN GRADE_FORMAT ON Form_Submissions.GRADE_FORMAT_ID = GRADE_FORMAT.GRADEID WHERE formID = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, formID);
		ResultSet rs = pstmt.executeQuery();
		double percentage;
		double newValue = 0.0;
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
		pstmt.setInt(2, formID);

		pstmt.executeUpdate();
		return newValue;
	}
	
	/**
	 * As Employeee can submit reimbursement
	 * @param EmpId
	 * @param fullCost
	 * @param gradeFormatID
	 * @param description
	 * @return
	 * @throws SQLException
	 */
	//Should this method check for a usable EmployeeID?
	public int submitReimbursementRequest(int empId, double fullCost, int gradeFormatID, String description) throws SQLException{
		if(fullCost <= 0.0)
		{
			log.warn("Attempted to set Reimbursement amount to a zero or negative value, you attempted to enter: "+ fullCost);
			return -1;
		}
		if(!(0 < gradeFormatID && gradeFormatID < 7))
		{
			log.warn("No such gradeFormatID, please enter a value in the range of 1-6, you attempted to input: " + gradeFormatID);
			return gradeFormatID;
		}
		
		String sql = "INSERT INTO FORM_SUBMISSION (FormID, EmployeeID, Date_Made, Full_Cost, Grade_Format_ID, Grade_Score, Description, Status)"
				+ " VALUES (1, ?, CURRENT_TIMESTAMP, ?, ?, -1, ?, 1)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, empId);
		pstmt.setDouble(2, fullCost);
		pstmt.setInt(3, gradeFormatID);
		pstmt.setString(4, description);
		
		pstmt.executeUpdate(sql, PreparedStatement.RETURN_GENERATED_KEYS);
		ResultSet rs = pstmt.getGeneratedKeys();
		int formID = rs.getInt(1);	
		return formID;
	
		
	}
	
	 //as X can access a grade (0 or 1 on pass or fail for now; Grade_Score

	public int checkGradeOnFormID(int formID) throws SQLException{
		
		
		String sql = "SELECT Grade_Score FROM FORM_SUBMISSION WHERE FormID = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, formID);
		
		ResultSet rs = pstmt.executeQuery();
		int gradeScore = -1;
		if(rs.next())
		{
			gradeScore = rs.getInt(1);
			if(rs.wasNull())
			{
				log.error("This Submission has no grade score");
				gradeScore = -1;
			}
		}
		else
		{
		 log.error("No such column found");	
		}
		
		return gradeScore;
	}
	/**
	 * 
	 * @param formID
	 * @return
	 * @throws SQLException
	 */
	
	public String increaseApprovalLevelOnFormID(int formID) throws SQLException{
		
		//Selecting the current status, extraInfo is used if we attempt to increase approval level above max
		String extraInfo = "";
		String statusLevel = "This function has failed, please check the FormDOA";
		String sql = "SELECT STATUS FROM FORM_SUBMISSION WHERE FormID = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, formID);
		
		ResultSet rs = pstmt.executeQuery();
		if(rs.next())
		{
			//If the current status is successfully selected, grab it
			int myApprovalLevel = rs.getInt(1);
			
			//Update form submission approval level to be one higher
			String sql2 = "UPDATE FORM_SUBMISSION SET STATUS = ? WHERE FormID = ? ";
			PreparedStatement pstmt2 = conn.prepareStatement(sql2);
			//Check that we don't go over our highest approval level
			if(myApprovalLevel < 5 )
			{
				pstmt2.setInt(1, (myApprovalLevel+1));
				myApprovalLevel++;
				log.info("New Approval level is: " + myApprovalLevel);
			}
			else
			{
				//If we are at max approval keep the value and let the user know;
				pstmt2.setInt(1, myApprovalLevel);
				log.info("Submission already at maximum approval level:" + myApprovalLevel);
				//Extra Info should no longer be the empty string, so we can let the caller of this method know they were already at full approval.
				extraInfo = " This form has already been fully approved for reimbursement!";
			}
			pstmt2.setInt(2, formID);
			int rs2 = pstmt2.executeUpdate();
			
			//Now grab relevant description of the approval level to pass to the method caller
			String sql3 = "SELECT Descript FROM APPROVAL_STATUS WHERE StatusID = ?";
			PreparedStatement pstmt3 = conn.prepareStatement(sql);
			pstmt2.setInt(1, myApprovalLevel);
			
			ResultSet rs3 = pstmt3.executeQuery();
			if(rs3.next())
			{
				statusLevel = rs3.getString("Descript");
			}
		}
		
		
		return statusLevel+extraInfo;
		
	}
	
	
}
