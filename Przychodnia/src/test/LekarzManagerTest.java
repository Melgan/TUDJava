package test;
import static org.junit.Assert.*;
import java.util.List;
import org.junit.Test;
import main.*;
import service.*;

public class LekarzManagerTest 
{
LekarzManager LekarzManager = new LekarzManager();
	
     private final static String Imie_1 = "Jan";
     private final static String Nazwisko_1 = "Nowak";
     private final static int nrSluzbowy_1 = 301;
     
     private final static String Imie_2 = "Kamil";
     private final static String Nazwisko_2 = "Kowalski";
     private final static int nrSluzbowy_2 = 302;
     
@Test
	public void sprawdzPolaczenie() 
	{
		assertNotNull(LekarzManager.wezConnection());
	}
@Test
	public void sprawdzDodajLekarza() 
	{	
	Lekarz Lekarz = new Lekarz (Imie_1, Nazwisko_1, nrSluzbowy_1);	
	LekarzManager.wyczyscLekarzow();
	assertEquals(1,LekarzManager.dodajLekarza(Lekarz));
		List<Lekarz> Pacjenci = LekarzManager.wezLekarzy();
	Lekarz pobieranieLekarza = Pacjenci.get(0);	
	assertEquals(Imie_1, pobieranieLekarza.wezImie());
	assertEquals(Nazwisko_1, pobieranieLekarza.wezNazwisko());
	assertEquals(nrSluzbowy_1, pobieranieLekarza.weznrSluzbowy());
	}

@Test
	public void sprawdzUsunLekarza() 
	{
	Lekarz Lekarz = new Lekarz (Imie_1, Nazwisko_1, nrSluzbowy_1);	
	LekarzManager.wyczyscLekarzow();
	assertEquals(1,LekarzManager.dodajLekarza(Lekarz));	
	List<Lekarz> k = LekarzManager.wezLekarzy();
	Lekarz pobieranieLekarza = k.get(0);	
	assertEquals(1, LekarzManager.usunLekarza(pobieranieLekarza));
	}
@Test
public void sprawdzEdytujLekarza() 
{
Lekarz Lekarz = new Lekarz (Imie_1, Nazwisko_1, nrSluzbowy_1);
LekarzManager.wyczyscLekarzow();
assertEquals(1,LekarzManager.dodajLekarza(Lekarz));
List<Lekarz> k = LekarzManager.wezLekarzy();
Lekarz pobieranieLekarza = k.get(0);
pobieranieLekarza.ustawImie(Imie_2);
pobieranieLekarza.ustawNazwisko(Nazwisko_2);
pobieranieLekarza.ustawnrSluzbowy(nrSluzbowy_2);
assertEquals(1, LekarzManager.zmienLekarza(pobieranieLekarza));
List<Lekarz> k2 = LekarzManager.wezLekarzy();
Lekarz pobieranieLekarza2 = k2.get(0);
assertEquals(Imie_2, pobieranieLekarza2.wezImie());
assertEquals(Nazwisko_2, pobieranieLekarza2.wezNazwisko());
assertEquals(nrSluzbowy_2, pobieranieLekarza2.weznrSluzbowy());
assertEquals(pobieranieLekarza2.wezid_Lekarz(), pobieranieLekarza2.wezid_Lekarz());
}
}


