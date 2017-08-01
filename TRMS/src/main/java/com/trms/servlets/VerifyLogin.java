package com.trms.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.trms.jdbc.doa.EmployeeImplementDOA;
import com.trms.jdbc.doa.FormImplementDOA;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
		ObjectMapper om = new ObjectMapper();
    	String blakeString = null;
    	try {
			blakeString = om.writeValueAsString(test);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//RequestDispatcher rd = request.getRequestDispatcher("login.html");
		
    	response.getWriter().write(blakeString);
		//response.sendRedirect("login.html");
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Made it here!");
	}

	/**
	 * 	System.out.println("YO IM HERE");

		
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
		
    	response.getWriter().write(test);
		response.sendRedirect("login.html");
	 */

}
