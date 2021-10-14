package view;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;

import controller.BillHelper;
import model.Bill;

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
	static BillHelper bih = new BillHelper();

	public static void main(String[] args) {
		MainMenu();
	}

	private static void performIntValidation() {
		// TODO Auto-generated method stub
		while (!in.hasNextInt()) {
			System.out.println("Entry must be a number.");
			in.nextLine();
		}
	}

	private static void performDoubleValidation() {
		// TODO Auto-generated method stub
		while (!in.hasNextDouble()) {
			System.out.println("You must enter a number. Please try again.");
			in.nextLine();
		}
	}

	private static void MainMenu() {
		boolean continueMainMenu = true;
		String menuOptions = "-- What would you like to do? --\n-- 1 <Create Bill>\n-- 2 <Search for a bill to edit or delete>\n-- 3 Show All Entries\n-- 4 <Exit>";

		System.out.println("Bill List");
		while (continueMainMenu) {
			System.out.println(menuOptions);
			performIntValidation();
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
		bih.exitResourceClosing();
		in.close();
		System.exit(0);
	}

	private static void createBill() {
		System.out.println("Enter bill name: ");
		String billName = in.nextLine();
		System.out.println("Enter bill amount: ");
		performDoubleValidation();
		double billCost = in.nextDouble();
		Bill newBillEntry = new Bill(billName, billCost);
		bih.addBill(newBillEntry);
		System.out.println(billName + ": " + df.format(billCost) + " added.");
	}

	private static void searchMenu() {
		System.out.println("1. Search for bill by name.\n2. Search for bill by cost.");
		boolean continueSearchMenu = true;
		while (continueSearchMenu) {
			performIntValidation();
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

	private static void searchBillsByName() {
		List<Bill> billEntry;
		System.out.println("Enter Name: ");
		String name = in.nextLine();
		billEntry = bih.searchByName(name);
		if (!billEntry.isEmpty()) {
			printEnteredList(billEntry);
			searchBillsById();
		} else {
			System.out.println("Nothing was found with that name.\nTry again?\n1. YES 2. NO");
			performIntValidation();
			int selection = in.nextInt();
			boolean exit = true;
			while (exit) {
				switch (selection) {
				case 1:
					in.nextLine();
					searchBillsByName();
					break;
				default:
					exit = false;
					break;

				}
			}
		}
	}

	private static void searchBillsByCost() {
		List<Bill> billEntry;
		System.out.println("Will show any bill > entered cost.\nEnter Cost: ");
		performDoubleValidation();
		double cost = in.nextDouble();
		billEntry = bih.searchByCost(cost);
		printEnteredList(billEntry);
		searchBillsById();

	}

	private static void searchBillsById() {
		// TODO Auto-generated method stub
		System.out.println("Enter ID to select bill: ");
		performIntValidation();
		int enteredNumber = in.nextInt();
		in.nextLine();
		editOrDeleteMenu(bih.searchBillsById(enteredNumber));

	}

	private static void editOrDeleteMenu(Bill selectedEntry) {

		boolean continueMenu = true;

		System.out.println("What would you like to do with this bill?\n 1. Edit\n 2. Delete\n 3. Cancel");
		continueMenu = true;
		while (continueMenu) {
			performIntValidation();
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

	public static void editBillEntry(Bill billToEdit) {
		System.out.println("1. Edit Name\n2. Edit Cost");
		performIntValidation();
		int selection = in.nextInt();
		in.nextLine();
		boolean continueMenu = true;
		while (continueMenu) {
			switch (selection) {
			case 1:
				System.out.println("Enter new bill Name: ");
				String newBillName = in.nextLine();
				billToEdit.setName(newBillName);
				continueMenu = false;
				break;
			case 2:
				System.out.println("Enter new bill Cost: ");
				performDoubleValidation();
				double newBillCost = in.nextDouble();
				billToEdit.setCost(newBillCost);
				continueMenu = false;
				break;
			default:
				System.out.println("Please select 1 or 2.");
				break;
			}
			bih.editBillEntry(billToEdit);

		}
	}

	public static void deleteBillEntry(Bill billToDelete) {
		System.out.println("This item will be deleted!!\nAre you sure?\n1. Yes\n2. No");
		performIntValidation();
		int selection = in.nextInt();
		if (selection == 1) {
			bih.deleteBillEntry(billToDelete);

		} else {
			editOrDeleteMenu(billToDelete);
			System.out.println(billToDelete + " has been deleted!");
		}
	}

	private static void printEnteredList(List<Bill> billList) {
		if (!billList.isEmpty()) {
			System.out.println("----------------");
			System.out.println("Matching Entries: ");
			System.out.println("----------------");
			for (Bill a : billList) {
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
