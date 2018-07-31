package applicationrun.persistence;

import org.springframework.data.repository.CrudRepository;

import applicationrun.domain.EntityObject;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

interface HelperEntityRepository extends CrudRepository<EntityObject, Integer> {

	public EntityObject findByEmail(String email);

	public EntityObject findByName(String name);

}
