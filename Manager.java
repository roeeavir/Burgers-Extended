
public class Manager extends Worker {

	// Variables
	private int bonus;

	// Constructor
	public Manager(int salary, int bonus, String userName, String password) {
		super(salary, userName, password);
		this.bonus = bonus;
	}

	// Getters & setters

	public int getBonus() {
		return bonus;
	}

	public void setBonus(int bonus) throws BonusException {
		this.bonus = bonus;
	}

	// toString
	@Override
	public String toString() {
		return "Manager : " + getName() + ", " + getSalary() + " + " + getBonus();
	}
}
