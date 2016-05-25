package dao.instance;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import model.UserModelBean;
import model.UserSubmissionModelBean;

public class UserDao {
	private Connection connection;
	private String dB_HOST;
	private String dB_PORT;
	private String dB_NAME;
	private String dB_USER;
	private String dB_PWD;

	public UserDao(String DB_HOST, String DB_PORT, String DB_NAME,
			String DB_USER, String DB_PWD) {
		dB_HOST = DB_HOST;
		dB_PORT = DB_PORT;
		dB_NAME = DB_NAME;
		dB_USER = DB_USER;
		dB_PWD = DB_PWD;
	}

	public void addUser(UserModelBean user) {
		// Création de la requête
		java.sql.PreparedStatement query;
		try {
			// create connection
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://"
					+ dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);
			query = connection
					.prepareStatement("Insert into UserModel (lastname,surname,login,age,pwd) values (?,?,?,?,?)");
			query.setString(1, user.getLastname());
			query.setString(2, user.getSurname());
			query.setString(3, user.getLogin());
			query.setInt(4, user.getAge());
			query.setString(5, user.getPwd());

			query.executeUpdate();
			// close
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<UserModelBean> getAllUser() {
		// return value
		ArrayList<UserModelBean> userList = new ArrayList<UserModelBean>();
		java.sql.Statement query;
		try {
			// create connection
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://"
					+ dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);
			// TODOA l’image de DB.java créer une réquète permettant de
			// récupérer l’ensemble des utilisateurs contenu dans la base et de
			// les placer dans uneliste
			query = connection.createStatement();
			// Executer puis parcourir les résultats
			java.sql.ResultSet rs = query.executeQuery("SELECT * FROM UserModel");

			while (rs.next()) {
				userList.add(new UserModelBean(rs.getString("lastname"), rs
						.getString("surname"), rs.getString("login"), rs
						.getInt("age"), rs.getString("pwd")));
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}

	public UserModelBean checkUser(String login, String pwd) {
		java.sql.PreparedStatement query;
		UserModelBean userbean = null;
		try {
			// create connection
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://"
					+ dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);
			// TODOA l’image de DB.java créer une réquète permettant de
			// récupérer l’ensemble des utilisateurs contenu dans la base et de
			// les placer dans uneliste
			query = connection
					.prepareStatement("SELECT * FROM UserModel where UPPER(login) = UPPER(?) And pwd = ?");
			query.setString(1, login);
			query.setString(2, pwd);

			// Executer puis parcourir les résultats
			java.sql.ResultSet rs = query.executeQuery();

			while (rs.next()) {
				userbean = new UserModelBean(rs.getString("lastname"),
						rs.getString("surname"), rs.getString("login"),
						rs.getInt("age"), rs.getString("pwd"));
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userbean;
	}

	public boolean userExists(UserSubmissionModelBean userSubmitted) {

		java.sql.PreparedStatement query;
		boolean exists = false;
		try {
			// create connection
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://"
					+ dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);
			// TODOA l’image de DB.java créer une réquète permettant de
			// récupérer l’ensemble des utilisateurs contenu dans la base et de
			// les placer dans uneliste
			query = connection
					.prepareStatement("SELECT * FROM UserModel where UPPER(login) = UPPER(?)");
			query.setString(1, userSubmitted.getLogin());

			// Executer puis parcourir les résultats
			java.sql.ResultSet rs = query.executeQuery();

			if (rs.next()) {
				exists = true;
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return exists;
	}

}
