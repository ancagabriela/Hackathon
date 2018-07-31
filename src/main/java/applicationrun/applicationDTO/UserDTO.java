package applicationrun.applicationDTO;
import com.google.gson.annotations.Expose;

import applicationrun.domain.User;
import applicationrun.utilities.NotFoundException;

public class UserDTO {
	@Expose
	private int userId;
	@Expose
	private String userName, userSurname, userEmail;	
	private String userPassword;

	public UserDTO(User user) throws NotFoundException {
		if (user == null)
			throw new NotFoundException();

		this.userName = user.getName();
		this.userSurname = user.getSurname();
		this.userEmail = user.getEmail();
		this.userPassword = user.getPassword();
		this.userId = user.getId();
	}

	public int getId() {
		return userId;
	}

	public String getName() {
		if(userName == null) return "";
		return userName;
	}
	
	public String getSurname() {
		if(userSurname == null) return "";
		return userSurname;
	}

	public String getEmail() {
		if(userEmail == null) return "";
		return userEmail;
	}

	public String getPassword() {
		if(userPassword == null) return "";
		return userPassword;
	}

	@Override
	public String toString() {
		return userName;
	}

}