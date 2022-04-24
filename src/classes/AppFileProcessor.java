package classes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
/**
 * AppFileReader handles reading files and returning Student or Course object.
 * @author manzura
 *
 */

public class AppFileProcessor {
	private File studentsFile, coursesFile;	
	private LinkedList<Course> coursesList;
	
	public AppFileProcessor(String studentsFilePath, String coursesFilePath) {
		studentsFile = new File(studentsFilePath);
		coursesFile = new File(coursesFilePath);
		coursesList = new LinkedList<Course>();
		populateCoursesList();
	}
	
	/**
	 * Reads the courses file, constructs each course and adds it to the courses list.
	 */
	private void populateCoursesList() {
    	try {
        	BufferedReader br = new BufferedReader(new FileReader(coursesFile));
    		String line;
        	while ((line = br.readLine()) != null) {
    	    	coursesList.add(constructCourse(line.split("\\s")));
    	    }
        	br.close();
		    
		} catch (Exception e) {
			e.printStackTrace();
		} 
    }
	
	/**
     * Parses the course from file and returns it.
     * @param course code 
     * @return Course object, null if no course with that course code is found.
     */
//    public Course createCourse(String courseCode) {
//    	String[] courseInfo = parseCourse(courseCode);
//    	if (courseInfo == null) return null;
//    	return constructCourse(courseInfo);
//    }
    
    /**
     * Helper method that returns an array of strings with the course info.
     * @param course code
     * @return course info, null if no course is found
     */
//    private String[] parseCourse(String courseCode) {
//    	try {
//        	BufferedReader br = new BufferedReader(new FileReader(coursesFile));
//    		String line;
//        	while ((line = br.readLine()) != null) {
//    	    	String strings[] = line.split("\\s");
//    	    	if (strings[0].equals(courseCode)) {
//    	    		br.close();
//    	    		return strings;
//    	    	}
//    	    }
//        	br.close();
//		    
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		} 
//		return null;
//    }
    
    /**
     * Helper method that constructs a course from the array of strings.
     * @param array of course info
     * @return course object
     */
    private Course constructCourse(String[] strings) {
    	String courseCode = strings[0];
		String courseName = strings[1];
		String startTime = strings[2];
		String endTime = strings[3];
		int seats = Integer.valueOf(strings[4]);
		String professorName = strings[5];
		String exams = strings[6];
		return new Course(courseName, courseCode, startTime, endTime, seats, 3, professorName, exams);
    }
    
    public LinkedList<Course> getAllCourses() {
    	return coursesList;
    }
	
 // --------------------------------- END COURSE METHODS --------------------------------------	
	
    
    /**
     * Parses the student from file into a Student object and returns it.
     * @param id of the student	
     * @return Student object, null if no student with that ID is found
     */
 	public Student createStudent(String id) {
 		String[] studentInfo = parseStudent(id);
 		if (studentInfo == null) return null;
 		return constructStudent(studentInfo);
 	}
    
 	/**
 	 * Helper method that returns an array of strings with the student info.
 	 * @param id of the student to be parsed
 	 * @return array of strings with the student info, null if no student is found.
 	 */
    private String[] parseStudent(String id) {
    	try {
        	BufferedReader br = new BufferedReader(new FileReader(studentsFile));
    		String line;
        	while ((line = br.readLine()) != null) {
    	    	String strings[] = line.split("\\s");
    	    	if (strings[0].equals(id)) {
    	    		br.close();
    	    		return strings;
    	    	}
    	    }
        	br.close();
		    
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
		return null;
    }
    
    /**
     * Helper method that constructs a student from the array of strings.
     * @param strings with student info
     * @return Student object
     */
    private Student constructStudent(String strings[]) {
    	int id = Integer.valueOf(strings[0]);
    	String firstName = strings[1];
		String lastName = strings[2];
    	Student student = new Student(firstName, lastName, id);
    	String registeredCourses = strings[3];
    	addCourses(registeredCourses, student, true); 
    	String waitlistedCourses = strings[4];
    	addCourses(waitlistedCourses, student, false); 
    	return student;
    }
    
    /**
     * Helper method that adds courses to the student being constructed.
     * Register flag indicates whether to add to the registered courses or waitlisted courses.
     * @param courseList
     */
    private void addCourses(String courseList, Student student, boolean register) {
    	String[] courseCodes = courseList.split("-");
    	for (int i = 0; i < courseCodes.length; i++) {
    		Course course = findCourse(courseCodes[i]);
    		if (register) student.addRegisterCourse(course);
			else student.addWaitlistCourse(course);
    	}
    	
    }
    
    public Course findCourse(String courseCode) {
    	for (int i = 0; i < coursesList.size(); i++) {
    		Course c = coursesList.get(i);
    		if (courseCode.equals(c.getCourseCode())) return c;
    	}
    	return null;
    }
    
    
// ------------------------------ END STUDENT METHODS --------------------------------------
    
        
    
// ------------------------------------- MAIN --------------------------------------------- //
    
    public static void main(String[] args) {
    	
		AppFileProcessor fp = new AppFileProcessor("src/textfiles/test_students.txt", "src/textfiles/test_courses.txt");
		Student a = fp.createStudent("123456");
		Student b = fp.createStudent("234567");
		a.displayRegisterCourses();
		a.displayWaitListCourses();
		System.out.println("-------------------------");
		b.displayRegisterCourses();
		b.displayWaitListCourses();
			
			
// ------------------------ SPRINT BUILDER VS ARRAY.SPLIT TEST !!! -----------------------------
//			LinkedList<String> list = new LinkedList<String>();
//			
//			String courseList = "CSE131-CSE247-CSE330";
//			StringBuilder sb = new StringBuilder();
//	    	sb.setLength(0);
//	    	for (int i = 0; i < courseList.length(); i++) {
//	    		if (courseList.charAt(i) == '-') {
//	    			list.add(sb.toString());
//	    			sb.setLength(0);
//	    		}
//	    		else {
//	    			sb.append(courseList.charAt(i));
//	    		}
//	    	}
//	    	if (sb.toString().length() > 0) {
//	    		list.add(sb.toString());
//	    	}
//	    	System.out.println(list);
//	    	System.out.println("-----------------");
//	    	String[] arr = courseList.split("-");
//	    	for (int i = 0; i < arr.length; i++) {
//	    		System.out.println(arr[i]);
//	    	}
// ------------------------ END IMPORTANT TEST -----------------------------
		
    }
    
    
    

}
