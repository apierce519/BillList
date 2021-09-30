package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BillItem;

/**
 * Servlet implementation class NavigationServlet
 */
@WebServlet("/navigationServlet")
public class NavigationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NavigationServlet() {
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

		BillItemHelper dao = new BillItemHelper();

		String act = request.getParameter("doThisCommand");
		String path = "/viewAllBillsServlet";

		if (act.equals("delete")) {
			try {
				Integer tempId = Integer.parseInt(request.getParameter("id"));
				BillItem billToDelete = dao.searchBillsById(tempId);
				dao.deleteBillEntry(billToDelete);
			} catch (NumberFormatException e) {
				System.out.println("No item selected.");
			}
		} else if (act.equals("edit")) {
			try {
				Integer tempId = Integer.parseInt(request.getParameter("id"));
				BillItem billToEdit = dao.searchBillsById(tempId);
				request.setAttribute("billToEdit", billToEdit);
				path = "/editBill.jsp";
			} catch (NumberFormatException e) {
				System.out.println("No item selected.");
			}
		} else if (act.equals("add")) {
			path = "/StartPage.html";
		}
		getServletContext().getRequestDispatcher(path).forward(request, response);
	}

}
