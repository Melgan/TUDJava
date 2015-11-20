package test;
import static org.junit.Assert.*;
import java.util.List;
import org.junit.Test;
import main.*;
import service.*;

	public class WizytaManagerTest 
	{
	WizytaManager WizytaManager=new WizytaManager();
	PacjentManager PacjentManager=new PacjentManager();
	LekarzManager LekarzManager=new LekarzManager();
	
	private final static int ID_Lekarz_1=1;
	private final static String Imie_1="PaŸdzioch";
    private final static String Nazwisko_1="Obecny";
    private final static int nrSluzbowy_1=345;
    
    private final static int ID_Pacjent_1=1;
    private final static String Imie_2="Dawid";
    private final static String Nazwisko_2="Szczêsny";
    private final static int Pesel_2=545678877;  
	     
	@Test
		public void sprawdzPolaczenie() 
		{
			assertNotNull(WizytaManager.getConnection());
		}
	
	@Test
	public void sprawdzDodajLekarz() 
	{	
	Lekarz Lekarz=new Lekarz (Imie_1, Nazwisko_1, nrSluzbowy_1);
	LekarzManager.wyczyscLekarzow();
	assertEquals(1, LekarzManager.dodajLekarza(Lekarz));	
	List<Lekarz> Lekarzy=LekarzManager.wezLekarzy();
	Lekarz pobieranieLekarzu=Lekarzy.get(0);
	assertEquals(Imie_1, pobieranieLekarzu.wezImie());
	assertEquals(Nazwisko_1, pobieranieLekarzu.wezNazwisko());
	assertEquals(nrSluzbowy_1, pobieranieLekarzu.weznrSluzbowy());	 
	}
	
	@Test
	public void sprawdzDodajPacjenta() 
	{
	Pacjent Pacjent=new Pacjent (Imie_2, Nazwisko_2, Pesel_2);	
	PacjentManager.wyczyscPacjentow();
	assertEquals(1,PacjentManager.dodajPacjenta(Pacjent));
	List<Pacjent> klienci=PacjentManager.wezPacjenty();
	Pacjent pobieraniePacjenta=klienci.get(0);
	assertEquals(Imie_2, pobieraniePacjenta.wezImie());
	assertEquals(Nazwisko_2, pobieraniePacjenta.wezNazwisko());
	assertEquals(Pesel_2, pobieraniePacjenta.wezpesel());
	}
	
	@Test
	public void sprawdzWizyta()
	{
		Wizyta Wizyta=new Wizyta(ID_Pacjent_1 , ID_Lekarz_1 );
		Pacjent Pacjent=new Pacjent (Imie_2, Nazwisko_2, Pesel_2);
		PacjentManager.wyczyscPacjentow();
		assertEquals(1,PacjentManager.dodajPacjenta(Pacjent));		
		Lekarz Lekarz=new Lekarz (Imie_1, Nazwisko_1, nrSluzbowy_1);
		LekarzManager.wyczyscLekarzow();
		assertEquals(1, LekarzManager.dodajLekarza(Lekarz));		
		WizytaManager.wyczyscSprzedazeLekarzu();
		assertEquals(1, WizytaManager.dodajWizyta(Pacjent, Lekarz ));
	}
	}
	

