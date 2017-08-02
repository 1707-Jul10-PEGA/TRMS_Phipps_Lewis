package com.trms.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.trms.jdbc.doa.FormImplementDOA;

/**
 * Servlet implementation class SubmitForm
 */
public class SubmitForm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SubmitForm() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		System.out.println("in Submit Servlet Post");
		Double myCost = Double.parseDouble(request.getParameter("cost"));
		String myType = request.getParameter("grade");
		Integer myID = (Integer) request.getSession().getAttribute("ID");
		int myGrade = 1;
		System.out.println(myCost + " " + myType);
		switch (myType) {
		case "University Course":
			myGrade = 1;
			break;
		case "Seminar":
			myGrade = 2;
			break;
		case "Certification Preparation Class":
			myGrade = 3;
			break;
		case "Certification":
			myGrade = 4;
			break;
		case "Technical Training":
			myGrade = 5;
			break;
		case "Other": myGrade = 6;
		break;
		default:
			throw new IllegalArgumentException("You have selected an invalid course type");
		}
		FormImplementDOA newDOA = new FormImplementDOA();
		try {
			newDOA.submitReimbursementRequest(myID, myCost, myGrade, "A new submission");
			System.out.println("Success on making a new submission!");
		} catch (SQLException e) {
			System.out.println("Failed to generate new submission");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
