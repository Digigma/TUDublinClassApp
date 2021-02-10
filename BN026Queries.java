/**
 * 
 */
package bn026Work;

/**
 * @author digig
 *
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;


public class BN026Queries implements AutoCloseable{
	
	private String url;
	private String user;
	private String password;
	private Connection conn;	
		
	public BN026Queries() throws SQLException, IOException{
		conn = getConnection();
	}
	
	private void loadProps() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(".\\src\\bn026.properties"));
		Properties bn026Props = new Properties();
		bn026Props.load(reader);
		this.url = bn026Props.getProperty("url");
		this.user = bn026Props.getProperty("user");
		this.password = bn026Props.getProperty("password");
	}
	
	// only looking at mysql connection
	private Connection getConnection() throws SQLException, IOException{
		loadProps();
		return DriverManager.getConnection(url, user, password);
	}
		
	public ArrayList<Student> getAllStudents() throws SQLException{
		PreparedStatement getAllStudents = conn.prepareStatement("SELECT * FROM student");
		ArrayList<Student> students = new ArrayList<>();
		ResultSet result = getAllStudents.executeQuery();
		while(result.next()) {
			String studentNo = result.getString(1);
			int age = result.getInt(2);
			String name = result.getString(3);
			int yearOfStudy = result.getInt(4);
			students.add(new Student(name, age, studentNo, yearOfStudy));
		}
		return students;
	}
	
	public int updateStudentAge(int age, String studentNo) throws SQLException{
		PreparedStatement updateStudentAge = conn.prepareStatement("UPDATE student SET age = ? WHERE studentNo = ?");
		updateStudentAge.setInt(1, age);
		updateStudentAge.setString(2, studentNo);		
		return updateStudentAge.executeUpdate();
	}
	
	public int deleteStudent(String studentNo) throws SQLException{
		PreparedStatement deleteStudent = conn.prepareStatement("DELETE FROM student WHERE studentNo = ?");
		deleteStudent.setString(1, studentNo);
		return deleteStudent.executeUpdate();
	}
	
	public int insertStudent(String studentNo, int age, String name, int yearOfStudy) 
			throws SQLException{
		PreparedStatement insertStudent = conn.prepareStatement("INSERT INTO student(studentNo, age, studentName, yearOfStudy)"
				+ " VALUES (?, ?, ?, ?)");
		insertStudent.setString(1, studentNo);
		insertStudent.setInt(2, age);
		insertStudent.setString(3, name);
		insertStudent.setInt(4, yearOfStudy);		
		return insertStudent.executeUpdate();
	}
	
	public ArrayList<bn026Work.Lecturer> getAllLecturers() throws SQLException{
		PreparedStatement getAllLecturers = conn.prepareStatement("SELECT * FROM lecturer");
		ArrayList<Lecturer> lecturers = new ArrayList<>();
		ResultSet result = getAllLecturers.executeQuery();
		while(result.next()) {
			String staffID = result.getString(1);
			int age = result.getInt(2);
			String lecturerName = result.getString(3);
			String subjectTaught = result.getString(4);
			lecturers.add(new Lecturer(lecturerName, age, subjectTaught, staffID, 2));
		}
		return lecturers;
	}
	
	public int updateLecturersAge(int age, String staffID) throws SQLException{
		PreparedStatement updateLecturersAge = conn.prepareStatement("UPDATE lecturer SET age = ? WHERE staffID = ?");
		updateLecturersAge.setInt(1, age);
		updateLecturersAge.setString(2, staffID);		
		return updateLecturersAge.executeUpdate();
	}
	
	public int deleteLecturer(String staffID) throws SQLException{
		PreparedStatement deleteLecturer = conn.prepareStatement("DELETE FROM lecturer WHERE staffID = ?");
		deleteLecturer.setString(1, staffID);
		return deleteLecturer.executeUpdate();
	}
	
	public int insertLecturer(String staffID, int age, String lecturerName, String subjectTaught) 
			throws SQLException{
		PreparedStatement insertLecturer = conn.prepareStatement("INSERT INTO lecturer(staffID, age, lecturerName, subjectTaught)"
				+ " VALUES (?, ?, ?, ?)");
		insertLecturer.setString(1, staffID);
		insertLecturer.setInt(2, age);
		insertLecturer.setString(3, lecturerName);
		insertLecturer.setString(4, subjectTaught);		
		return insertLecturer.executeUpdate();
	}
	
	@Override
	public void close() throws SQLException {
		conn.close();
	}
	
}

