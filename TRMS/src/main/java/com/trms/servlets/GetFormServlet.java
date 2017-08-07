package com.trms.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trms.jdbc.doa.EmployeeImplementDOA;
import com.trms.jdbc.doa.FormImplementDOA;
import com.trms.pojo.Form;

/**
 * Servlet implementation class GetFormServlet
 */
public class GetFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getRootLogger();  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetFormServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Form> myForms = new ArrayList<Form>();
		FormImplementDOA formdoa = new FormImplementDOA();
		try {
			myForms = formdoa.getFormsOnEmpID(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

		
		ObjectMapper om = new ObjectMapper();
		String output = null;
		try{
			output = om.writeValueAsString(myForms);
			}catch(JsonProcessingException e){
				e.printStackTrace();
			}
		log.info(myForms);
		response.getWriter().append(output);
	
	}
}