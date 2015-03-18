package services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.MembershipGroupDAO;
import domain.IdMembershipGroup;
import domain.MembershipGroup;
import domain.User;

@Service
@Transactional
public class MembershipGroupService {
	
	@Autowired
	private MembershipGroupDAO membershipGroupDAO;
	
	public void addUser(User user, String groupName) {
		MembershipGroup membershipGroup = new MembershipGroup();
		IdMembershipGroup idMembershipGroup = new IdMembershipGroup();
		idMembershipGroup.setId(user.getId());
		idMembershipGroup.setNameG(groupName);
		membershipGroup.setIdMembershipGroup(idMembershipGroup);
		membershipGroupDAO.save(membershipGroup);
	}
	
}
