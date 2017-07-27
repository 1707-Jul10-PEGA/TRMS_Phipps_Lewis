package com.trms.jdbc.doa;

import java.sql.SQLException;

public interface FormDOA {
	
	public double getReimbursementCostOnFormID(Integer formID)throws SQLException;
	public double getFullCostOnFormID(Integer formID)throws SQLException;
	public double setFullCostOnFormID(Integer formID, Double newCost) throws SQLException;
	public double setReimbursementCostOnFormID(Integer formID, Double newCost) throws SQLException;
	

}
