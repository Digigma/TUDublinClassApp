package bn026Work;

public class Person {
	private static int counter;
	protected String name;
	protected int age;
	
	public Person(String n, int a) {
		if(n==null || n.equals(""))
			throw new RuntimeException("Name cannot be null or empty!");
		else
			name = n;
		if(a < 0 || a > 120)
			throw new RuntimeException("Age cannot be negative or above 120");
		else
			age = a;
		
		counter++;
	}
	
	public Person() {
		this("name...", 0);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		if(name ==null || name.equals(""))
			throw new RuntimeException("Name cannot be null or empty!");
		else
			this.name = name;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		if(age < 0 || age > 120)
			throw new RuntimeException("Age cannot be negative or above 120!");
		else
			this.age = age;
	}

	public static int getCounter() {
		return counter;
	}
	
	@Override 
	public String toString() {
		return "Person: [name= " + name + ", age= " + age + "]";
	}
	
}

