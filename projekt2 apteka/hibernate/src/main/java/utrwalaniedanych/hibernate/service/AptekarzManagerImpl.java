package utrwalaniedanych.hibernate.service;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import utrwalaniedanych.hibernate.domain.Apteka;
import utrwalaniedanych.hibernate.domain.Aptekarz;

@Component
@Transactional
public class AptekarzManagerImpl implements AptekarzManager{
	
	@Autowired
	private SessionFactory session;
	
	public SessionFactory getSessionFactory(){
		return session;
	}
	
	public void setSessionFactory(SessionFactory session){
		this.session = session;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Aptekarz> getAllAptekarz() {
		return session.getCurrentSession().getNamedQuery("Aptekarz.getAll").list();
	}

	@Override
	public Aptekarz getAptekarzById(Aptekarz aptekarz) {
		return (Aptekarz) session.getCurrentSession().get(Aptekarz.class, aptekarz.getId());
	}
	
	@Override
	public List<Aptekarz> getAptekarzByApteka(Apteka apteka) {
		Apteka c = (Apteka) session.getCurrentSession().get(Apteka.class, apteka.getId());
		return c.getApteki();
	}
	
	@Override
	public void addAptekarz(Aptekarz aptekarz) {
		aptekarz.setId(null);
		session.getCurrentSession().persist(aptekarz);	
	}

	@Override
	public void addAptekarztoApteka(Aptekarz aptekarz, Apteka apteka) {
		Apteka c = (Apteka) session.getCurrentSession().get(Apteka.class, apteka.getId());
		c.getApteki().add(aptekarz);
	}
	
	@Override
	public void editAptekarz(Aptekarz aptekarz) {
		session.getCurrentSession().update(aptekarz);
	}

	@Override
	public void removeAptekarz(Aptekarz aptekarz) {
		session.getCurrentSession().delete(aptekarz);	
	}
}
