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
	//protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Made it here!");


		String us = request.getParameter("username");
		String pass = request.getParameter("password");
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
		
		request.getSession(true).setAttribute("ID", test);
		System.out.println("Your UserID is: " + request.getSession().getAttribute("ID"));
		
//		RequestDispatcher dispatcher = request.getRequestDispatcher("employee.html");
//		dispatcher.forward(request, response);
		response.sendRedirect("employee.html");
}
	
	
}
