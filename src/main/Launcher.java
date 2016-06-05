package main;

import java.util.List;

import model.RecipeModel;

import model.UserModelBean;
import dao.fabric.DaoFabric;
import dao.instance.RecipesDao;
import dao.instance.UserDao;

public class Launcher {
	public static void main(String[] args) {
		UserDao userDao=DaoFabric.getInstance().createUserDao();
		RecipesDao recipesDao=DaoFabric.getInstance().createRecipesDao();
		UserModelBean user1=new UserModelBean("benjamin","grenier","benji2092@hotmail.fr","benji2092","test",24);
		RecipeModel recipe1=new RecipeModel("Fish Salad","bla bla bal bla",5, 180, 10,"salad");
		RecipeModel recipe2=new RecipeModel("Fresh Meat","bla bla bal bla",1, 20, 1,"meat");
		userDao.addUser(user1);
		recipesDao.addRecipe(recipe1);
		recipesDao.addRecipe(recipe2);
		List<UserModelBean> userList=userDao.getAllUser();
		List<RecipeModel> recipeList=recipesDao.getAllRecipes();
		for(UserModelBean userTmp:userList){
			System.out.println("User added:"+userTmp);
			}	
		for(RecipeModel recipeTmp:recipeList){
			System.out.println("User added:"+recipeTmp);
			}
		}
}
