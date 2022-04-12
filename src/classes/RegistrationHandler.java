package classes;

import java.io.IOException;
import java.util.LinkedList;

public class RegistrationHandler {
	private Student student;

    public RegistrationHandler(Student student) {
        this.student = student;
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
