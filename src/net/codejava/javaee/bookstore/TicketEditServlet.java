package net.codejava.javaee.bookstore;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.codejava.javaee.bookstore.DAO.LotteryDAO;
import net.codejava.javaee.bookstore.DAO.TicketDAO;
import net.project.entity.StudentM;
import net.project.entity.Ticket;

public class TicketEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LotteryDAO lotteryDAO;
	private TicketDAO DAO;

	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");

		DAO = new TicketDAO(jdbcURL, jdbcUsername, jdbcPassword);
		lotteryDAO = new LotteryDAO(jdbcURL, jdbcUsername, jdbcPassword);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		try {
			List<StudentM> lotteries = lotteryDAO.listAll();
			request.setAttribute("lotteries", lotteries);
			
			if (request.getParameter("id") != null)
				showEditForm(request, response);
			else
				showNewForm(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("TicketForm.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Ticket existingBook = DAO.get(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("TicketForm.jsp");
		request.setAttribute("entity", existingBook);
		dispatcher.forward(request, response);

	}

}
