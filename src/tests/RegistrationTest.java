package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.LinkedList;
import java.util.List;

import classes.Course;
import classes.ErrorCodes;
import classes.Registration;
import classes.Student;

class RegistrationTest {
	Registration registration;
	
	@BeforeEach
	void setup() throws IOException {
		registration = new Registration();
		String studentTextPath = "src/textfiles/students.txt";
		String courseTextPath = "src/textfiles/courses.txt";
		BufferedWriter cbw = new BufferedWriter(new FileWriter(courseTextPath));
		cbw.write("CSE131 Introduction-to-Computer-Science 2022-08-29T11:30:00.000Z 2022-12-09T12:50:00.000Z 0 Shook Yes Urbauer-222 \n"
+ "CSE132 Introduction-to-Computer-Engineering 2022-08-29T13:00:00.000Z 2022-12-09T14:20:00.000Z 0 Chamberlain Yes Urbauer-218 \n"
+ "CSE204 Web-Development 2022-08-29T08:30:00.000Z 2022-12-09T09:50:00.000Z 100 Clapp No Urbauer-220 \n"
+ "CSE217 Introduction-to-Data-Science 2022-08-29T14:30:00.000Z 2022-12-09T15:50:00.000Z 100 Singh No Urbauer-224 \n"
+ "CSE240 Logic-and-Discrete-Mathematics 2022-08-29T10:00:00.000Z 2022-12-09T11:20:00.000Z 100 Garnett No Urbauer-226 \n"
+ "CSE247 Data-Structures-and-Algorithms 2022-08-29T13:00:00.000Z 2022-12-09T14:20:00.000Z 100 Cole No Eads-016 \n"
+ "CSE330 Rapid-Prototype-Development-and-Creative-Programming 2022-08-29T11:30:00.000Z 2022-12-09T12:50:00.000Z 60 Sproull No Urbauer-222 \n"
+ "CSE332 Object-Oriented-Software-Development-Laboratory 2022-08-29T10:00:00.000Z 2022-12-09T11:20:00.000Z 100 Shidal No Eads-016 \n"
+ "CSE347 Analysis-of-Algorithms 2022-08-29T11:30:00.000Z 2022-12-09T12:50:00.000Z 0 Buhler Yes Brown-118 \n"
+ "");
		cbw.close();
		BufferedWriter sbw = new BufferedWriter(new FileWriter(studentTextPath));
		sbw.write("Jack Sparrow 123456 CSE247-CSE204 CSE132 \n"
+ "Dorothy Gale 234567 CSE132-CSE131 CSE347 ");
		sbw.close();
	}
	
	@Test
	void testRegister() throws IOException {
		String courseCode = "CSE240";
		Student student = new Student("Jack", "Sparrow", 123456);
    	InputStream isLogIn = new ByteArrayInputStream((courseCode + "\n").getBytes());    			
		registration.register(student, isLogIn);
		Course course = student.getRegisteredCourses().getLast();
		System.out.println(course);
		assertTrue(courseCode.equals(student.getRegisteredCourses().getLast().getCourseCode()));

	}

	@Test
	void testLogIn() throws FileNotFoundException {
		
    	InputStream is = new ByteArrayInputStream("123456".getBytes());    	
		Student student = registration.logIn(is);
		
		assertFalse(student == null);
		
	}

	@Test
	void testMakeCourse() throws FileNotFoundException {
		String courseCode = "CSE131";
		Course createdCourse = registration.makeCourse(courseCode);
		System.out.println(createdCourse.getCourseCode());
		assertTrue(courseCode.equals(createdCourse.getCourseCode()));
	}

	@Test
	void testAddRegisteredCoursesToStudentObject() throws FileNotFoundException {
		String courseCode = "CSE217";
		Student student = new Student("Jack", "Sparrow", 123456);
		registration.AddRegisteredCoursesToStudentObject(courseCode, student);
		assertTrue(courseCode.equals(student.getRegisteredCourses().getLast().getCourseCode()));
	}

	@Test
	void testAddWaitlistedCoursesToStudentObject() throws FileNotFoundException {
		String courseCode = "CSE132";
		Student student = new Student("Jack", "Sparrow", 123456);
		registration.AddWaitlistedCoursesToStudentObject(courseCode, student);
		assertTrue(courseCode.equals(student.getWaitlistedCourses().getLast().getCourseCode()));
	}

	@Test
	void testRegisterForSingleCourse() throws IOException {
		String courseCode = "CSE332";
		Student currentStudent = new Student("Jack", "Sparrow", 123456);
		Course course = registration.makeCourse(courseCode);
		registration.registerForSingleCourse(course, currentStudent);
		assertTrue(courseCode.equals(currentStudent.getRegisteredCourses().getLast().getCourseCode()));
	}
	
	@Test
	void testRegisterWaitlist() throws IOException {
		Student currentStudent = new Student("Jack", "Sparrow", 123456);
		InputStream isLogIn = new ByteArrayInputStream("CSE132\n".getBytes());    			
		registration.register(currentStudent, isLogIn);
		String expected = "Name: Introduction-to-Computer-Engineering \n"
+ "Code: CSE132 \n"
+ "start time: 13:00:00 \n"
+ "end time: 14:20:00 \n"
+ "seats left: 0 \n"
+ "Credits: 3\n"
+ "Professor: Chamberlain\n"
+ "Exams: Yes";
		assertTrue(expected.equals(currentStudent.getWaitlistedCourses().get(0).toString()));
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
  
	@Test
	void testPrintCourse() throws FileNotFoundException {
		InputStream is = new ByteArrayInputStream("CSE131".getBytes());    	
		ErrorCodes isPrinted = registration.printCourse(is);
		
		assertEquals(ErrorCodes.SUCCESS, isPrinted);
	}
	
	@Test
	void testPrintCourseWrongCourse() throws FileNotFoundException {
		InputStream is = new ByteArrayInputStream("hello".getBytes());    	
		ErrorCodes isPrinted = registration.printCourse(is);
		
		assertEquals(ErrorCodes.ERROR, isPrinted);
	}
  
	@Test
	void testPrintAllCourses() throws FileNotFoundException {
		String returnedString = registration.printAllCourses();
		String expected = "Name: Introduction-to-Computer-Science \n"+ "Code: CSE131 \n"+ "start time: 11:30:00 \n"+ "end time: 12:50:00 \n"+ "seats left: 0 \n"+ "Credits: 3\n"+ "Professor: Shook\n"+ "Exams: Yes\n"+ "\n"+ "Name: Introduction-to-Computer-Engineering \n"+ "Code: CSE132 \n"+ "start time: 13:00:00 \n"+ "end time: 14:20:00 \n"+ "seats left: 0 \n"+ "Credits: 3\n"+ "Professor: Chamberlain\n"+ "Exams: Yes\n"+ "\n"+ "Name: Web-Development \n"+ "Code: CSE204 \n"+ "start time: 08:30:00 \n"+ "end time: 09:50:00 \n"+ "seats left: 100 \n"+ "Credits: 3\n"+ "Professor: Clapp\n"+ "Exams: No\n"+ "\n"+ "Name: Introduction-to-Data-Science \n"+ "Code: CSE217 \n"+ "start time: 14:30:00 \n"+ "end time: 15:50:00 \n"+ "seats left: 100 \n"+ "Credits: 3\n"+ "Professor: Singh\n"+ "Exams: No\n"+ "\n"+ "Name: Logic-and-Discrete-Mathematics \n"+ "Code: CSE240 \n"+ "start time: 10:00:00 \n"+ "end time: 11:20:00 \n"+ "seats left: 100 \n"+ "Credits: 3\n"+ "Professor: Garnett\n"+ "Exams: No\n"+ "\n"+ "Name: Data-Structures-and-Algorithms \n"+ "Code: CSE247 \n"+ "start time: 13:00:00 \n"+ "end time: 14:20:00 \n"+ "seats left: 100 \n"+ "Credits: 3\n"+ "Professor: Cole\n"+ "Exams: No\n"+ "\n"+ "Name: Rapid-Prototype-Development-and-Creative-Programming \n"+ "Code: CSE330 \n"+ "start time: 11:30:00 \n"+ "end time: 12:50:00 \n"+ "seats left: 60 \n"+ "Credits: 3\n"+ "Professor: Sproull\n"+ "Exams: No\n"+ "\n"+ "Name: Object-Oriented-Software-Development-Laboratory \n"+ "Code: CSE332 \n"+ "start time: 10:00:00 \n"+ "end time: 11:20:00 \n"+ "seats left: 100 \n"+ "Credits: 3\n"+ "Professor: Shidal\n"+ "Exams: No\n"+ "\n"+ "Name: Analysis-of-Algorithms \n"+ "Code: CSE347 \n"+ "start time: 11:30:00 \n"+ "end time: 12:50:00 \n"+ "seats left: 0 \n"+ "Credits: 3\n"+ "Professor: Buhler\n"+ "Exams: Yes\n"+ "\n";
    	assertTrue(returnedString.equals(expected));
    	
	}
	
	@Test
	void testSaveRegistrationCourseInfo() throws IOException {
		String courseCode = "CSE204";
		int seatsBefore = getSeatsOfCourse();
		Student student = new Student("Jack", "Sparrow", 123456);
    	InputStream isLogIn = new ByteArrayInputStream((courseCode + "\n").getBytes());    	
		registration.register(student, isLogIn);
		int seatsAfter = getSeatsOfCourse();
		assertTrue(seatsBefore-seatsAfter==1);
	}
	
	int getSeatsOfCourse() throws IOException {
		String courseTextPath = "src/textfiles/courses.txt";
		BufferedReader cbrBefore = new BufferedReader(new FileReader(courseTextPath));
		String courseLineBefore;
		LinkedList<String[]> courseListBefore = new LinkedList<>();
		while ((courseLineBefore = cbrBefore.readLine()) != null) {
			String words[] = courseLineBefore.split("\\s");
			courseListBefore.add(words);
		}
		return Integer.valueOf(courseListBefore.get(2)[4]);
	}
	
	@Test
	void testSaveRegistrationStudentInfo() throws IOException {
	    String studentTextPath = "src/textfiles/students.txt";
	    String courseCode = "CSE330";
		Student student = new Student("Jack", "Sparrow", 123456);
    	InputStream isLogIn = new ByteArrayInputStream((courseCode + "\n").getBytes());    	
		registration.register(student, isLogIn);
		BufferedReader cbr = new BufferedReader(new FileReader(studentTextPath));
		String studentLine;
		LinkedList<String[]> studentList = new LinkedList<>();
		while ((studentLine = cbr.readLine()) != null) {
			String words[] = studentLine.split("\\s");
			studentList.add(words);
		}
		cbr.close();
		String[] registeredCourses = studentList.get(0)[3].split("-");
		System.out.println(registeredCourses[registeredCourses.length-1]);
		assertTrue(registeredCourses[registeredCourses.length-1].equals(courseCode));
	}

}
