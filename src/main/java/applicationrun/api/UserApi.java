package applicationrun.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import applicationrun.application.UserController;
import applicationrun.applicationDTO.UserDTO;
import applicationrun.utilities.InvalidParamException;
import applicationrun.utilities.NotFoundException;

@CrossOrigin
@RestController
@RequestMapping("/hackathon")
public class UserApi {

	@Autowired
	private UserController controller;

	@RequestMapping(value = "/users", method= RequestMethod.POST)
	public String register(@RequestBody String jUser) throws Exception, JsonSyntaxException {

		UserDTO newUser = new Gson().fromJson(jUser, UserDTO.class);

		UserDTO user = controller.register(newUser);

		return toJson(user);
	}

	private String toJson(Object object){
		
		Gson gson=new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		return gson.toJson(object);
	}
	
	@GetMapping(value = "/users", produces = "application/json;charset=UTF-8")
	public String listUsers() throws NotFoundException {

		List<UserDTO> users = controller.listUsers();

		return toJson(users);
	}

	@PostMapping(value = "/login", produces = "application/json;charset=UTF-8")
	public String login(@RequestBody String jUser) throws NotFoundException, InvalidParamException {

		UserDTO userToLog = new Gson().fromJson(jUser, UserDTO.class);

		UserDTO user = controller.login(userToLog);

		return toJson(user);
	}

	@GetMapping(value = "/users/{userId}", produces = "application/json;charset=UTF-8")
	public String getUser(@PathVariable int userId) throws NotFoundException {

		UserDTO user = controller.getUserDTO(userId);

		return toJson(user);
	}

	@PutMapping(value = "/users/{userId}", produces = "application/json;charset=UTF-8")
	public String UpdateUser(@PathVariable int userId, @RequestBody String jUser)
			throws NotFoundException, InvalidParamException {

		UserDTO userToUpdate = new Gson().fromJson(jUser, UserDTO.class);

		UserDTO user = controller.updateUser(userId, userToUpdate);

		return toJson(user);
	}
}
