package test;
import static org.junit.Assert.*;
import java.util.List;
import org.junit.Test;

import main.*;
import service.*;



public class PacjentManagerTest {
PacjentManager PacjentManager = new PacjentManager();
	

     private final static String Imie_1 = "èdzis≥aw";
     private final static String Nazwisko_1 = "Nowak";
     private final static int Pesel_1 = 99553322;
     

     private final static String Imie_2 = "Sebastian";
     private final static String Nazwisko_2 = "Jakistam";
     private final static int Pesel_2 = 11455677;
     
@Test
	public void sprawdzPolaczenie() {
		assertNotNull(PacjentManager.wezConnection());
	}
@Test
	public void sprawdzDodajPacjenta() {
	
	Pacjent Pacjent = new Pacjent (Imie_1, Nazwisko_1, Pesel_1);
	
	PacjentManager.wyczyscPacjentow();
	assertEquals(1,PacjentManager.dodajPacjenta(Pacjent));
	
	List<Pacjent> Pacjenci = PacjentManager.wezPacjenty();
	Pacjent pobieraniePacjenta = Pacjenci.get(0);
	
	assertEquals(Imie_1, pobieraniePacjenta.wezImie());
	assertEquals(Nazwisko_1, pobieraniePacjenta.wezNazwisko());
	assertEquals(Pesel_1, pobieraniePacjenta.wezpesel());
	}

@Test
	public void sprawdzUsunPacjenta() {
	
	Pacjent Pacjent = new Pacjent (Imie_1, Nazwisko_1, Pesel_1);
	
	PacjentManager.wyczyscPacjentow();
	assertEquals(1,PacjentManager.dodajPacjenta(Pacjent));
	
	List<Pacjent> k = PacjentManager.wezPacjenty();
	Pacjent pobieraniePacjenta = k.get(0);
	
	
	assertEquals(1, PacjentManager.usunPacjenta(pobieraniePacjenta));
	

}


@Test
public void sprawdzEdytujPacjenta() {

Pacjent Pacjent = new Pacjent (Imie_1, Nazwisko_1, Pesel_1);

PacjentManager.wyczyscPacjentow();
assertEquals(1,PacjentManager.dodajPacjenta(Pacjent));

List<Pacjent> k = PacjentManager.wezPacjenty();
Pacjent pobieraniePacjenta = k.get(0);


pobieraniePacjenta.ustawImie(Imie_2);
pobieraniePacjenta.ustawNazwisko(Nazwisko_2);
pobieraniePacjenta.ustawpesel(Pesel_2);


assertEquals(1, PacjentManager.zmienPacjenta(pobieraniePacjenta));

List<Pacjent> k2 = PacjentManager.wezPacjenty();
Pacjent pobieraniePacjenta2 = k2.get(0);


assertEquals(Imie_2, pobieraniePacjenta2.wezImie());
assertEquals(Nazwisko_2, pobieraniePacjenta2.wezNazwisko());
assertEquals(Pesel_2, pobieraniePacjenta2.wezpesel());
assertEquals(pobieraniePacjenta2.wezid_Pacjent(), pobieraniePacjenta2.wezid_Pacjent());

}



}


