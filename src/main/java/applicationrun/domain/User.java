package applicationrun.domain;

import applicationrun.utilities.Encryptor;
import applicationrun.utilities.InvalidParamException;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity(name = "User")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="userId")
	private Integer userId;
	private String userName;
	private String userSurname;
	private String userEmail;
	private String userPassword;
	
	public User() {
	}
	
	public User (String name, String surname, String email, String password) throws InvalidParamException{
		checkValidName(name);
		checkValidSurname(surname);
		checkValidEmail(email);
		checkValidPassword(password);
		this.userName = name;
		this.userSurname = surname;
		this.userEmail = email;
		this.userPassword = Encryptor.encryptPassword(password);
	}

	private void checkValidName(String name) throws InvalidParamException {
		if (name.equals(""))
			throw new InvalidParamException();
	}
	private void checkValidSurname(String surname) throws InvalidParamException {
		if (surname.equals(""))
			throw new InvalidParamException();
	}

	private void checkValidEmail(String email) throws InvalidParamException {
		if (email.equals("") || !email.contains(".com") || !email.contains("@"))
			throw new InvalidParamException();
	}
	
	 public void checkValidPassword(String password) throws InvalidParamException {
		 if (password.length() < 7)
		 throw new InvalidParamException();
	}

	public void checkPasswordIsCorrect(String password) throws InvalidParamException {
		Encryptor.checkIfPasswordMatches(password, this.userPassword);
	}

	public Integer getId() {
		return userId;
	}

	public String getName() {
		return userName;
	}
	
	public String getSurname() {
		return userSurname;
	}

	public void setName(String name) throws InvalidParamException {
		checkValidName(name);
		this.userName = name;
	}
	public void setSurname(String surname) throws InvalidParamException {
		checkValidSurname(surname);
		this.userSurname = surname;
	}

	public void setEmail(String email) throws InvalidParamException {
		checkValidEmail(email);
		this.userEmail = email;
	}

	public String getEmail() {
		return userEmail;
	}

	public String getPassword() {
		return userPassword;
	}
}
