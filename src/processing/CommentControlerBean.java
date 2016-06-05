package processing;

import dao.fabric.DaoFabric;
import dao.instance.CommentDao;
import model.CommentModelBean;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@ManagedBean
@ApplicationScoped
public class CommentControlerBean implements Serializable{

	private CommentDao commentDao;
	public CommentControlerBean() {
		this.commentDao=DaoFabric.getInstance().createCommentDao();
	}



	public void addComment(CommentModelBean comment, String user_login, String recipe_title){
		comment.setUser_login(user_login);
		comment.setRecipe_title(recipe_title);
		this.commentDao.addComment(comment);

		// refresh comments
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		List<CommentModelBean>  listComment = this.getAllCommentsFromRecipeTitle(recipe_title);
		sessionMap.put("listComment", listComment);
	}

	public List<CommentModelBean> getAllCommentsFromRecipeTitle(String recipeTitle){
		return  this.commentDao.getAllCommentsFromRecipeTitle(recipeTitle);
	}

}