package com.trms.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.trms.BD.Middleman;
import com.trms.jdbc.doa.FormImplementDOA;
import com.trms.jdbc.util.ConversionTools;

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
		Integer myID = 10;//(Integer) request.getSession().getAttribute("ID");
		String date = request.getParameter("date");
		String descript = request.getParameter("description");
		System.out.println(myCost + " " + myType + " " + descript + " date: "+ date);
		//Attempt to create a form, not if the latter functions fail to generate a form, this will be false
		if(!Middleman.createForm(myID, date, myCost, ConversionTools.NameToGrade(myType), descript)){
			//if it fails, set response code to 400
			response.setStatus(400);
		}


	}

}
