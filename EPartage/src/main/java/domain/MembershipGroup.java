package domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class MembershipGroup {
	
	@EmbeddedId
	private IdMembershipGroup idMembershipGroup;

	
	public IdMembershipGroup getIdMembershipGroup() {
		return idMembershipGroup;
	}

	public void setIdMembershipGroup(IdMembershipGroup idMembershipGroup) {
		this.idMembershipGroup = idMembershipGroup;
	}
	
}
