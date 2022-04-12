package classes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.*;
import java.util.*;
import java.text.*;

public class Course{
	private String name;
	private String courseCode;
	private Date startTime;
	private Date endTime;
	private int seats;
	private int credits;
	private String professorName;
	private String exams;
	
	public Course () {
		this.name = "unknown";
		this.courseCode = "N/A";
		this.startTime = initializeDate();
		this.endTime = initializeDate();
		this.seats = -1;
		this.credits = -1;
		this.professorName = "unknown";
		this.exams = "Yes";
	}
	
	public Course (String name, String courseCode, String startTime, String endTime, int seats, int credits, String professorName, String exams) {
		this.name = name;
		this.courseCode = courseCode;
		setStartTime(startTime);
		setEndTime(endTime);
		this.seats = seats;
		this.credits = credits;
		this.professorName = professorName;
		this.exams = exams;
	}
	
	/**
	 * 
	 * @return a default date object initialized to a dummy date.
	 */
	public Date initializeDate() {
		Date defaultDate = new Date();
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		try {
			defaultDate = dateFormatter.parse("1970-01-01T01:01:01.000Z");
		} catch (ParseException e) {
			System.out.println("could not initialize dates: " + e);
		}
		return defaultDate;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setProfessorName(String professorName) {
		this.professorName = professorName;
	}
	public void setExams(String exams) {
		this.exams = exams;
	}
	public String getExams() {
		return this.exams;
	}
	public String getProfessorName() {
		return this.professorName;
	}
	
	
	public void setCourseCode(String code) {
		this.courseCode = code;
	}
	
	public void setStartTime(String time) {
		Date startTime = parseStringToDate(time);
		this.startTime = startTime;
	}
	
	public void setEndTime(String time) {
		Date endTime = parseStringToDate(time);
		this.endTime = endTime;
	}
	
	public void setSeats(int seats) {
		this.seats = seats;
	}
	
	public void setCredits(int credits) {
		this.credits = credits;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getCredits() {
		return this.credits;
	}
	
	public String getCourseCode() {
		return this.courseCode;
	}
	
	public Date getStartTimeAsDate() {
		return this.startTime;
	}
	
	public Date getEndDateAsDate() {
		return this.endTime;
	}
	
	public String getStartTime() {
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
		String time = timeFormat.format(this.startTime);
		return time;
	}
	
	public String getEndTime() {
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
		String time = timeFormat.format(this.endTime);
		return time;
	}
	
	public int getSeats (){
		return this.seats;
	}
	
	/**
	 * 
	 * @param time: a string time to be converted to date object
	 * @return: a date object from time.
	 */
	public Date parseStringToDate(String time) {
		Date courseDate = new Date();
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		try {
			courseDate = dateFormatter.parse(time);
		} catch (ParseException e) {
			System.out.println("could not parse given string to date: " + e);
		}
		return courseDate;
	}
	
	/**
	 * @return a string containing all information about this course.
	 */
	public String toString(){
		return "Name: " + this.getName() + " \nCode: " + this.getCourseCode() + " \nstart time: " + this.getStartTime() + " \nend time: " + this.getEndTime() + " \nseats left: " + this.getSeats() + " \nCredits: " + this.getCredits() + "\nProfessor: " + this.getProfessorName() + "\nExams: " + this.getExams();
	}
	
	@Override
	public boolean equals(Object o) {
	    // self check
	    if (this == o) return true;
	    // null check
	    if (o == null) return false;
	    Course course = (Course) o;
	    // field comparison
	    return Objects.equals(this.courseCode, course.courseCode);
	}
	
}