import java.util.ArrayList;
import java.util.List;

import controller.BillHelper;
import controller.BillListHelper;
import model.Bill;
import model.BillList;

/**
 * @author Andrew Pierce - ajpierce1
 * CIS175 - Fall 2021
 * Oct 13, 2021
 */

/**
 * @author Andrew Pierce - ajpierce1
 */
public class BillListTester {

	/**
	 * 
	 */
	public BillListTester() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		BillHelper bh = new BillHelper();
		Bill testOne = new Bill("electric", 50);
		Bill testTwo = new Bill("water", 20);
		
		bh.addBill(testOne);
		bh.addBill(testTwo);
		
		BillListHelper blh = new BillListHelper();
		BillList bl = new BillList("home bills");
		
		List<Bill> bList = new ArrayList<Bill>();
		bList.add(testOne);
		bList.add(testTwo);
		
		bl.setListOfBills(bList);
		
		blh.insertNewBillList(bl);

	}

}
