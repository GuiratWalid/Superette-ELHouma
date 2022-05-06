package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import oo.Facture;

public class FactureDAO implements IDAO<Facture>{

	private Connection connexion = null;
	private Statement st = null;
	private PreparedStatement ps = null;
	
	public FactureDAO() {
		
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
	public Facture ajouter(Facture element) {
		if(connexion != null) {
			try {
				SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
				String ch = d.format(element.getDate());
				ps = connexion.prepareStatement("INSERT INTO `facture` VALUES (?,?,?,?,?,?)");
				ps.setInt(1, element.getIdFacture());
				ps.setString(2, ch);
				ps.setInt(3, element.getFournisseur().getIdFournisseur());
				ps.setInt(4, element.getEmploye().getIdEmploye());
				ps.setDouble(5, element.getCash());
				ps.setDouble(6, element.getTotal());
				ps.executeUpdate();
				for(int i=0;i<element.getProduits().size();i++)
				{
					Produit_FactureDAO pfd = new Produit_FactureDAO();
					pfd.ajouter(element.getProduits().get(i).getCodeBar(), element.getIdFacture(), element.getProduits().get(i).getQuantite());
				}
				return element;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Error"+e.getMessage());
			}
		}
		return null;
	}

	@Override
	public Facture supprimer(Facture element) {
		if(connexion != null) {
			try {
				ps = connexion.prepareStatement("DELETE FROM `facture` WHERE `idFacture`  = "+element.getIdFacture()); 
				ps.executeUpdate();
				for(int i=0;i<element.getProduits().size();i++)
				{
					Produit_FactureDAO pfd = new Produit_FactureDAO();
					pfd.supprimer(element.getProduits().get(i).getCodeBar(), element.getFournisseur().getIdFournisseur());
				}
				connexion.close();
				return element;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Error"+e.getMessage());
			}
		}
		return null;
	}
	
	public int supprimer(int id) {
		int a = -1;
		if(connexion != null) {
			try {
				ps = connexion.prepareStatement("DELETE FROM `facture` WHERE `idFacture`  = "+id); 
				a = ps.executeUpdate();
				connexion.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Error"+e.getMessage());
			}
		}
		return a;
	}
	

	
	public ResultSet factureJour() {
		if(connexion != null) {
			try {
				st = connexion.createStatement(); 
				ResultSet rs = st.executeQuery("SELECT * FROM facture WHERE date = CURRENT_DATE ;");
				return rs;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Error"+e.getMessage());
			}
		}
		return null;
	}

	@Override
	public Facture modifier(Facture element) {
		if(connexion != null) {
			try {
				SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
				String ch = d.format(element.getDate());
				ps = connexion.prepareStatement("UPDATE facture SET idFacture = "+element.getIdFacture()+", date ='"+ch+"', idFournisseur ="+element.getFournisseur().getIdFournisseur()+", idEmploye = "+element.getEmploye().getIdEmploye()+" , cash = "+element.getCash()+" , total = "+element.getTotal()+" WHERE idFacture = "+element.getIdFacture());
				ps.executeUpdate();
				connexion.close();
				Produit_FactureDAO pfd = new Produit_FactureDAO();
				pfd.supprimer(element.getIdFacture());
				for(int i=0;i<element.getProduits().size();i++)
				{
					pfd = new Produit_FactureDAO();
					pfd.ajouter(element.getProduits().get(i).getCodeBar(), element.getIdFacture(),element.getProduits().get(i).getQuantite());
				}
				return element;
			} catch (SQLException e) {
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
				ResultSet rs = st.executeQuery("SELECT * FROM facture WHERE idFacture = "+id);
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
				ResultSet rs = st.executeQuery("SELECT * FROM facture");
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
				ResultSet rs = st.executeQuery("select * from facture");
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
