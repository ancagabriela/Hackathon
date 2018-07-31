package applicationrun.application;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import applicationrun.applicationDTO.EntityDTO;
import applicationrun.domain.EntityObject;
import applicationrun.persistence.EntityRepository;
import applicationrun.utilities.InvalidParamException;
import applicationrun.utilities.NotFoundException;

@Controller
public class EntityController {

	@Autowired
	private EntityRepository entityRepository;

	public EntityDTO register(EntityDTO entityDTO) throws InvalidParamException, NotFoundException {

		EntityObject entity = new EntityObject(entityDTO.getName(), entityDTO.getEmail(), entityDTO.getPassword());

		entityRepository.save(entity);

		return new EntityDTO(entity);
	}

	public EntityDTO login(EntityDTO entityToLog) throws InvalidParamException, NotFoundException {

		EntityObject entity = entityRepository.getEntityByEmail(entityToLog.getEmail());

		entity.checkPasswordIsCorrect(entityToLog.getPassword());

		return new EntityDTO(entity);
	}

	public List<EntityDTO> listEntities() throws NotFoundException {
		List<EntityObject> entityList = entityRepository.getAllEntities();
		List<EntityDTO> entityDTOList = new ArrayList<>();

		if (entityList.isEmpty())
			throw new NotFoundException();

		for (EntityObject e : entityList) {
			entityDTOList.add(new EntityDTO(e));
		}

		return entityDTOList;
	}

	EntityObject getEntity(int entityId) throws NotFoundException {
		EntityObject entity = entityRepository.getEntityById(entityId);

		return entity;
	}

	public EntityDTO getEntityDTO(int entityId) throws NotFoundException {

		EntityObject entity = entityRepository.getEntityById(entityId);

		return new EntityDTO(entity);
	}

	public EntityDTO updateEntity(int entityId, EntityDTO entityToUpdate) throws NotFoundException, InvalidParamException {

		EntityObject entity = entityRepository.getEntityById(entityId);

		if (!entityToUpdate.getEmail().equals(""))
			entity.setEmail(entityToUpdate.getEmail());

		if (!entityToUpdate.getName().equals(""))
			entity.setName(entityToUpdate.getName());

		entityRepository.save(entity);

		return new EntityDTO(entity);
	}

}