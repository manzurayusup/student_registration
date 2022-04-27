package classes;

/**
 * Enumerations indicating success or error.
 * @author manzura, irtaza, yab, khushi
 *
 */
public enum ErrorCodes {
	SUCCESS("Action sucessful."),
	SUCCESS_REGISTER("You've been registered!."),
	ADDED_TO_WAITLIST("You've been waitlisted for this course."),
	ERROR_MAX_CREDITS("You've exceeded your maximum number of allowed credits."),
	ERROR_ALREADY_ENROLLED("You're already enrolled in this course."),
	ERROR_ALREADY_WAITLISTED("You're already waitlisted for this course."),
	COURSE_DOES_NOT_EXIST("Course does not exist."),
	ERROR("An error occurred.");
	
	private String message;
	
	ErrorCodes(String msg) {
		this.message = msg;
	}
	
	public String getMessage() {
		return this.message;
	}
	
}
