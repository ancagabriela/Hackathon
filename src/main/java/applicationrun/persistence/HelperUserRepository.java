package applicationrun.persistence;

import org.springframework.data.repository.CrudRepository;

import applicationrun.domain.User;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

interface HelperUserRepository extends CrudRepository<User, Integer> {

	public User findByEmail(String email);

	public User findByName(String name);
	
	public User findBySurname(String surname);

}
