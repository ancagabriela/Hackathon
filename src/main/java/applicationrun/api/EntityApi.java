package applicationrun.api;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import applicationrun.application.EntityController;
import applicationrun.applicationDTO.EntityDTO;
import applicationrun.utilities.InvalidParamException;
import applicationrun.utilities.NotFoundException;

@CrossOrigin
@RestController
public class EntityApi {

	@Autowired
	private EntityController controller;

	@PostMapping(value = "/entities", produces = "application/json;charset=UTF-8")
	public String register(@RequestBody String jEntity) throws InvalidParamException, NotFoundException {

		EntityDTO newEntity = new Gson().fromJson(jEntity, EntityDTO.class);

		EntityDTO entity = controller.register(newEntity);

		return toJson(entity);
	}

	private String toJson(Object object){
		
		Gson gson=new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		return gson.toJson(object);
	}
	
	@GetMapping(value = "/entities", produces = "application/json;charset=UTF-8")
	public String listEntities() throws NotFoundException {

		List<EntityDTO> entities = controller.listEntities();

		return toJson(entities);
	}

	@PostMapping(value = "/login", produces = "application/json;charset=UTF-8")
	public String login(@RequestBody String jEntity) throws NotFoundException, InvalidParamException {

		EntityDTO entityToLog = new Gson().fromJson(jEntity, EntityDTO.class);

		EntityDTO entity = controller.login(entityToLog);

		return toJson(entity);
	}

	@GetMapping(value = "/entities/{entityId}", produces = "application/json;charset=UTF-8")
	public String getEntity(@PathVariable int entityId) throws NotFoundException {

		EntityDTO entity = controller.getEntityDTO(entityId);

		return toJson(entity);
	}

	@PutMapping(value = "/entities/{entityId}", produces = "application/json;charset=UTF-8")
	public String UpdateEntity(@PathVariable int entityId, @RequestBody String jEntity)
			throws NotFoundException, InvalidParamException {

		EntityDTO entityToUpdate = new Gson().fromJson(jEntity, EntityDTO.class);

		EntityDTO entity = controller.updateEntity(entityId, entityToUpdate);

		return toJson(entity);
	}
}
