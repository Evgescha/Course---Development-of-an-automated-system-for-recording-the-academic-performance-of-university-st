package net.project.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import net.project.entity.GroupM;
import net.project.entity.StudentM;

public class StudentMDAO {
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;

	public StudentMDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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

	public boolean insert(StudentM entity) throws SQLException {
		String sql = "INSERT INTO studentM (name, groupM) VALUES (?,?)";
		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);

		statement.setString(1, entity.getName());
		statement.setLong(2, entity.getGroup().getId());

		boolean rowInserted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowInserted;
	}

	public List<StudentM> listAll() throws SQLException {
		List<StudentM> listEntity = new ArrayList<>();

		String sql = "SELECT * FROM studentM";

		connect();

		Statement statement = jdbcConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			String name = resultSet.getString("name");
			int groupId = resultSet.getInt("groupM");

			GroupM group = new GroupMDAO(jdbcURL, jdbcUsername, jdbcPassword).get(groupId);
			StudentM entity = new StudentM(id, name, group);
			listEntity.add(entity);
		}

		resultSet.close();
		statement.close();

		disconnect();

		return listEntity;
	}

	public boolean delete(StudentM entity) throws SQLException {
		String sql = "DELETE FROM studentM where id = ?";

		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, entity.getId());

		boolean rowDeleted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowDeleted;
	}

	public boolean update(StudentM entity) throws SQLException {
		String sql = "UPDATE studentM SET name = ?, groupM=?";
		sql += " WHERE id = ?";
		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, entity.getName());
		statement.setLong(2, entity.getGroup().getId());
		statement.setLong(3, entity.getId());

		boolean rowUpdated = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowUpdated;
	}

	public StudentM get(int id) throws SQLException {
		StudentM entity = null;
		String sql = "SELECT * FROM studentM WHERE id = ?";
		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, id);

		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {

			String name = resultSet.getString("name");
			int groupId = resultSet.getInt("groupM");

			GroupM group = new GroupMDAO(jdbcURL, jdbcUsername, jdbcPassword).get(groupId);
			entity = new StudentM(id, name, group);

		}

		resultSet.close();
		statement.close();

		return entity;
	}
}
