package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BillItem;

/**
 * Servlet implementation class addBillServlet
 */
@WebServlet("/addBillServlet")
public class addBillServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public addBillServlet() {
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
		
		BillItem newBill = new BillItem(billName,Double.parseDouble(billCost));
		BillItemHelper dao = new BillItemHelper();
		dao.addBill(newBill);
		
		getServletContext().getRequestDispatcher("/StartPage.html").forward(request, response);
		
	}

}
