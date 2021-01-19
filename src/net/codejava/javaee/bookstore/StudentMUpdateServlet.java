package net.codejava.javaee.bookstore;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.codejava.javaee.bookstore.DAO.GroupMDAO;
import net.codejava.javaee.bookstore.DAO.StudentMDAO;
import net.project.entity.StudentM;

public class StudentMUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentMDAO DAO;
	String jdbcURL = getServletContext().getInitParameter("jdbcURL");
	String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
	String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");

	public void init() {
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
				update(request, response);
			else
				insert(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void insert(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String name = request.getParameter("name");
		int groupId = Integer.parseInt(request.getParameter("groupId"));
		StudentM entity = new StudentM(name, new GroupMDAO(jdbcURL, jdbcUsername, jdbcPassword).get(groupId));
		DAO.insert(entity);
		response.sendRedirect("student");
	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		int groupId = Integer.parseInt(request.getParameter("groupId"));
		StudentM entity = new StudentM(id,name, new GroupMDAO(jdbcURL, jdbcUsername, jdbcPassword).get(groupId));
		DAO.update(entity);
		response.sendRedirect("student");
	}

}
