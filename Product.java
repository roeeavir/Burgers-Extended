
public abstract class Product implements ProductFunction {
	// Variables
	protected String name;
	protected Number price;

	public Product(String name, Number price) throws PriceException {
		this.name = name;
		setPrice(price);
	}

	// A method of differentiating between whole number prices and rational numbers.
	public void setPrice(Number price) throws PriceException {
		if (price.doubleValue() < 0) {
			throw new PriceException();
		}
		if (price.doubleValue() - price.intValue() == 0) {
			this.price = price.intValue();
		} else {
			this.price = price.doubleValue();
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;

	}

	public Number getPrice() {
		return price;
	}

	// An override method of comparison between two products.
	@Override
	public int compareTo(Product p) {
		if (p == null) {
			return 1;
		}
		if (this.name.equals(p.getName())) {
			if (this.getPrice() == p.getPrice()) {
				return 0;
			} else if (this.getPrice().doubleValue() - p.getPrice().doubleValue() > 0) {
				return 1;
			} else if (this.getPrice().doubleValue() - p.getPrice().doubleValue() < 0) {
				return -1;
			}
		} else if (this.name.compareTo(p.getName()) > 0) {
			return this.name.compareTo(p.getName());
		}
		return -1;
	}

	// A method of cloning with an exception support.
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();

	}

}
