package classes;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
/**
 * Reads user input and parses it into a command.
 * @author manzura
 *
 */

public class CommandReader {
	private Scanner scanner;

	// whichever class is using this will pass System.in as input stream
	public CommandReader(InputStream inputStream) {
		scanner = new Scanner(inputStream);
	}

	// get a choice from user and process it accordingly.
	// input: the current student that is making the choices.
	public Commands processCommand() throws IOException {
		int choice = 888;

		choice = scanner.nextInt();
		scanner.nextLine();
		switch (choice) {
		// more functionalities will be added as we go.
		case 1:
			return Commands.REGISTER;
		case 2:
			return Commands.PRINT_COURSE;
		case 3:
			return Commands.PRINT_COURSES_ALL;
		case 4:
			return Commands.PRINT_COURSES_ENROLLED;
		case 5:
			return Commands.QUIT;
		default:
			return Commands.NON_COMMAND;
		}
	}
	

}
