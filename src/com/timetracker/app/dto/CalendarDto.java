package com.timetracker.app.dto;

/**
 * 
 * @author alexanderbarbosaayala
 *
 */
public class CalendarDto {
	private String usuarioId;
	private int TaskID;
	private String Title;
	private String Description;
	private String StartTimezone;
	private String Start;
	private String End;
	private String EndTimeZone;
	private String RecurrenceRule;
	private String RecurrenceId;
	private String RecurrenceException;
	private Boolean IsAllDay;
	
	
	
	
	public String getUsuarioId() {
		return usuarioId;
	}
	public void setUsuarioId(String usuarioId) {
		this.usuarioId = usuarioId;
	}
	public int getTaskID() {
		return TaskID;
	}
	public void setTaskID(int taskID) {
		TaskID = taskID;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getStartTimezone() {
		return StartTimezone;
	}
	public void setStartTimezone(String startTimezone) {
		StartTimezone = startTimezone;
	}
	public String getStart() {
		return Start;
	}
	public void setStart(String start) {
		Start = start;
	}
	public String getEnd() {
		return End;
	}
	public void setEnd(String end) {
		End = end;
	}
	public String getEndTimeZone() {
		return EndTimeZone;
	}
	public void setEndTimeZone(String endTimeZone) {
		EndTimeZone = endTimeZone;
	}
	public String getRecurrenceRule() {
		return RecurrenceRule;
	}
	public void setRecurrenceRule(String recurrenceRule) {
		RecurrenceRule = recurrenceRule;
	}
	public String getRecurrenceId() {
		return RecurrenceId;
	}
	public void setRecurrenceId(String recurrenceId) {
		RecurrenceId = recurrenceId;
	}
	public String getRecurrenceException() {
		return RecurrenceException;
	}
	public void setRecurrenceException(String recurrenceException) {
		RecurrenceException = recurrenceException;
	}
	public Boolean getIsAllDay() {
		return IsAllDay;
	}
	public void setIsAllDay(Boolean isAllDay) {
		IsAllDay = isAllDay;
	}
	
	
}
