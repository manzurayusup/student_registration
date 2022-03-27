package classes;

import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

public class Registration
{
	private static Scanner keyboard=new Scanner(System.in);
    public static void main(String[] args) throws IOException
    {

        Student currentStudent = makeStudent();
        
        int choice=5;
        do {
            printChoices();
            choice= keyboard.nextInt();
            keyboard.nextLine();
            switch (choice)
            {
				// more functionalities will be added as we go.
                case 1: register(currentStudent); break;
                case 2: waitlist(currentStudent); break;
                case 3: printCourse(); break;
                case 4: printAllCourses(); break;
                default: break;
            }
        }while (choice!=5);
        saveRegistration();
    }
	
    public static Student makeStudent() {
		// this method should call on to make course method to make the registered and waitlisted courses for the student.
		// this method should also get user's id through user input and make a student object for the user.
		System.out.println("this is the make student method");
		return null;
	}
	
	public static Course makeCourse() {
		System.out.println("this is the makeCourse Method");
		return null;
	}
	
	public static void printChoices() {
		// the print pattern should match the order of the switch cases in the main function.
	}
	
	public static void saveRegistration() {
		// this method saves the current registration status of the student in the student file.
	}
	
	public static void register(Student currentStudent) {
		// this method gets the name of course the student wants to register in, check's availability and adds it to students registered courses list.
	}
	
	public static void waitlist(Student currentStudent) {
		// this method gets the name of course the student wants to waitlist in, adds it to students waitlisted courses list.
	}
	
	public static void printCourse() {
		// this method gets the coursecode from user input and displays details of that course
	}
	
	public static void printAllCourses() {
		// this method prints all courses in the database (courses.txt).
	}
}