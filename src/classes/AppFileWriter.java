package classes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
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
			String[] contents = returnFileContentsAndLine(studentFile, String.valueOf(s.getID()));
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
	 * Helper method that creates a string to be written to the students file.
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
	/**
	 * Helper method that reads all the lines of the file and returns an array containing all
	 * the contents of the file and the line that needs to be replaced with new data.
	 * @param file
	 * @return array: [0]->file contents, [1]->line to be replaced
	 * @throws IOException
	 */
	private String[] returnFileContentsAndLine(File file, String firstString) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line;
		String lines = "";
		String[] contents = new String[2];	// [0]: lines, [1]: the line to be replaced
		while ((line = br.readLine()) != null) {
			String[] data = line.split("\\s");
			if (data[0].equals(firstString)) contents[1] = line;
			lines += line + "\n";
		}
		br.close();
		contents[0] = lines;
		return contents;
	}
	
	
//	------------------------- COURSE METHODS START -----------------------------------------

	public boolean saveCourseData(Course c) {
		try {
			String[] contents = returnFileContentsAndLine(courseFile, String.valueOf(c.getCourseCode()));
			String data = contents[0];
			String newData = constructFileStringCourse(c);
			BufferedWriter bw = new BufferedWriter(new FileWriter(courseFile));
			data = data.replace(contents[1], newData);
			bw.write(data);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public String constructFileStringCourse(Course c) {
		return c.getCourseCode() + " " + c.getName() + " " + c.getStartTime() + " " + c.getEndTime() + " " + c.getSeats() + " " + c.getProfessorName() + " " + c.getExams();
	}
	
//	---------------------------------- MAIN -----------------------------------------

	public static void main(String[] args) throws IOException {
		String studentPath = "src/textfiles/test_students.txt";
		String coursePath = "src/textfiles/test_courses.txt";
		AppFileWriter appWriter = new AppFileWriter(studentPath, coursePath);
		AppFileProcessor fp = new AppFileProcessor(studentPath, coursePath);
//		Student dor = fp.createStudent("234567");
		Course c = fp.findCourse("CSE204");
		Course c2 = fp.findCourse("CSE347");
//		Course c2 = fp.findCourse("CSE330");
//		dor.addRegisterCourse(c);
//		ErrorCodes error = dor.addWaitlistCourse(c2);
//		System.out.println(error);
//		appWriter.writeStudentData(dor);
//		c.setSeats(c.getSeats() - 50);
//		appWriter.saveCourseData(c);
		c.setSeats(c.getSeats()-40);
		c2.setSeats(c2.getSeats()-10);
		appWriter.saveCourseData(c);
		appWriter.saveCourseData(c2);

	}

}
