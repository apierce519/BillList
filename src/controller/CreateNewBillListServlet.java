package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BillItem;
import model.BillList;

/**
 * Servlet implementation class CreateNewBillListServlet
 */
@WebServlet("/createNewBillListServlet")
public class CreateNewBillListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateNewBillListServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		BillItemHelper bih = new BillItemHelper();
		String billListName = request.getParameter("billListName");
		System.out.println("Bill List Name: " + billListName);

		String[] selectedBills = request.getParameterValues("allBillsToAdd");
		List<BillItem> selectedBillList = new ArrayList<BillItem>();
		if (selectedBills != null && selectedBills.length > 0) {
			for (int i = 0; i < selectedBills.length; i++) {
				System.out.println(selectedBills[i]);
				BillItem c = bih.searchBillsById(Integer.parseInt(selectedBills[i]));
				selectedBillList.add(c);
			}
		}
		BillList bl = new BillList(billListName);
		bl.setListOfBills(selectedBillList);

		BillListHelper blh = new BillListHelper();
		blh.insertNewBillList(bl);
		
		System.out.println("Success");
		System.out.println(bl.toString());
		
		getServletContext().getRequestDispatcher("/generateBillItemListServlet").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
