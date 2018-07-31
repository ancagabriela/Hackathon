package applicationrun.persistence;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import applicationrun.domain.EntityObject;
import applicationrun.utilities.InvalidParamException;
import applicationrun.utilities.NotFoundException;

@Repository
public class EntityRepository {

	@Autowired
	private HelperEntityRepository repository;

	public void save(EntityObject entity) throws InvalidParamException {
		if (entity == null)
			throw new InvalidParamException();
		try {
			repository.save(entity);
		} catch (Exception e) {
			throw new InvalidParamException();
		}
	}

	public EntityObject getEntityByEmail(String email) throws InvalidParamException {
		EntityObject entity = repository.findByEmail(email);
		if (entity == null)
			throw new InvalidParamException();
		return entity;
	}

	public List<EntityObject> getAllEntities() {
		List<EntityObject> result = new ArrayList<>();

		for (EntityObject e : repository.findAll()) {
			result.add(e);
		}

		return result;
	}

	public EntityObject getEntityById(int entityId) throws NotFoundException {

		try {
			return repository.findById(entityId).get();
		} catch (Exception exception) {
			throw new NotFoundException();
		}
	}

}
