package classes;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class Menu {
	private RegistrationHandler registrationHandler;
	private Student student;
	public Menu(Student student) {
		this.student  = student;
		registrationHandler = new RegistrationHandler(student);
	}
	// get a choice from user and process it accordingly.
    // input: the current student that is making the choices.
    public void processChoice(Student currentStudent, InputStream inputStream) throws IOException {
    	int choice=6;
    	
        do {
        	// todo: use display to print choices here.
            Display.printChoices();
            Scanner scanner = new Scanner(inputStream);
            choice= scanner.nextInt();
            scanner.nextLine();
            switch (choice)
            {
				// more functionalities will be added as we go.
                case 1: registrationHandler.register(this.student, System.in); break;
                case 2: Display.printCourse(System.in); break;
                case 3: Display.printAllCourses(); break;
                case 4: Display.displayRegisteredCourses(this.student); break;
                case 5: Display.displayWaitlistedCourses(this.student); break;
                default: break;
            }
        }while (choice!=6);
    }
}