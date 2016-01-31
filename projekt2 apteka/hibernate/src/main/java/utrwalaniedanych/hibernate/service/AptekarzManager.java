package utrwalaniedanych.hibernate.service;

import java.util.List;

import utrwalaniedanych.hibernate.domain.Apteka;
import utrwalaniedanych.hibernate.domain.Aptekarz;

public interface AptekarzManager {
	
	Aptekarz getAptekarzById(Aptekarz candle);
	List<Aptekarz> getAptekarzByApteka(Apteka cake);
	void addAptekarz(Aptekarz candle);
	void addAptekarztoApteka(Aptekarz candle, Apteka cake);
	void editAptekarz(Aptekarz candle);
	void removeAptekarz(Aptekarz candle);
	public List<Aptekarz> getAllAptekarz();
	
}
