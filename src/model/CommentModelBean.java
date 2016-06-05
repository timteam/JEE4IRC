package model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.Date;

@ManagedBean
@RequestScoped
public class CommentModelBean implements Serializable {

	private String content;
	private int id;
	private String user_login;
	private String recipe_title;
	private Date date;
	private int rate;

	public CommentModelBean(){}

	public CommentModelBean(String content, int id, String user_login, String recipe_title, Date date, int rate) {
		this.content = content;
		this.id = id;
		this.user_login = user_login;
		this.recipe_title = recipe_title;
		this.date = date;
		this.rate = rate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUser_login() {
		return user_login;
	}

	public void setUser_login(String user_login) {
		this.user_login = user_login;
	}

	public String getRecipe_title() {
		return recipe_title;
	}

	public void setRecipe_title(String recipe_title) {
		this.recipe_title = recipe_title;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}
}
