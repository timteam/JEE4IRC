package model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.Date;

@ManagedBean
@SessionScoped
public class UserModelBean implements Serializable {

	private String lastname;
	private String surname;
	private String email;
	private String login;
	private String pwd;
	private Date lastConnection;
	private int age;

	public UserModelBean(){}

	public UserModelBean(String surname, String lastname, String email, String login, String pwd, int age) {
		this.lastname = lastname;
		this.surname = surname;
		this.email = email;
		this.login = login;
		this.pwd = pwd;
		this.age = age;
	}

	public UserModelBean(String surname, String lastname, String email, String login, String pwd, int age, Date lastConnection) {
		this.lastname = lastname;
		this.surname = surname;
		this.email = email;
		this.login = login;
		this.pwd = pwd;
		this.age = age;
		this.lastConnection = lastConnection;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getLastConnection() {
		return lastConnection;
	}

	public void setLastConnection(Date lastConnection) {
		this.lastConnection = lastConnection;
	}

	@Override
	public String toString() {
		return"[SURNAME]:"+this.getSurname()+",[LASTNAME]:"+this.getLastname()+",[AGE]:"+this.getAge()+"[EMAIL] : "+this.getEmail()+",[LOGIN]:"+this.getLogin()+",[PWD]:"+this.getPwd();
	}

}
