package services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.GroupDAO;
import domain.Group;

@Service
@Transactional
public class GroupService {
	
	@Autowired
	private GroupDAO groupDao;

	public void save(Group group) {
		groupDao.save(group);
	}

	public List<Group> findAll() {
		return groupDao.findAll();
	}
	
}
