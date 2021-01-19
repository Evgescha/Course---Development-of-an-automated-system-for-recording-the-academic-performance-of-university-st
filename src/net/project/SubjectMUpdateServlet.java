package net.project;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.project.DAO.SubjectMDAO;
import net.project.DAO.TeacherMDAO;
import net.project.entity.SubjectM;

public class SubjectMUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SubjectMDAO DAO;
	String jdbcURL;
	String jdbcUsername;
	String jdbcPassword;
	public void init() {
		jdbcURL = getServletContext().getInitParameter("jdbcURL");
		jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		jdbcPassword = getServletContext().getInitParameter("jdbcPassword");

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
		response.sendRedirect("subject");
	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		int teacherId = Integer.parseInt(request.getParameter("teacherId"));
		SubjectM entity = new SubjectM(id,name, new TeacherMDAO(jdbcURL, jdbcUsername, jdbcPassword).get(teacherId));
		DAO.update(entity);
		response.sendRedirect("subject");
	}

}
