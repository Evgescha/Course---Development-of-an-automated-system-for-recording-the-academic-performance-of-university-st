package net.codejava.javaee.bookstore;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.codejava.javaee.bookstore.DAO.RezultDAO;
import net.codejava.javaee.bookstore.entity.Lottery;
import net.codejava.javaee.bookstore.entity.Rezult;
import net.codejava.javaee.bookstore.entity.Ticket;

public class RezultUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RezultDAO DAO;

	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");

		DAO = new RezultDAO(jdbcURL, jdbcUsername, jdbcPassword);

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
			if (request.getParameter("id") != null)
				update(request, response);
			else
				insert(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void insert(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		
		int lottery= Integer.parseInt( request.getParameter("lottery"));
		String numbers= request.getParameter("numbers");
		int winner= Integer.parseInt( request.getParameter("winner"));
		
		Lottery type = new Lottery(lottery);
		Ticket winnr = new Ticket (winner);
		Rezult entity = new Rezult(type,numbers,winnr);
		DAO.insert(entity);
		response.sendRedirect("rezult");
	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		
		int id= Integer.parseInt( request.getParameter("id"));
		int lottery= Integer.parseInt( request.getParameter("lottery"));
		String numbers= request.getParameter("numbers");
		int winner= Integer.parseInt( request.getParameter("winner"));
		
		Lottery type = new Lottery(lottery);
		Ticket winnr = new Ticket (winner);
		Rezult entity = new Rezult(id,type,numbers,winnr);
		DAO.update(entity);
		response.sendRedirect("rezult");
	}


}
