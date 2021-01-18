package net.codejava.javaee.bookstore.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import net.codejava.javaee.bookstore.entity.Lottery;
import net.codejava.javaee.bookstore.entity.LotteryType;
import net.codejava.javaee.bookstore.entity.Prize;
import net.codejava.javaee.bookstore.entity.Ticket;


public class TicketDAO {
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;

	public TicketDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		this.jdbcURL = jdbcURL;
		this.jdbcUsername = jdbcUsername;
		this.jdbcPassword = jdbcPassword;
	}

	protected void connect() throws SQLException {
		if (jdbcConnection == null || jdbcConnection.isClosed()) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				throw new SQLException(e);
			}
			jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		}
	}

	protected void disconnect() throws SQLException {
		if (jdbcConnection != null && !jdbcConnection.isClosed()) {
			jdbcConnection.close();
		}
	}

	public boolean insert(Ticket entity) throws SQLException {
		String sql = "INSERT INTO ticket (lottery, numbers) VALUES (?,?)";
		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		
		statement.setInt(1, entity.getLottery().getId());		
		statement.setString(2, entity.getNumbers());		

		boolean rowInserted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowInserted;
	}

	public List<Ticket> listAll() throws SQLException {
		List<Ticket> listEntity = new ArrayList<>();

		String sql = "SELECT * FROM ticket";

		connect();

		Statement statement = jdbcConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			int lottery= resultSet.getInt("lottery");
			String numbers= resultSet.getString("numbers");

			Lottery lType = new LotteryDAO(jdbcURL, jdbcUsername, jdbcPassword).get(lottery);
			Ticket entity = new Ticket(id, lType,numbers);
			listEntity.add(entity);
		}

		resultSet.close();
		statement.close();

		disconnect();

		return listEntity;
	}

	public boolean delete(Ticket entity) throws SQLException {
		String sql = "DELETE FROM ticket where id = ?";

		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, entity.getId());

		boolean rowDeleted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowDeleted;
	}

	public boolean update(Ticket entity) throws SQLException {
		String sql = "UPDATE ticket SET lottery = ?, numbers=?";
		sql += " WHERE id = ?";
		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, entity.getLottery().getId());		
		statement.setString(2, entity.getNumbers());		
		statement.setInt(3, entity.getId());
		
		boolean rowUpdated = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowUpdated;
	}

	public Ticket get(int id) throws SQLException {
		Ticket entity = null;
		String sql = "SELECT * FROM ticket WHERE id = ?";

		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, id);

		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {
			
			int lottery= resultSet.getInt("lottery");
			String numbers= resultSet.getString("numbers");

			Lottery lType = new LotteryDAO(jdbcURL, jdbcUsername, jdbcPassword).get(lottery);
			entity = new Ticket(id, lType,numbers);
			
		}

		resultSet.close();
		statement.close();

		return entity;
	}
}
