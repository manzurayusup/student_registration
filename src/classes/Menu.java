package classes;

/**
 * Only handles dislaying options/messages to the user.
 * 
 * @author manzura
 *
 */
public class Menu {

	// Post: prints user command options.
	public static void printChoices() {
		System.out.println("\t1. Register for a course");
		System.out.println("\t2. Print a course");
		System.out.println("\t3. Print all courses");
		System.out.println("\t4. Print enrolled courses");
		System.out.println("\t5. Quit");
	}
	
	public static void printIOError() {
		System.out.println("Could not read input.");
	}
	
	public static void printErrorNonCommand() {
		System.out.println("That's not a recognized command. Please try again.");
	}
	
	public static void printErrorCouldNotReadDatabase() {
		System.out.println("Could not read the student database.");

	}
	
	public static void displayLoginMessage() {
		System.out.println("You are now logged in! Enter the number corresponding to the option you want to choose");
	}
	
	
	
}
