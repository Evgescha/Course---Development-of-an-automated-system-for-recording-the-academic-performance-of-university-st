package net.codejava.javaee.bookstore;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.codejava.javaee.bookstore.DAO.SubjectMDAO;
import net.codejava.javaee.bookstore.DAO.TeacherMDAO;
import net.project.entity.SubjectM;

public class SubjectMUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SubjectMDAO DAO;
	String jdbcURL = getServletContext().getInitParameter("jdbcURL");
	String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
	String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");

	public void init() {

		DAO = new SubjectMDAO(jdbcURL, jdbcUsername, jdbcPassword);

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
		int teacherId = Integer.parseInt(request.getParameter("teacherId"));
		SubjectM entity = new SubjectM(name, new TeacherMDAO(jdbcURL, jdbcUsername, jdbcPassword).get(teacherId));
		DAO.insert(entity);
		response.sendRedirect("group");
	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		int teacherId = Integer.parseInt(request.getParameter("teacherId"));
		SubjectM entity = new SubjectM(name, new TeacherMDAO(jdbcURL, jdbcUsername, jdbcPassword).get(teacherId));
		DAO.update(entity);
		response.sendRedirect("group");
	}

}
