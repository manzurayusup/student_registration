package classes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Vector;


public class Registration
{
	private Scanner keyboard=new Scanner(System.in);
	private static String courseTextPath = "src/textfiles/courses.txt";
	private String studentTextPath = "src/textfiles/students.txt";
    public static void main(String[] args) throws IOException
    {
    	Registration registration = new Registration();
        Student currentStudent = registration.intro();
        registration.processChoice(currentStudent, System.in);
        
    }
    
    // get a choice from user and process it accordingly.
    // input: the current student that is making the choices.
    public void processChoice(Student currentStudent, InputStream inputStream) throws IOException {
    	int choice=6;
    	
        do {
            printChoices();
            Scanner scanner = new Scanner(inputStream);
            choice= scanner.nextInt();
            scanner.nextLine();
            switch (choice)
            {
				// more functionalities will be added as we go.
                case 1: register(currentStudent, System.in); break;
                case 2: printCourse(System.in); break;
                case 3: printAllCourses(); break;
                case 4: currentStudent.displayRegisterCourses(); break;
                default: break;
            }
        }while (choice!=5);
    }
    
    // print an intro to the user and get their information.
    public Student intro() {
    	System.out.println("Welcome to the student registration system!");
        System.out.println("Enter Student ID to log in:");
        Student currentStudent = logIn(System.in);
        
        while (currentStudent == null) {
        	System.out.println("Did not recognize ID, please try again");
        	currentStudent = logIn(System.in); 	
        }
        return currentStudent;
    }
    
    // use user Id to create a student object, hence logging them in.
    public Student logIn(InputStream inputStream) {
    	
    	int id = getId(inputStream);
    	if (id == -1) {
    		return null;
    	}
    	String stringId = String.valueOf(id);
    	Student loggedUser = populateStudentObjectFromFile(stringId);
    	return loggedUser;
    }
    
    // return: an integer Id inputted by a user.
    public int getId(InputStream inputStream) {
    	try {
    		String id = "";
    		Scanner scanner = new Scanner(inputStream);
    		if (scanner.hasNext()) {
    			id = scanner.next();
    		}
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
    
    // return: a student object referring to an ID provided as argument. null if student could not be created.
    // input: a String corresponding to a students ID.
    public Student populateStudentObjectFromFile(String studentID) {
    	File file = new File(studentTextPath);
    	if (file.canRead()) {
    		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
    			return findStudentInFile (studentID, br);
    		    
    		} catch (Exception e) {
    			System.out.println("Could not read the student database.");
    			return null;
    		}
    	}
    	return null;
    }
    
    // return: a student referring to an ID provided as argument from a file given. Null if student not found in file.
    // input: a string student id and a buffered reader that points to a file.
    public Student findStudentInFile (String studentID, BufferedReader br) throws FileNotFoundException, IOException {
    	String line;
    	while ((line = br.readLine()) != null) {
	    	String words[] = line.split("\\s");
	    	if (words[2].equals(studentID)) {
	    		System.out.println("You are now logged in! Enter the number corresponding to the option you want to choose");
	    		return createSingleStudent(words, studentID);
	    	}
	    }
	    return null;
    }
    
    // return: a student object referring to a student Id.
    // input: an array of strings containing information about a student and student id
    //			strings should be in order of: first name, last name, registered courses, waitlisted courses.
    public Student createSingleStudent(String[] words, String studentID) throws FileNotFoundException {
    	String firstName = words[0];
		String lastName = words[1];
    	Student student = new Student(firstName, lastName, Integer.valueOf(studentID));
    	String registeredCourses = words[3];
    	AddRegisteredCoursesToStudentObject(registeredCourses, student);
    	String waitlistedCourses = words[4];
    	AddWaitlistedCoursesToStudentObject(waitlistedCourses, student);
    	return student;
    }
    
    // input: a string containing all registered courses, and a student object.
    // Desc: populates the registered courses for a student object from the string.
    // 		registerdCourses should be a '-' separated string containing course codes.
    public void AddRegisteredCoursesToStudentObject(String registeredCourses, Student student) throws FileNotFoundException {
    	StringBuilder sb = new StringBuilder();
    	for (int i=0; i<registeredCourses.length(); i++) {
    		if (registeredCourses.charAt(i)=='-') {	
    			student.addRegisterCourse(MakeCourse.makeCourse(sb.toString()));
    			sb.setLength(0);
    		}
    		else {
    			sb.append(registeredCourses.charAt(i));
    		}
    	}
    	if (sb.toString().length()>0) {
    		student.addRegisterCourse(MakeCourse.makeCourse(sb.toString()));
    	}
    }
    
    // input: a string containing all waitlisted courses, and a student object.
    // Desc: populates the waitlisted courses for a student object from the string.
    // 		waitlistedCourses should be a '-' separated string containing course codes.
    public void AddWaitlistedCoursesToStudentObject(String waitlistedCourses, Student student) throws FileNotFoundException {
    	StringBuilder sb = new StringBuilder();
    	for (int i=0; i<waitlistedCourses.length(); i++) {
    		if (waitlistedCourses.charAt(i)=='-') {
    			student.addWaitlistCourse(MakeCourse.makeCourse(sb.toString()));
    			sb.setLength(0);
    		}
    		else {
    			sb.append(waitlistedCourses.charAt(i));
    		}
    	}
    	if (sb.toString().length()>0) {
    		student.addWaitlistCourse(MakeCourse.makeCourse(sb.toString()));
    	}
    }
    
    
	
    // Post: prints the action options of the user.
	public void printChoices() {
		// the print pattern should match the order of the switch cases in the main function.
		System.out.println("\t1. Register for a course");
        System.out.println("\t2. Print a course");
        System.out.println("\t3. Print all courses");
        System.out.println("\t4. Print enrolled courses");
        System.out.println("\t5. Quit");
	}
	
	// input: the current student that registers for a course.
	// Desc: prompts user for course code, checks if course is in database and adds course to registered courses for student.
	public void register(Student currentStudent, InputStream inputStream) throws IOException {
		System.out.println("Please enter the course code of the course you want to register for");
		Scanner scanner = new Scanner(inputStream);
		String courseCode = scanner.next();
		System.out.println("Course code: " + courseCode);
		scanner.nextLine();
		Course course = MakeCourse.makeCourse(courseCode);
		if (course != null) {
			RegistrationHandler registrationHandler= new RegistrationHandler(currentStudent);
			registrationHandler.register(courseCode);
		}
		else {
			System.out.println("Course does not exist");
		}
	}
	
	public ErrorCodes printCourse(InputStream inputStream) throws FileNotFoundException {
		// this method gets the coursecode from user input and displays details of that course
		// Course (String name, String courseCode, String startTime, String endTime, int seats, int credits)
		System.out.println("Please enter the course code: ");
		Scanner scanner = new Scanner(inputStream);
		String courseCode = scanner.nextLine();
		
		Course course = MakeCourse.makeCourse(courseCode);
		if (course != null) {
			System.out.println(course);
			return ErrorCodes.SUCCESS;
		}
		else {
			System.out.println("Course does not exist");
			return ErrorCodes.ERROR;
		}
	}
	//return: a String which contains all the courses in the database (courses.txt)
	public String printAllCourses() throws FileNotFoundException {
    	File file = new File(courseTextPath);
    	Scanner courseReader = new Scanner(file);
    	String allCourses="";
    	while(courseReader.hasNextLine()) {
    		String splittedCurrentCourse[] = courseReader.nextLine().split("\\s");
    		Course course = MakeCourse.makeCourse(splittedCurrentCourse[0]);
    		allCourses+=course.toString()+"\n\n";
    	}
    	System.out.println(allCourses);
    	return allCourses;
	}
	
}