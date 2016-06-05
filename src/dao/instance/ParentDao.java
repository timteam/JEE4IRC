package dao.instance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class ParentDao {
	protected Connection connection;
	private String dB_HOST;
	private String dB_PORT;
	private String dB_NAME;
	private String dB_USER;
	private String dB_PWD;

	public ParentDao(String DB_HOST, String DB_PORT, String DB_NAME, String DB_USER, String DB_PWD) {
		dB_HOST = DB_HOST;
		dB_PORT = DB_PORT;
		dB_NAME = DB_NAME;
		dB_USER = DB_USER;
		dB_PWD = DB_PWD;
	}
	
	protected Connection connect(){
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://"+dB_HOST+":"+dB_PORT+"/"+dB_NAME,dB_USER, dB_PWD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
}
