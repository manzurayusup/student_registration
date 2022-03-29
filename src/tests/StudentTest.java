package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import classes.Course;
import classes.ErrorCodes;
import classes.Student;

class StudentTest {
    private Student student;
    private Course waitlistCourse1;
    private Course waitlistCourse2;
    private Course registerCourse1;
    private Course registerCourse2;
    private Course course3;
    private Course course4;
    private Course course5;
    private Course course6;
    private Course course7;
    
    
    @BeforeEach
    void setup() {
        student = new Student("Irtaza", "Waseem", 472936);
        waitlistCourse1 = new Course("programming tools and techniques", "CSE237", "2022-08-29T13:00:00.000Z", "2022-08-29T14:20:00.000Z", 45, 3);
        waitlistCourse2 = new Course("Introduction to Computer Engineering", "CSE132", "2022-08-29T13:00:00.000Z", "2022-12-09T14:20:00.000Z", 60, 3);
        registerCourse1 = new Course("Introduction to Data Science", "CSE217A", "2022-08-29T14:30:00.000Z", "2022-12-09T15:50:00.000Z", 30, 3);
        registerCourse2 = new Course("Data Structures and Algorithms", "CSE247", "2022-08-29T13:00:00.000Z", "2022-12-09T14:20:00.000Z", 70, 3);
        student.addWaitlistCourse(waitlistCourse1);
        student.addWaitlistCourse(waitlistCourse2);
        student.addRegisterCourse(registerCourse1);
        student.addRegisterCourse(registerCourse2);
        
        course3 = new Course("abc", "CSE236", "2022-08-29T13:00:00.000Z", "2022-08-29T14:20:00.000Z", 45, 3);
        course4 = new Course("def", "CSE235", "2022-08-29T13:00:00.000Z", "2022-08-29T14:20:00.000Z", 45, 3);
        course5 = new Course("ghi", "CSE234", "2022-08-29T13:00:00.000Z", "2022-08-29T14:20:00.000Z", 45, 3);
        course6 = new Course("jkl", "CSE233", "2022-08-29T13:00:00.000Z", "2022-08-29T14:20:00.000Z", 45, 3);
        course7 = new Course("mno", "CSE232", "2022-08-29T13:00:00.000Z", "2022-08-29T14:20:00.000Z", 45, 3);
    }
    @Test
	void testGetFirstName() {
		String expected = "Irtaza";
		assertEquals(true, expected.equals(student.getFirstName()));
	}
    @Test
	void testGetLastName() {
		String expected = "Waseem";
		assertEquals(true, expected.equals(student.getLastName()));
	}
    @Test
	void testGetID() {
    	System.out.println("Hello0");

		int expected = 472936;
		assertEquals(true, expected == student.getID());
	}
    @Test
	void testToString() {
		String expected = "Irtaza Waseem 472936";
		assertEquals(true, expected.equals(student.toString()));
	}
    
    
    
    @Test
    void testGetWaitlistedCourses() {
    	LinkedList<Course> expectedList = new LinkedList<>();
    	expectedList.add(waitlistCourse1);
    	expectedList.add(waitlistCourse2);
    	assertEquals(true, expectedList.equals(student.getWaitlistedCourses()));
    }
    
    @Test
    void testAddWaitlistCourse() {
    	System.out.println("hello 100");
    	ErrorCodes returnValue = student.addWaitlistCourse(course3);
    	assertEquals(ErrorCodes.SUCCESS, returnValue);	
    }
    
    @Test
    void testAddDuplicateWaitlistCourse() {
    	ErrorCodes returnValue = student.addWaitlistCourse(waitlistCourse1);
    	assertEquals(ErrorCodes.ERROR, returnValue);	
    }
    
    @Test
    void testAddWaitlistCourseThatAlreadyExistsInRegisterCourses() {
    	ErrorCodes returnValue = student.addWaitlistCourse(registerCourse1);
    	assertEquals(ErrorCodes.ERROR, returnValue);
    }
    @Test
    void testCreditsExceedWhenAddingWaitlistCourse() {
    	student.addWaitlistCourse(course3);
    	student.addWaitlistCourse(course4);
    	student.addWaitlistCourse(course5);
    	ErrorCodes returnValue = student.addWaitlistCourse(course6);
    	assertEquals(ErrorCodes.ERROR, returnValue);
    }
    @Test
    void testRemoveWaitlistCourse() {
    	LinkedList<Course> expectedList = new LinkedList<>();
    	expectedList.add(waitlistCourse1);
    	student.removeWaitlistCourse(waitlistCourse2);
    	assertEquals(true, expectedList.equals(student.getWaitlistedCourses()));
    }
    @Test
    void testRemoveWaitlistCourseThatDoesNotExistInStudentWaitlistCourses() {
    	ErrorCodes returnValue = student.removeWaitlistCourse(course3);
    	assertEquals(ErrorCodes.ERROR, returnValue);
    }
    
    
    @Test
    void testGetRegisteredCourses() {
    	LinkedList<Course> expectedList = new LinkedList<>();
    	expectedList.add(registerCourse1);
    	expectedList.add(registerCourse2);
    	assertEquals(true, expectedList.equals(student.getRegisteredCourses()));
    }
    @Test
    void testAddRegisterCourse() {
    	LinkedList<Course> expectedList = new LinkedList<>();
    	expectedList.add(registerCourse1);
    	expectedList.add(registerCourse2);
    	expectedList.add(course3);
    	student.addRegisterCourse(course3);
    	assertEquals(true, expectedList.equals(student.getRegisteredCourses()));
    }
    @Test
    void testAddDuplicateRegisterCourse() {
    	ErrorCodes returnValue = student.addRegisterCourse(registerCourse1);
    	assertEquals(ErrorCodes.ERROR, returnValue);
    }
    @Test
    void testAddRegisterCourseThatAlreadyExistsInWaitlistCourses() {
    	ErrorCodes returnValue = student.addRegisterCourse(waitlistCourse1);
    	assertEquals(ErrorCodes.ERROR, returnValue);
    }
    @Test
    void testCreditsExceedWhenAddingRegisterCourse() {
    	student.addRegisterCourse(course3);
    	student.addRegisterCourse(course4);
    	student.addRegisterCourse(course5);
    	ErrorCodes returnValue = student.addRegisterCourse(course6);
    	assertEquals(ErrorCodes.ERROR, returnValue);
    }
    @Test
    void testRemoveRegisterCourse() {
    	LinkedList<Course> expectedList = new LinkedList<>();
    	expectedList.add(registerCourse1);
    	student.removeRegisterCourse(registerCourse2);
    	assertEquals(true, expectedList.equals(student.getRegisteredCourses()));
    }
    @Test
    void testRemoveRegisterCourseThatDoesNotExistInStudentRegisterCourses() {
    	ErrorCodes returnValue = student.removeRegisterCourse(course3);
    	assertEquals(ErrorCodes.ERROR, returnValue);
    }
    
    
    
    
}
