package model;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped //Durée de vue uniquement lors d'une requète
//même propriétés que UserModelBean mais portée différente
public class UserSubmissionModelBean extends UserModelBean{
	
	public UserSubmissionModelBean() {
		super();
	}
}