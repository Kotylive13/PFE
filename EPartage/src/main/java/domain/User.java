package domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;


@Entity
@NamedQueries({
    @NamedQuery(name = User.FIND_ALL, query = "SELECT u FROM User u"),
})
public class User {

	public static final String FIND_ALL = "FIND_ALL_USERS";
	@Id
	private String id;
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}
	
	
	
	
	
	
}
