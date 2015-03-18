package services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.GroupDAO;
import domain.Group;
import domain.User;

@Service
@Transactional
public class GroupService {
	
	@Autowired
	private GroupDAO groupDao;

	public List<Group> findAll() {
		return groupDao.findAll();
	}
	
	public Group findGroupByName(String name){
		return groupDao.findGroupByName(name);
	}
	
	public void save(Group group) {
		groupDao.save(group);
	}
	
	public void delete(Group group){
		groupDao.delete(group);
	}
	
	public void modify(Group group, String nameOld){
		groupDao.modify(group.getName(),group.getDescription(), group.getAvatar(), nameOld);
	}
	
	public void addUser(User user, String groupName) {
		groupDao.addUser(user.getId(), groupName);
	}
	
}
