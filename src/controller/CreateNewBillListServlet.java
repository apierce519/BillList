package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Bill;
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
		retrieveAndSetNewValues(request);
		sendToNextPage(request, response);
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

	public void retrieveAndSetNewValues(HttpServletRequest request) {
		BillListHelper dao = new BillListHelper();
		BillList bl = new BillList();
		
		bl.setListName(request.getParameter("billListName"));
		
		try {
			bl.setListOfBills(addBillsToBillList(request));
		}catch(NullPointerException e) {
			List<Bill> emptyList = new ArrayList<Bill>();
			bl.setListOfBills(emptyList);
		}
		dao.insertNewBillList(bl);
		
		System.out.println("Task Successfully Succeeded.");
		System.out.println(bl.toString());

	}

	public List<Bill> addBillsToBillList(HttpServletRequest request){
		BillHelper bh = new BillHelper();
		String[] selectedBills = request.getParameterValues("allBillsToAdd");
		List<Bill> billsToAdd = new ArrayList<Bill>();
		
		for (int i = 0; i < selectedBills.length; i++) {
			System.out.println(selectedBills[i]);
			Bill c = bh.searchBillsById(Integer.parseInt(selectedBills[i]));
			billsToAdd.add(c);
		}
		
		return billsToAdd;		
	}
	
	public void sendToNextPage(HttpServletRequest request,HttpServletResponse response) {
		try {
			getServletContext().getRequestDispatcher("/viewAllBillListsServlet").forward(request, response);
		} catch(ServletException | IOException e) {
			e.printStackTrace();
		}
	}
}
