package dao.instance;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import model.RecipeModelBean;

public class RecipesDao {

	private Connection connection;
	private String dB_HOST;
	private String dB_PORT;
	private String dB_NAME;
	private String dB_USER;
	private String dB_PWD;

	public RecipesDao(String DB_HOST, String DB_PORT, String DB_NAME,
			String DB_USER, String DB_PWD) {
		// TODO
		dB_HOST = DB_HOST;
		dB_PORT = DB_PORT;
		dB_NAME = DB_NAME;
		dB_USER = DB_USER;
		dB_PWD = DB_PWD;
	}

	public void addRecipe(RecipeModelBean recipe) {
		// TODO
		// Création de la requête
		java.sql.PreparedStatement query;
		try {
			// create connection
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://"
					+ dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);
			query = connection
					.prepareStatement("Insert into recipes (title,description,expertise,duration,nbpeople,type) values (?,?,?,?,?,?)");
			query.setString(1, recipe.getTitle());
			query.setString(2, recipe.getDescription());
			query.setInt(3, recipe.getExpertise());
			query.setInt(4, recipe.getDuration());
			query.setInt(5, recipe.getNbpeople());
			query.setString(6, recipe.getType());

			query.executeUpdate();
			// close
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<RecipeModelBean> getAllRecipes() {
		// return value
		ArrayList<RecipeModelBean> recipeList = new ArrayList<RecipeModelBean>();
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
			java.sql.ResultSet rs = query.executeQuery("SELECT * FROM recipes");

			while (rs.next()) {
				recipeList.add(new RecipeModelBean(rs.getString("title"), rs
						.getString("description"), rs.getInt("expertise"), rs
						.getInt("duration"), rs.getInt("nbpeople"), rs
						.getString("type")));
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return recipeList;
	}
}
