
public class Hamburger extends Product {
	// Variables
	protected String ingridients;

	protected enum Doneness {
		RARE, MEDIUM, MEDIUMWELL, WELLDONE
	};

	// Constructor
	public Hamburger(String doneness, String ingridients, String name, Number price) throws PriceException {
		super(name, price);
		setIngridients(ingridients);
	}

	public String getIngridients() {
		return ingridients;
	}

	public void setIngridients(String ingridients) {
		this.ingridients = ingridients;
	}

	@Override
	public String toString() {
		return "\tHamburger\t " + getName() + "\t" + getPrice() + "\t" + this.ingridients;
	}

}
