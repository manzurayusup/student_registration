package oop_classes;

import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

import classes.Commands;
import classes.Course;
import classes.ErrorCodes;
import classes.Student;

public class Commandline {
	private Scanner scanner;
	private Student student;
	private AppFileProcessor fp;
	private AppFileWriter fw;
	
	public Commandline() {
		scanner = new Scanner(System.in);
		fp = new AppFileProcessor();
		fw = new AppFileWriter();
	}
	
	public void printChoices() {
		System.out.println("\t1. Register for a course");
		System.out.println("\t2. Print a course");
		System.out.println("\t3. Print all courses");
		System.out.println("\t4. Print enrolled courses");
		System.out.println("\t5. Print waitlisted courses");
		System.out.println("\t6. Quit");
	}
	
	/**
	 * Helper method that parses user ID input into a string and makes sure it is six characters long.
	 * @return ID as string if input is 6-digits long, null otherwise
	 */
	public String parseInputId() {
		String id = "";
		if (scanner.hasNext()) {
			id = scanner.next();
		}
		if (id.length() != 6) {
			return null;
		}
		return id;
	}
	
	/**
	 * Reads and returns the command the user typed, null if input is invalid.
	 * @return
	 */
	public Commands parseInputCommand() {
		int choice;
		try {
			if (scanner.hasNextInt()) choice = scanner.nextInt();
			else choice = 0;
	        scanner.nextLine();
	        System.out.println("Watermelon: "+ choice);
	        return Commands.valueOf(choice);
		} catch (InputMismatchException e) {
			System.out.println("Watermelon:");
			e.printStackTrace();
			return Commands.NON_COMMAND;
		}
	}
	
	/**
	 * Creates a student from user ID input and assigns the student to a property of this class.
	 * @return logged-in student if success, null otherwise
	 */
	public Student login() {
		System.out.println("Please enter your student ID: ");
		String id = parseInputId();
		student = fp.createStudent(id);
		return student;
		
	}
	/**
	 * Helper method that parses user input into a course. 
	 * @return course if successful, null otherwise
	 */
	public Course parseInputCourse() {
		String code = "";
		if (scanner.hasNext()) {
			code = scanner.next();
		}
		return fp.findCourse(code);
	}
	/**
	 * Register the logged-in student for the given course.
	 * @return error code based on success or failure
	 */
	public ErrorCodes registerForACourse() {
		System.out.println("Enter course code: ");
		Course course = parseInputCourse();
		if (course != null) {
			return student.register(course);
		}
//		if ((course != null) && (student.addRegisterCourse(course) == ErrorCodes.SUCCESS)) return true;
		return ErrorCodes.ERROR;
	}
	
	public void printAllCourses() {
		LinkedList<Course> list = fp.getAllCourses();
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
	
	public void printEnrolledCourses() {
		this.student.displayRegisterCourses();
	}
	
	public void printWaitlistedCourses() {
		this.student.displayWaitListCourses();
	}
	
	public Student getStudent() {
		return this.student;
	}
	public void saveStatus() {
		fw.writeStudentData(this.student);
		
		LinkedList<Course> list = fp.getAllCourses();
		for (int i = 0; i < list.size(); i++) {
			fw.saveCourseData(list.get(i));
		}
	}
//	------------------------------- Display class -------------------------------
	public ErrorCodes printCourse() throws FileNotFoundException {
		// this method gets the coursecode from user input and displays details of that course
		// Course (String name, String courseCode, String startTime, String endTime, int seats, int credits)
		System.out.println("Please enter the course code: ");
		
		String courseCode = scanner.nextLine();
		Course course = fp.findCourse(courseCode);
		if (course != null) {
			System.out.println(course);
			return ErrorCodes.SUCCESS;
		}
		else {
			System.out.println(ErrorCodes.ERROR.getMessage());
			return ErrorCodes.ERROR;
		}
	}

	public static void displayRegisteredCourses(Student student) {
		student.displayRegisterCourses();
	}
	public static void displayWaitlistedCourses(Student student) {
		student.displayWaitListCourses();
	}
	
// -------------------------------- MAIN ----------------------------------------------
	public static void main(String[] args) throws FileNotFoundException {
		/* Setup */
		Commandline cline = new Commandline();
		 
		System.out.println("Welcome to Student Registration App!");
		
		/* Login user */
		
		Student s = cline.login();
		while(s == null) {
			System.out.println("Invalid ID or user doesn't exist.");
			s = cline.login();
		}
		
		System.out.println("Logged in as " + s.getFirstName() + " " + s.getLastName() + ".");
		
		/* Display commands and handle user input */
		System.out.println("Please choose one of the commands:");
		Commands choice = null;
        while(choice != Commands.QUIT) {
        	cline.printChoices();
        	choice = cline.parseInputCommand();

        	if (choice == null) choice = Commands.NON_COMMAND;
        	
            switch (choice)
            {
				// more functionalities will be added as we go.
                case REGISTER_FOR_COURSE: 
                	System.out.println(cline.registerForACourse().getMessage());
                	break;
                case PRINT_COURSE: 
//                	System.out.println("Print a course?");
                	cline.printCourse();
                	break;
                case PRINT_COURSES_ALL: 
                	System.out.println("Printing all courses.");
                	cline.printAllCourses(); 
                	break;
                case PRINT_COURSES_ENROLLED: 
                	System.out.println("Your enrolled courses:");
                	cline.printEnrolledCourses(); 
                	break;
                case PRINT_COURSES_WAITLISTED: 
                	System.out.println("Your waitlisted courses:");
                	cline.printWaitlistedCourses();
                	break;
                case QUIT: 
                	System.out.println("Logging you out... Bye!");
                	break;
                case NON_COMMAND: 
                	System.out.println("Invalid command.");
                	break;
                default: 
                	System.out.println("Not a valid command.");
                	break;
            }
        }
        
        cline.saveStatus();
        
		System.out.println("_____Program ended_____");
		
	}

}
