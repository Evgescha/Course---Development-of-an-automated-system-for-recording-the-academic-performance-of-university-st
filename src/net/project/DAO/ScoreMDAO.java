package net.project.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import net.project.entity.ScoreM;
import net.project.entity.StudentM;
import net.project.entity.SubjectM;

public class ScoreMDAO {
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;

	public ScoreMDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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

	public boolean insert(ScoreM entity) throws SQLException {
		String sql = "INSERT INTO scoreM (studentM,subjectM,score,semestr) VALUES (?,?,?,?)";
		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);

		statement.setLong(1, entity.getStudent().getId());
		statement.setLong(2, entity.getSubject().getId());
		statement.setInt(3, entity.getScore());
		statement.setInt(4, entity.getSemestr());

		boolean rowInserted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowInserted;
	}

	public List<ScoreM> listAll() throws SQLException {
		List<ScoreM> listEntity = new ArrayList<>();

		String sql = "SELECT * FROM scoreM";

		connect();

		Statement statement = jdbcConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			int studentId = resultSet.getInt("studentM");
			int subjectId = resultSet.getInt("subjectM");
			int score = resultSet.getInt("score");
			int semestr = resultSet.getInt("semestr");

			StudentM student = new StudentMDAO(jdbcURL, jdbcUsername, jdbcPassword).get(studentId);
			SubjectM subject = new SubjectMDAO(jdbcURL, jdbcUsername, jdbcPassword).get(subjectId);
			ScoreM entity = new ScoreM(id, student, subject, score, semestr);
			listEntity.add(entity);
		}

		resultSet.close();
		statement.close();

		disconnect();

		return listEntity;
	}

	public boolean delete(ScoreM entity) throws SQLException {
		String sql = "DELETE FROM scoreM where id = ?";

		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, entity.getId());

		boolean rowDeleted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowDeleted;
	}

	public boolean update(ScoreM entity) throws SQLException {
		String sql = "UPDATE scoreM SET studentM= ?, subjectM=?, score=?, semestr=? WHERE id = ?";
		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setLong(1, entity.getStudent().getId());
		statement.setLong(2, entity.getSubject().getId());
		statement.setInt(3, entity.getScore());
		statement.setInt(4, entity.getSemestr());
		statement.setLong(5, entity.getId());

		boolean rowUpdated = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowUpdated;
	}

	public ScoreM get(int id) throws SQLException {
		ScoreM entity = null;
		String sql = "SELECT * FROM scoreM WHERE id = ?";
		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, id);

		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {

			int studentId = resultSet.getInt("studentM");
			int subjectId = resultSet.getInt("subjectM");
			int score = resultSet.getInt("score");
			int semestr = resultSet.getInt("semestr");

			StudentM student = new StudentMDAO(jdbcURL, jdbcUsername, jdbcPassword).get(studentId);
			SubjectM subject = new SubjectMDAO(jdbcURL, jdbcUsername, jdbcPassword).get(subjectId);
			entity = new ScoreM(id, student, subject, score, semestr);

		}

		resultSet.close();
		statement.close();

		return entity;
	}
}
