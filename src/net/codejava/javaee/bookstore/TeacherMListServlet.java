package net.codejava.javaee.bookstore;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.codejava.javaee.bookstore.DAO.TeacherMDAO;
import net.project.entity.TeacherM;

public class TeacherMListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TeacherMDAO DAO;

	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");

		DAO = new TeacherMDAO(jdbcURL, jdbcUsername, jdbcPassword);

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
			list(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void list(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<TeacherM> list = DAO.listAll();
		request.setAttribute("list", list);
		RequestDispatcher dispatcher = request.getRequestDispatcher("teacherList.jsp");
		dispatcher.forward(request, response);
	}

}
