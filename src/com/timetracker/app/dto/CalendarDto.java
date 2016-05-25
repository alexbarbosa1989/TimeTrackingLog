package com.timetracker.app.dto;

/**
 * 
 * @author alexanderbarbosaayala
 *
 */
public class CalendarDto {
	private String usuarioId;
	private int taskId;
	private String title;
	private String description;
	private String startTimeZone;
	private String start;
	private String end;
	private String endTimeZone;
	private String recurrenceRule;
	private String recurrenceId;
	private String recurrenceException;
	private Boolean isAllDay;
	
	public String getUsuarioId() {
		return usuarioId;
	}
	public void setUsuarioId(String usuarioId) {
		this.usuarioId = usuarioId;
	}
	public int getTaskId() {
		return taskId;
	}
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStartTimeZone() {
		return startTimeZone;
	}
	public void setStartTimeZone(String startTimeZone) {
		this.startTimeZone = startTimeZone;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public String getEndTimeZone() {
		return endTimeZone;
	}
	public void setEndTimeZone(String endTimeZone) {
		this.endTimeZone = endTimeZone;
	}
	public String getRecurrenceRule() {
		return recurrenceRule;
	}
	public void setRecurrenceRule(String recurrenceRule) {
		this.recurrenceRule = recurrenceRule;
	}
	public String getRecurrenceId() {
		return recurrenceId;
	}
	public void setRecurrenceId(String recurrenceId) {
		this.recurrenceId = recurrenceId;
	}
	public String getRecurrenceException() {
		return recurrenceException;
	}
	public void setRecurrenceException(String recurrenceException) {
		this.recurrenceException = recurrenceException;
	}
	public Boolean getIsAllDay() {
		return isAllDay;
	}
	public void setIsAllDay(Boolean isAllDay) {
		this.isAllDay = isAllDay;
	}
	
}
