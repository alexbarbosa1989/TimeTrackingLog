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
		JSONObject rta = new JSONObject();
		StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferReader = request.getReader();
        String linea = null;
        Boolean calendarRta = null;
        JSONObject jObj;
        
        while ((linea = bufferReader.readLine()) != null) {
        	stringBuilder.append(linea);
        }
        
		try {
			jObj = new JSONObject(stringBuilder.toString());
			
			calendarRta = setOptionCalendar(jObj);
			
			if (calendarRta){
				rta.put("success", true);
			}else{
				rta.put("success", false);
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		out.print(rta.toString());
	}
	

	private Boolean setOptionCalendar(JSONObject obj) throws JSONException{
		CalendarDao calendarDao = new CalendarDao();
		String option = obj.getString("action");
		String usermail = obj.getString("usermail");
		String title = obj.getString("title");
		String description = obj.getString("description");
		String start = obj.getString("start");
		String end = obj.getString("end");
		Boolean isAllDay = obj.getBoolean("isAllDay");
		Boolean result = false;
		
		CalendarDto calendardto = new CalendarDto();
		calendardto.setUsuarioId(usermail);
		calendardto.setTitle(title);
		calendardto.setDescription(description);
		calendardto.setIsAllDay(isAllDay);
		calendardto.setStart(start);
		calendardto.setEnd(end);
		
		if (option.equalsIgnoreCase("create")){
			result = calendarDao.setActivity(calendardto);
		}else if(option.equalsIgnoreCase("update")){
			Integer taskId = Integer.parseInt(obj.getString("taskId"));
			calendardto.setTaskID(taskId);
			result = calendarDao.setUpdateActivity(calendardto);
		}else if(option.equalsIgnoreCase("destroy")){
			Integer taskId = Integer.parseInt(obj.getString("taskId"));
			calendardto.setTaskID(taskId);
			result = calendarDao.setDeleteActivity(calendardto); 
		}
		return result;
	}
	


}
