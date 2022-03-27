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
    	
        Course data_structures = new Course("Data Structures and Algorithms", "CSE247", "2022-08-29T13:00:00.000Z", "2022-12-09T14:20:00.000Z", 70, 3);
        map.put("CSE247", data_structures);
        Course intro_to_cs = new Course("Introduction to Computer Science", "CSE131", "2022-08-29T13:00:00.000Z", "2022-12-09T14:20:00.000Z", 70, 3);
        Course intro_to_ce = new Course("Introduction to Computer Engineering", "CSE132", "2022-08-29T13:00:00.000Z", "2022-12-09T14:20:00.000Z", 70, 3);
        map.put("CSE131", intro_to_cs);
        map.put("CSE132", intro_to_ce);
        Course rapid = new Course("Rapid Prototype Development and Creative Programming", "CSE330", "2022-08-29T13:00:00.000Z", "2022-12-09T14:20:00.000Z", 70, 3);
        map.put("CSE330", rapid);
        System.out.println("Welcome to the student registration system!");
        Student currentStudent = logIn();
        System.out.println(currentStudent);
        System.out.println(currentStudent.getRegisteredCourses());
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
        String pathToFileStudents = "/Users/manzura/git/student_registration/src/textfiles/students.txt";
//        String pathToFileStudents = "./../textfiles/students.txt";

        
    }
    
    public static Student logIn() {
    	int id = getId();
    	String stringId = String.valueOf(id);
    	Student loggedUser = populateStudentObjectFromFile("/Users/Yeab/git/student_registration/src/textfiles/students.txt" , stringId);
    	return loggedUser;
    }
    
    public static int getId() {
    	int id = keyboard.nextInt();
    	return id;
    }
    
    public static Student populateStudentObjectFromFile(String filename, String studentID) {
    	File file = new File(filename);
    	System.out.println(file.getName());
    	System.out.println(file.canRead());
    	if (file.canRead()) {
    		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
    		    String line;
    		    
    		    while ((line = br.readLine()) != null) {
    		    	String words[];
    		    	words = line.split("\\s");
    		    	// 0: FirstName 1: LastName 2: wustl ID
    		    	if (words[2].equals(studentID)) {
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
		String path = "/Users/Yeab/git/student_registration/src/textfiles/courses.txt";
		File file = new File(path);
		Scanner courseReader = new Scanner(file);
		
		while(courseReader.hasNextLine()) {

			String currentCourse = courseReader.nextLine();
			String splittedCurrentCourse[] = currentCourse.split("\\s");
			
			if(courseCode.equals(splittedCurrentCourse[0])) {
				String courseName = splittedCurrentCourse[1];
				String startTime = splittedCurrentCourse[2];
				String endTime = splittedCurrentCourse[3];
				Course course = new Course(courseName, courseCode, startTime, endTime, -1, -1);
				
				return course;
			}
		}
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