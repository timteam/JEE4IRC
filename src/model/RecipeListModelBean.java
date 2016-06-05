package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class RecipeListModelBean implements Serializable {
	private List<RecipeModel> recipeList;
	public RecipeListModelBean() {
		recipeList=new ArrayList<RecipeModel>();
	}
	public void addRecipeList(RecipeModel recipe){
		this.recipeList.add(recipe);
	}
	public List<RecipeModel> getRecipeList() {
		return recipeList;
	}
}