import java.util.ArrayList;
import java.util.Collections;

public class Menu<T extends Product> implements MenuFunction<T> {

	// Variables
	private ArrayList<T> products = new ArrayList<T>();
	private double totalPrice;

	public ArrayList<T> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<T> products) {
		this.products = products;
	}

	public <E> double sumRational(E num1, E num2) {
		return (double) num1 + (double) num2;
	}

	@Override
	public void createProduct(ArrayList<Product> list, T product) {
		list.add(product);

	}

	@Override
	public void deleteProduct(ArrayList<Product> list, int index) {
		list.remove(index);
	}

	@Override
	public void add(T product) {
		products.add(product);

	}

	@Override
	public void addAll(ArrayList<Product> list1, ArrayList<Product> list2) {
		list1.addAll(list2);

	}

	@Override
	public void remove(int index) {
		products.remove(index);

	}

	@Override
	public void removeAll() {
		products.removeAll(products);
		System.out.println("The whole menu was wiped clean!");
	}

	// A method for printing an existing list of products.
	@Override
	public void print(ArrayList<? extends Product> list) {
		if (list.isEmpty()) {
			System.out.println("No products at the moment");
		}
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}

	}

	@Override
	public void sort(ArrayList<? extends Product> list) {
		Collections.sort(list);
	}

	// A method for binary searching a product.
	@Override
	public int search(ArrayList<? extends Product> list, String name, double price) {
		int counter = 0;
		int index = -1;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getName().equals(name)) {
				counter++;
			}
			if (counter > 1) {
				break;
			}
		}
		int high = list.size() - 1;
		int low = 0;
		int mid = -1;
		int temp = -1;
		double closestPrice = 0; // Sorting by the product closest to the wanted price.
		while (high >= low) {
			temp = mid;
			mid = low + (high - low) / 2;
			if (temp == mid) {
				break;
			}
			if (list.get(mid).getName().equals(name)) {
				if (counter > 1) {		
					if (list.get(mid).getPrice().doubleValue() == price && list.get(mid).getName().equals(name)) {
						return mid;
					} else if (list.get(mid).getPrice().doubleValue() > price) {
						high = mid - 1;
					} else if (list.get(mid).getPrice().doubleValue() < price) {
						low = mid + 1;
					}
					if (closestPrice == 0 || closestPrice >= Math.abs(sumRational(list.get(mid).getPrice().doubleValue(), -price))) {
						closestPrice = Math.abs(sumRational(list.get(mid).getPrice().doubleValue(), -price));
						index = mid;
					}
				} else {
					return mid;
				}
			} else if (list.get(mid).getName().compareTo(name) > 0) {
				high = mid - 1;
			} else if (list.get(mid).getName().compareTo(name) < 0) {
				low = mid + 1;
			}
		}
		return index;
	}

	// A method for calculating the total price of all the products on the menu.
	public void setTotalPrice() {
		double sum = 0;
		for (int i = 0; i < products.size(); i++) {
			sum += products.get(i).getPrice().doubleValue();
		}
		this.totalPrice = sum;
	}

	public double getTotalPrice() {
		setTotalPrice();
		return totalPrice;
	}

}
