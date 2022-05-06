package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import oo.Ticket;

public class TicketDAO implements IDAO<Ticket>{

	private Connection connexion = null;
	private Statement st = null;
	private PreparedStatement ps = null;
	
	public TicketDAO() {
		
		// Chargement du Driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		
		// Connexion
		try {
			connexion = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gStock","root","");
		} catch (SQLException e) {
			System.out.println("Error"+e.getMessage());
		}
	}
	
	@Override
	public Ticket ajouter(Ticket element) {
		if(connexion != null) {
			try {
				SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
				String ch = d.format(element.getDate());
				ps = connexion.prepareStatement("INSERT INTO `ticket` VALUES (?,?,?,?,?,?,?)");
				ps.setInt(1, element.getIdTicket());
				ps.setString(2, ch);
				if(element.getClient()!=null)
					ps.setInt(3, element.getClient().getIdClient());
				else
					ps.setInt(3, 0);
				ps.setInt(4, element.getEmploye().getIdEmploye());
				ps.setLong(5, element.getMagasin().getIdMagasin());
				ps.setDouble(6, element.getCash());
				ps.setDouble(7, element.getTotal());
				ps.executeUpdate();
				for(int j=0;j<element.getProduits().size();j++)
				{
					Produit_TicketDAO pfd = new Produit_TicketDAO();
					pfd.ajouter(element.getProduits().get(j).getCodeBar(), element.getIdTicket(), element.getProduits().get(j).getQuantite());
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
	public Ticket supprimer(Ticket element) {
		if(connexion != null) {
			try {
				ps = connexion.prepareStatement("DELETE FROM `ticket` WHERE `idTicket`  = "+element.getIdTicket()); 
				ps.executeUpdate();
				for(int i=0;i<element.getProduits().size();i++)
				{
					Produit_TicketDAO pfd = new Produit_TicketDAO();
					pfd.supprimer(element.getProduits().get(i).getCodeBar(), element.getClient().getIdClient());
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
		if(connexion != null) {
			try {
				ps = connexion.prepareStatement("DELETE FROM `ticket` WHERE `idTicket`  = "+id); 
				int a = ps.executeUpdate();
				connexion.close();
				return a;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Error"+e.getMessage());
			}
		}
		return 0;
	}

	@Override
	public Ticket modifier(Ticket element) {
		if(connexion != null) {
			try {
				SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
				String ch = d.format(element.getDate());
				ps = connexion.prepareStatement("UPDATE ticket SET idTicket = "+element.getIdTicket()+", idMagasin = "+element.getMagasin().getIdMagasin()+" , idClient = "+element.getClient().getIdClient()+" , idEmploye = "+element.getEmploye().getIdEmploye()+" , cash = "+element.getCash()+", idEmploye = "+element.getEmploye().getIdEmploye()+" , cash = "+element.getCash()+" , total = "+element.getTotal()+" WHERE idTicket = "+element.getIdTicket()); 
				ps.executeUpdate();
				connexion.close();
				Produit_TicketDAO pfd = new Produit_TicketDAO();
				pfd.supprimer(element.getIdTicket());
				for(int i=0;i<element.getProduits().size();i++)
				{
					pfd = new Produit_TicketDAO();
					pfd.ajouter(element.getProduits().get(i).getCodeBar(), element.getIdTicket(), element.getProduits().get(i).getQuantite());
				}
				return element;
			} catch (SQLException e) {
				System.out.println("Error "+e.getMessage());
			}
		}
		return null;
	}
	
	public ResultSet ticketJour() {
		if(connexion != null) {
			try {
				st = connexion.createStatement(); 
				ResultSet rs = st.executeQuery("SELECT * FROM ticket WHERE date = CURRENT_DATE ;");
				return rs;
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
				ResultSet rs = st.executeQuery("SELECT * FROM ticket WHERE idTicket = "+id);
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
				ResultSet rs = st.executeQuery("SELECT * FROM ticket");
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
				ResultSet rs = st.executeQuery("select * from ticket");
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
