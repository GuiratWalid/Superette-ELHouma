package dao;


import java.sql.ResultSet;
import java.sql.ResultSetMetaData;


public interface IDAO <T> {
	
	public T ajouter (T element);
	
	public T supprimer (T element);
	
	public T modifier (T element);
	
	public ResultSet rechercherId (int id);
	
	public ResultSet afficherTout ();
	
	public ResultSetMetaData colonne();
	
	public void fermerConnexion();
	
}
