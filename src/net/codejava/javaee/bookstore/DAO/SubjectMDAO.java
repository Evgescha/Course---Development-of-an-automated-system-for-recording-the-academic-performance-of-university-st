package net.codejava.javaee.bookstore.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import net.project.entity.SubjectM;
import net.project.entity.TeacherM;

public class SubjectMDAO {
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;

	public SubjectMDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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

	public boolean insert(SubjectM entity) throws SQLException {
		String sql = "INSERT INTO subjectM (name, teacherM) VALUES (?,?)";
		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);

		statement.setString(1, entity.getName());
		statement.setLong(2, entity.getTeacher().getId());

		boolean rowInserted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowInserted;
	}

	public List<SubjectM> listAll() throws SQLException {
		List<SubjectM> listEntity = new ArrayList<>();

		String sql = "SELECT * FROM subjectM";

		connect();

		Statement statement = jdbcConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			String name = resultSet.getString("name");
			int groupId = resultSet.getInt("teacherM");

			TeacherM group = new TeacherMDAO(jdbcURL, jdbcUsername, jdbcPassword).get(groupId);
			SubjectM entity = new SubjectM(id, name, group);
			listEntity.add(entity);
		}

		resultSet.close();
		statement.close();

		disconnect();

		return listEntity;
	}

	public boolean delete(SubjectM entity) throws SQLException {
		String sql = "DELETE FROM subjectM where id = ?";

		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, entity.getId());

		boolean rowDeleted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowDeleted;
	}

	public boolean update(SubjectM entity) throws SQLException {
		String sql = "UPDATE subjectM SET name = ?, teacherM=?";
		sql += " WHERE id = ?";
		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, entity.getName());
		statement.setLong(2, entity.getTeacher().getId());
		statement.setLong(3, entity.getId());

		boolean rowUpdated = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowUpdated;
	}

	public SubjectM get(int id) throws SQLException {
		SubjectM entity = null;
		String sql = "SELECT * FROM subjectM WHERE id = ?";
		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, id);

		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {

			String name = resultSet.getString("name");
			int groupId = resultSet.getInt("teacherM");

			TeacherM group = new TeacherMDAO(jdbcURL, jdbcUsername, jdbcPassword).get(groupId);
			entity = new SubjectM(id, name, group);

		}

		resultSet.close();
		statement.close();

		return entity;
	}
}
