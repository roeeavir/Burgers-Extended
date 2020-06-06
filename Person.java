
public class Person {

	// Variables
	private String name;

	// Getters & setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object obj) {// A method for comparing between two employees.
		if (!(getClass().equals(obj.getClass()))) {
			return false;
		} else if (obj instanceof Owner) {
			if ((((Owner) this).getBonus() + ((Owner) this).getSalary()) != (((Owner) obj).getBonus()
					+ ((Owner) obj).getSalary())) {
				return false;
			}
		} else if (obj instanceof Manager) {
			if ((((Manager) this).getBonus() + ((Manager) this).getSalary()) != (((Manager) obj).getBonus()
					+ ((Manager) obj).getSalary())) {
				return false;
			}
		} else if (obj instanceof Worker) {
			if (((Worker) this).getSalary() != ((Worker) obj).getSalary()) {
				return false;
			}
		}
		return true;
	}

}
