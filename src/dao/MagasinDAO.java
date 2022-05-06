package dao;

import oo.Magasin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class MagasinDAO implements IDAO<Magasin> {

	private Connection connexion = null;
	private Statement st = null;
	private PreparedStatement ps = null;
	
	public MagasinDAO() {
		
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
	public Magasin ajouter(Magasin element) {
		if(connexion != null) {
			try {
				ps = connexion.prepareStatement("INSERT INTO `magasin` VALUES (?,?,?,?)");
				ps.setInt(1, element.getIdMagasin());
				ps.setString(2, element.getNomMagasin());
				ps.setString(3, element.getAdresseMagasin());
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
	public Magasin supprimer(Magasin element) {
		if(connexion != null) {
			try {
				ps = connexion.prepareStatement("DELETE FROM `magasin` WHERE `idMagasin`  = "+element.getIdMagasin()); 
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
	public Magasin modifier(Magasin element) {
		if(connexion != null) {
			try {
				ps = connexion.prepareStatement("UPDATE magasin SET idMagasin = "+element.getIdMagasin()+", nomMagasin ='"+element.getNomMagasin()+"', adresseMagasin ='"+element.getAdresseMagasin()+"', telMagasin = "+element.getNumTel()+" WHERE `idMagasin` = "+element.getIdMagasin()); 
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
				ResultSet rs = st.executeQuery("SELECT * FROM magasin WHERE idMagasin = "+id);
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
				ResultSet rs = st.executeQuery("SELECT * FROM magasin");
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
				ResultSet rs = st.executeQuery("SELECT * FROM magasin");
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
 