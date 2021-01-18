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


public class LotteryDAO {
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;

	public LotteryDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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

	public boolean insert(Lottery entity) throws SQLException {
		String sql = "INSERT INTO lottery (lottery_type, dates, prize) VALUES (?,?,?)";
		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, entity.getType().getId());
		statement.setDate(2, entity.getDates());
		statement.setInt(3, entity.getPrize().getId());

		boolean rowInserted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowInserted;
	}

	public List<Lottery> listAll() throws SQLException {
		List<Lottery> listEntity = new ArrayList<>();

		String sql = "SELECT * FROM lottery";

		connect();

		Statement statement = jdbcConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			int type= resultSet.getInt("lottery_type");
			Date dates= resultSet.getDate("dates");
			int prize= resultSet.getInt("prize");

			LotteryType lType = new LotteryTypeDAO(jdbcURL, jdbcUsername, jdbcPassword).get(type);
			Prize lPrize = new PrizeDAO(jdbcURL, jdbcUsername, jdbcPassword).get(prize);
			Lottery entity = new Lottery(id, lType,dates,lPrize);
			listEntity.add(entity);
		}

		resultSet.close();
		statement.close();

		disconnect();

		return listEntity;
	}

	public boolean delete(Lottery entity) throws SQLException {
		String sql = "DELETE FROM lottery where id = ?";

		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, entity.getId());

		boolean rowDeleted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowDeleted;
	}

	public boolean update(Lottery entity) throws SQLException {
		String sql = "UPDATE lottery SET lottery_type = ?, dates=?, prize=?";
		sql += " WHERE id = ?";
		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, entity.getType().getId());
		statement.setDate(2, entity.getDates());
		statement.setInt(3, entity.getPrize().getId());
		statement.setInt(4, entity.getId());
		
		boolean rowUpdated = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowUpdated;
	}

	public Lottery get(int id) throws SQLException {
		Lottery entity = null;
		String sql = "SELECT * FROM lottery WHERE id = ?";

		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, id);

		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {
			int type= resultSet.getInt("lottery_type");
			Date dates= resultSet.getDate("dates");
			int prize= resultSet.getInt("prize");

			LotteryType lType = new LotteryTypeDAO(jdbcURL, jdbcUsername, jdbcPassword).get(type);
			Prize lPrize = new PrizeDAO(jdbcURL, jdbcUsername, jdbcPassword).get(prize);
			entity = new Lottery(id, lType,dates,lPrize);
		}

		resultSet.close();
		statement.close();

		return entity;
	}
}
