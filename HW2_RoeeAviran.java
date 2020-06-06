
import java.util.ArrayList;
import java.util.Scanner;

/*Name: Roee Aviran
ID: 316492644 */

public class HW2_RoeeAviran {

	public static void main(String[] args) throws CloneNotSupportedException {
		Scanner in = new Scanner(System.in);// Input scanner.
		char type;
		// A try and catch sequence for determining if and owners bonus is too high, and
		// ends the program if it is.
		try {
			ArrayList<Worker> list = insertIntoList(in);
			System.out.println("The list content:\n");
			printList(list);// Printing the list of employees.

			// using the method that prints the costs of all bonuses
			System.out.println("Cost of all bonuses: " + costOfAllBonuses(list) + "\n");

			// A loop for the user to check each group of employees total costs, as many
			// times as the user wants.
			do {
				System.out.println("Please type a character to get total cost by type:");
				type = pickAType(in);
				if ((type != 'f' && type != 'F') && (type == 'W' || type == 'w') || type == 'M' || type == 'm'
						|| type == 'O' || type == 'o') {
					System.out.println("Cost = " + costByType(in, list, type));
				}
			} while (type != 'f' && type != 'F');

			int first, second;
			// A loop for comparing any two employees, as many times as the user wants.
			do {
				System.out.println("Please enter the first index in the list (-1 to finish):");
				first = getIndexes(in, list.size());
				if (first == -1) {
					break;
				}
				System.out.println("Please enter the second index in the list: ");
				second = getIndexes(in, list.size());
				if (compare(list, first, second)) {
					System.out.println("EQUALS!");
				} else {
					System.out.println("NOT EQUALS!");
				}
			} while (first != -1);
			if (userLogIn(in, list)) {
				createMenu(in);
			}

		} catch (BonusException e) {// Ends the program in case of an owner inputing a bonus exceeding 10000.
			System.out.println(e.getMessage());
		}

		System.out.println("Program ends now...");

		in.close();
	}

	// A method for inputing the first character for the worker from the user.
	public static char pickAType(Scanner in) {

		char type;
		System.out.println("[M/m], [W/w], [O/o]. [F/f] to finish:");
		type = in.next().charAt(0);
		return type;

	}

	// A method for inserting workers into a list, and returning said list.
	public static ArrayList<Worker> insertIntoList(Scanner in) throws BonusException {
		ArrayList<Worker> list = new ArrayList<Worker>();
		char type;
		int salary = 0;
		String userName = "";
		String password = "";
		String name = null;

		// A loop to keep adding employees as long as the user wants.
		do {
			type = pickAType(in);
			if ((type != 'f' && type != 'F') && (type == 'W' || type == 'w') || type == 'M' || type == 'm'
					|| type == 'O' || type == 'o') {
				name = nameInfo(in);
				in.nextLine();
				salary = salaryInfo(in);
				// A loop for repeating the users salary input in case of a bad salary input.
				while (salary <= -1) {
					System.out.println("Try again!");
					salary = salaryInfo(in);
				}
				userName = setUserName(in);
				password = setPassword(in);
				// Choosing between the 3 types of employees.
				if (type == 'W' || type == 'w') {
					creatWorker(in, name, salary, list, userName, password);
				} else if (type == 'M' || type == 'm') {
					creatManager(in, name, salary, list, userName, password);
				} else if (type == 'O' || type == 'o') {
					creatOwner(in, name, salary, list, userName, password);
				} else {
					System.out.println("Wrong input, please try again!");
				}
			}

		} while (type != 'f' && type != 'F');

		return list;
	}

	// A method for inserting the right variables into the owners constructor.
	public static void creatOwner(Scanner in, String name, int salary, ArrayList<Worker> list, String userName,
			String password) throws BonusException {
		System.out.println("Please enter bonus: ");
		int bonus = in.nextInt();
		Owner owner = new Owner(salary, bonus, userName, password);
		owner.setName(name);

		list.add(owner);
	}

	// A method for inserting the right variables into the managers constructor.
	public static void creatManager(Scanner in, String name, int salary, ArrayList<Worker> list, String userName,
			String password) {
		System.out.println("Please enter bonus: ");
		int bonus = in.nextInt();
		Manager manager = new Manager(salary, bonus, userName, password);
		manager.setName(name);

		list.add(manager);
	}

	// A method for inserting the right variables into the workers constructor.
	public static void creatWorker(Scanner in, String name, int salary, ArrayList<Worker> list, String userName,
			String password) {
		Worker worker = new Worker(salary, userName, password);
		worker.setName(name);

		list.add(worker);
	}

	public static String nameInfo(Scanner in) {// A method for setting the name.
		System.out.println("Please enter full name: ");
		String name = in.next();

		return name;
	}

	// A method for setting the salary and to check if it is indeed and integer.
	public static int salaryInfo(Scanner in) {
		System.out.println("Please enter salary: ");
		int salary = 0;
		String stringSalary = in.nextLine();
		salary = checkSalary(stringSalary);
		return salary;
	}

	// A method for checking if the salary is indeed an integer and if the salary is
	// o and above.
	public static int checkSalary(String stringSalary) {
		int salary = 0;
		try {
			salary = Integer.parseInt(stringSalary);
		} catch (NumberFormatException e) {
			System.out.println("Invalid type for salary, integer is needed!");
			return -1;// Returning a negative value to sign that there is a bad input.
		}
		try {
			if (salary < 0) {
				throw new NumberFormatException();
			}
		} catch (NumberFormatException e) {
			System.out.println("Salary must be at least 0!");
			return -1;// Returning a negative value to sign that there is a bad input.
		}

		return salary;
	}

	// A method that sums up all the bonuses of all the employees.
	public static int costOfAllBonuses(ArrayList<Worker> list) {
		int sum = 0;
		for (int i = 0; i < list.size(); i++) {

			if (list.get(i) instanceof Manager) {
				sum += ((Manager) list.get(i)).getBonus();

			} else if (list.get(i) instanceof Owner) {
				sum += ((Owner) list.get(i)).getBonus();
			}
		}
		return sum;
	}

	// A method that sums up all the costs of each employee type group.
	public static int costByType(Scanner in, ArrayList<Worker> list, char type) {
		int cost = 0;
		if (type == 'W' || type == 'w') {

			for (int i = 0; i < list.size(); i++) {

				if (list.get(i) instanceof Worker && !(list.get(i) instanceof Manager)

						&& !(list.get(i) instanceof Owner)) {
					cost += ((Worker) list.get(i)).getSalary();
				}
			}

			return cost;
		} else if (type == 'M' || type == 'm') {
			for (int i = 0; i < list.size(); i++) {

				if (list.get(i) instanceof Manager && !(list.get(i) instanceof Owner)) {

					cost += ((Manager) list.get(i)).getSalary() + ((Manager) list.get(i)).getBonus();
				}
			}

			return cost;
		} else if (type == 'O' || type == 'o') {

			for (int i = 0; i < list.size(); i++) {

				if (list.get(i) instanceof Owner) {

					cost += ((Owner) list.get(i)).getBonus() + ((Owner) list.get(i)).getSalary() + Owner.BASE;
				}
			}

			return cost;
		}
		System.out.println("Wrong input, please try again!");
		return 0;
	}

	// A method for comparing workers from each type by their total salary (salary +
	// bonus + base).
	public static boolean compare(ArrayList<Worker> list, int first, int second) {

		return list.get(first).equals(list.get(second));
	}

	// A method for getting the indexes (arraylist) for comparison between two
	// employees.
	public static int getIndexes(Scanner in, int size) {
		int num;
		num = in.nextInt();

		if ((num < 0 || num > size) && !(num == -1)) {

			System.out.println("Index is too high or too low!");
		}
		return num;
	}

	// A method for printing the list of all employees.
	public static void printList(ArrayList<Worker> list) {

		for (int i = 0; i < list.size(); i++) {

			System.out.println(list.get(i));
		}
		System.out.println();
	}

	// A method for setting the user name of created worker.
	public static String setUserName(Scanner in) {
		System.out.println("Please enter username:");
		String userName = in.next();
		return userName;
	}

	// A method for setting the password of created worker.
	public static String setPassword(Scanner in) {
		System.out.println("Please enter password:");
		String password = in.next();
		return password;
	}

	// A method for creating a new menu for one of the workers to work with. Also
	// for future menus.
	public static void createMenu(Scanner in) {
		createBurgerMenu(in);
	}

	// A method for creating a new hamburger menu.
	public static void createBurgerMenu(Scanner in) {
		try {
			char pick;
			ArrayList<Product> productList = new ArrayList<>();
			Menu<Hamburger> menu = new Menu<>();
			System.out.println("------------------------------------------------------------------------- \n"
					+ "Welcome to \"On-Click Hamburger\" Menu Creator \n"
					+ "-------------------------------------------------------------------------");
			do {
				System.out.println("-------------------------------------------------------------------------\r\n"
						+ "1. Create New Hamburger\r\n" + "2. Delete Hamburger\r\n" + "3. Add Hamburger To Menu\r\n"
						+ "4. Remove Hamburger From Menu\r\n" + "5. Remove All Hamburgers From Menu\r\n"
						+ "6. Search Hamburger By Name And Price\r\n" + "7. Print All Products\r\n"
						+ "8. Print Menu\r\n" + "Choose your option or any other key to EXIT");
				pick = in.next().charAt(0);
				cases(pick, in, menu, productList);
			} while ((int) pick > '0' && (int) pick < '9');
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// Switch case between the options of the menu.
	public static void cases(char pick, Scanner in, Menu<Hamburger> menu, ArrayList<Product> productList)
			throws PriceException {
		switch (pick) {
		case '1':
			System.out.println("Your option: 1");
			burgerCreation(in, menu, productList);
			break;
		case '2':
			System.out.println("Your option: 2");
			burgerDeletion(in, menu, productList);
			menu.sort(productList);
			break;
		case '3':
			System.out.println("Your option: 3");
			addBurger(in, menu, productList);
			break;
		case '4':
			System.out.println("Your option: 4");
			burgerRemoval(in, menu);
			break;
		case '5':
			System.out.println("Your option: 5");
			menu.removeAll();
			break;
		case '6':
			System.out.println("Your option: 6");
			searchBurger(in, menu);
			break;
		case '7':
			System.out.println("Your option: 7");
			menu.print(productList);
			break;
		case '8':
			System.out.println("Your option: 8");
			printMenu(in, menu);
			break;
		}
	}

	// A method for creating a burger and adding it to an array list.
	public static void burgerCreation(Scanner in, Menu<Hamburger> menu, ArrayList<Product> productList)
			throws PriceException {
		System.out.println("Create new hamburger: ");
		String name = nameOfProduct(in);
		System.out.println("Please enter price:");
		Number price = 0;
		String strPrice = in.next();
		try {
			price = Double.parseDouble(strPrice);
		} catch (Exception e) {
			System.out.println("Price must be a number!\n"/* + e.getMessage() */);
			throw new PriceException();
		}
		Hamburger hamburger = new Hamburger("RARE", "", name, price);
		System.out.println("Please enter Ingredients Info: ");
		in.nextLine();
		String ingridients = in.nextLine();
		System.out.println("Please enter Degree Of Doneness(RARE|MEDIUM|MEDIUMWELL|WELLDONE): ");
		String Doneness = in.next();
		Hamburger.Doneness.valueOf(Doneness.toUpperCase());
		hamburger = new Hamburger(Doneness, ingridients, name, price);
		menu.createProduct(productList, hamburger);
		System.out.println("Hamburger Created Successfully!");
		menu.sort(productList);
	}

	// A method for removing a burger from an array list.
	public static void burgerDeletion(Scanner in, Menu<Hamburger> menu, ArrayList<Product> productList) {
		System.out.println("Delete hamburger: ");
		String name = nameOfProduct(in);
		Number price = priceOfProduct(in);
		if (menu.search(productList, name, price.doubleValue()) == -1) {
			System.out.println("Hamburger not found!");
		} else {
			System.out.println("Result: \n" + productList.get(menu.search(productList, name, price.doubleValue())));
			if (answer(in)) {
				menu.deleteProduct(productList, menu.search(productList, name, price.doubleValue()));
				System.out.println("Hamburger deleted successfully!");
			} else {
				System.out.println("No hamburger was deleted!");
			}
		}
		menu.sort(productList);
	}

	// A method for adding a burger from the array list to the menu.
	public static void addBurger(Scanner in, Menu<Hamburger> menu, ArrayList<Product> productList) {
		System.out.println("add hamburger to menu: ");
		String name = nameOfProduct(in);
		Number price = priceOfProduct(in);
		if (menu.search(productList, name, price.doubleValue()) == -1) {
			System.out.println("Hamburger not found!");
		} else {
			System.out.println("Result: \n" + productList.get(menu.search(productList, name, price.doubleValue())));
			if (answer(in)) {
				menu.add((Hamburger) productList.get(menu.search(productList, name, price.doubleValue())));
				System.out.println("Hamburger added successfully!");
			} else {
				System.out.println("No hamburger was added!");
			}
		}
		menu.sort(menu.getProducts());
	}

	// A method for removing a burger from the menu.
	public static void burgerRemoval(Scanner in, Menu<Hamburger> menu) {
		System.out.println("Remove hamburger from menu");
		String name = nameOfProduct(in);
		Number price = priceOfProduct(in);
		if (menu.search(menu.getProducts(), name, price.doubleValue()) == -1) {
			System.out.println("Hamburger not found!");
		} else {
			System.out.println(
					"Result: \n" + menu.getProducts().get(menu.search(menu.getProducts(), name, price.doubleValue())));
			if (answer(in)) {
				menu.remove(menu.search(menu.getProducts(), name, price.doubleValue()));
				System.out.println("Hamburger removed successfully!");
			} else {
				System.out.println("No hamburger was removed!");
			}
		}
		menu.sort(menu.getProducts());
	}

	// A method for searching a burger from the menu, by name and price.
	public static void searchBurger(Scanner in, Menu<Hamburger> menu) {
		System.out.println("Search hamburger by name and price: ");
		String name = nameOfProduct(in);
		Number price = priceOfProduct(in);
		if (menu.search(menu.getProducts(), name, price.doubleValue()) < 0) {
			System.out.println("Hamburger not found!");
		} else {
			System.out.println("Result:");
			System.out.println(menu.getProducts().get(menu.search(menu.getProducts(), name, price.doubleValue())));
		}

	}

	// A method for printing the menu.
	public static void printMenu(Scanner in, Menu<Hamburger> menu) {
		System.out
				.println("Menu [ Count: " + menu.getProducts().size() + " Total Price: " + menu.getTotalPrice() + " ]");
		if (menu.getProducts().isEmpty()) {
			System.out.println("Result:\r\n" + "The Menu Is Empty!\r\n" + "");
		}
		for (int i = 0; i < menu.getProducts().size(); i++) {
			System.out.println(menu.getProducts().get(i));
		}
	}

	// A method for setting a name in certain functions.
	public static String nameOfProduct(Scanner in) {
		System.out.println("Please Enter Name : ");
		in.nextLine();
		String name = in.nextLine();
		return name;
	}

	// A method for setting a price in certain functions.
	public static Number priceOfProduct(Scanner in) {
		System.out.println("Please Enter Price : ");
		Number price;
		try {
			price = in.nextDouble();
			if (price.doubleValue() < 0) {
				throw new PriceException();
			}
		} catch (Exception e) {
			in.nextLine();
			return 0;
		}
		return price;
	}

	// A method for setting an answer in certain functions.
	public static boolean answer(Scanner in) {
		char answer;
		do {
			System.out.println("Are You Sure?(Y/N): ");
			answer = in.next().toUpperCase().charAt(0);
		} while (!(answer == 'Y' || answer == 'N'));
		if (answer == 'Y') {
			return true;
		}
		return false;
	}

	// A method of a log in function for the workers.
	public static boolean userLogIn(Scanner in, ArrayList<Worker> list) {
		System.out.println("*** System LogIn Option: ***");
		System.out.println("Please Enter UserName: ");
		String userName = in.next();
		System.out.println("Please Enter Password: ");
		String password = in.next();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getUserName().equals(userName)) {
				if (list.get(i).getPassword().equals(password)) {
					return true;
				}
			}
		}
		return false;
	}
}
