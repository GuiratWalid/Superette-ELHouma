package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Produit_FactureDAO {

	private Connection connexion = null;
	private Statement st = null;
	private PreparedStatement ps = null;
	
	public Produit_FactureDAO() {
		
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
	
	public boolean ajouter(long l,int idFacture,double quantite) {
		if(connexion != null) {
			try {
				ps = connexion.prepareStatement("INSERT INTO produit_facture values (?,?,?);"); 
				ps.setLong(1,l);
				ps.setInt(2,idFacture);
				ps.setDouble(3,quantite);
				ps.executeUpdate();
				return true;
			} catch (SQLException e) {
				System.out.println("Error"+e.getMessage());

				e.printStackTrace();
			}
		}
		return false;
	}
	
	public boolean modifier(long idProduit,int idFacture,double quantite) {
		if(connexion != null) {
			try {
				ps = connexion.prepareStatement("UPDATE produit_facture SET idProduit ="+idProduit+", idFacture ="+idFacture+", quantite = "+quantite+"WHERE idProduit = "+idProduit+" AND idFacture = "+idFacture);
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
	
	public boolean supprimer(long l,int idFacture) {
		if(connexion != null) {
			try {
				ps = connexion.prepareStatement("DELETE FROM `produit_facture` WHERE `idProduit`  = "+l+" AND `idFacture` = "+idFacture); 
				ps.executeUpdate();
				connexion.close();
				return true;
			} catch (SQLException e) {
				
				System.out.println("Error"+e.getMessage());
			}
		}
		return false;
	}
	
	public boolean supprimer(int idFacture) {
		if(connexion != null) {
			try {
				ps = connexion.prepareStatement("DELETE FROM `produit_facture` WHERE `idFacture` = "+idFacture); 
				ps.executeUpdate();
				connexion.close();
				return true;
			} catch (SQLException e) {
				System.out.println("Error "+e.getMessage());
			}
		}
		return false;
	}
	
	public ResultSet rechercherId(int idProduit,int idFacture) {
		if(connexion != null) {
			try {
				st = connexion.createStatement(); 
				ResultSet rs = st.executeQuery("SELECT * FROM produit_facture WHERE idProduit = "+idProduit+" AND idFacture = "+idFacture);
				return rs;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Error"+e.getMessage());
			}
		}
		return null;
	}
	
	public ResultSet afficherTout(int idFacture) {
		if(connexion != null) {
			try {
				st = connexion.createStatement(); 
				ResultSet rs = st.executeQuery("SELECT * FROM produit_facture WHERE idFacture = "+idFacture);
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
