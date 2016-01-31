package utrwalaniedanych.hibernate.service;

import java.util.List;

import utrwalaniedanych.hibernate.domain.Apteka;
import utrwalaniedanych.hibernate.domain.Aptekarz;

public interface AptekaManager {
	
	List<Apteka> getAllApteki();
	Apteka getAptekaById(Apteka apteka);
	void addApteka(Apteka apteka);
	void editApteka(Apteka apteka);
	void removeApteka(Apteka apteka);
	public List<Apteka> getAptekaByCity(String city);
}

