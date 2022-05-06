package model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import oo.Produit;

public class FactureModel_Rechercher extends AbstractTableModel{

	private ArrayList <Object[]> data = new ArrayList <Object[]> ();
	private int somme;
	
	public FactureModel_Rechercher(ArrayList<Produit> produits) {
		
		somme = 0;
		for(int i = 0; i < produits.size();i++) {
			Object[] ligne = new Object[6];
			ligne[0] = produits.get(i).getCodeBar();
			ligne[1] = produits.get(i).getDesignation();
			ligne[2] = produits.get(i).getQuantite();
			ligne[3] = produits.get(i).getPrixUnitaire();
			ligne[4] = (double)ligne[2]*(double)ligne[3]*0.15;
			ligne[5] = (double)ligne[2]*(double)ligne[3]+(double)ligne[4];
			somme += (double)ligne[5];
			data.add(ligne);
		}
		
	}
	
	@Override
	public int getRowCount() {

		return data.size();
		
	}

	@Override
	public int getColumnCount() {

		return 6;
		
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
			return "Code à Bar";
		else if (c == 1)
			return "Désignation";
		else if (c == 2)
			return "Quantité";
		else if (c == 3)
			return "Prix Unitaire";
		else if (c == 4)
			return "TVA";
		else 
			return "Totale";
		
	}
	
	public double somme() {
		
		return somme;
		
	}

}
