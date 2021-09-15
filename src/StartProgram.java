import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;

import controller.BillItemHelper;
import model.BillItem;

/**
 * @author Andrew Pierce - ajpierce1
 * CIS175 - Fall 2021
 * Sep 12, 2021
 */

/**
 * @author Andrew Pierce - ajpierce1
 */
public class StartProgram {
	static Scanner in = new Scanner(System.in);
	static DecimalFormat df = new DecimalFormat("$#.00");
	static BillItemHelper bih = new BillItemHelper();

	public static void main(String[] args) {
		MainMenu();
	}

	// this method will output the options for the menu.
	private static void MainMenu() {
		boolean continueMainMenu = true;
		String menuOptions = "-- What would you like to do? --\n-- 1 <Create Bill>\n-- 2 <Search for a bill to edit or delete>\n-- 3 Show All Entries\n-- 4 <Exit>";

		System.out.println("Bill List");
		while (continueMainMenu) {
			System.out.println(menuOptions);
			int userEntry = in.nextInt();
			in.nextLine();

			switch (userEntry) {
			case 1:
				createBill();
				break;
			case 2:
				searchMenu();
				break;
			case 3:
				showAllBillEntries();
				break;
			case 4:
				continueMainMenu = false;
				break;
			default:
				System.out.println("Entry not recognized. Please try again.");
				break;
			}
		}
		System.out.println("Exiting.");
		in.close();
		System.exit(0);
	}

	// Need a method each for CRUD (create, retrieve, update, delete)
	/*
	 * This method is the create (insert into table). The first thing to do is
	 * accept everything from the console and then pass those values to the method
	 * of the helper class.
	 */
	private static void createBill() {
		System.out.println("Enter bill name: ");
		String billName = in.nextLine();
		System.out.println("Enter bill amount: ");
		while (!in.hasNextDouble()) {
			System.out.println("You must enter a number. Please try again.");
			in.nextLine();
			System.out.println("Enter bill amount: ");
		}
		double billCost = in.nextDouble();
		BillItem newBillEntry = new BillItem(billName, billCost);
		bih.addBill(newBillEntry);
		System.out.println(billName + ": " + df.format(billCost) + " added.");
	}

	/*
	 * Retrieve method (retrieval). Search.
	 */
	private static void searchMenu() {
		System.out.println("1. Search for bill by name.\n2. Search for bill by cost.");
		boolean continueSearchMenu = true;
		while (continueSearchMenu) {
			while (!in.hasNextInt()) {
				System.out.println("Entry must be a number. Try again: ");
				in.nextLine();
			}
			int searchSelection = in.nextInt();
			in.nextLine();
			switch (searchSelection) {
			case 1:
				searchBillsByName();
				continueSearchMenu = false;
				break;
			case 2:
				searchBillsByCost();
				continueSearchMenu = false;
				break;

			default:
				System.out.println("Enter either 1 or 2");
			}
		}

	}

	/**
	 * @param billEntry
	 * 
	 */

	private static void searchBillsByName() {
		List<BillItem> billEntry;
		System.out.println("Enter Name: ");
		String name = in.nextLine();
		billEntry = bih.searchByName(name);
		printEnteredList(billEntry);
		searchBillsById();
	}

	private static void searchBillsByCost() {
		List<BillItem> billEntry;
		System.out.println("Will show any bill > entered cost.\nEnter Cost: ");
		while (!in.hasNextDouble()) {
			System.out.println("Entry must be a number. Try again: ");
			in.nextLine();
		}
		double cost = in.nextDouble();
		billEntry = bih.searchByCost(cost);
		printEnteredList(billEntry);
		searchBillsById();

	}

	private static void searchBillsById() {
		// TODO Auto-generated method stub
		System.out.println("Enter ID to select bill: ");
		while (!in.hasNextInt()) {
			System.out.println("Entry must be a number.");
			in.nextLine();
		}
		int enteredNumber = in.nextInt();
		in.nextLine();
		editOrDeleteOptions(bih.searchBillsById(enteredNumber));

	}

	private static void editOrDeleteOptions(BillItem selectedEntry) {

		boolean continueMenu = true;

		System.out.println("What would you like to do with this bill?\n 1. Edit\n 2. Delete\n 3. Cancel");
		continueMenu = true;
		while (continueMenu) {
			while (!in.hasNextInt()) {
				System.out.println("Entry must be the number 1 or 2 or 3.");
				in.nextLine();
			}
			int searchSelection = in.nextInt();
			in.nextLine();
			switch (searchSelection) {
			case 1:
				editBillEntry(selectedEntry);
				continueMenu = false;
				break;
			case 2:
				deleteBillEntry(selectedEntry);
				continueMenu = false;
				break;
			case 3:
				continueMenu = false;
				break;
			default:
				System.out.println("You must enter number 1 or 2 or 3.");
				break;
			}
		}
	}

	public static void editBillEntry(BillItem billToEdit) {
		System.out.println("1. Edit Name\n2. Edit Cost");
		while (!in.hasNextInt()) {
			System.out.println("Entry must be a number.");
			in.nextLine();
		}
		int selection = in.nextInt();
		in.nextLine();
		boolean continueMenu = true;
		while (continueMenu) {
			switch (selection) {
			case 1:
				System.out.println("Enter new bill Name: ");
				String newBillName = in.nextLine();
				billToEdit.setBillName(newBillName);
				continueMenu = false;
				break;
			case 2:
				System.out.println("Enter new bill Cost: ");
				while (!in.hasNextDouble()) {
					System.out.println("Please enter a number.");
					in.nextLine();
				}
				double newBillCost = in.nextDouble();
				billToEdit.setBillCost(newBillCost);
				continueMenu = false;
				break;
			default:
				System.out.println("Please select 1 or 2.");
				break;
			}
			bih.editBillEntry(billToEdit);

		}
	}

	public static void deleteBillEntry(BillItem billToDelete) {
		System.out.println("This item will be deleted!!\nAre you sure?\n1. Yes\n2. No");
		while (!in.hasNextInt()) {
			System.out.println("Entry must be 1(yes) or 2(no)");
			in.nextLine();
		}
		int selection = in.nextInt();
		if (selection == 1) {
			bih.deleteBillEntry(billToDelete);

		} else {
			editOrDeleteOptions(billToDelete);
		}
	}

	private static void printEnteredList(List<BillItem> billList) {
		if (!billList.isEmpty()) {
			System.out.println("----------------");
			System.out.println("Matching Entries: ");
			System.out.println("----------------");
			for (BillItem a : billList) {
				System.out.println(a.toString());
			}
			System.out.println("----------------");
		} else {
			System.out.println("No results");
		}

	}

	private static void showAllBillEntries() {
		printEnteredList(bih.showAllBills());
	}

}
