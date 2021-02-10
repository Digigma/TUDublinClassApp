package bn026Work;

import java.util.ArrayList;

public class BN026 {
	
	private ArrayList<Student> students;
	private ArrayList<Lecturer> lecturers;
	
	public BN026() {
		students = new ArrayList<>();
		lecturers = new ArrayList<>();
	}
	
	public void setStudents(ArrayList<Student> students) {
		this.students = students;
	}

	public ArrayList<Student> getStudents() {
		return students;
	}

	public ArrayList<Lecturer> getLecturers() {
		return lecturers;
	}

	public void setLecturers(ArrayList<Lecturer> lecturers) {
		this.lecturers = lecturers;
	}
	
	public void addStudent(Student s) {
		if(students.size() >= 20 )
			throw new RuntimeException("The class is full");
		else
			students.add(s);
	}
	
	public void addLecturer(Lecturer l) {
		if(lecturers.size() >= 3)
			throw new RuntimeException("All subjects already have lecturers");
		else
			lecturers.add(l);
	}
	
	public Student removeStudentByID(String id) {
		if(students.size() <= 3) 
			throw new RuntimeException("The class must have at least 3 students"
					+ ", otherwise is not profitable");
		for(int i = 0; i < students.size(); i++) {
			Student s = students.get(i);
			if(s.getStudentNo().equals(id)) {
				students.remove(s);		
				return s;
			}
		}
		return null;
	}
	
	public Lecturer removeLecturerByID(String id) {
		if(lecturers.size() <= 1) 
			throw new RuntimeException("The class must have at least 1 lecturer"
					+ ", otherwise there would be no one to teach");
		for(int i = 0; i < lecturers.size(); i++) {
			Lecturer l = lecturers.get(i);
			if(l.getStaffId().equals(id)) {
				lecturers.remove(l);
				return l;
			}
		}
		return null;
	}
	
	@Override
	public String toString() {
		return "BN026 has the following students: " + students+
				" and the following lecturers: " + lecturers;
	}
}

