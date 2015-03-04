package domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Test {
	
	@Id
	private Integer id;
	
	@Column 
	private String text;

}
