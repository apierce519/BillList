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
		// TODO Auto-generated method stub
		doGet(request, response);
		BillListHelper dao = new BillListHelper();
		String act = request.getParameter("doThisCommand");
		if (act == null) {
			System.out.println("Nothing selected.");
			getServletContext().getRequestDispatcher("/viewAllBillListsServlet").forward(request, response);
		} else if (act.equals("delete")) {
			try {
				Integer tempId = Integer.parseInt(request.getParameter("id"));
				BillList foundList = dao.searchForBillListById(tempId);
				dao.deleteList(foundList);
			} catch (NumberFormatException e) {
				System.out.println("No item selected.");
			} finally {
				getServletContext().getRequestDispatcher("/viewAllBillListsServlet").forward(request, response);
			}
		} else if (act.equals("edit")) {
			try {
				Integer tempId = Integer.parseInt(request.getParameter("id"));
				BillList foundList = dao.searchForBillListById(tempId);
				request.setAttribute("billListToEdit", foundList);

				BillItemHelper daoForItems = new BillItemHelper();
				request.setAttribute("allListItems", daoForItems.showAllBills());

				if (daoForItems.showAllBills().isEmpty()) {
					request.setAttribute("allListItems", " ");
				}
				getServletContext().getRequestDispatcher("/edit-bill-list.jsp").forward(request, response);
			} catch (NumberFormatException | ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				getServletContext().getRequestDispatcher("/viewAllListsServlet").forward(request, response);
			}
		} else if (act.equals("add")) {
			getServletContext().getRequestDispatcher("/generateBillItemListServlet").forward(request, response);
		}
	}

}
