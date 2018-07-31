package applicationrun.applicationDTO;
import com.google.gson.annotations.Expose;

import applicationrun.domain.EntityObject;
import applicationrun.utilities.NotFoundException;

public class EntityDTO {
	@Expose
	private int entityId;
	@Expose
	private String entityName, entityEmail;	
	private String entityPassword;

	public EntityDTO(EntityObject entity) throws NotFoundException {
		if (entity == null)
			throw new NotFoundException();

		this.entityName = entity.getName();
		this.entityEmail = entity.getEmail();
		this.entityPassword = entity.getPassword();
		this.entityId = entity.getId();
	}

	public int getId() {
		return entityId;
	}

	public String getName() {
		if(entityName == null) return "";
		return entityName;
	}
	
	public String getEmail() {
		if(entityEmail == null) return "";
		return entityEmail;
	}

	public String getPassword() {
		if(entityPassword == null) return "";
		return entityPassword;
	}

	@Override
	public String toString() {
		return entityName;
	}

}