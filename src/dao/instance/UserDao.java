package dao.instance;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.UserModelBean;

public class UserDao extends ParentDao {

	public UserDao(String DB_HOST, String DB_PORT, String DB_NAME, String DB_USER, String DB_PWD) {
		super(DB_HOST, DB_PORT, DB_NAME, DB_USER, DB_PWD);
	}

	public void addUser(UserModelBean user) {
		// Création de la requête
		PreparedStatement query;
		try {
			// create connection
			connection = connect();
			String sql = "INSERT INTO user(surname, lastname, email, login, pwd, age) VALUES(?, ?, ?, ?, ?, ?)";
			query = connection.prepareStatement(sql);
			query.setString(1, user.getSurname());
			query.setString(2, user.getLastname());
			query.setString(3, user.getEmail());
			query.setString(4, user.getLogin());
			query.setString(5, user.getPwd());
			query.setInt(6, user.getAge());
			query.executeUpdate();
			// connection.commit();
			query.close();
			// connection.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public List<UserModelBean> getAllUser() {
		// return value
		List<UserModelBean> userList = new ArrayList<>();
		Statement query;
		try {
			// create connection
			connection = connect();
			query = connection.createStatement();
			String sql = "SELECT surname, lastname, login, email, pwd, age FROM user";
			ResultSet result = query.executeQuery(sql);
			while (result.next()) {
				userList.add(new UserModelBean(result.getString("surname"), result.getString("lastname"),
						result.getString("email"), result.getString("login"), result.getString("pwd"),
						result.getInt("age")));
			}
			query.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}

	public UserModelBean checkUser(String login, String pwd) {
		PreparedStatement preparedStatement;
		UserModelBean userCheck = null;
		try {
			// create connection
			connection = connect();
			String sql = "SELECT * FROM user where login = ? and pwd = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, login);
			preparedStatement.setString(2, pwd);

			ResultSet result = preparedStatement.executeQuery();

			if (result.next()) {
				userCheck = new UserModelBean(result.getString("lastname"), result.getString("surname"),
						result.getString("email"), result.getString("login"), result.getString("pwd"),
						result.getInt("age"));
			}
			preparedStatement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userCheck;
	}

	public UserModelBean checkAdminUser(String login, String pwd) {
		PreparedStatement preparedStatement;
		UserModelBean userCheck = null;
		try {
			// create connection
			connection = connect();
			String sql = "SELECT * FROM user where login = ? and pwd = ? and isAdmin = 1";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, login);
			preparedStatement.setString(2, pwd);

			ResultSet result = preparedStatement.executeQuery();

			if (result.next()) {
				userCheck = new UserModelBean(result.getString("lastname"), result.getString("surname"),
						result.getString("email"), result.getString("login"), result.getString("pwd"),
						result.getInt("age"), result.getDate("lastConnection"));
			}
			preparedStatement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userCheck;
	}

}
