package model;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class RecipeListModelBean {
	private List<RecipeModelBean> recipeList;
	public RecipeListModelBean() {
		recipeList=new ArrayList<RecipeModelBean>();
	}
	public void addRecipeList(RecipeModelBean recipe){
		this.recipeList.add(recipe);
	}
	public List<RecipeModelBean> getRecipeList() {
		return recipeList;
	}
}