package classes;

import java.io.IOException;
import java.io.InputStream;

/**
 * Handles command execution once the user is already logged in.
 * @author manzura
 *
 */

public class CommandExecutor {
	private CommandReader cmdReader;
	private Student student;
//	private Course course;

	public CommandExecutor(Student student) {
		this.cmdReader = new CommandReader(System.in);
		this.student = student;
	}
	
	
	public void displayMenuAndProcess() {
		Menu.printChoices();
		
		try {
			Commands cmd = cmdReader.processCommand();
			while (cmd != Commands.QUIT) {
				cmd = cmdReader.processCommand();
				
				switch (cmd) {
				case REGISTER: 
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
				case NON_COMMAND: 
					Menu.printErrorNonCommand();
					break;
				}
			}
		} catch (IOException e) {
			Menu.printIOError();
		}		
	}
	
	// other methods go here

}
