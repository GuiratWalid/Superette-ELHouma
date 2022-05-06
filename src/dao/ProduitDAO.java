package dao;

import oo.Produit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.PreparedStatement;

public class ProduitDAO implements IDAO<Produit>{

	private Connection connexion = null;
	private Statement st = null;
	private PreparedStatement ps = null;
	
	public ProduitDAO() {
		
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
	public Produit ajouter(Produit element) {
		if(connexion != null) {
			try {
				ps = connexion.prepareStatement("INSERT INTO `produit` VALUES (?,?,?,?,?,?,?)");
				ps.setLong(1, element.getCodeBar());
				ps.setString(2, element.getDesignation());
				ps.setDouble(3, element.getQuantite());
				ps.setDouble(4, element.getPrixUnitaire());
				ps.setInt(5, element.getCategorie().getIdCategorie());
				ps.setString(6, element.getDateFabrication());
				ps.setString(7, element.getDateExpiration());
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
	public Produit supprimer(Produit element) {
		if(connexion != null) {
			try {
				ps = connexion.prepareStatement("DELETE FROM `produit` WHERE `codeBar`  = "+element.getCodeBar()); 
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
	
	public int supprimer(int codeBar) {
		int a = -1;
		if(connexion != null) {
			try {
				ps = connexion.prepareStatement("DELETE FROM `produit` WHERE `codeBar`  = "+codeBar); 
				ps.executeUpdate();
				connexion.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Error"+e.getMessage());
			}
		}
		return a;
	}

	@Override
	public Produit modifier(Produit element) {
		if(connexion != null) {
			try {
				ps = connexion.prepareStatement("UPDATE produit SET codeBar = "+element.getCodeBar()+", designation ='"+element.getDesignation()+"', quantite ="+element.getQuantite()+", prixUnitaire = "+element.getPrixUnitaire()+", idCategorie = "+element.getCategorie().getIdCategorie()+", dateFabrication = '"+element.getDateFabrication()+"', dateExpiration = '"+element.getDateExpiration()+"' WHERE `codeBar` = "+element.getCodeBar()); 
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
	public ResultSet rechercherId(int l) {
		if(connexion != null) {
			try {
				st = connexion.createStatement(); 
				ResultSet rs = st.executeQuery("select * from produit where codeBar = "+l);
				return rs;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Error"+e.getMessage());
			}
		}
		return null;
	}
	
	public ResultSet produitPerime() {
		if(connexion != null) {
			try {
				st = connexion.createStatement(); 
				ResultSet rs = st.executeQuery("select * from produit where dateExpiration <= CURRENT_DATE ");
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
				ResultSet rs = st.executeQuery("select * from produit");
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
				ResultSet rs = st.executeQuery("select * from produit");
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
