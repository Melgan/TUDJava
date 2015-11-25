package main;

public class Pacjent {
	private int id_Pacjent;
	private String imie;
	private String nazwisko;
	private int pesel;
	
	public Pacjent() 
	{
	}
	 public Pacjent(String imie, String nazwisko, int pesel) 
	 {
		 super();		
		 this.imie=imie;
		 this.nazwisko=nazwisko;
		 this.pesel=pesel;
	 }
	 
	 public int wezid_Pacjent()
	 {
		 return id_Pacjent;
	 }
	 	 
	 public void ustawid_Pacjent(int id_Pacjent)
	 {
		 this.id_Pacjent=id_Pacjent;
	 }
	 
	 public String wezImie() 
	 {
		 return imie;
	 }
	 
	 public void ustawImie(String imie) 
	 {
		 this.imie=imie;	
	 }
	 
	 public String wezNazwisko() 
	 {
	 return nazwisko;
	 }
	 
	 public void ustawNazwisko(String nazwisko)
	 {
	 this.nazwisko=nazwisko;	 
	 }
	 
	 public int wezpesel() 
	 {
		 return pesel;
	 }
	 
	 public void ustawpesel(int pesel)
	 {
	 this.pesel=pesel;
	 }	 
	 }

