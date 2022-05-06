package dao;

import oo.Categorie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;


import java.sql.PreparedStatement;

public class CatégorieDAO implements IDAO<Categorie>{

	private Connection connexion = null;
	private Statement st = null;
	private PreparedStatement ps = null;
	
	public CatégorieDAO() {
		
		// Chargement du driver
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
	public Categorie ajouter(Categorie element) {
		if(connexion != null) {
			try {
				ps = connexion.prepareStatement("INSERT INTO `categorie`(`id`, `designation`) VALUES (?,?)");
				ps.setInt(1, element.getIdCategorie());
				ps.setString(2, element.getDesignation());
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
	public Categorie supprimer(Categorie element) {
		if(connexion != null) {
			try {
				ps = connexion.prepareStatement("DELETE FROM `categorie` WHERE `id`  = "+element.getIdCategorie()); 
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
	public Categorie modifier(Categorie element) {
		if(connexion != null) {
			try {
				ps = connexion.prepareStatement("UPDATE categorie SET designation = '"+element.getDesignation()+"' where id = "+element.getIdCategorie()); 
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
				ResultSet rs = st.executeQuery("select * from categorie where id = "+id);
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
				ResultSet rs = st.executeQuery("select * from categorie");
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
				ResultSet rs = st.executeQuery("select * from categorie");
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
