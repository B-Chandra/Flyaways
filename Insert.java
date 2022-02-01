package com.flyways.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.flyways.database.DbConnection;


@WebServlet(name = "Insertflight", urlPatterns = { "/Insertflight" })
public class Insert extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DbConnection dao;
       
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String name=request.getParameter("name");
    	String from=request.getParameter("from");
    	String to=request.getParameter("to");
    	String departure=request.getParameter("departure");
    	String time=request.getParameter("time");
    	String price=request.getParameter("price");
    	
    	HashMap<String,String> flights=new HashMap<>();
    	flights.put("name", name);
    	flights.put("from", from);
    	flights.put("to", to);
    	flights.put("date", departure);
    	flights.put("time", time);
    	flights.put("price", price);
    	try {
    	DbConnection Connection=new DbConnection();
    	HttpSession session=request.getSession();
    	if(dao.insertFlight(flights)) {
    	session.setAttribute("message", "Flight Added Successfully");
    	}
    	else {
    	session.setAttribute("message", "Invalid Details");
    	}
    	} catch (ClassNotFoundException | SQLException e) {
    	// TODO Auto-generated catch block
    	System.out.print("error");
    	e.printStackTrace();
    	}
    	response.sendRedirect("AdminHome.jsp");
    	}
	}


