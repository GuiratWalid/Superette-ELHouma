package model;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import dao.ProduitDAO;
import dao.Produit_TicketDAO;
import oo.Produit;

public class TicketModel_Rechercher extends AbstractTableModel{

	private ResultSetMetaData rsmd;
	private int id;
	private ArrayList <Object[]> data = new ArrayList <Object[]> ();
	private Produit_TicketDAO ptd = new Produit_TicketDAO();
	private double somme;
	
	public TicketModel_Rechercher(ArrayList<Produit> produits) {
		
		somme = 0;
		for(int i = 0; i < produits.size();i++) {
			Object[] ligne = new Object[5];
			ligne[0] = produits.get(i).getQuantite();
			ligne[1] = produits.get(i).getDesignation();
			ligne[2] = produits.get(i).getCodeBar();
			ligne[3] = produits.get(i).getPrixUnitaire();
			ligne[4] = (double)ligne[0]*(double)ligne[3];
			somme += (double)ligne[4];
			data.add(ligne);
		}
		
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
		
		return false;
		
	}
	
	@Override
	public String getColumnName(int c) {
		
		if (c == 0)
			return "Quantité";
		if (c == 1)
			return "Désignation";
		if (c == 2)
			return "Code à Bar";
		if (c == 3)
			return "Prix Unitaire";
		else 
			return "Prix";
		
	}
	
	public double somme() {
		
		return somme;
		
	}

}
