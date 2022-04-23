package classes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
/**
 * AppFileReader handles reading files and returning Student or Course object.
 * @author manzura
 *
 */

public class AppFileProcessor {
	private File studentsFile, coursesFile;	
	
	public AppFileProcessor(String studentsFilePath, String coursesFilePath) throws FileNotFoundException {
		studentsFile = new File(studentsFilePath);
		
	}
	
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
    	    	if (strings[2].equals(id)) {
    	    		br.close();
    	    		return strings;
    	    	}
    	    }
		    
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
    	String firstName = strings[0];
		String lastName = strings[1];
    	Student student = new Student(firstName, lastName, Integer.valueOf(strings[2]));
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
    	StringBuilder sb = new StringBuilder();
    	sb.setLength(0);
    	for (int i = 0; i < courseList.length(); i++) {
    		if (courseList.charAt(i) == '-') {
    			if (register) student.addRegisterCourse(createCourse(sb.toString()));
    			else student.addWaitlistCourse(createCourse(sb.toString()));
    			sb.setLength(0);
    		}
    		else {
    			sb.append(courseList.charAt(i));
    		}
    	}
    	if (sb.toString().length() > 0) {
    		if (register) student.addRegisterCourse(createCourse(sb.toString()));
			else student.addWaitlistCourse(createCourse(sb.toString()));
    	}
    	
    }
    
    
// ------------------------------ END STUDENT METHODS --------------------------------------
    /**
     * Parses the course from file and returns it.
     * @param course code 
     * @return Course object, null if no course with that course code is found.
     */
    public Course createCourse(String courseCode) {
    	String[] courseInfo = parseCourse(courseCode);
    	if (courseInfo == null) return null;
    	return constructCourse(courseInfo);
    }
    
    /**
     * Helper method that returns an array of strings with the course info.
     * @param course code
     * @return course info, null if no course is found
     */
    private String[] parseCourse(String courseCode) {
    	try {
        	BufferedReader br = new BufferedReader(new FileReader(coursesFile));
    		String line;
    		int i = 0;
        	while ((line = br.readLine()) != null) {
    	    	String strings[] = line.split("\\s");
    	    	i++;
    	    	if (strings[0].equals(courseCode)) {
    	    		br.close();
    	    		return strings;
    	    	}
    	    	
    	    }
		    
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
		return null;
    }
    
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
    
    
    
    
    // ------------------------------------- MAIN ----------------------------------------- //
    
    public static void main(String[] args) {
    	try {
			AppFileProcessor fp = new AppFileProcessor("src/textfiles/test_students.txt", "src/textfiles/test_courses.txt");
			// Test course creation: DONE!
//			System.out.println(fp.createCourse("CSE247"));
//			System.out.println(fp.createCourse("CSE132"));
//			System.out.println(fp.createCourse("CSE330"));
			
			// Test student creation: DONE!
//			Student jack = fp.createStudent("123456");
//			Student dor = fp.createStudent("234567");
//			System.out.println(jack);
//			jack.displayRegisterCourses();
//			jack.displayWaitListCourses();
//			System.out.println(dor);
//			dor.displayRegisterCourses();
//			dor.displayWaitListCourses();

		} catch (FileNotFoundException e) {
			// Display appropriate user message
			e.printStackTrace();
		}
    }
    
    
    

}
