package com.timetracker.services;

import java.util.ArrayList;

import javax.websocket.server.PathParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONArray;

import com.timetracker.app.dao.CalendarDao;
import com.timetracker.app.dao.UsuarioDao;
import com.timetracker.app.dto.CalendarDto;
import com.timetracker.app.dto.PersonaDto;
import com.timetracker.app.dto.PersonasTareaDto;
import com.timetracker.app.dto.ReporteDto;


@Path("/calendarReadServ")
public class CalendarReadServ {
	CalendarDao calendarDao = new CalendarDao();
	UsuarioDao usuarioDao = new UsuarioDao();

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
   
   @GET
   @Path("setModify")
   @Produces(MediaType.APPLICATION_JSON)
   public String setModify(){
       return "callback()";
   }
   
   @GET
   @Path("getCalEmployees")
   @Produces(MediaType.APPLICATION_JSON)
   public ArrayList<PersonasTareaDto> getCalendarEmployees(){
	   ArrayList<PersonasTareaDto> listaRTA = new ArrayList<PersonasTareaDto>();
	   PersonasTareaDto personaTarea = new PersonasTareaDto();
	   ArrayList<PersonaDto> usuarioList = new ArrayList<PersonaDto>();
	   ArrayList<ReporteDto> calendarList = new ArrayList<ReporteDto>();
	   usuarioList = usuarioDao.geAlltUsr();
	   
		   
	   for (int i = 0; i < usuarioList.size(); i++) {
		   calendarList = calendarDao.getReporteCalendar(usuarioList.get(i).getId_persona());
		   usuarioList.get(i).setTareas(calendarList);
	   }
	   personaTarea.setPersonas_tarea(usuarioList);
	   listaRTA.add(personaTarea);
	   return listaRTA;

   }
   
   
   @POST
   @Path("getCalendarByUsr/{param}")
   @Produces(MediaType.APPLICATION_JSON)
   public String getCalendarByUsr(@PathParam("param") String param){
	   
	   ArrayList<CalendarDto> calendarList = new ArrayList<CalendarDto>();
	   calendarList = calendarDao.getUserCalendar(param);
	   JSONArray arrCalendar = new JSONArray(calendarList);
	   String arrResp = arrCalendar.toString().replace("\\\\", "\\");
	   String arrResponse = arrResp.replace("\"null\"", "null");
       return arrResponse;
   }
   
}
