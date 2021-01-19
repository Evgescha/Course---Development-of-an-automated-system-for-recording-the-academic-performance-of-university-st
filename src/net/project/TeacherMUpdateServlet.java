package net.project;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.project.DAO.TeacherMDAO;
import net.project.entity.TeacherM;

public class TeacherMUpdateServlet extends HttpServlet {
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
			if (request.getParameter("id") != null)
				update(request, response);
			else
				insert(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void insert(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String name = request.getParameter("name");

		TeacherM entity = new TeacherM(name);
		DAO.insert(entity);
		response.sendRedirect("teacher");
	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		TeacherM entity = new TeacherM(id, name);
		DAO.update(entity);
		response.sendRedirect("teacher");
	}

}
