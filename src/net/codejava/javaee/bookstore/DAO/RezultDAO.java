package net.codejava.javaee.bookstore.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import net.codejava.javaee.bookstore.entity.Lottery;
import net.codejava.javaee.bookstore.entity.Rezult;
import net.codejava.javaee.bookstore.entity.Ticket;


public class RezultDAO {
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;

	public RezultDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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

	public boolean insert(Rezult entity) throws SQLException {
		String sql = "INSERT INTO result (lottery, numbers,winner) VALUES (?,?,?)";
		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		
		statement.setInt(1, entity.getLottery().getId());		
		statement.setString(2, entity.getNumbers());		
		statement.setInt(3, entity.getWinner().getId());		

		boolean rowInserted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowInserted;
	}

	public List<Rezult> listAll() throws SQLException {
		List<Rezult> listEntity = new ArrayList<>();

		String sql = "SELECT * FROM result";

		connect();

		Statement statement = jdbcConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			int lottery= resultSet.getInt("lottery");
			String numbers= resultSet.getString("numbers");
			int winner= resultSet.getInt("winner");
			
			Lottery lType = new LotteryDAO(jdbcURL, jdbcUsername, jdbcPassword).get(lottery);
			Ticket Twinner = new TicketDAO(jdbcURL, jdbcUsername, jdbcPassword).get(winner);
			Rezult entity = new Rezult(id, lType, numbers, Twinner);
			listEntity.add(entity);
		}

		resultSet.close();
		statement.close();

		disconnect();

		return listEntity;
	}

	public boolean delete(Rezult entity) throws SQLException {
		String sql = "DELETE FROM result where id = ?";

		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, entity.getId());

		boolean rowDeleted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowDeleted;
	}

	public boolean update(Rezult entity) throws SQLException {
		String sql = "UPDATE result SET lottery = ?, numbers=?, winner=?";
		sql += " WHERE id = ?";
		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, entity.getLottery().getId());		
		statement.setString(2, entity.getNumbers());		
		statement.setInt(3, entity.getWinner().getId());
		statement.setInt(4, entity.getId());
		
		boolean rowUpdated = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowUpdated;
	}

	public Rezult get(int id) throws SQLException {
		Rezult entity = null;
		String sql = "SELECT * FROM result WHERE id = ?";

		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, id);

		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {
			
			int lottery= resultSet.getInt("lottery");
			String numbers= resultSet.getString("numbers");
			int winner= resultSet.getInt("winner");
			
			Ticket Twinner = new TicketDAO(jdbcURL, jdbcUsername, jdbcPassword).get(winner);
			
			Lottery lType = new LotteryDAO(jdbcURL, jdbcUsername, jdbcPassword).get(lottery);
			entity = new Rezult(id, lType,numbers,Twinner);
			
		}

		resultSet.close();
		statement.close();

		return entity;
	}
}
