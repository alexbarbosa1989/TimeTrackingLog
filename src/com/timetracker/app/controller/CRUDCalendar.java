package com.timetracker.app.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.timetracker.app.dao.CalendarDao;
import com.timetracker.app.dto.CalendarDto;

/**
 * Servlet implementation class CRUDCalendar
 */
@WebServlet("/CRUDCalendar")
public class CRUDCalendar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CRUDCalendar() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferReader = request.getReader();
        String linea = null;
        String calendarUsr = null;
        JSONObject jObj;
        
        while ((linea = bufferReader.readLine()) != null) {
        	stringBuilder.append(linea);
        }
        
		try {
			jObj = new JSONObject(stringBuilder.toString());
			
			calendarUsr = setOptionCalendar(jObj);
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		out.print(calendarUsr);
	}
	
	private String setOptionCalendar(JSONObject obj) throws JSONException{
		String option = obj.getString("action");
		String usermail = obj.getString("usermail");
		
		ArrayList<CalendarDto> calendarList = new ArrayList<CalendarDto>();
		if (option.equalsIgnoreCase("read")){
			calendarList = getCalendarUsr(usermail);
		}
		JSONArray arrCalendar = new JSONArray(calendarList);
		return arrCalendar.toString();
	}
	
	private ArrayList<CalendarDto> getCalendarUsr(String usrId) {
		ArrayList<CalendarDto> calendarList = new ArrayList<CalendarDto>();
//		CalendarDao calendarDao = new CalendarDao(); 
//		calendarList = calendarDao.getUserCalendar(usrId);
//		
		return calendarList;
	}

}
