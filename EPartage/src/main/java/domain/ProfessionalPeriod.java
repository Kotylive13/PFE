package domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * Class representing users professional period
 * @author 
 *
 */

@Entity
@IdClass(IdPeriod.class)
public class ProfessionalPeriod {
	
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	@Column (name = "ID_P")
	private Integer id;
	
	
	@Column (name = "START", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date start;
	
	
	@Column (name = "END", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date end;
	
	@Column (name = "NAME", nullable = false)
	private String name;
	
	@Column (name = "DESCRIPTION", nullable = false, columnDefinition="TEXT")
	private String description;
	
	@Id
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "ID_U")
	private User user;
	
	
	public ProfessionalPeriod(){
		
	}


	public Date getStart() {
		return start;
	}


	public void setStart(Date start) {
		this.start = start;
	}


	public Date getEnd() {
		return end;
	}


	public void setEnd(Date end) {
		this.end = end;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Integer getId() {
		return id;
	}
	
	

}
