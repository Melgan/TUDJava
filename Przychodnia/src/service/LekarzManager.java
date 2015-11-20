package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import main.Lekarz;

public class LekarzManager 
{
	private Connection connection;
	private String url= "jdbc:hsqldb:hsql://localhost/";
	private String createTableLekarz="CREATE TABLE Lekarz(id_Lekarz int GENERATED BY DEFAULT AS IDENTITY, imie varchar(20), nazwisko varchar(20), nrSluzbowy int)"; 
	
	private PreparedStatement dodajLekarza;
	private PreparedStatement usunLekarza;
	private PreparedStatement usunLekarzy;
	private PreparedStatement wezLekarzy;
	private PreparedStatement zmienLekarza;
	
	private Statement statement;
	public LekarzManager()
	{
		try
		{
			connection=DriverManager.getConnection(url);
			statement=connection.createStatement();
			
			ResultSet wyn =	connection.getMetaData().getTables(null, null, null, null);
			boolean tableExists=false;
			while(wyn.next())
			{
				if("Lekarz".equalsIgnoreCase(wyn.getString("TABLE_NAME")))
				{
					tableExists=true;
					break;
				}
			}
			
			if(!tableExists)
				statement.executeUpdate(createTableLekarz);
			
			dodajLekarza=connection.prepareStatement("INSERT INTO Lekarz(imie, nazwisko, nrSluzbowy) VALUES (?, ?, ?)");
			usunLekarza=connection.prepareStatement("DELETE FROM Lekarz WHERE id_Lekarz=?");
			usunLekarzy=connection.prepareStatement("DELETE FROM Lekarz");
			wezLekarzy=connection.prepareStatement("SELECT id_Lekarz, imie, nazwisko, nrSluzbowy FROM Lekarz");
			zmienLekarza=connection.prepareStatement("UPDATE Lekarz set imie=?, nazwisko=?, nrSluzbowy=? WHERE id_Lekarz=?");
				
			
	}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
}

public Connection wezConnection()
{
	return connection;
}
	public void wyczyscLekarzow()
	{
	try
		{
			usunLekarzy.executeUpdate();
		}
		catch (SQLException e)
		{ 
			e.printStackTrace();
		}
}

	public int dodajLekarza(Lekarz Lekarz)
		{
			int licznik=0;
			try
			{
			dodajLekarza.setString(1, Lekarz.wezImie());
			dodajLekarza.setString(2, Lekarz.wezNazwisko());
			dodajLekarza.setInt(3, Lekarz.weznrSluzbowy());
			
			licznik=dodajLekarza.executeUpdate();
			
		}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			return licznik;
	}
	
	public int zmienLekarza(Lekarz Lekarz)
	{
		int licznik=0;
		try
		{
			zmienLekarza.setString(1, Lekarz.wezImie());
			zmienLekarza.setString(2, Lekarz.wezNazwisko());
			zmienLekarza.setInt(3, Lekarz.weznrSluzbowy());
			zmienLekarza.setInt(4, Lekarz.wezid_Lekarz());
			
			licznik=zmienLekarza.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return licznik;
	}
	public int usunLekarza(Lekarz Lekarz)
	{
		int licznik=0;
		try
		{
			usunLekarza.setInt(1, Lekarz.wezid_Lekarz());
			licznik=usunLekarza.executeUpdate();
			
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return licznik;
	}
	
	public List<Lekarz> wezLekarzy()
	{
		List<Lekarz> klienci=new ArrayList<Lekarz>();
		
		try
		{
			ResultSet wyn=wezLekarzy.executeQuery();
			
			while (wyn.next())
			{
				Lekarz k=new Lekarz();
				k.ustawid_Lekarz(wyn.getInt("id_Lekarz"));
				k.ustawImie(wyn.getString("imie"));
				k.ustawNazwisko(wyn.getString("nazwisko"));
				k.ustawnrSluzbowy(wyn.getInt("nrSluzbowy"));
				klienci.add(k);
			}
			}
		catch(SQLException e) 
		{
				e.printStackTrace();
			}
			return klienci;
		}
	}