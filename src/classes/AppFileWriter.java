package classes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class AppFileWriter {
//	private String studentFilePath = "src/textfiles/test_students.txt";
//	private String courseFilePath = "src/textfiles/test_courses.txt";
	private File studentFile, courseFile;
	
	public AppFileWriter(String studentFilePath, String courseFilePath) {
		studentFile = new File(studentFilePath);
		courseFile = new File(courseFilePath);
	}
	/**
	 * Writes to the students file with the updated student info. This method assumes the student
	 * already exists in the file.
	 * @param student whose info should be saved
	 * @return true if data is successfully saved, false otherwise
	 */
	public boolean writeStudentData(Student s) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(studentFile));
			String[] contents = returnFileContentsStudents(studentFile, s.getID());
			String oldData = contents[0];
			String newData = constructFileStringStudent(s);
			oldData.replace(contents[1], null);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	/**
	 * Helper method that creates a string to be written to the students file.
	 * @param student
	 * @return constructed string 
	 */
	public String constructFileStringStudent(Student s) {
		String result = s.getFirstName() + " " + s.getLastName() + " " + s.getID() + " ";
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
	/**
	 * Helper method that reads all the lines of the file and returns an array containing all
	 * the contents of the file and the line that needs to be replaced with new data.
	 * @param file
	 * @return all the contents of the file in a string
	 * @throws IOException
	 */
	private String[] returnFileContentsStudents(File file, int studentId) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line;
		String lines = "";
		String[] contents = new String[2];	// [0]: lines, [1]: the line to be replaced
		while ((line = br.readLine()) != null) {
			String[] data = line.split("\\s");
			if (data[2].equals(String.valueOf(studentId))) contents[1] = line;
			lines += line + "\n";
		}
		br.close();
		contents[0] = lines;
		return contents;
	}
	
	
	
//	---------------------------------- MAIN -----------------------------------------

	public static void main(String[] args) throws IOException {
		String studentPath = "src/textfiles/test_students.txt";
		String coursePath = "src/textfiles/test_courses.txt";
		AppFileWriter appWriter = new AppFileWriter(studentPath, coursePath);
		AppFileProcessor fp = new AppFileProcessor(studentPath, coursePath);
		Student jack = fp.createStudent("234567");
		String[] contents = appWriter.returnFileContentsStudents(new File(studentPath), jack.getID());
		String newData = appWriter.constructFileStringStudent(jack);
		System.out.println(newData);
		System.out.println(contents[1]);
		System.out.println(contents[0]);
	}

}
