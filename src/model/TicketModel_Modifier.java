package model;

import java.awt.HeadlessException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import dao.CatégorieDAO;
import dao.ProduitDAO;
import dao.Produit_TicketDAO;
import ihm.ModifierTicket;
import oo.Categorie;
import oo.Produit;

public class TicketModel_Modifier extends AbstractTableModel{

	private ResultSetMetaData rsmd;
	private ArrayList <Object[]> data = new ArrayList <Object[]> ();
	private ModifierTicket mt = null;
	
	public TicketModel_Modifier(ModifierTicket mt) {
		this.mt = mt;
	}
	
	public void Init(int id) {
		data.clear();
		try {
			Produit_TicketDAO ptd = new Produit_TicketDAO();
			ResultSet rs = ptd.afficherTout(id);
			rsmd = rs.getMetaData();
			while(rs.next()) {
				Object[] ligne = new Object[5];
				ligne[0] = rs.getLong(1);
				ligne[2] = rs.getDouble(3);
				ProduitDAO pd = new ProduitDAO();
				ResultSet rs1 = pd.rechercherId(rs.getInt(1));
				if(rs1.next()) {
					ligne[1] = rs1.getString(2);
					ligne[3] = rs1.getDouble(4);
					ligne[4] = (double)ligne[2]*(double)ligne[3];
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void afficher(long codeBar,double quantite) {
		
		ProduitDAO pd = new ProduitDAO();
		ResultSet rs1 = pd.rechercherId((int)codeBar);
		Object[] ligne = new Object[5];
		ligne[0] = codeBar;
		ligne[2] = quantite;
		try {
			if(rs1.next()) {
				ligne[1] = rs1.getString(2);
				ligne[3] = rs1.getDouble(4);
				ligne[4] = (double)ligne[2]*(double)ligne[3];
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		data.add(ligne);
		this.fireTableDataChanged();
		
	}
	
	public ArrayList<Produit> produits (){
		ArrayList<Produit> produits = new ArrayList<Produit>();
		for(int i=0 ;i<data.size();i++) {
			ProduitDAO prd = new ProduitDAO();
			ResultSet rs4 = prd.rechercherId(Integer.parseInt(""+data.get(i)[0]));
				try {
					if (rs4.next()) {
						CatégorieDAO cad = new CatégorieDAO();
						ResultSet rs5 = cad.rechercherId(rs4.getInt(5));
						Categorie cat = null;
						if(rs5.next()) {
							cat = new Categorie(rs5.getInt(1),rs5.getString(2));
						}						
						Produit p = new Produit(Long.parseLong(""+data.get(i)[0]),rs4.getString(2),Double.parseDouble((""+data.get(i)[2])),rs4.getDouble(4),cat,rs4.getString(6),rs4.getString(7));
						produits.add(p);
						Produit p2 = new Produit(rs4.getLong(1),rs4.getString(2),0-Double.parseDouble((""+data.get(i)[2]))+rs4.getDouble(3),rs4.getDouble(4),cat,rs4.getString(6),rs4.getString(7));
						prd.modifier(p2);

						
					}
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}
		return produits;
	}
	
	@Override
	public int getRowCount() {
	
		return data.size();
		
	}
	
	@Override
	public int getColumnCount() {
	
		return 5;
		
	}
	
	@Override
	public Object getValueAt(int l, int c) {
	
		return data.get(l)[c];
	}
	
	@Override
	public boolean isCellEditable(int l, int c) {
		
		if(c  == 2)
			return true;
		else 
			return false;
		
	}
	
	@Override
	public String getColumnName(int c) {
		
		if (c == 0)
			return "Code à Bar";
		if (c == 1)
			return "Désignation";
		if (c == 2)
			return "Quantité";
		if (c == 3)
			return "Prix Unitaire";
		else 
			return "Prix";
		
	}
	
	@Override
	public void setValueAt(Object val, int l, int c) {
		
		data.get(l)[2] = Double.parseDouble((String)val);
		data.get(l)[4] = (double)data.get(l)[2]*(double)data.get(l)[3];
		mt.textTotal.setText(Double.toString(getSomme()));
		this.fireTableDataChanged();
		
	}
	
	public void supprimerLigne(int l) {
		
		data.remove(l);
		mt.textTotal.setText(Double.toString(getSomme()));
		this.fireTableDataChanged();
		
	}
	
	public void ajouter(long codeBar,String designation,double qteStock,double quantite,double prixUnitaire) {

		ProduitDAO pd = new ProduitDAO();
		ResultSet rs = pd.rechercherId((int)codeBar);
		for(int i=0; i<data.size();i++) {
			if (Long.parseLong(""+data.get(i)[0]) == codeBar) {
				if (qteStock < quantite+(double)data.get(i)[2]) {
					JOptionPane.showMessageDialog(null, "La quantité demandé n'existe pas dans le stock !");
					return;
				} 
				data.get(i)[2]=quantite+Double.parseDouble(data.get(i)[2].toString());
				data.get(i)[4] = (double)data.get(i)[2]*(double)data.get(i)[3];
				mt.textTotal.setText(Double.toString(getSomme()));
				this.fireTableDataChanged();
				return;
			}
		}
		if (qteStock < quantite) {
			JOptionPane.showMessageDialog(null, "La quantité demandé n'existe pas dans le stock !");
				return;
		} 
		double prix = prixUnitaire*quantite;
		data.add(new Object [] {codeBar,designation,quantite,prixUnitaire,prix});
		mt.textTotal.setText(Double.toString(getSomme()));
		this.fireTableDataChanged();
		
	}
	
	public double getSomme() {
		double sum = 0;
		for(int i = 0;i<data.size();i++) {
			sum += (double)data.get(i)[4];
		}
		return sum;
	}

}
