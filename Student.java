package bn026Work;

public class Student extends Person{
	private String studentNo;
	private int yearOfStudy;
	
	public Student() {
		super();
		studentNo = "B00...";
		yearOfStudy = 1;
	}
	
	public Student(String name, int age, String studentNo, int yearOfStudy) {
		super(name, age);
		if(studentNo == null || studentNo.equals(""))
			throw new RuntimeException("Student number must be provided a value");
		else
			this.studentNo = studentNo;
		if(yearOfStudy < 1 || yearOfStudy > 4)
			throw new RuntimeException("Year of study must be between 1 and 4");
		else
			this.yearOfStudy = yearOfStudy;		
	}
	
	public String getStudentNo() {
		return studentNo;
	}

	public void setStudentNo(String studentNo) {
		if(studentNo == null || studentNo.equals(""))
			throw new RuntimeException("Student number must be provided a value");
		else
			this.studentNo = studentNo;
	}

	public int getYearOfStudy() {
		return yearOfStudy;
	}

	public void setYearOfStudy(int yearOfStudy) {
		if(yearOfStudy < 1 || yearOfStudy > 4)
			throw new RuntimeException("Year of study must be between 1 and 4");
		else
			this.yearOfStudy = yearOfStudy;
	}	
	
	
	@Override
	public String toString() {
		return name + " [Age=" + age + ", studentNo=" + studentNo + ", yearOfStudy=" + yearOfStudy + "]";
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
