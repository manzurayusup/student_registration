package classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class Display {
	private static String courseTextPath = "src/textfiles/courses.txt";
    // Post: prints the action options of the user.
	public static void printChoices() {
		// the print pattern should match the order of the switch cases in the main function.
		System.out.println("\t1. Register for a course");
        System.out.println("\t2. Print a course");
        System.out.println("\t3. Print all courses");
        System.out.println("\t4. Print enrolled courses");
        System.out.println("\t5. Quit");
	}
	public static ErrorCodes printCourse(InputStream inputStream) throws FileNotFoundException {
		// this method gets the coursecode from user input and displays details of that course
		// Course (String name, String courseCode, String startTime, String endTime, int seats, int credits)
		System.out.println("Please enter the course code: ");
		Scanner scanner = new Scanner(inputStream);
		String courseCode = scanner.nextLine();
		Course course = MakeCourse.makeCourse(courseCode);
		if (course != null) {
			System.out.println(course);
			return ErrorCodes.SUCCESS;
		}
		else {
			System.out.println("Course does not exist");
			return ErrorCodes.ERROR;
		}
	}
	//return: a String which contains all the courses in the database (courses.txt)
	public static String printAllCourses() throws FileNotFoundException {
    	File file = new File(courseTextPath);
    	Scanner courseReader = new Scanner(file);
    	String allCourses="";
    	while(courseReader.hasNextLine()) {
    		String splittedCurrentCourse[] = courseReader.nextLine().split("\\s");
    		Course course = MakeCourse.makeCourse(splittedCurrentCourse[0]);
    		allCourses+=course.toString()+"\n\n";
    	}
    	System.out.println(allCourses);
    	return allCourses;
	}
}
