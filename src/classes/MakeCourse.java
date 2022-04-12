package classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MakeCourse {
	private static String courseTextPath = "src/textfiles/courses.txt";
	private String studentTextPath = "src/textfiles/students.txt";
	
	// return: a course object or null if course could not be created
    // input: a string coursecode.
    public static Course makeCourse(String courseCode) throws FileNotFoundException {
    	String path = courseTextPath;
    	File file = new File(path);
    	Scanner courseReader = new Scanner(file);
    	
    	Course newCourse = findAndCreateCourse(courseCode, courseReader);
    	return newCourse;
    }
    
    // return: a course object corresponding to a coursecode given as input, null if course could not be created.
    // input: a string coursecode and a scanner pointing to courses.txt
    private static Course findAndCreateCourse(String courseCode, Scanner courseReader) {
    	while(courseReader.hasNextLine()) {
    		String currentCourse = courseReader.nextLine();
    		String splittedCurrentCourse[] = currentCourse.split("\\s");
    		if(courseCode.equals(splittedCurrentCourse[0])) {
    			return createSingleCourse(splittedCurrentCourse);
    		}
    	}
    	return null;
    }
//    CSE131 Introduction-to-Computer-Science 2022-08-29T11:30:00.000Z 2022-12-09T12:50:00.000Z TR Shook Yes Urbauer-222


    // return: a course object
    // input: an array of strings with information about the course.
    // 		array should be in the structure of: courseCode, courseName, courseStartTime, and courseEndTime. separaged with dashes.
    private static Course createSingleCourse (String[] splittedCurrentCourse) {
    	String courseCode = splittedCurrentCourse[0];
		String courseName = splittedCurrentCourse[1];
		String startTime = splittedCurrentCourse[2];
		String endTime = splittedCurrentCourse[3];
		int seats = Integer.valueOf(splittedCurrentCourse[4]);
		String professorName = splittedCurrentCourse[5];
		String exams = splittedCurrentCourse[6];
		Course course = new Course(courseName, courseCode, startTime, endTime, seats, 3, professorName, exams);
		return course;
    }
}
