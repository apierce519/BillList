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
 * Servlet implementation class EditBillServlet
 */
@WebServlet("/editBillServlet")
public class EditBillServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditBillServlet() {
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

		BillHelper dao = new BillHelper();

		String billName = request.getParameter("billName");
		String billCost = request.getParameter("billCost");
		Integer tempId = Integer.parseInt(request.getParameter("id"));

		Bill billToUpdate = dao.searchBillsById(tempId);
		billToUpdate.setName(billName);
		billToUpdate.setCost(Double.parseDouble(billCost));

		dao.editBillEntry(billToUpdate);

		getServletContext().getRequestDispatcher("/viewAllBillsServlet").forward(request, response);
	}

}
