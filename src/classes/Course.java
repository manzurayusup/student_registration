package classes;
/**
 * 
 * @author yab
 *
 */
public class Course{
	private String name;
	private String courseCode;
	private String startTime;
	private String endTime;
	private int seats;
	private int credits;
	private String professorName;
	private String exams;
	
	public Course () {
		this.name = "unknown";
		this.courseCode = "N/A";
		this.seats = -1;
		this.credits = -1;
		this.professorName = "unknown";
		this.exams = "Yes";
	}
	
	public Course (String name, String courseCode, String startTime, String endTime, int seats, int credits, String professorName, String exams) {
		this.name = name;
		this.courseCode = courseCode;
		this.startTime = startTime;
		this.endTime = endTime;
		this.seats = seats;
		this.credits = credits;
		this.professorName = professorName;
		this.exams = exams;
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
	
	public String getStartTime() {
		return this.startTime;
	}
	
	public String getEndTime() {
		return this.endTime;
	}
	
	public int getSeats (){
		return this.seats;
	}
	
	/**
	 * @return a string containing all information about this course.
	 */
	public String toString(){
		return "{ Name: " + this.getName() + " \nCode: " + this.getCourseCode() + " \nstart time: " + this.getStartTime() + " \nend time: " + this.getEndTime() + " \nseats left: " + this.getSeats() + " \nCredits: " + this.getCredits() + "\nProfessor: " + this.getProfessorName() + "\nExams: " + this.getExams() + " }\n";
	}
	
	@Override
	public boolean equals(Object o) {
	    // self check
	    if (this == o) return true;
	    // null check
	    if (o == null) return false;
	    Course course = (Course) o;
	    // field comparison
	    return this.courseCode.equals(course.courseCode);
	}
	
}