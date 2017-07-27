package com.trms.jdbc.doa;

import java.sql.SQLException;

public interface FormDOA {
	
	public double getReimbursementCostOnFormID(int formID)throws SQLException;
	public double getFullCostOnFormID(int formID)throws SQLException;
	public double setFullCostOnFormID(int formID, Double newCost) throws SQLException;
	public double setReimbursementCostOnFormID(int formID, Double newCost) throws SQLException;
	

}
