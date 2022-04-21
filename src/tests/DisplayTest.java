package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import classes.ErrorCodes;
import classes.Display;
import classes.Course;
import classes.Registration;
import classes.Student;

class DisplayTest {
	@BeforeEach
	void setup() throws IOException {
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
	}
	@Test
	void testPrintCourse() throws FileNotFoundException {
		InputStream is = new ByteArrayInputStream("CSE131".getBytes());    	
		ErrorCodes isPrinted = Display.printCourse(is);
		assertEquals(ErrorCodes.SUCCESS, isPrinted);
	}
	@Test
	void testPrintCourseWrongCourse() throws FileNotFoundException {
		InputStream is = new ByteArrayInputStream("hello".getBytes());    	
		ErrorCodes isPrinted = Display.printCourse(is);
		assertEquals(ErrorCodes.ERROR, isPrinted);
	}
	@Test
	void testPrintAllCourses() throws FileNotFoundException {
		String returnedString = Display.printAllCourses();
		String expected = "Name: Introduction-to-Computer-Science \n"+ "Code: CSE131 \n"+ "start time: 11:30:00 \n"+ "end time: 12:50:00 \n"+ "seats left: 0 \n"+ "Credits: 3\n"+ "Professor: Shook\n"+ "Exams: Yes\n"+ "\n"+ "Name: Introduction-to-Computer-Engineering \n"+ "Code: CSE132 \n"+ "start time: 13:00:00 \n"+ "end time: 14:20:00 \n"+ "seats left: 0 \n"+ "Credits: 3\n"+ "Professor: Chamberlain\n"+ "Exams: Yes\n"+ "\n"+ "Name: Web-Development \n"+ "Code: CSE204 \n"+ "start time: 08:30:00 \n"+ "end time: 09:50:00 \n"+ "seats left: 100 \n"+ "Credits: 3\n"+ "Professor: Clapp\n"+ "Exams: No\n"+ "\n"+ "Name: Introduction-to-Data-Science \n"+ "Code: CSE217 \n"+ "start time: 14:30:00 \n"+ "end time: 15:50:00 \n"+ "seats left: 100 \n"+ "Credits: 3\n"+ "Professor: Singh\n"+ "Exams: No\n"+ "\n"+ "Name: Logic-and-Discrete-Mathematics \n"+ "Code: CSE240 \n"+ "start time: 10:00:00 \n"+ "end time: 11:20:00 \n"+ "seats left: 100 \n"+ "Credits: 3\n"+ "Professor: Garnett\n"+ "Exams: No\n"+ "\n"+ "Name: Data-Structures-and-Algorithms \n"+ "Code: CSE247 \n"+ "start time: 13:00:00 \n"+ "end time: 14:20:00 \n"+ "seats left: 100 \n"+ "Credits: 3\n"+ "Professor: Cole\n"+ "Exams: No\n"+ "\n"+ "Name: Rapid-Prototype-Development-and-Creative-Programming \n"+ "Code: CSE330 \n"+ "start time: 11:30:00 \n"+ "end time: 12:50:00 \n"+ "seats left: 60 \n"+ "Credits: 3\n"+ "Professor: Sproull\n"+ "Exams: No\n"+ "\n"+ "Name: Object-Oriented-Software-Development-Laboratory \n"+ "Code: CSE332 \n"+ "start time: 10:00:00 \n"+ "end time: 11:20:00 \n"+ "seats left: 100 \n"+ "Credits: 3\n"+ "Professor: Shidal\n"+ "Exams: No\n"+ "\n"+ "Name: Analysis-of-Algorithms \n"+ "Code: CSE347 \n"+ "start time: 11:30:00 \n"+ "end time: 12:50:00 \n"+ "seats left: 0 \n"+ "Credits: 3\n"+ "Professor: Buhler\n"+ "Exams: Yes\n\n";
    	assertTrue(returnedString.equals(expected));
	}

}
