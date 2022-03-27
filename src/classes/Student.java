package classes;

import java.util.LinkedList;

public class Student {
    private String firstName;
    private String lastName;
    private int wustlID;
    private LinkedList<Course> waitlistedCourses;
    private LinkedList<Course> registeredCourses;

    public Student(String firstName, String lastName, int wustlID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.wustlID = wustlID;
        this.waitlistedCourses = new LinkedList<>();
        this.registeredCourses = new LinkedList<>();
    }

    
    public String getFirstName() {
        return this.firstName;
    }
    public String getLastName() {
        return this.lastName;
    }
    public int getID() {
        return this.wustlID;
    }
    public String toString(){
        return this.firstName + " " + this.lastName + " " + this.wustlID;
    }

    public LinkedList<Course> getWaitlistedCourses() {
        return this.waitlistedCourses;
    }
    public ErrorCodes addWaitlistCourse(Course waitlistCourse) {
    	if (this.waitlistedCourses.size() + this.registeredCourses.size() == 7) {
//    		System.out.println("Error: Cannot exceed 21 total credits");
    		return ErrorCodes.ERROR;
    	}
    	else {
    		if (this.registeredCourses.contains(waitlistCourse)) {
//    			System.out.println("Error: Student is already registered for this course. Remove course from registered courses first");
    			return ErrorCodes.ERROR;
    		}
    		else {
    			if (!this.waitlistedCourses.contains(waitlistCourse)) {
                    this.waitlistedCourses.add(waitlistCourse);
                }
        		else {
//        			System.out.println("Course already exists in the waitlisted courses");
        			return ErrorCodes.ERROR;
        		}
    		}
    	}
    	return ErrorCodes.SUCCESS;
    }
    public ErrorCodes removeWaitlistCourse(Course waitlistCourse) {
        if (this.waitlistedCourses.contains(waitlistCourse)) {
            this.waitlistedCourses.remove(waitlistCourse);
        }
        else {
//            System.out.println("Error: Course does not exist in student's waitlisted courses");
        	return ErrorCodes.ERROR;
        }
        return ErrorCodes.SUCCESS;
    }
    
    public void displayWaitListCourses() {
        for (int i=0; i<this.waitlistedCourses.size(); i++) {
            System.out.println(this.waitlistedCourses.get(i));
        }
    }

    public LinkedList<Course> getRegisteredCourses() {
        return this.registeredCourses;
    }
    
    public ErrorCodes addRegisterCourse(Course registerCourse) {
    	if (this.waitlistedCourses.size() + this.registeredCourses.size() == 7) {
//    		System.out.println("Error: Cannot exceed 21 total credits");
    		return ErrorCodes.ERROR;
    	}
    	else {
    		if (this.waitlistedCourses.contains(registerCourse)) {
//    			System.out.println("Error: Student is already waitlisted for this course");
    			return ErrorCodes.ERROR;
    		}
    		else {
    			if (!this.registeredCourses.contains(registerCourse)) {
	                this.registeredCourses.add(registerCourse);
	            }
	    		else {
//	    			System.out.println("Course already exists in the registered courses");
	    			return ErrorCodes.ERROR;
	    		}
    		}
    	}
    	return ErrorCodes.SUCCESS;
    }
    public ErrorCodes removeRegisterCourse(Course registerCourse) {
        if (this.registeredCourses.contains(registerCourse)) {
            this.registeredCourses.remove(registerCourse);
        }
        else {
            System.out.println("Error: Course does not exist in student's registered courses");
            return ErrorCodes.ERROR;
        }
        return ErrorCodes.SUCCESS;
    }
    public void displayRegisterCourses() {
        for (int i=0; i<this.registeredCourses.size(); i++) {
            System.out.println(this.registeredCourses.get(i));
        }
    }

}
