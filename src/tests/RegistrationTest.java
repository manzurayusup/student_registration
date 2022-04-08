package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.LinkedList;

import classes.Course;
import classes.Registration;
import classes.Student;

class RegistrationTest {
	Registration registration;
	
	@BeforeEach
	void setup() {
		registration = new Registration();
	}
	
	@Test
	void testRegister() throws FileNotFoundException {
		Student student = new Student("Jack", "Sparrow", 123456);
    	InputStream isLogIn = new ByteArrayInputStream("CSE131\n".getBytes());    	
		registration.register(student, isLogIn);
		String expected = "Name: Introduction-to-Computer-Science \n" + "Code: CSE131 \n" + "start time: 11:30:00 \n"
				+ "end time: 12:50:00 \n" + "seats left: 1 \n" + "Credits: 1";
		assertTrue(expected.equals(student.getRegisteredCourses().get(0).toString()));

	}
	@Test
	void testLogIn() throws FileNotFoundException {
		
    	InputStream is = new ByteArrayInputStream("123456".getBytes());    	
		Student student = registration.logIn(is);
		
		assertFalse(student == null);
		
	}

	@Test
	void testMakeCourse() throws FileNotFoundException {
		Course createdCourse = registration.makeCourse("CSE131");
		String expected = "Name: Introduction-to-Computer-Science \n" + "Code: CSE131 \n" + "start time: 11:30:00 \n"
				+ "end time: 12:50:00 \n" + "seats left: 1 \n" + "Credits: 1";
		assertTrue(expected.equals(createdCourse.toString()));
	}

	@Test
	void testAddRegisteredCoursesToStudentObject() throws FileNotFoundException {
		Student student = new Student("Jack", "Sparrow", 123456);
		registration.AddRegisteredCoursesToStudentObject("CSE131", student);
		String expected = "Name: Introduction-to-Computer-Science \n" + "Code: CSE131 \n" + "start time: 11:30:00 \n"
				+ "end time: 12:50:00 \n" + "seats left: 1 \n" + "Credits: 1";
		assertTrue(expected.equals(student.getRegisteredCourses().get(0).toString()));
	}

	@Test
	void testAddWaitlistedCoursesToStudentObject() throws FileNotFoundException {
		Student student = new Student("Jack", "Sparrow", 123456);
		registration.AddWaitlistedCoursesToStudentObject("CSE131", student);
		String expected = "Name: Introduction-to-Computer-Science \n" + "Code: CSE131 \n" + "start time: 11:30:00 \n"
				+ "end time: 12:50:00 \n" + "seats left: 1 \n" + "Credits: 1";
		assertTrue(expected.equals(student.getWaitlistedCourses().get(0).toString()));
	}

	@Test
	void testRegisterForSingleCourse() throws FileNotFoundException {
		Student currentStudent = new Student("Jack", "Sparrow", 123456);
		Course course = registration.makeCourse("CSE131");
		registration.registerForSingleCourse(course, currentStudent);
		String expected = "Name: Introduction-to-Computer-Science \n" + "Code: CSE131 \n" + "start time: 11:30:00 \n"
				+ "end time: 12:50:00 \n" + "seats left: 1 \n" + "Credits: 1";
		assertTrue(expected.equals(currentStudent.getRegisteredCourses().get(0).toString()));
	}

	@Test
	void testCreateSingleStudent() throws FileNotFoundException {
		String[] words = { "Jack", "Sparrow", "123456", "CSE247-CSE132", "CSE330" };
		Student student = registration.createSingleStudent(words, "123456");
		String expectedToString = "Jack Sparrow 123456";
		assertTrue(expectedToString.equals(student.toString()));
		Course rcourse = registration.makeCourse("CSE247");
		Course rcourse1 = registration.makeCourse("CSE132");
		LinkedList<Course> rcourses = new LinkedList<>();
		rcourses.add(rcourse);
		rcourses.add(rcourse1);
		for (int i = 0; i < student.getRegisteredCourses().size(); i++) {
			assertTrue(student.getRegisteredCourses().get(i).getName().equals(rcourses.get(i).getName()));
		}
		Course wcourse = registration.makeCourse("CSE330");
		assertTrue(student.getWaitlistedCourses().get(0).getName().equals(wcourse.getName()));
	}

}
