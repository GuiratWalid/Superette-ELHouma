package dao;

import oo.Fournisseur;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class FournisseurDAO implements IDAO<Fournisseur> {

	private Connection connexion = null;
	private Statement st = null;
	private PreparedStatement ps = null;
	
	public FournisseurDAO() {
		
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
	
	@Override
	public Fournisseur ajouter(Fournisseur element) {
		if(connexion != null) {
			try {
				ps = connexion.prepareStatement("INSERT INTO `fournisseur` VALUES (?,?,?,?)");
				ps.setInt(1, element.getIdFournisseur());
				ps.setString(2, element.getNomFournisseur());
				ps.setString(3, element.getAdresseFournisseur());
				ps.setLong(4, element.getNumTel());
				ps.executeUpdate();
				return element;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Error"+e.getMessage());
			}
		}
		return null;
	}

	@Override
	public Fournisseur supprimer(Fournisseur element) {
		if(connexion != null) {
			try {
				ps = connexion.prepareStatement("DELETE FROM `fournisseur` WHERE `idFournisseur`  = "+element.getIdFournisseur()); 
				ps.executeUpdate();
				connexion.close();
				return element;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Error"+e.getMessage());
			}
		}
		return null;
	}

	@Override
	public Fournisseur modifier(Fournisseur element) {
		if(connexion != null) {
			try {
				ps = connexion.prepareStatement("UPDATE fournisseur SET idFournisseur = "+element.getIdFournisseur()+", nomFournisseur ='"+element.getNomFournisseur()+"', adresseFournisseur ='"+element.getAdresseFournisseur()+"', numTel = "+element.getNumTel()+" WHERE `idFournisseur` = "+element.getIdFournisseur()); 
				ps.executeUpdate();
				connexion.close();
				return element;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Error"+e.getMessage());
			}
		}
		return null;
	}

	@Override
	public ResultSet rechercherId(int id) {
		if(connexion != null) {
			try {
				st = connexion.createStatement(); 
				ResultSet rs = st.executeQuery("SELECT * FROM fournisseur WHERE idFournisseur = "+id);
				return rs;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Error"+e.getMessage());
			}
		}
		return null;
	}

	@Override
	public ResultSet afficherTout() {
		if(connexion != null) {
			try {
				st = connexion.createStatement(); 
				ResultSet rs = st.executeQuery("SELECT * FROM fournisseur");
				return rs;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Error"+e.getMessage());
			}
		}
		return null;
	}

	@Override
	public ResultSetMetaData colonne() {
		if(connexion != null) {
			try {
				st = connexion.createStatement(); 
				ResultSet rs = st.executeQuery("select * from fournisseur");
				ResultSetMetaData rsmd = rs.getMetaData();
				return rsmd;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Error"+e.getMessage());
			}
		}
		return null;
	}
	
	@Override
	public void fermerConnexion() {
		if(connexion != null) {
			try {
				connexion.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
 