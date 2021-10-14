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
	String path = "/view-all-bill-lists.jsp";

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
		List<BillList> blh = getBillLists();
		validateBillList(request, blh);
		sendToNextPage(request, response, path);
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

	public HttpServletRequest validateBillList(HttpServletRequest request, List<BillList> tempList) {
		if(tempList.isEmpty()) {
			path = "/generateBillItemListServlet";
			System.out.println("No lists were found");
		}else {
			request.setAttribute("allLists", tempList);
			System.out.println("Not empty");
			System.out.println(tempList.toString());
		}
		return request;
	}
	
	public void sendToNextPage(HttpServletRequest request,HttpServletResponse response, String path) {
		try {
			getServletContext().getRequestDispatcher(path).forward(request, response);
		} catch(ServletException | IOException e) {
			e.printStackTrace();
		}
	}


	public List<BillList> getBillLists(){
		BillListHelper blh = new BillListHelper();
		List<BillList> blList = blh.getLists();
		return blList;
	}}
