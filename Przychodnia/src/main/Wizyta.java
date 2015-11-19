package main;


public class Wizyta 
	{
	private int id_Wizyta;
	private int id_Pacjent;
	private int id_Lekarz;
		
	public Wizyta(int id_Pacjent, int id_Lekarz)
	{
	}
	
	public Wizyta() 
	{
	}

	public  int getId_Pacjent()
	{
		return id_Pacjent;
	}
	
	public void setId_Pacjent(int id_Pacjent)
	{
		this.id_Pacjent=id_Pacjent;		
	}
	
	public  int getId_Lekarz()
	{
		return id_Lekarz;
	}
	
	public void setId_Lekarz(int id_Lekarz)
	{
		this.id_Lekarz=id_Lekarz;
	}

	public int getId_Wizyta() 
	{
		return id_Wizyta;
	}
	
	public void setId_Wizyta(int id_Wizyta) 
	{
		this.id_Wizyta=id_Wizyta;
	}
	
	
}


