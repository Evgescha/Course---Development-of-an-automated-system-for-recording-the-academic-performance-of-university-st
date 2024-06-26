package net.project;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.project.DAO.ScoreMDAO;
import net.project.DAO.StudentMDAO;
import net.project.DAO.SubjectMDAO;
import net.project.entity.ScoreM;

public class ScoreMUpdateServlet extends HttpServlet {
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
				update(request, response);
			else
				insert(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void insert(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {

		int studentId = Integer.parseInt(request.getParameter("studentId"));
		int subjectId = Integer.parseInt(request.getParameter("subjectId"));
		int score = Integer.parseInt(request.getParameter("score"));
		int semestr = Integer.parseInt(request.getParameter("semestr"));
		ScoreM entity = new ScoreM(new StudentMDAO(jdbcURL, jdbcUsername, jdbcPassword).get(studentId),
				new SubjectMDAO(jdbcURL, jdbcUsername, jdbcPassword).get(subjectId), score, semestr);
		DAO.insert(entity);
		response.sendRedirect("score");
	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		int studentId = Integer.parseInt(request.getParameter("studentId"));
		int subjectId = Integer.parseInt(request.getParameter("subjectId"));
		int score = Integer.parseInt(request.getParameter("score"));
		int semestr = Integer.parseInt(request.getParameter("semestr"));
		ScoreM entity = new ScoreM(id,
				new StudentMDAO(jdbcURL, jdbcUsername, jdbcPassword).get(studentId),
				new SubjectMDAO(jdbcURL, jdbcUsername, jdbcPassword).get(subjectId), score, semestr);
		DAO.update(entity);
		response.sendRedirect("score");
	}

}
