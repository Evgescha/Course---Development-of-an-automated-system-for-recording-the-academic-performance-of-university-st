package net.codejava.javaee.bookstore.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import net.codejava.javaee.bookstore.entity.Prize;


public class PrizeDAO {
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;

	public PrizeDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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

	public boolean insert(Prize entity) throws SQLException {
		String sql = "INSERT INTO prize (name,description) VALUES (?,?)";
		connect();
		System.out.println("INSERT "+entity.getName());
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, entity.getName());
		statement.setString(2, entity.getDescription());

		boolean rowInserted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowInserted;
	}

	public List<Prize> listAll() throws SQLException {
		List<Prize> listEntity = new ArrayList<>();

		String sql = "SELECT * FROM prize";

		connect();

		Statement statement = jdbcConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			String name = resultSet.getString("name");
			String description = resultSet.getString("description");

			Prize entity = new Prize(id, name,description);
			listEntity.add(entity);
		}

		resultSet.close();
		statement.close();

		disconnect();

		return listEntity;
	}

	public boolean delete(Prize entity) throws SQLException {
		String sql = "DELETE FROM prize where id = ?";

		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, entity.getId());

		boolean rowDeleted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowDeleted;
	}

	public boolean update(Prize entity) throws SQLException {
		String sql = "UPDATE prize SET name = ?, description=?";
		sql += " WHERE id = ?";
		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, entity.getName());
		statement.setString(2, entity.getDescription());
		statement.setInt(3, entity.getId());

		boolean rowUpdated = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowUpdated;
	}

	public Prize get(int id) throws SQLException {
		Prize entity = null;
		String sql = "SELECT * FROM prize WHERE id = ?";

		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, id);

		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {
			String name = resultSet.getString("name");
			String description = resultSet.getString("description");

			entity = new Prize(id, name,description);
		}

		resultSet.close();
		statement.close();

		return entity;
	}
}
