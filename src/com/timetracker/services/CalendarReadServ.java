package com.timetracker.services;

import java.util.ArrayList;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONArray;

import com.timetracker.app.dao.CalendarDao;
import com.timetracker.app.dto.CalendarDto;

@Path("/calendarReadServ")
public class CalendarReadServ {
	CalendarDao calendarDao = new CalendarDao();

   @GET
   @Path("getCalendar")
   @Produces(MediaType.APPLICATION_JSON)
   public String getCalendar(){
	   
	   ArrayList<CalendarDto> calendarList = new ArrayList<CalendarDto>();
	   calendarList = calendarDao.getUserCalendar("correo");
	   JSONArray arrCalendar = new JSONArray(calendarList);
	   String arrResp = arrCalendar.toString().replace("\\\\", "\\");
	   String arrResponse = arrResp.replace("\"null\"", "null");
       return arrResponse;
   }
}
