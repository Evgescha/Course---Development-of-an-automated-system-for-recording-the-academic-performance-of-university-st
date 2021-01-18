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
import net.codejava.javaee.bookstore.DAO.LotteryTypeDAO;
import net.codejava.javaee.bookstore.DAO.PrizeDAO;
import net.codejava.javaee.bookstore.entity.Lottery;
import net.codejava.javaee.bookstore.entity.LotteryType;
import net.codejava.javaee.bookstore.entity.Prize;

public class LotteryEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LotteryDAO DAO;
	private LotteryTypeDAO lotteryTypeDAO;
	private PrizeDAO prizeDAO;

	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");

		DAO = new LotteryDAO(jdbcURL, jdbcUsername, jdbcPassword);
		lotteryTypeDAO = new LotteryTypeDAO(jdbcURL, jdbcUsername, jdbcPassword);
		prizeDAO = new PrizeDAO(jdbcURL, jdbcUsername, jdbcPassword);
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
			List<LotteryType> listAllTypes = lotteryTypeDAO.listAll();
			request.setAttribute("lotteryTypes", listAllTypes);
			
			List<Prize> listAllPrizes = prizeDAO.listAll();
			request.setAttribute("prizes", listAllPrizes);
			
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
		RequestDispatcher dispatcher = request.getRequestDispatcher("LotteryForm.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Lottery existingBook = DAO.get(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("LotteryForm.jsp");
		request.setAttribute("entity", existingBook);
		dispatcher.forward(request, response);

	}

}
