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
    
    /**
     * 
     * @param waitlistCourse: Course to be added to the waitlist
     * @return An enum indicating if the addition was successful.
     */
    public ErrorCodes addWaitlistCourse(Course waitlistCourse) {
    	if (this.waitlistedCourses.size() + this.registeredCourses.size() == 7) {
    		return ErrorCodes.ERROR;
    	} else {
    		if (this.registeredCourses.contains(waitlistCourse)) {;
    			return ErrorCodes.ERROR;
    		} else {
    			if (!this.waitlistedCourses.contains(waitlistCourse)) {
                    this.waitlistedCourses.add(waitlistCourse);
                } else {
        			return ErrorCodes.ERROR;
        		}
    		}
    	}
    	return ErrorCodes.SUCCESS;
    }
    
    /**
     * 
     * @param waitlistCourse: Course to be removed from the waitlist
     * @return An enum indicating if the removal was successful.
     */
    public ErrorCodes removeWaitlistCourse(Course waitlistCourse) {
        if (this.waitlistedCourses.contains(waitlistCourse)) {
            this.waitlistedCourses.remove(waitlistCourse);
        } else {
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
    
    /**
     * 
     * @param registerCourse: Add a Course to the Student's register list.
     * @return An enum indicating whether the action was successful.
     */
    public ErrorCodes addRegisterCourse(Course registerCourse) {
    	if (this.waitlistedCourses.size() + this.registeredCourses.size() == 7) {
    		return ErrorCodes.ERROR;
    	} else {
    		if (this.waitlistedCourses.contains(registerCourse)) {
    			return ErrorCodes.ERROR;
    		} else {
    			if (!this.registeredCourses.contains(registerCourse)) {
	                this.registeredCourses.add(registerCourse);
	            } else {
	    			return ErrorCodes.ERROR;
	    		}
    		}
    	}
    	return ErrorCodes.SUCCESS;
    }
    
    /**
     * 
     * @param registerCourse: Remove a Course from the Student's register list.
     * @return	An enum indicating whether the action was successful.
     */
    public ErrorCodes removeRegisterCourse(Course registerCourse) {
        if (this.registeredCourses.contains(registerCourse)) {
            this.registeredCourses.remove(registerCourse);
        } else {
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
