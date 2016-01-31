package utrwalaniedanych.hibernate.service;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import utrwalaniedanych.hibernate.domain.Apteka;

@Component
@Transactional
public class AptekaManagerImpl implements AptekaManager{
	
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
	public List<Apteka> getAllApteki(){
		return session.getCurrentSession().getNamedQuery("Apteka.getAll").list();
	}
	
	@Override
	public Apteka getAptekaById(Apteka apteka){
		return (Apteka) session.getCurrentSession().get(Apteka.class, apteka.getId());
	}
	
	@Override
	public void addApteka(Apteka apteka){
		apteka.setId(null);
		session.getCurrentSession().persist(apteka);
	}
	
	@Override
	public void editApteka(Apteka apteka){
		session.getCurrentSession().update(apteka);
	}
	
	@Override
	public void removeApteka(Apteka apteka){
		session.getCurrentSession().delete(apteka);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Apteka> getAptekaByCity(String city) {
		return session.getCurrentSession().getNamedQuery("Apteka.getByCity").setString("city", city).list();
	}
}