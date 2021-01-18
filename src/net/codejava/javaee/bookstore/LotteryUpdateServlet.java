package net.codejava.javaee.bookstore;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.codejava.javaee.bookstore.DAO.LotteryDAO;
import net.project.entity.StudentM;
import net.project.entity.GroupM;
import net.project.entity.Prize;

public class LotteryUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LotteryDAO DAO;

	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");

		DAO = new LotteryDAO(jdbcURL, jdbcUsername, jdbcPassword);

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
		
		int lottery_type= Integer.parseInt( request.getParameter("lottery_type"));
		Date dates= Date.valueOf(request.getParameter("dates"));
		int prize = Integer.parseInt(request.getParameter("prize"));

		GroupM type = new GroupM(lottery_type);
		Prize priz= new Prize(prize);
		
		StudentM entity = new StudentM(type,dates,priz);
		DAO.insert(entity);
		response.sendRedirect("lottery");
	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		
		int id= Integer.parseInt( request.getParameter("id"));
		int lottery_type= Integer.parseInt( request.getParameter("lottery_type"));
		Date dates= Date.valueOf(request.getParameter("dates"));
		int prize = Integer.parseInt(request.getParameter("prize"));

		GroupM type = new GroupM(lottery_type);
		Prize priz= new Prize(prize);
		
		StudentM entity = new StudentM(id,type,dates,priz);
		DAO.update(entity);
		response.sendRedirect("lottery");
	}


}
