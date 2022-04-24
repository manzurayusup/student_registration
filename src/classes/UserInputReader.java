package classes;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import oop_classes.AppFileProcessor;
/**
 * Reads user input and parses it into a command.
 * @author manzura
 *
 */

public class UserInputReader {
	private Scanner scanner;
	private AppFileProcessor fp;

	// whichever class is using this will pass System.in as input stream
	public UserInputReader(InputStream inputStream) {
		scanner = new Scanner(inputStream);
		fp = new AppFileProcessor();
	}

	// get a choice from user and process it accordingly.
	// input: the current student that is making the choices.
	public Commands processCommand() throws IOException {
		int choice = 888;
//		Scanner scanner = new Scanner(System.in);
		choice = scanner.nextInt();
		scanner.nextLine();
		
		switch (choice) {
		// more functionalities will be added as we go.
		case 1:
			return Commands.REGISTER_FOR_COURSE;
		case 2:
			return Commands.PRINT_COURSE;
		case 3:
			return Commands.PRINT_COURSES_ALL;
		case 4:
			return Commands.PRINT_COURSES_ENROLLED;
		case 5:
			return Commands.QUIT;
		default:
			return null;
		}
	}
//	/**
//	 * Creates a Student object from the given ID.
//	 * @param student ID
//	 * @return student that was created, null if no student with that ID is found.
//	 */
//	public Student loginStudent(String id) {
//		System.out.println("Please enter your 6-digit ID: ");
//		String studentID = parseInputStudentId();
//		return fp.createStudent(studentID);
//	}
	/**
	 * Reads user input from console and parses it to a valid WUSTL ID.
	 * @return 6-digit id if successful, -1 otherwise
	 */
	public String parseInputStudentId() {
		String id = "";
		if (scanner.hasNext()) {
			id = scanner.next();
		}
		if (id.length() != 6) {
			return null;
		}
		return id;
	}
	

	

}
