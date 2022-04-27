package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import classes.AppFileProcessor;
import classes.AppFileWriter;
import classes.Course;
import classes.Student;

/**
 * Tests the important public methods of AppFileWriter class.
 * @author manzura
 *
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AppFileWriterTest {
	private String studentsFilePath = "src/textfiles/test_students.txt";
	private String coursesFilePath = "src/textfiles/test_courses.txt";
	private Course course1 = new Course("Web-Development", "CSE204", "08:30:00", "09:50:00", 100, 3, "Clapp", "No");
	private Course course2 = new Course("Analysis-of-Algorithms", "CSE347", "11:30:00", "12:50:00", 0, 3, "Buhler", "Yes");
	private String studentFileContentsOriginal = "", courseFileContentsOriginal = "";
	private AppFileProcessor fp;
	private AppFileWriter fw;
	
	@BeforeAll
	void beforeAll() throws IOException {
		BufferedReader brStudentFile = new BufferedReader(new FileReader(studentsFilePath));
		BufferedReader brCourseFile = new BufferedReader(new FileReader(coursesFilePath));
		String line;
		while ((line = brStudentFile.readLine()) != null) {
			studentFileContentsOriginal += line + "\n";
		}
		brStudentFile.close();
		while ((line = brCourseFile.readLine()) != null) {
			courseFileContentsOriginal += line + "\n";
		}
		brCourseFile.close();
	}
	
	@AfterAll
	void afterAll() throws IOException {
		BufferedWriter bwStudentFile = new BufferedWriter(new FileWriter(studentsFilePath, false));
		BufferedWriter bwCourseFile = new BufferedWriter(new FileWriter(coursesFilePath, false));
		bwStudentFile.write(studentFileContentsOriginal);
		bwStudentFile.close();
		bwCourseFile.write(courseFileContentsOriginal);
		bwCourseFile.close();
	}
	
	@BeforeEach
	void setUp() throws Exception {
		fp = new AppFileProcessor(studentsFilePath, coursesFilePath);
		fw = new AppFileWriter(studentsFilePath, coursesFilePath);
	}

	@Test
	void saveStudentDataTest() {
		Student studentBefore = new Student("Jack", "Sparrow", 123456);
		studentBefore.addRegisterCourse(course1);
		studentBefore.addWaitlistCourse(course2);
		
		fw.saveStudentData(studentBefore);
		Student studentAfter = fp.createStudent("123456");
		
		assertEquals(studentAfter.getRegisteredCourses(), studentBefore.getRegisteredCourses());
		assertEquals(studentAfter.getWaitlistedCourses(), studentBefore.getWaitlistedCourses());
	}
	
	@Test
	void saveCourseDataTest() {
		course1.setSeats(500);
		fw.saveCourseData(course1);
		Course c = fp.findCourse(course1.getCourseCode());
		assertEquals(course1, c);
	}
	

}
