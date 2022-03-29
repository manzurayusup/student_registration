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
	private static String courseTextPath = "src/textfiles/courses.txt";
	private static String studentTextPath = "src/textfiles/students.txt";
    public static void main(String[] args) throws IOException
    {
    	
        Student currentStudent = intro();
        processChoice(currentStudent);
        saveRegistration();
    }
    
    // get a choice from user and process it accordingly.
    // input: the current student that is making the choices.
    public static void processChoice(Student currentStudent) throws FileNotFoundException {
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
    }
    
    // print an intro to the user and get their information.
    public static Student intro() {
    	System.out.println("Welcome to the student registration system!");
        System.out.println("Enter Student ID to log in:");
        Student currentStudent = logIn();
        
        while (currentStudent == null) {
        	System.out.println("Did not recognize ID, please try again");
        	currentStudent = logIn(); 	
        }
        return currentStudent;
    }
    
    // use user Id to create a student object, hence logging them in.
    public static Student logIn() {
    	int id = getId();
    	if (id == -1) {
    		return null;
    	}
    	String stringId = String.valueOf(id);
    	Student loggedUser = populateStudentObjectFromFile(stringId);
    	return loggedUser;
    }
    
    // return: an integer Id inputted by a user.
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
    
    // return: a student object referring to an ID provided as argument. null if student could not be created.
    // input: a String corresponding to a students ID.
    public static Student populateStudentObjectFromFile(String studentID) {
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
    public static Student findStudentInFile (String studentID, BufferedReader br) throws FileNotFoundException, IOException {
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
    public static Student createSingleStudent(String[] words, String studentID) throws FileNotFoundException {
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
    public static void AddRegisteredCoursesToStudentObject(String registeredCourses, Student student) throws FileNotFoundException {
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
    }
    
    // input: a string containing all waitlisted courses, and a student object.
    // Desc: populates the waitlisted courses for a student object from the string.
    // 		waitlistedCourses should be a '-' separated string containing course codes.
    public static void AddWaitlistedCoursesToStudentObject(String waitlistedCourses, Student student) throws FileNotFoundException {
    	StringBuilder sb = new StringBuilder();
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
    }
    
    // return: a course object or null if course could not be created
    // input: a string coursecode.
    public static Course makeCourse(String courseCode) throws FileNotFoundException {
    	String path = courseTextPath;
    	File file = new File(path);
    	Scanner courseReader = new Scanner(file);
    	
    	Course newCourse = findAndCreateCourse(courseCode, courseReader);
    	return newCourse;
    }
    
    // return: a course object corresponding to a coursecode given as input, null if course could not be created.
    // input: a string coursecode and a scanner pointing to courses.txt
    private static Course findAndCreateCourse(String courseCode, Scanner courseReader) {
    	while(courseReader.hasNextLine()) {
    		String currentCourse = courseReader.nextLine();
    		String splittedCurrentCourse[] = currentCourse.split("\\s");
    		if(courseCode.equals(splittedCurrentCourse[0])) {
    			return createSingleCourse(splittedCurrentCourse);
    		}
    	}
    	return null;
    }

    // return: a course object
    // input: an array of strings with information about the course.
    // 		array should be in the structure of: courseCode, courseName, courseStartTime, and courseEndTime. separaged with dashes.
    private static Course createSingleCourse (String[] splittedCurrentCourse) {
    	String courseCode = splittedCurrentCourse[0];
		String courseName = splittedCurrentCourse[1];
		String startTime = splittedCurrentCourse[2];
		String endTime = splittedCurrentCourse[3];
		Course course = new Course(courseName, courseCode, startTime, endTime, 1, 1);
		return course;
    }
	
    // Post: prints the action options of the user.
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
	
	// input: the current student that registers for a course.
	// Desc: prompts user for course code, checks if course is in database and adds course to registered courses for student.
	public static void register(Student currentStudent) throws FileNotFoundException {
		System.out.println("Please enter the course code of the course you want to register for");
		String courseCode = keyboard.next();
		keyboard.nextLine();
		Course course = makeCourse(courseCode);
		if (course != null) {
			registerForSingleCourse(course, currentStudent);
		}
		else {
			System.out.println("Course does not exist");
		}
	}
	
	// input: a course to register for and the registering student
	public static void registerForSingleCourse (Course course, Student currentStudent) {
		if (course.getSeats() > 0) {
			ErrorCodes register = currentStudent.addRegisterCourse(course);
			if (register == ErrorCodes.ERROR) {
				System.out.println("Student was not able to register to this course");
			}else {
				System.out.println("Student has successfully registered to this course");
				System.out.println(currentStudent.toString() + " Registered Courses: ");
				System.out.println(currentStudent.getRegisteredCourses());
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