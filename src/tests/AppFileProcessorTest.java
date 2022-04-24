package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import classes.AppFileProcessor;
import classes.Course;
import classes.Student;

class AppFileProcessorTest {
	private String studentsFilePath = "src/textfiles/test_students.txt";
	private String coursesFilePath = "src/textfiles/test_courses.txt";
	private AppFileProcessor fp;

	@BeforeEach
	void setUp() throws FileNotFoundException {
		fp = new AppFileProcessor(studentsFilePath, coursesFilePath);
	}

	@Test
	void populateCoursesListTest() {
		Path path = Paths.get(coursesFilePath);
		long numLines = 0;
		try { numLines = Files.lines(path).parallel().count(); } 
		catch (IOException e) { e.printStackTrace(); }
		long numCourses = fp.getAllCourses().size();
		
		assertEquals(numLines, numCourses);
	}
	
	@Test
	void createStudentTest() {
		Student s = new Student("Jack", "Sparrow", 123456);
		Student actual = fp.createStudent("123456");
		
		assertEquals(s, actual);
	}
	
	@Test
	void createStudentRegisteredCoursesTest() {
		String[] registered = {"CSE247", "CSE204", "CSE332"};
		Student s = new Student("Jack", "Sparrow", 123456);
		for (String code : registered) s.addRegisterCourse(createCourse(code));
		Student actual = fp.createStudent("123456");
		
		assertEquals(s.getRegisteredCourses(), actual.getRegisteredCourses());
	}
	
	@Test
	void createStudentWaitlistedCoursesTest() {
		String[] waitlisted = {"CSE132"};
		Student s = new Student("Jack", "Sparrow", 123456);
		for (String code : waitlisted) s.addWaitlistCourse(createCourse(code));
		Student actual = fp.createStudent("123456");
		
		assertEquals(s.getWaitlistedCourses(), actual.getWaitlistedCourses());
	}
	
	
	
	private Course createCourse(String code) {
		try {
        	BufferedReader br = new BufferedReader(new FileReader(coursesFilePath));
    		String line;
        	while ((line = br.readLine()) != null) {
    	    	String strings[] = line.split("\\s");
    	    	if (strings[0].equals(code)) {
    	    		br.close();
    	    		return new Course(strings[1], strings[0], strings[2], strings[3], Integer.valueOf(strings[4]), 3, strings[5], strings[6]);
    	    	}
    	    }
        	br.close();
		    
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
		return null;
	}
	

}
