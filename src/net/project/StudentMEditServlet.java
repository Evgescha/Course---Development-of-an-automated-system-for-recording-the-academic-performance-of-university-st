package net.project;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.project.DAO.GroupMDAO;
import net.project.DAO.StudentMDAO;
import net.project.entity.StudentM;

public class StudentMEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentMDAO DAO;

	String jdbcURL;
	String jdbcUsername;
	String jdbcPassword;
	public void init() {
		jdbcURL = getServletContext().getInitParameter("jdbcURL");
		jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
		DAO = new StudentMDAO(jdbcURL, jdbcUsername, jdbcPassword);
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
				showEditForm(request, response);
			else
				showNewForm(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("studentForm.jsp");
		request.setAttribute("groups", new GroupMDAO(jdbcURL, jdbcUsername, jdbcPassword).listAll());
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		StudentM entity = DAO.get(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("studentForm.jsp");
		request.setAttribute("entity", entity);
		request.setAttribute("groups", new GroupMDAO(jdbcURL, jdbcUsername, jdbcPassword).listAll());
		dispatcher.forward(request, response);

	}

}
