package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import oo.Client;

public class ClientDAO implements IDAO<Client>{

	private Connection connexion = null;
	private Statement st = null;
	private PreparedStatement ps = null;
	
	public ClientDAO() {
		
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
	public Client ajouter(Client element) {
		if(connexion != null) {
			try {
				SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
				String ch = d.format(element.getDateInscription());
				ps = connexion.prepareStatement("INSERT INTO `client` VALUES (?,?,?,?,?,?,?)");
				ps.setInt(1, element.getIdClient());
				ps.setString(2, element.getNom());
				ps.setString(3, element.getPrenom());
				ps.setLong(4, element.getTel());
				ps.setString(5, element.getAdresse());
				ps.setDouble(6, element.getBonus());
				ps.setString(7, ch);
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
	public Client supprimer(Client element) {
		if(connexion != null) {
			try {
				ps = connexion.prepareStatement("DELETE FROM `client` WHERE `idClient`  = "+element.getIdClient()); 
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
	public Client modifier(Client element) {
		if(connexion != null) {
			try {
				SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
				String ch = d.format(element.getDateInscription());
				ps = connexion.prepareStatement("UPDATE client SET idClient = "+element.getIdClient()+", nom ='"+element.getNom()+"', prenom ='"+element.getPrenom()+"', tel = "+element.getTel()+", adresse = '"+element.getAdresse()+"', bonus = "+element.getBonus()+" WHERE `idClient` = "+element.getIdClient()); 
				ps.executeUpdate();
				connexion.close();
				return element;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Error "+e.getMessage());
			}
		}
		return null;
	}

	@Override
	public ResultSet rechercherId(int id) {
		if(connexion != null) {
			try {
				st = connexion.createStatement(); 
				ResultSet rs = st.executeQuery("SELECT * FROM client WHERE idClient = "+id);
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
				ResultSet rs = st.executeQuery("SELECT * FROM client");
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
				ResultSet rs = st.executeQuery("select * from client");
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
