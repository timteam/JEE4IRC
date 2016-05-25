package model;

import javax.annotation.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped //Durée de vue uniquement lors d'une requète
//même propriétés que UserModelBean mais portée différente
public class UserSubmissionModelBean extends UserModelBean{

	
	public UserSubmissionModelBean() {
		
	}
}
