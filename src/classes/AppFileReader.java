package classes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
/**
 * This should handle reading files and returning Student or Course object.
 * @author manzura
 *
 */

public class AppFileReader {
	private File file;
	private FileReader fileReader;
	
	public AppFileReader(String filePath) throws FileNotFoundException {
		file = new File(filePath);
		fileReader = new FileReader(file);
	}
	// TODO: need to implement this...
	public Student readStudent() {
		return null;
	}
	
	// return: a student object referring to an ID provided as argument. null if student could not be created.
    // input: a String corresponding to a students ID.
    public Student loginStudent(String studentID) {
    	if (file.canRead()) {
    		
    	}
    	return null;
    }
    
    private Student parseStudent(String id) {
    	
    	try {
    		BufferedReader br = new BufferedReader(fileReader);
    		String line;
        	while ((line = br.readLine()) != null) {
    	    	String words[] = line.split("\\s");
    	    	if (words[2].equals(id)) {
    	    		Menu.displayLoginMessage();
    	    		return createStudent(words, id);
    	    	}
    	    }
		    
		} catch (Exception e) {
			return null;
		}
		return null;
    }
    
    private Student createStudent(String words[], String studentID) {
    	String firstName = words[0];
		String lastName = words[1];
    	Student student = new Student(firstName, lastName, Integer.valueOf(studentID));
    	String registeredCourses = words[3];
//    	AddRegisteredCoursesToStudentObject(registeredCourses, student);
    	String waitlistedCourses = words[4];
//    	AddWaitlistedCoursesToStudentObject(waitlistedCourses, student);
    	return student;
    }

}
