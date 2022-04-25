package classes;

import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;
/**
 * The Commandline class handles displaying the menu options and useful messages to the user and 
 * executing the commands the user types in.
 * @authors manzura, yab, khushi, irtaza
 *
 */
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
	
	public void printMenuOptions() {
		System.out.println("\t1. Register for a course");
		System.out.println("\t2. Print a course");
		System.out.println("\t3. Print all courses");
		System.out.println("\t4. Print enrolled courses");
		System.out.println("\t5. Print waitlisted courses");
		System.out.println("\t6. Quit");
	}
	
	/**
	 * Displays welcome message, logs student in, and returns the student.
	 * @return logged-in student, null if error
	 */
	public Student welcomeAndLogin() {
		System.out.println("Welcome to Student Registration App!");
		Student s = login();
		while(s == null) {
			System.out.println("Invalid ID or user doesn't exist.");
			s = login();
		}
		return s;
	}
	
	/**
	 * Helper method that creates a student from user ID input and assigns the student to a 
	 * property of this class.
	 * @return logged-in student if success, null otherwise
	 */
	private Student login() {
		System.out.println("Please enter your student ID: ");
		String id = parseInputId();
		student = fp.createStudent(id);
		return student;
		
	}
	
	/**
	 * Displays a welcome message and menu after login, continuously listens to user input and calls
	 * the method to execute the commands.
	 * @throws FileNotFoundException
	 */
	public void displayMenuAndProcessInput() throws FileNotFoundException {
		System.out.println("Logged in as " + student.getFirstName() + " " + student.getLastName() + ".");
		System.out.println("Please choose one of the commands:");
		
		Commands choice = null;
        while(choice != Commands.QUIT) {
        	printMenuOptions();
        	choice = parseInputCommand(); 
        	executeCommand(choice);
        }
	}
	
	/**
	 * Helper method that takes in a command and executes the appropriate task.
	 * @param command enum to be executed, assumes a non_command if null
	 * @throws FileNotFoundException
	 */
	private void executeCommand(Commands choice) throws FileNotFoundException {
		if (choice == null) choice = Commands.NON_COMMAND;
		switch (choice) {
            case REGISTER_FOR_COURSE: 
            	System.out.println(registerForACourse().getMessage());
            	break;
            case PRINT_COURSE: 
            	printCourse();
            	break;
            case PRINT_COURSES_ALL: 
            	System.out.println("Printing all courses.");
            	printAllCourses(); 
            	break;
            case PRINT_COURSES_ENROLLED: 
            	System.out.println("Your enrolled courses:");
            	printEnrolledCourses(); 
            	break;
            case PRINT_COURSES_WAITLISTED: 
            	System.out.println("Your waitlisted courses:");
            	printWaitlistedCourses();
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
	        return Commands.valueOf(choice);
		} catch (InputMismatchException e) {
			e.printStackTrace();
			return Commands.NON_COMMAND;
		}
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
	
	/**
	 * Saves the info of the logged-in student and all the courses into the appropriate files.
	 */
	public void saveStatus() {
		fw.saveStudentData(this.student);
		
		LinkedList<Course> list = fp.getAllCourses();
		for (int i = 0; i < list.size(); i++) {
			fw.saveCourseData(list.get(i));
		}
	}
	
	/**
	 * Gets the course code from user input and displays details of that course.
	 * @return error code corresponding with the error
	 * @throws FileNotFoundException
	 */
	public ErrorCodes printCourse() throws FileNotFoundException {
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
		Commandline cline = new Commandline();
		Student s = cline.welcomeAndLogin();
		cline.displayMenuAndProcessInput();
        cline.saveStatus();
        
		System.out.println("_______Program ended_______");
		
	}

}
