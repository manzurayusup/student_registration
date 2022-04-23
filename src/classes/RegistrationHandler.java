package classes;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Scanner;

public class RegistrationHandler {
	private Student student;

    public RegistrationHandler(Student student) {
        this.student = student;
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
 			register(courseCode);
 		}
 		else {
 			System.out.println("Course does not exist");
 		}
 	}

	public void register(String courseCode) throws IOException {
		// TODO Auto-generated method stub
		Course course = MakeCourse.makeCourse(courseCode);
 		if (student.getRegisteredCourses().contains(course)) {
 			System.out.println("You're already registered for this course");
 			return;
 		}
 		if (student.getWaitlistedCourses().contains(course)) {
 			System.out.println("You're already waitlisted for this course");
 			return;
 		}
 		if (course.getSeats() > 0) {
 			ErrorCodes register = student.addRegisterCourse(course);
 			if (register == ErrorCodes.ERROR) {
 				System.out.println("Student was not able to register to this course");
 			}else {
 				course.setSeats(course.getSeats() - 1);
 				System.out.println("Student has successfully registered to this course");
 				SaveHandler save = new SaveHandler(student, course);
 				save.saveRegistration();
 				student.printSummary();
 			}
 		} else {
 			// waitlist the student
 			student.addWaitlistCourse(course);
 			System.out.println("Student added to the waitlist for this course.");
 			student.printSummary();
 		}
	}
}
