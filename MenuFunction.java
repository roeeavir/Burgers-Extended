import java.util.ArrayList;

public interface MenuFunction<T extends Product> {

	public void createProduct(ArrayList<Product> list, T product);

	public void deleteProduct(ArrayList<Product> list, int index);

	public void add(T product);

	public void addAll(ArrayList<Product> list1, ArrayList<Product> list2);

	public void remove(int index);

	public void removeAll();

	public void print(ArrayList<? extends Product> list);

	public void sort(ArrayList<? extends Product> list) throws CloneNotSupportedException;

	public int search(ArrayList<? extends Product> list, String name, double price);

}
