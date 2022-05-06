package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import oo.Employe;

public class EmployeDAO implements IDAO<Employe>{

	private Connection connexion = null;
	private Statement st = null;
	private PreparedStatement ps = null;
	
	public EmployeDAO() {
		
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
	public Employe ajouter(Employe element) {
		if(connexion != null) {
			try {
				ps = connexion.prepareStatement("INSERT INTO `employe` VALUES (?,?,?,?,?,?,?,?)");
				ps.setInt(1, element.getIdEmploye());
				ps.setString(2, element.getNom());
				ps.setString(3, element.getPrenom());
				ps.setLong(4, element.getTel());
				ps.setString(5, element.getAdresse());
				ps.setDouble(6, element.getSalaire());
				ps.setString(7, element.getDateNaissance());
				ps.setString(8, element.getDateEmbauche());
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
	public Employe supprimer(Employe element) {
		if(connexion != null) {
			try {
				ps = connexion.prepareStatement("DELETE FROM `employe` WHERE `idEmploye`  = "+element.getIdEmploye()); 
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
	public Employe modifier(Employe element) {
		if(connexion != null) {
			try {
				ps = connexion.prepareStatement("UPDATE employe SET idEmploye = "+element.getIdEmploye()+", nom ='"+element.getNom()+"', prenom ='"+element.getPrenom()+"', tel = "+element.getTel()+", adresse = '"+element.getAdresse()+"' , salaire = "+element.getSalaire()+" , dateEmbauche = '"+element.getDateEmbauche()+"' , dateNaissance = '"+element.getDateNaissance()+"' WHERE `idEmploye` = "+element.getIdEmploye()); 
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
				ResultSet rs = st.executeQuery("SELECT * FROM employe WHERE idEmploye = "+id);
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
				ResultSet rs = st.executeQuery("SELECT * FROM employe");
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
				ResultSet rs = st.executeQuery("select * from employe");
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
