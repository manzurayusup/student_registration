package classes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
/**
 * AppFileWriter class has methods that writes to the text files with the updated
 * course and student info. 
 * @author manzura
 *
 */
public class AppFileWriter {
	private String defaultStudentFilePath = "src/textfiles/test_students.txt";
	private String defaultCourseFilePath = "src/textfiles/test_courses.txt";
	private File studentFile, courseFile;
	
	public AppFileWriter(String studentFilePath, String courseFilePath) {
		studentFile = new File(studentFilePath);
		courseFile = new File(courseFilePath);
	}
	
	public AppFileWriter() {
		studentFile = new File(defaultStudentFilePath);
		courseFile = new File(defaultCourseFilePath);
	}
	
	/**
	 * Writes to the students file with the updated student info. This method assumes the student
	 * already exists in the file.
	 * @param student whose info should be saved
	 * @return true if data is successfully saved, false otherwise
	 */
	public boolean saveStudentData(Student s) {
		
		try {
			String[] contents = getFileContentsAndLine(studentFile, String.valueOf(s.getID()));
			if (contents == null) return false;
			String data = contents[0];
			String newData = constructFileStringStudent(s);
			BufferedWriter bw = new BufferedWriter(new FileWriter(studentFile));
			data = data.replace(contents[1], newData);
			bw.write(data);
			bw.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Helper method that creates a string to be written to the students file. This method expects
	 * the passed student to be a valid Student object.
	 * @param student
	 * @return constructed string 
	 */
	public String constructFileStringStudent(Student s) {
		String result = s.getID() + " " + s.getFirstName() + " " + s.getLastName() + " ";
		LinkedList<Course> registered = s.getRegisteredCourses();
		LinkedList<Course> waitlisted = s.getWaitlistedCourses();
		
		for (int i = 0; i < registered.size(); i++) {
			result += registered.get(i).getCourseCode();
			if (i == registered.size() - 1) result += " ";
			else result += "-";
		}
		for (int i = 0; i < waitlisted.size(); i++) {
			result += waitlisted.get(i).getCourseCode();
			if (i == waitlisted.size() - 1) result += " ";
			else result += "-";
		}
		return result;
	}	
	
	
//	--------------------------- COURSE METHODS  ------------------------------------
	/**
	 * Writes to the courses file with the updated course data.
	 * @param course to be updated
	 * @return true if successfully written, false otherwise
	 */
	public boolean saveCourseData(Course c) {
		try {
			String[] contents = getFileContentsAndLine(courseFile, String.valueOf(c.getCourseCode()));
			if (contents == null) return false;
			String data = contents[0];
			String newData = constructFileStringCourse(c);
			BufferedWriter bw = new BufferedWriter(new FileWriter(courseFile));
			data = data.replace(contents[1], newData);
			bw.write(data);
			bw.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Helper method that creates a string to be written to the courses file. This method expects the 
	 * passed course to be a valid Course object.
	 * @param course 
	 * @return constructed line
	 */
	public String constructFileStringCourse(Course c) {
		return c.getCourseCode() + " " + c.getName() + " " + c.getStartTime() + " " + c.getEndTime() + " " + c.getSeats() + " " + c.getProfessorName() + " " + c.getExams();
	}
	
	/**
	 * Helper method that reads all the lines of the file and returns an array containing all
	 * the contents of the file and the line that needs to be replaced with new data.
	 * @param file
	 * @return array: [0]->file contents, [1]->line to be replaced, null if no line with 
	 * the matching first string is found
	 * @throws IOException
	 */
	private String[] getFileContentsAndLine(File file, String firstString) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line; 
		String lines = "";
		String[] contents = new String[2];	
		boolean foundMatchingLine = false;
		while ((line = br.readLine()) != null) {
			if (line.split("\\s")[0].equals(firstString)) {
				contents[1] = line;
				foundMatchingLine = true;
			}
			lines += line + "\n";
		}
		br.close();
		if (foundMatchingLine) {
			contents[0] = lines;
			return contents;
		} else {
			return null;
		}
		
	}


}
