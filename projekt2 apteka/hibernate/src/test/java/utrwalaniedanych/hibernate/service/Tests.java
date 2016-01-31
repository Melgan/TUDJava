package utrwalaniedanych.hibernate.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import utrwalaniedanych.hibernate.domain.Apteka;
import utrwalaniedanych.hibernate.domain.Aptekarz;
import utrwalaniedanych.hibernate.service.AptekaManager;
import utrwalaniedanych.hibernate.service.AptekarzManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/beans.xml"})
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = false)
@Transactional
public class Tests {
	
	private static final String apteka_name = "Staroplska";
	private static final String apteka_name2 = "Starowiejska";
	private static final String apteka_city = "Warszawa";
	private static final String apteka_city2 = "Sopot";
	private static final String apteka_name3 = "PaniGoździkowa";
	private static final String apteka_city3 = "Gdańsk";
	
	private static final String aptekarz_imie = "Melchior";
	private static final String aptekarz_imie2 = "Janusz";


	
	
	private final List<Long> addedApteki = new ArrayList<Long>();
	private final List<Long> addedAptekarze = new ArrayList<Long>();

	
	@Autowired
	AptekaManager aptekaManager;
	
	@Autowired
	AptekarzManager aptekarzManager;
	
	@Before
	 public void addAptekiToList() {

	        List<Apteka> apteki = aptekaManager.getAllApteki();
	        List<Aptekarz> aptekarze = aptekarzManager.getAllAptekarz();


	        for(Apteka apteka : apteki)
	        	addedApteki.add(apteka.getId());
	        
	        for(Aptekarz aptekarz : aptekarze)
	        	addedAptekarze.add(aptekarz.getId());
	    }
	
	@After
    public void removeApteki() {

    	List<Apteka> apteki = aptekaManager.getAllApteki();
        List<Aptekarz> aptekarze = aptekarzManager.getAllAptekarz();


        boolean usun;

        for(Apteka apteka : apteki) {
            usun = true;
            for (Long apteka2 : addedApteki)
                if (apteka.getId() == apteka2) {
                usun = false;
                break;
                }
            if(usun)
                aptekaManager.removeApteka(apteka);
        }
        for(Aptekarz aptekarz : aptekarze) {
            usun = true;
            for (Long aptekarz2 : addedAptekarze)
                if (aptekarz.getId() == aptekarz2) {
                usun = false;
                break;
                }
            if(usun)
            	aptekarzManager.removeAptekarz(aptekarz);
        }
    }
	
	@Test
	public void checkAddApteka(){
		
		int n = aptekaManager.getAllApteki().size();
		
		Apteka apteka = new Apteka(apteka_name, apteka_city);
		aptekaManager.addApteka(apteka);
		
		Apteka cakeRetrived = aptekaManager.getAptekaById(apteka);
		assertEquals(apteka.getId(), cakeRetrived.getId());
		assertEquals(apteka_name, cakeRetrived.getName());
		assertEquals(apteka_city, cakeRetrived.getCity());
		
		assertEquals(n+1, aptekaManager.getAllApteki().size());
	}
	
	@Test
	public void checkEditApteka(){
		
		Apteka apteka = new Apteka(apteka_name, apteka_city);
		aptekaManager.addApteka(apteka);
		
		Apteka aptekaRetrived = aptekaManager.getAptekaById(apteka);
		assertEquals(apteka.getId(), aptekaRetrived.getId());
		assertEquals(apteka_name, aptekaRetrived.getName());
		assertEquals(apteka_city, aptekaRetrived.getCity());
		
		aptekaRetrived.setName(apteka_name2);
		aptekaRetrived.setCity(apteka_city2);
		aptekaManager.editApteka(aptekaRetrived);
		
		Apteka aptekaRetrived2 = aptekaManager.getAptekaById(aptekaRetrived);
		assertEquals(aptekaRetrived.getId(), aptekaRetrived2.getId());
		assertEquals(apteka_name2, aptekaRetrived2.getName());
		assertEquals(apteka_city2, aptekaRetrived2.getCity());		

	}
	
	@Test
	public void checkRemoveApteka() {
		
		Apteka apteka = new Apteka(apteka_name, apteka_city);
		aptekaManager.addApteka(apteka);
		
		int n = aptekaManager.getAllApteki().size();
	
		Apteka aptekaRetrived = aptekaManager.getAptekaById(apteka);
		assertEquals(apteka.getId(), aptekaRetrived.getId());
		assertEquals(apteka_name, aptekaRetrived.getName());
		assertEquals(apteka_city, aptekaRetrived.getCity());
		
		aptekaManager.removeApteka(apteka);

		
		assertNull(aptekaManager.getAptekaById(aptekaRetrived));
		assertEquals(n-1, aptekaManager.getAllApteki().size());
		}
	
	@Test
	public void checkGetAptekaByMiejscowość(){
		
		List<Apteka> apteki_w_mieście_przed = aptekaManager.getAptekaByCity(apteka_city);
		int n = apteki_w_mieście_przed.size();
		
		Apteka apteka = new Apteka(apteka_name, apteka_city);
		aptekaManager.addApteka(apteka);
		
		Apteka aptekaRetrived = aptekaManager.getAptekaById(apteka);
		assertEquals(apteka.getId(), aptekaRetrived.getId());
		assertEquals(apteka_name, aptekaRetrived.getName());
		assertEquals(apteka_city, aptekaRetrived.getCity());
		
		List<Apteka> apteki_w_mieście = aptekaManager.getAptekaByCity(apteka_city);
		
			Apteka apteka_w_mieście = apteki_w_mieście.get(0);
			Apteka apteka2 = aptekaManager.getAptekaById(apteka_w_mieście);
			assertEquals(apteka_w_mieście.getId(), apteka2.getId());
			assertEquals(apteka_w_mieście.getName(), apteka2.getName());
			assertEquals(apteka_w_mieście.getCity(), apteka2.getCity());
		

		assertEquals(n+1, apteki_w_mieście.size());
	}
	
	@Test
	public void checkAddAptekarzToApteka(){
		
		Apteka apteka = new Apteka(apteka_name3, apteka_city3);
		aptekaManager.addApteka(apteka);
		Apteka aptekaRetrived = aptekaManager.getAptekaById(apteka);
		assertEquals(apteka.getId(), aptekaRetrived.getId());
		assertEquals(apteka_name3, aptekaRetrived.getName());
		assertEquals(apteka_city3, aptekaRetrived.getCity());

		Aptekarz aptekarz = new Aptekarz(aptekarz_imie);
		aptekarzManager.addAptekarz(aptekarz);
		Aptekarz aptekarzRetrived = aptekarzManager.getAptekarzById(aptekarz);
		assertEquals(aptekarz.getId(), aptekarzRetrived.getId());
		assertEquals(aptekarz_imie, aptekarzRetrived.getImie());

		Aptekarz aptekarz2 = new Aptekarz(aptekarz_imie2);
		aptekarzManager.addAptekarz(aptekarz2);
		Aptekarz aptekarzRetrived2 = aptekarzManager.getAptekarzById(aptekarz2);
		assertEquals(aptekarz2.getId(), aptekarzRetrived2.getId());
		assertEquals(aptekarz_imie2, aptekarzRetrived2.getImie());
		
		List<Aptekarz> aptekarzeWAptece = aptekarzManager.getAptekarzByApteka(aptekaRetrived);
		int n = aptekarzeWAptece.size();

		aptekarzManager.addAptekarztoApteka(aptekarzRetrived,aptekaRetrived);
		aptekarzManager.addAptekarztoApteka(aptekarzRetrived2,aptekaRetrived);
				
		assertEquals(n+2, aptekarzeWAptece.size());
		
		for(Aptekarz aptekarz_ : aptekarzeWAptece){
			Aptekarz aptekarz_test = aptekarzManager.getAptekarzById(aptekarz_);
			assertEquals(aptekarz_.getId(), aptekarz_test.getId());
			assertEquals(aptekarz_.getImie(), aptekarz_test.getImie());
		}
	}
	
	@Test
	public void checkRemoveCakeWithCandles(){
		Apteka apteka = new Apteka(apteka_name3, apteka_city3);
		aptekaManager.addApteka(apteka);
		Apteka aptekaRetrived = aptekaManager.getAptekaById(apteka);
		assertEquals(apteka.getId(), aptekaRetrived.getId());
		assertEquals(apteka_name3, aptekaRetrived.getName());
		assertEquals(apteka_city3, aptekaRetrived.getCity());

		Aptekarz aptekarz = new Aptekarz(aptekarz_imie);
		aptekarzManager.addAptekarz(aptekarz);
		Aptekarz aptekarzRetrived = aptekarzManager.getAptekarzById(aptekarz);
		assertEquals(aptekarz.getId(), aptekarzRetrived.getId());
		assertEquals(aptekarz_imie, aptekarzRetrived.getImie());

		Aptekarz aptekarz2 = new Aptekarz(aptekarz_imie2);
		aptekarzManager.addAptekarz(aptekarz2);
		Aptekarz aptekarzRetrived2 = aptekarzManager.getAptekarzById(aptekarz2);
		assertEquals(aptekarz2.getId(), aptekarzRetrived2.getId());
		assertEquals(aptekarz_imie2, aptekarzRetrived2.getImie());
		
		List<Aptekarz> aptekarzeWAptece = aptekarzManager.getAptekarzByApteka(aptekaRetrived);

		aptekarzManager.addAptekarztoApteka(aptekarzRetrived,aptekaRetrived);
		aptekarzManager.addAptekarztoApteka(aptekarzRetrived2,aptekaRetrived);
						
		for(Aptekarz aptekarz_ : aptekarzeWAptece){
			Aptekarz aptekarz_test = aptekarzManager.getAptekarzById(aptekarz_);
			assertEquals(aptekarz_.getId(), aptekarz_test.getId());
			assertEquals(aptekarz_.getImie(), aptekarz_test.getImie());
		}
		
		int m = aptekarzManager.getAllAptekarz().size();
		int n = aptekaManager.getAllApteki().size();

		aptekaManager.removeApteka(apteka);
		
		assertNull(aptekaManager.getAptekaById(aptekaRetrived));
		assertNull(aptekarzManager.getAptekarzById(aptekarzRetrived));
		assertNull(aptekarzManager.getAptekarzById(aptekarzRetrived2));
		assertEquals(n-1, aptekaManager.getAllApteki().size());
		assertEquals(m-2, aptekarzManager.getAllAptekarz().size());
	}
	
	
}
	
	
	