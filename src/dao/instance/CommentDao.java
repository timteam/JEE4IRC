package dao.instance;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.CommentModelBean;

public class CommentDao extends ParentDao {

	public CommentDao(String DB_HOST, String DB_PORT, String DB_NAME, String DB_USER, String DB_PWD) {
		super(DB_HOST, DB_PORT, DB_NAME, DB_USER, DB_PWD);
	}

	public List<CommentModelBean> getAllCommentsFromRecipeTitle(String recipe_title) {
		List<CommentModelBean> list = new ArrayList<>();
		PreparedStatement preparedStatement;

		try {
			// create connection
			connection = connect();

			String sql = "SELECT content, id, recipe_title, user_login, date, rate FROM comment where recipe_title = ? ORDER BY date";
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, recipe_title);

			ResultSet result = preparedStatement.executeQuery();

			while (result.next()) {
				list.add(new CommentModelBean(result.getString("content"), result.getInt("id"),
						result.getString("user_login"), result.getString("recipe_title"), result.getDate("date"),
						result.getInt("rate")));
			}

			preparedStatement.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public void addComment(CommentModelBean comment) {
		// Création de la requête
		PreparedStatement query;
		try {
			// create connection
			connection = connect();
			String sql = "INSERT INTO comment" + " (content, rate, date, user_login, recipe_title)"
					+ " VALUES(?, ?, ?, ?, ?)";
			query = connection.prepareStatement(sql);
			query.setString(1, comment.getContent());
			query.setInt(2, comment.getRate());
			query.setDate(3, new java.sql.Date(new java.util.Date().getTime()));
			query.setString(4, comment.getUser_login());
			query.setString(5, comment.getRecipe_title());
			query.executeUpdate();
			query.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
