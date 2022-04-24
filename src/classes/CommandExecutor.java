package classes;

import java.io.IOException;
import java.io.InputStream;

import oop_classes.AppFileProcessor;

/**
 * Handles command execution once the user is already logged in.
 * @author manzura
 *
 */

public class CommandExecutor {
	private UserInputReader cmdReader;
	private AppFileProcessor fp;
	private Student student;
//	private Course course;

	public CommandExecutor(Student student) {
		this.cmdReader = new UserInputReader(System.in);
		this.fp = new AppFileProcessor();
		this.student = student;
	}
	
	
	public void displayMenuAndProcess() {
		Menu.printChoices();
		
		try {
			Commands cmd = cmdReader.processCommand();
			while (cmd != Commands.QUIT) {
				cmd = cmdReader.processCommand();
				executeCommand(cmd);
				
			}
		} catch (IOException e) {
			Menu.printIOError();
		}		
	}
	
	/**
	 * Takes in a Command and executes it.
	 * @param cmd
	 */
	public void executeCommand(Commands cmd) {
		switch (cmd) {
		case REGISTER_FOR_COURSE: 
			// get course code, make course object
			// call register method on this.student
			break;
		case PRINT_COURSE: 
			// get course code, make course object
			// call course's toString
			break;
		case PRINT_COURSES_ALL: 
			// read courses.txt, make Course object, and call toString on each 
			break;
		case PRINT_COURSES_ENROLLED: 
			// call printEnrolledCourses() on this.student
			break;
		}
	}
	/**
	 * If a student exists, creates the Student and returns it.
	 * @param id
	 * @return 
	 */
//	public Student loginStudentWithId(String id) {
//		
//	}
	

// ----------------------------- MAIN ------------------------------------------

	public static void main(String args[]) {
		UserInputReader reader = new UserInputReader(System.in);
		
		System.out.println("Please enter a number:");
		try {
			Commands command = reader.processCommand();
			System.out.println(command);
			
			System.out.println("Please enter another number:");
			Commands c2 = reader.processCommand();
			System.out.println(c2);
			
			System.out.println("Please enter student ID:");
			
			String id = reader.parseInputStudentId();
//			Student student = reader(id);
			while (id == null) {
				System.out.println("Invalid id, please try again.");
				id = reader.parseInputStudentId();
			}
			
			System.out.println(id);
			
//			System.out.println("Not a legal integer, please only enter 6 digits");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
