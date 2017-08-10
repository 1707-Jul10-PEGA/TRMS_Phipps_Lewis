package com.trms.jdbc.doa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.trms.jdbc.util.ConnectionFactory;
import com.trms.jdbc.util.Passwords;

public class ReadImplementDOA implements ReadDOA {
	private static Logger log = Logger.getRootLogger();
	Connection conn = null;
	private void setup(){
		conn = ConnectionFactory.getInstance().getConnection();
	}
	public ReadImplementDOA(){
		setup();
	}
	@Override
	public int CheckEmployeePosition(int empID) throws SQLException{
		return 0;
	}

	@Override
	public int getEmployeeIDOnLoginInfo(String username, String plainPass) throws SQLException{
			String sql = "SELECT EmployeeID, PASS FROM Employee WHERE Username = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, username);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
	
					try {
						if(Passwords.check(plainPass, rs.getString("PASS")))
						{
						return rs.getInt(1);
						}
					} catch (Exception e) {
						log.error("Invalid PasswordFormat in Database");
						e.printStackTrace();
					}
				
			}
			
			return -1;
		}
	}


