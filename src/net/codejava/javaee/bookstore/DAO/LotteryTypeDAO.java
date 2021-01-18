package net.codejava.javaee.bookstore.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import net.codejava.javaee.bookstore.entity.LotteryType;


public class LotteryTypeDAO {
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;

	public LotteryTypeDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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

	public boolean insert(LotteryType entity) throws SQLException {
		String sql = "INSERT INTO lottery_type (name) VALUES (?)";
		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, entity.getName());

		boolean rowInserted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowInserted;
	}

	public List<LotteryType> listAll() throws SQLException {
		List<LotteryType> listEntity = new ArrayList<>();

		String sql = "SELECT * FROM lottery_type";

		connect();

		Statement statement = jdbcConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			String name = resultSet.getString("name");

			LotteryType entity = new LotteryType(id, name);
			listEntity.add(entity);
		}

		resultSet.close();
		statement.close();

		disconnect();

		return listEntity;
	}

	public boolean delete(LotteryType entity) throws SQLException {
		String sql = "DELETE FROM lottery_type where id = ?";

		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, entity.getId());

		boolean rowDeleted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowDeleted;
	}

	public boolean update(LotteryType entity) throws SQLException {
		String sql = "UPDATE lottery_type SET name = ?";
		sql += " WHERE id = ?";
		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, entity.getName());
		statement.setInt(2, entity.getId());

		boolean rowUpdated = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowUpdated;
	}

	public LotteryType get(int id) throws SQLException {
		LotteryType entity = null;
		String sql = "SELECT * FROM lottery_type WHERE id = ?";

		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, id);

		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {
			String name = resultSet.getString("name");

			entity = new LotteryType(id, name);
		}

		resultSet.close();
		statement.close();

		return entity;
	}
}
