package com.trms.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.trms.jdbc.doa.EmployeeImplementDOA;
import com.trms.jdbc.doa.FormImplementDOA;

public class VerifyLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerifyLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("YO IM HERE");

		
		String us = request.getParameter("uname");
		String pass = request.getParameter("psw");
		System.out.println(us + " " + pass);
		EmployeeImplementDOA newDOA =  new EmployeeImplementDOA();
		int test = -1;
		try {
			test = newDOA.getEmployeeIDOnLoginInfo(us, pass);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Failed");
			e.printStackTrace();
		}
		
		System.out.println("Your UserID is: " + test);
		
//		else
//		{
//			 FormImplementDOA newFDOA = new FormImplementDOA();
//			 try {
//				newFDOA.submitReimbursementRequest(1, 100.0, 2, "Submitted this via website");
//			} catch (SQLException e) {
//				System.out.println("Failed to submit request");
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//	
//		}
		
//    	System.out.println(emp);
		String emp = "<h1>New message, successfuly wrote back.</h1>";
    	response.getWriter().write(emp);
		response.sendRedirect("login.html");
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Made it here!");
	}


}
