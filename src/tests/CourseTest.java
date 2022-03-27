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
		course2 = new Course("programming tools and techniques", "CSE237", "2022-08-29T13:00:00.000Z", "2022-08-29T14:20:00.000Z", 45, 3);
	}

	// test toString overloadded method method.
	@Test
	void testToString () {
//		String expected = "Name: programming tools and techniques \nCode: CSE237 \nstart time: 13:00:00 \nendtime: 14:20:00 \nseats left: 45 \nCredits: 3";
		String expected = "Name: programming tools and techniques \n"
				+ "Code: CSE237 \n"
				+ "start time: 13:00:00 \n"
				+ "end time: 14:20:00 \n"
				+ "seats left: 45 \n"
				+ "Credits: 3";
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
	
	// test setStartTime and getStartTime
	@Test
	void testStartTime(){
		course.setStartTime("2022-08-29T13:00:00.000Z");
		String returnedTime = course.getStartTime();
		assertTrue("13:00:00".equals(returnedTime));
	}
	
	// test setEndTime and getEndTime
	@Test
	void testEndTime(){
		course.setEndTime("2022-08-29T13:00:00.000Z");
		String returnedTime = course.getEndTime();
		assertTrue("13:00:00".equals(returnedTime));
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
