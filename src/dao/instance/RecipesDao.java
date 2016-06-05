package dao.instance;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.fabric.DaoFabric;
import model.RecipeModel;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

public class RecipesDao extends ParentDao{

	public RecipesDao(String DB_HOST, String DB_PORT, String DB_NAME, String DB_USER, String DB_PWD) {
		super(DB_HOST, DB_PORT, DB_NAME, DB_USER, DB_PWD);
	}

	public void addRecipe(RecipeModel recipe) {
		PreparedStatement query;
		try {
			connection = connect();

			String sql = "INSERT INTO cookbcf.recipe" + "(description, title, expertise, nb_people, duration, type)"
					+ "VALUES(?, ?, ?, ?, ?, ?)";

			query = connection.prepareStatement(sql);

			query.setString(1, recipe.getDescription());
			query.setString(2, recipe.getTitle());
			query.setInt(3, recipe.getExpertise());
			query.setInt(4, recipe.getNbpeople());
			query.setInt(5, recipe.getDurationInt());
			query.setString(6, recipe.getType());

			query.executeUpdate();

			query.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<RecipeModel> getAllRecipes() {
		List<RecipeModel> recipeList = new ArrayList<>();
		Statement query;

		try {
			// create connection
			connection = connect();
			query = connection.createStatement();

			String sql = "SELECT description, title, expertise, nb_people, duration, type FROM recipe";

			ResultSet result = query.executeQuery(sql);

			while (result.next()) {
				recipeList.add(new RecipeModel(result.getString("title"), result.getString("description"),
						result.getInt("expertise"), result.getInt("nb_people"), result.getInt("duration"),
						result.getString("type")));
			}

			query.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return recipeList;
	}

	public RecipeModel getRecipeByTitle(String title) {

		RecipeModel recipeModel = null;
		PreparedStatement preparedStatement;

		try {
			preparedStatement = connect().prepareStatement(
					"select title, description, duration, expertise, nb_people, type from recipe where title = ?");

			preparedStatement.setString(1, title);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next())
				recipeModel = new RecipeModel(resultSet.getString("title"), resultSet.getString("description"),
						resultSet.getInt("expertise"), resultSet.getInt("nb_people"), resultSet.getInt("duration"),
						resultSet.getString("type"));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return recipeModel;
	}

	public List<RecipeModel> searchRecipes(int duration, int expertise, int nbPeople, String type) {
		List<RecipeModel> recipeModels = new ArrayList<>();
		PreparedStatement preparedStatement;

		String sql = "select title, description, duration, expertise, nb_people, type " + "from recipe "
				+ "where duration <= ? and expertise <= ? and nb_people >= ?";
		if (type.compareTo("[ALL]") != 0)
			sql += " and type = ? ";

		try {
			preparedStatement = connect().prepareStatement(sql);

			preparedStatement.setInt(1, duration);
			preparedStatement.setInt(2, expertise);
			preparedStatement.setInt(3, nbPeople);
			if (type.compareTo("[ALL]") != 0)
				preparedStatement.setString(4, type);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				recipeModels.add(new RecipeModel(resultSet.getString("title"), resultSet.getString("description"),
						resultSet.getInt("expertise"), resultSet.getInt("nb_people"), resultSet.getInt("duration"),
						resultSet.getString("type")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return recipeModels;
	}
}
