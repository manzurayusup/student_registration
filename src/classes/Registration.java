package classes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Vector;


public class Registration
{
	private static Scanner keyboard=new Scanner(System.in);
	public static HashMap<String, Course> map = new HashMap<>();
    public static void main(String[] args) throws IOException
    {
    	
        System.out.println("Welcome to the student registration system!");
        Student currentStudent = logIn();
        while (currentStudent == null) {
        	System.out.println("Did not recognize ID, please try again");
        	currentStudent = logIn(); 	
        }

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
            System.out.println(currentStudent.getRegisteredCourses());
        }while (choice!=5);
        saveRegistration();
        String pathToFileStudents = "src/textfiles/students.txt";
//        String pathToFileStudents = "./../textfiles/students.txt";

        
    }
    
    public static Student logIn() {
    	int id = getId();
    	if (id == -1) {
    		return null;
    	}
    	String stringId = String.valueOf(id);
    	Student loggedUser = populateStudentObjectFromFile("src/textfiles/students.txt" , stringId);
    	return loggedUser;
    }
    
    public static int getId() {
    	try {
    		String id = keyboard.next();
    		if (id.length() > 6) {
    			System.out.println("Not a legal integer, please only enter 6 digits");
    			return -1;
    		}
    		return Integer.valueOf(id);
    	}
    	catch(Exception e) {
    		System.out.println("Not a legal integer, please only enter 6 digits");
    		return -1;
    	}
    	
    }
    
    public static Student populateStudentObjectFromFile(String filename, String studentID) {
    	File file = new File(filename);
//    	System.out.println(file.getName());
//    	System.out.println(file.canRead());
    	if (file.canRead()) {
    		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
    		    String line;
    		    
    		    while ((line = br.readLine()) != null) {
    		    	String words[];
    		    	words = line.split("\\s");
    		    	// 0: FirstName 1: LastName 2: wustl ID
    		    	if (words[2].equals(studentID)) {
    		    		System.out.println("You are now logged in!");
	    		    	Student student = new Student(words[0], words[1], Integer.valueOf(words[2]));
	    		    	String registeredCourses = words[3];
	    		    	StringBuilder sb = new StringBuilder();
	    		    	for (int i=0; i<registeredCourses.length(); i++) {
	    		    		if (registeredCourses.charAt(i)=='-') {
	    		    			
	    		    			student.addRegisterCourse(makeCourse(sb.toString()));
	    		    			sb.setLength(0);
	    		    		}
	    		    		else {
	    		    			sb.append(registeredCourses.charAt(i));
	    		    		}
	    		    	}
	    		    	if (sb.toString().length()>0) {
	    		    		student.addRegisterCourse(makeCourse(sb.toString()));
	    		    	}
	    		    	sb.setLength(0);
	    		    	String waitlistedCourses = words[4];
	    		    	for (int i=0; i<waitlistedCourses.length(); i++) {
	    		    		if (waitlistedCourses.charAt(i)=='-') {
	    		    			student.addWaitlistCourse(makeCourse(sb.toString()));
	    		    			sb.setLength(0);
	    		    		}
	    		    		else {
	    		    			sb.append(waitlistedCourses.charAt(i));
	    		    		}
	    		    	}
	    		    	if (sb.toString().length()>0) {
	    		    		student.addWaitlistCourse(makeCourse(sb.toString()));
	    		    	}
	    		    	return student;
    		    	}
    		    	
    		    	
    		    	
    		    }
    		    return null;
    		    
    		} catch (Exception e) {
    			e.printStackTrace();
    			return null;
    		}
    	}
    	System.out.println("never going into the if statement");
    	return null;
    }
    

	
	public static Course makeCourse(String courseCode) throws FileNotFoundException {
		String path = "src/textfiles/courses.txt";
		File file = new File(path);
		Scanner courseReader = new Scanner(file);
		
		while(courseReader.hasNextLine()) {

			String currentCourse = courseReader.nextLine();
			String splittedCurrentCourse[] = currentCourse.split("\\s");
			
			if(courseCode.equals(splittedCurrentCourse[0])) {
				String courseName = splittedCurrentCourse[1];
				String startTime = splittedCurrentCourse[2];
				String endTime = splittedCurrentCourse[3];
				Course course = new Course(courseName, courseCode, startTime, endTime, 1, 1);
				
				return course;
			}
		}
		return null;
	}
	
	public static void printChoices() {
		// the print pattern should match the order of the switch cases in the main function.
		System.out.println("\t1. Register for a course");
        System.out.println("\t2. Add course to waitlist");
        System.out.println("\t3. Print a course");
        System.out.println("\t4. Print all courses");
        System.out.println("\t5. Quit");
	}
	
	public static void saveRegistration() {
		// this method saves the current registration status of the student in the student file.
	}
	
	public static void register(Student currentStudent) throws FileNotFoundException {
		// this method gets the name of course the student wants to register in, check's availability and adds it to students registered courses list.
		String courseCode = keyboard.next();
		keyboard.nextLine();
		Course course = makeCourse(courseCode);
		if (course.getSeats() > 0) {
			ErrorCodes register = currentStudent.addRegisterCourse(course);
			if (register == ErrorCodes.ERROR) {
				System.out.println("Student was not able to register to this course");
			}else {
				System.out.println("Student has successfully registered to this course");
			}
		}
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