package net.codejava.javaee.bookstore;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.codejava.javaee.bookstore.DAO.TicketDAO;
import net.project.entity.StudentM;
import net.project.entity.GroupM;
import net.project.entity.Prize;
import net.project.entity.Ticket;

public class TicketUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TicketDAO DAO;

	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");

		DAO = new TicketDAO(jdbcURL, jdbcUsername, jdbcPassword);

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

		StudentM type = new StudentM(lottery);
		
		Ticket entity = new Ticket (type,numbers);
		DAO.insert(entity);
		response.sendRedirect("ticket");
	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		
		int id= Integer.parseInt( request.getParameter("id"));
		int lottery= Integer.parseInt( request.getParameter("lottery"));
		String numbers= request.getParameter("numbers");

		StudentM type = new StudentM(lottery);
		
		Ticket entity = new Ticket (id,type,numbers);
		DAO.update(entity);
		response.sendRedirect("ticket");
	}


}
