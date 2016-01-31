package utrwalaniedanych.hibernate.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
	@NamedQuery(name = "Aptekarz.getAll", query = "Select c from Aptekarz c"),
})
public class Aptekarz {
	
	private Long id;
	private String imie;
	
	
	public Aptekarz(){}
	
	public Aptekarz(String imie){
		this.imie = imie;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImie() {
		return imie;
	}

	public void setImie(String imie) {
		this.imie = imie;
	}

}
