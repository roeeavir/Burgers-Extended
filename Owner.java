
public class Owner extends Manager {
	// Variables
	public static final int BASE = 20_000;

	// Constructor
	public Owner(int salary, int bonus, String userName, String password) throws BonusException {
		super(salary, bonus, userName, password);
		this.setBonus(bonus);
	}

	// toString
	@Override
	public String toString() {
		return "Owner : " + getName() + ", " + getSalary() + " + " + getBonus() + " + " + BASE;

	}

	// A method to keep in check the owners bonus.
	@Override
	public void setBonus(int bonus) throws BonusException {
		if (bonus > 10000) {
			throw new BonusException();
		} else {

		}

	}

}
