package com.timetracker.app.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import com.timetracker.app.dto.CalendarDto;
import com.timetracker.app.dto.ReporteDto;
import com.timetracker.database.conn.ConexionBD;

/**
 * 
 * @author alexanderbarbosaayala
 *
 */
public class CalendarDao {

	public ArrayList<CalendarDto> getUserCalendar(String idUsuario) {
		ArrayList<CalendarDto> calendarList = new ArrayList<CalendarDto>();
        
        ConexionBD conex= new ConexionBD();
     
        try {
            String consulta = "SELECT * FROM calendar where usuario_id = '"+idUsuario+"';";
            Statement st = conex.getConnection().createStatement();
            ResultSet rs = st.executeQuery(consulta);
            
            while(rs.next()){
            	CalendarDto calendarSet = new CalendarDto();
            	calendarSet.setTaskID(rs.getInt("TaskID"));
            	calendarSet.setTitle(rs.getString("Title"));
            	calendarSet.setDescription(rs.getString("Description"));
            	if (rs.getString("StartTimezone")==null){
            		calendarSet.setStartTimezone("null");
            	}else{
            		calendarSet.setStartTimezone(rs.getString("StartTimezone"));
            	}
            	calendarSet.setStart("\\/Date("+rs.getString("Start")+")\\/");
            	calendarSet.setEnd("\\/Date("+rs.getString("End")+")\\/");
            	if (rs.getString("EndTimezone")==null){
            		calendarSet.setEndTimeZone("null");
            	}else{
            		calendarSet.setEndTimeZone(rs.getString("EndTimezone"));
            	}
            	if(rs.getString("RecurrenceRule")==null){
            		calendarSet.setRecurrenceRule("null");
            	}else{
            		calendarSet.setRecurrenceRule(rs.getString("RecurrenceRule"));
            	}
            	if (rs.getString("RecurrenceID")==null){
            		calendarSet.setRecurrenceId("null");
            	}else{
            		calendarSet.setRecurrenceId(rs.getString("RecurrenceID"));
            	}
            	if (rs.getString("RecurrenceException")==null){
            		calendarSet.setRecurrenceException("null");
            	}else{
            		calendarSet.setRecurrenceException(rs.getString("RecurrenceException"));
            	}
            	calendarSet.setIsAllDay(Boolean.parseBoolean(rs.getString("IsAllDay")));
            	
            	calendarList.add(calendarSet);
            }
        }catch(NumberFormatException e){
            System.out.println("Error de numero "+e.getMessage());
        }catch(SQLException e){
            System.out.println("Error en la conexion "+e.getMessage());
        }
        
        return calendarList;
    }

	
	
	public ArrayList<ReporteDto> getReporteCalendar(String idUsuario) {
		ArrayList<ReporteDto> calendarList = new ArrayList<ReporteDto>();
		String descripcionAct = new String();
        
        ConexionBD conex= new ConexionBD();
     
        try {
            String consulta = "select c.TaskID,c.Title,c.Description,c.start,c.end from calendar c "
            		+ " where c.usuario_id = '"+idUsuario+"';";
            Statement st = conex.getConnection().createStatement();
            ResultSet rs = st.executeQuery(consulta);
            
            while(rs.next()){
            	ReporteDto calendarSet = new ReporteDto();
            	calendarSet.setId_tarea(rs.getInt("TaskID"));
            	calendarSet.setNombre_tarea(rs.getString("Title"));
            	calendarSet.setFecha_inicio(rs.getString("Start"));
            	calendarSet.setFecha_fin(rs.getString("End"));
            	
            	descripcionAct = rs.getString("Description");
            	
            	if(descripcionAct.toLowerCase().contains("no urgente")){
            		calendarSet.setUrgente("n");
            	} else if(descripcionAct.toLowerCase().contains("urgente")){
            		calendarSet.setUrgente("y");
            	}else{
            		calendarSet.setUrgente("null");
            	}
            	
            	if(descripcionAct.toLowerCase().contains("no importante")){
            		calendarSet.setImportante("n");
            	} else if(descripcionAct.toLowerCase().contains("importante")){
            		calendarSet.setImportante("y");
            	} else{
            		calendarSet.setImportante("null");
            	}
            	
            	calendarList.add(calendarSet);
            }
        }catch(NumberFormatException e){
            System.out.println("Error de numero "+e.getMessage());
        }catch(SQLException e){
            System.out.println("Error en la conexion "+e.getMessage());
        }
        
        return calendarList;
    }
	
}
