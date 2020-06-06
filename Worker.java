
public class Worker extends Person {

	// Variables
	private int salary;
	private String userName;
	private String password;

	// Constractor
	public Worker(int salary, String userName, String password) {
		super();
		setSalary(salary);
		setPassword(password);
		setUserName(userName);
	}

	// Getters & setters

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	// toString
	@Override
	public String toString() {
		return "Worker : " + getName() + ", " + getSalary();
	}
}
