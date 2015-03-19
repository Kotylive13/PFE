package domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class MembershipGroup {
	
	@EmbeddedId
	private IdMembershipGroup idMembershipGroup;
//
//	@MapsId("id_u")
//	@JoinColumn(name = "id_u")
//	private User receiver;
//	
//	@MapsId("groups")
//	@JoinColumn(name = "groups")
//	private Group group;
	
	public IdMembershipGroup getIdMembershipGroup() {
		return idMembershipGroup;
	}

	public void setIdMembershipGroup(IdMembershipGroup idMembershipGroup) {
		this.idMembershipGroup = idMembershipGroup;
	}
	
}
