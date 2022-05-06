package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Produit_TicketDAO {
	private Connection connexion = null;
	private Statement st = null;
	private PreparedStatement ps = null;
	
	public Produit_TicketDAO() {
		
		// Chargement du Driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// Connexion
		try {
			connexion = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gStock","root","");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error"+e.getMessage());
		}
		
	}
	
	public boolean ajouter(long l,int idTicket,double quantite) {
		if(connexion != null) {
			try {
				ps = connexion.prepareStatement("INSERT INTO `produit_ticket` values (?,?,?);"); 
				ps.setLong(1,l);
				ps.setInt(2,idTicket);
				ps.setDouble(3,quantite);
				ps.executeUpdate();
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Error"+e.getMessage());
			}
		}
		return false;
	}
	
	public boolean modifier(long l,int idTicket,double quantite) {
		if(connexion != null) {
			try {
				ps = connexion.prepareStatement("UPDATE produit_ticket SET quantite = "+quantite+" WHERE idProduit = "+l+" AND idTicket = "+idTicket);
				ps.executeUpdate();
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Error"+e.getMessage());
			}
		}
		return false;
	}
	
	public boolean supprimer(long l,int idTicket) {
		if(connexion != null) {
			try {
				ps = connexion.prepareStatement("DELETE FROM `produit_ticket` WHERE `idProduit`  = "+l+" AND `idTicket` = "+idTicket); 
				ps.executeUpdate();
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Error"+e.getMessage());
			}
		}
		return false;
	}
	
	public boolean supprimer(int idTicket) {
		if(connexion != null) {
			try {
				ps = connexion.prepareStatement("DELETE FROM `produit_ticket` WHERE `idTicket` = "+idTicket); 
				ps.executeUpdate();
				connexion.close();
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Error"+e.getMessage());
			}
		}
		return false;
	}
	
	public ResultSet rechercherId(int idProduit,int idTicket) {
		if(connexion != null) {
			try {
				st = connexion.createStatement(); 
				ResultSet rs = st.executeQuery("SELECT * FROM produit_ticket WHERE idProduit = "+idProduit+" AND idTicket = "+idTicket);
				return rs;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Error"+e.getMessage());
			}
		}
		return null;
	}
	
	public ResultSet afficherTout(int idTicket) {
		if(connexion != null) {
			try {
				st = connexion.createStatement(); 
				ResultSet rs = st.executeQuery("SELECT * FROM produit_ticket WHERE idTicket = "+idTicket);
				return rs;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Error"+e.getMessage());
			}
		}
		return null;
	}
	
	public void fermerConnexion() {
		if(connexion != null) {
			try {
				connexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
}
