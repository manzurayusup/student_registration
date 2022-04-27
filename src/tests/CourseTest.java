package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import classes.Course;

class CourseTest {
	
	private Course course;
	private Course course2;
	
	@BeforeEach
	void setup() {
		course = new Course();
		course2 = new Course("Introduction-to-Computer-Science", "CSE131", "11:30:00", "12:50:00", 20, 3, "Shook", "Yes");
	}

	// test toString overloadded method method.
	@Test
	void testToString () {
		String expected = "{ Name: Introduction-to-Computer-Science \n"
				+ "Code: CSE131 \n"
				+ "start time: 11:30:00 \n"
				+ "end time: 12:50:00 \n"
				+ "seats left: 20 \n"
				+ "Credits: 3\n"
				+ "Professor: Shook\n"
				+ "Exams: Yes }\n";
		System.out.println(course2.toString());
		assertTrue(expected.equals(course2.toString()));
	}
	
	// test setName and getName
	@Test
	void testName(){
		course.setName("Data Structures");
		String returnedName = course.getName();
		assertTrue("Data Structures".equals(returnedName));
	}
	
	// test setCourseCode and getCourseCode
	@Test
	void testCourseCode(){
		course.setCourseCode("CSE247");
		String returnedCode = course.getCourseCode();
		assertTrue("CSE247".equals(returnedCode));
	}
	
	// test setSeats and getSeats
	@Test
	void testSeats(){
		course.setSeats(56);
		int returnedSeats = course.getSeats();
		assertEquals(56, returnedSeats);
	}
	
	// test setCredits and getCredits
	@Test
	void testCredits(){
		course.setCredits(4);
		int returnedCredits = course.getCredits();
		assertEquals(4, returnedCredits);
	}
}
