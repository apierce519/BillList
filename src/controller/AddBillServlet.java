package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Bill;

/**
 * Servlet implementation class addBillServlet
 */
@WebServlet("/addBillServlet")
public class AddBillServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddBillServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String billName = request.getParameter("billName");
		String billCost = request.getParameter("billCost");
		
		Bill newBill = new Bill(billName,Double.parseDouble(billCost));
		BillHelper dao = new BillHelper();
		dao.addBill(newBill);
		
		getServletContext().getRequestDispatcher("/start-page.html").forward(request, response);
		
	}

}
