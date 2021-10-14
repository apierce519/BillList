package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BillList;

/**
 * Servlet implementation class BillListNavigationServlet
 */
@WebServlet("/billListNavigationServlet")
public class BillListNavigationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String path = "/viewAllBillListsServlet";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BillListNavigationServlet() {
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String command = request.getParameter("doThisCommand");

		if (command == null) {
			System.out.println("Nothing Selected");
			sendToNextPage(request, response, path);
		} else {
			switch (command) {
			case "add":
				addBillList();
				break;
			case "delete":
				deleteBillList(request, response);
				break;
			case "edit":
				editBillList(request, response);
			default:
				break;
			}
		}

		sendToNextPage(request, response, path);

	}

	public void sendToNextPage(HttpServletRequest request, HttpServletResponse response, String path) {
		try {
			getServletContext().getRequestDispatcher(path).forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	public BillList searchBillLists(HttpServletRequest request, HttpServletResponse response) {
		BillListHelper dao = new BillListHelper();
		Integer tempId = null;
		try {
			tempId = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			System.out.println("No list was selected");
			sendToNextPage(request, response, path);
		}
		return dao.searchForBillListById(tempId);
	}

	public void deleteBillList(HttpServletRequest request, HttpServletResponse response) {
		BillListHelper dao = new BillListHelper();
		dao.deleteList(searchBillLists(request, response));
	}

	public void editBillList(HttpServletRequest request, HttpServletResponse response) {
		BillList toEdit = searchBillLists(request, response);
		request.setAttribute("billListToEdit", toEdit);

		BillHelper billDao = new BillHelper();
		request.setAttribute("allBills", billDao.showAllBills());

		if (billDao.showAllBills().isEmpty()) {
			request.setAttribute("allBills", " ");
		}
		path = "/edit-bill-list.jsp";
	}

	public void addBillList() {
		path = "/create-new-bill-list.jsp";
	}
}
