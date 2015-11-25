package main;

public class Lekarz {
	private int id_Lekarz;
	private String imie;
	private String nazwisko;
	private int nrSluzbowy;
	
	public Lekarz() 
	{
	}
	 public Lekarz(String imie, String nazwisko, int nrSluzbowy) 
	 {
		 super();		
		 this.imie=imie;
		 this.nazwisko=nazwisko;
		 this.nrSluzbowy=nrSluzbowy;
	 }
	 
	 public int wezid_Lekarz()
	 {
		 return id_Lekarz;
	 }
	 	 
	 public void ustawid_Lekarz(int id_Lekarz)
	 {
		 this.id_Lekarz=id_Lekarz;
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
	 
	 public int weznrSluzbowy() 
	 {
		 return nrSluzbowy;
	 }
	 
	 public void ustawnrSluzbowy(int nrSluzbowy)
	 {
	 this.nrSluzbowy=nrSluzbowy;
	 }	 
	 }

