package domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@PrimaryKeyJoinColumn(name="ID_U")
public class Student extends User {
	
	@Column (name = "numStudent", unique=true)
	@NotNull(message = "Veuillez saisir votre numéro d'étudiant !")
	@NotEmpty(message = "Veuillez saisir votre numéro d'étudiant !")
	private String numStudent;
	
	@Column (name = "inscriptUnivDate")
	@Temporal(TemporalType.DATE)
	@Past (message = "Veuillez saisir une date dans le passé !")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@NotNull (message = "Veuillez saisir votre date d'inscription !")
	private Date inscriptUnivDate;
	
	@Column (name = "promo")
	private String promo;

	
	public String getNumStudent() {
		return numStudent;
	}

	public void setNumStudent(String numStudent) {
		this.numStudent = numStudent;
	}

	public Date getInscriptUnivDate() {
		return inscriptUnivDate;
	}

	public void setInscriptUnivDate(Date inscriptUnivDate) {
		this.inscriptUnivDate = inscriptUnivDate;
	}

	public String getPromo() {
		return promo;
	}

	public void setPromo(String promo) {
		this.promo = promo;
	}

}