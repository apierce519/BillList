package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BillList;

/**
 * Servlet implementation class viewAllBillListsServlet
 */
@WebServlet("/viewAllBillListsServlet")
public class ViewAllBillListsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ViewAllBillListsServlet() {
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
		BillListHelper blh = new BillListHelper();
		List<BillList> bl = blh.getLists();
		request.setAttribute("allLists", bl);
		System.out.println(blh.getLists());
		for (BillList a : bl) {
			System.out.println(a.toString());
			if (bl.isEmpty()) {
				request.setAttribute("allLists", " ");
			}
		}
		getServletContext().getRequestDispatcher("/view-all-bill-lists.jsp").forward(request, response);
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
