package com.timetracker.app.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import com.timetracker.app.dto.CalendarDto;
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
	
}
