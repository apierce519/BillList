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
	public static void MainMenu() {
		boolean resumeMenu = true;
		String menu = "-- What would you like to do? --\n-- 1 <Create Bill>\n-- 2 <Search for a bill to edit or delete>\n-- 3 <Exit>";
		System.out.println("Bill List");
		while (resumeMenu) {
			System.out.println(menu);
			int userEntry = in.nextInt();
			in.nextLine();

			switch (userEntry) {
			case 1:
				createBill();
				break;
			case 2:
				retrieveBill();
				break;
			case 3:
				resumeMenu = false;
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

	// Need a method each for CRUD (create, re, update, delete)
	/*
	 * This method is the create (insert into table). The first thing to do is
	 * accept everything from the console and then pass those values to the method
	 * of the helper class.
	 */
	public static void createBill() {
		System.out.println("Enter bill name: ");
		String billName = in.nextLine();
		System.out.println("Enter bill amount: ");
		while (!in.hasNextDouble()) {
			System.out.println("You must enter a number. Please try again.");
			in.nextLine();
			System.out.println("Enter bill amount: ");
		}
		double billCost = in.nextDouble();
		BillItem newBit = new BillItem(billName, billCost);
		bih.addBill(newBit);
		System.out.println(billName + ": " + df.format(billCost) + " added.");
	}

	/*
	 * Retrieve method (retrieval). Search.
	 */
	public static void retrieveBill() {
		System.out.println("1. Search for bill by source.");
		System.out.println("2. Search for bill by cost.");
		List<BillItem> bItem = null;
		boolean thirdOption = true;
		while (thirdOption) {
			while (!in.hasNextInt()) {
				System.out.println("Entry must be a number. Try again: ");
				in.nextLine();
			}
			int searchSelection = in.nextInt();
			switch (searchSelection) {
			case 1:
				System.out.print("Enter Source: ");
				String source = in.nextLine();
				bItem = bih.searchBySource(source);
				thirdOption = false;
				break;
			case 2:
				System.out.println("Enter Cost: ");
				while (!in.hasNextDouble()) {
					System.out.println("Entry must be a number. Try again: ");
					in.nextLine();
				}
				double cost = in.nextDouble();
				bItem = bih.searchByCost(cost);
				thirdOption = false;
				break;

			default:
				System.out.println("Enter either 1 or 2");
			}
		}
		if (!bItem.isEmpty()) {
			System.out.println("Enter ID to select bill: ");

			System.out.println("What would you like to do with this bill?\n 1. Edit\n 2. Delete\n 3. Cancel");
			thirdOption = true;
			while (thirdOption) {
				while (!in.hasNextInt()) {
					System.out.println("Entry must be the number 1 or 2 or 3.");
					in.nextLine();
				}
				int searchSelection = in.nextInt();
				switch (searchSelection) {
				case 1:
					updateBill(bItem);
					thirdOption = false;
					break;
				case 2:
					deleteBill(bItem);
					thirdOption = false;
					break;
				case 3:
					thirdOption = false;
					break;
				default:
					System.out.println("You must enter number 1 or 2 or 3.");
					break;
				}

			}
		} else {
			System.out.println("No bills found.");
		}
	}

	public static void updateBill(BillItem bItem) {
		System.out.println("This one worked. Update method.");

	}

	public static void deleteBill(BillItem bItem) {
		System.out.println("This one worked. Delete method.");

	}

	private static void showBillList() {

	}

}
