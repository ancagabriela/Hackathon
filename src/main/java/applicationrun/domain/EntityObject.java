package applicationrun.domain;

import applicationrun.utilities.Encryptor;
import applicationrun.utilities.InvalidParamException;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "Entity")
public class EntityObject {
	@Id
	private Integer entityId;
	private String name;
	private String email;
	private String entityPassword;
	
	public EntityObject(String name, String email, String password) throws InvalidParamException {
		checkValidName(name);
		checkValidEmail(email);
		checkValidPassword(password);
		this.name = name;
		this.email = email;
		this.entityPassword = Encryptor.encryptPassword(password);
	}

	private void checkValidName(String name) throws InvalidParamException {
		if (name.equals(""))
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
		Encryptor.checkIfPasswordMatches(password, this.entityPassword);
	}

	public Integer getId() {
		return entityId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) throws InvalidParamException {
		checkValidName(name);
		this.name = name;
	}

	public void setEmail(String email) throws InvalidParamException {
		checkValidEmail(email);
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return entityPassword;
	}
	
}
