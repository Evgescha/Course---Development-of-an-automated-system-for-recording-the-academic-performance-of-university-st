package net.project;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.project.DAO.ScoreMDAO;
import net.project.DAO.StudentMDAO;
import net.project.DAO.SubjectMDAO;
import net.project.entity.ScoreM;

public class ScoreMEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ScoreMDAO DAO;

	String jdbcURL;
	String jdbcUsername;
	String jdbcPassword;

	public void init() {
		jdbcURL = getServletContext().getInitParameter("jdbcURL");
		jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		jdbcPassword = getServletContext().getInitParameter("jdbcPassword");

		DAO = new ScoreMDAO(jdbcURL, jdbcUsername, jdbcPassword);

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
		RequestDispatcher dispatcher = request.getRequestDispatcher("scoreForm.jsp");
		request.setAttribute("students", new StudentMDAO(jdbcURL, jdbcUsername, jdbcPassword).listAll());
		request.setAttribute("subjects", new SubjectMDAO(jdbcURL, jdbcUsername, jdbcPassword).listAll());
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		ScoreM entity = DAO.get(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("scoreForm.jsp");
		request.setAttribute("students", new StudentMDAO(jdbcURL, jdbcUsername, jdbcPassword).listAll());
		request.setAttribute("subjects", new SubjectMDAO(jdbcURL, jdbcUsername, jdbcPassword).listAll());
		request.setAttribute("entity", entity);
		dispatcher.forward(request, response);

	}

}
