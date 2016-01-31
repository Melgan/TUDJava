package utrwalaniedanych.hibernate.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({
	@NamedQuery(name = "Apteka.getAll", query = "SELECT c FROM Apteka c"),
	@NamedQuery(name = "Apteka.getByCity", query = "SELECT c FROM Apteka c where c.city =:city")

})
public class Apteka {
	
	private Long id;
	private String name;
	private String city;
	
	private List<Aptekarz> aptekarze = new ArrayList<Aptekarz>();
	
	public Apteka(){}
	
	public Apteka(String name, String city){
		this.name = name;
		this.city= city;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city= city;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<Aptekarz> getApteki() {
		return aptekarze;
	}

	public void setApteki(List<Aptekarz> candles) {
		this.aptekarze = candles;
	}
}