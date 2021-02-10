package bn026Work;

public class Lecturer extends Person{
	private String subject;
	private String staffId;
	private int year;
	
	public Lecturer() {
		super();
		subject = "unknown";
		staffId = "L00...";
		year = 2;
	}
	
	public Lecturer(String name, int age, String subject, String staffId, int year) {
		super(name, age);
		if(subject == null || subject.equals(""))
			throw new RuntimeException("subject must have a value");
		else
			this.subject = subject;
		if(staffId == null || staffId.equals(""))
			throw new RuntimeException("staff id must have a value");
		else
			this.staffId = staffId;
		if(year <1 || year >4)
			throw new RuntimeException("Teaching year must be between 1 and 4");
		else
			this.year = year;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		if(subject == null || subject.equals(""))
			throw new RuntimeException("subject must have a value");
		else
			this.subject = subject;
	}
	
	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		if(staffId == null || staffId.equals(""))
			throw new RuntimeException("staff id must have a value");
		else
			this.staffId = staffId;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		if(year <1 || year >4)
			throw new RuntimeException("Teaching year must be between 1 and 4");
		else
			this.year = year;
	}

	@Override
	public String toString() {
		return name + " [Age=" + age + ", Subject Taught =" 
						+ subject+ ", ID="+ staffId + ", Teaching Year=" + year + "]";
	}
}

