package classes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.io.FileWriter;


public class SaveHandler {
	private Student student;
	private Course course;
	private static String courseTextPath = "src/textfiles/courses.txt";
	private String studentTextPath = "src/textfiles/students.txt";
	
	public SaveHandler(Student student, Course course) {
		this.student = student;
		this.course = course;
	}
	
	public void saveRegistration() throws IOException {
		// this method saves the current registration status of the student in the student file.
		LinkedList<String[]> studentList = bufferedReaderSaveRegistration(studentTextPath);
		
		BufferedWriter sbw = new BufferedWriter(new FileWriter(studentTextPath));
		for (int i = 0; i <studentList.size(); i++) {
			String words[] = studentList.get(i);
			String studentOnThisLine = words[0] + " " + words[1] + " " + words[2];
			for (int j = 0; j < words.length; j++) {
				if ((studentOnThisLine.equals(student.toString())) && (j == 3)) {
					sbw.write(words[3] + "-" + course.getCourseCode());
				} else {
					sbw.write(words[j]);
				}
				sbw.write(" ");
			}
			sbw.write("\n");
		}
		sbw.close();
		
		
		LinkedList<String[]> courseList = bufferedReaderSaveRegistration(courseTextPath);
		
		BufferedWriter cbw = new BufferedWriter(new FileWriter(courseTextPath));
		for (int i = 0; i < courseList.size(); i++) {
			String words[] = courseList.get(i);
			for (int j = 0; j < words.length; j++) {
				if ((words[0].equals(course.getCourseCode())) && (j == 4)) {
					cbw.write(String.valueOf(course.getSeats()));
				} else {
					cbw.write(words[j]);
				}
				cbw.write(" ");
			}
			cbw.write("\n");
		}
		cbw.close();
	}
	
	public LinkedList<String[]> bufferedReaderSaveRegistration(String textpath) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(textpath));
		String line;
		LinkedList<String[]> list = new LinkedList<>();
		while ((line = br.readLine()) != null) {
			String words[] = line.split("\\s");
			list.add(words);
		}
		br.close();
		return list;
	}
}
