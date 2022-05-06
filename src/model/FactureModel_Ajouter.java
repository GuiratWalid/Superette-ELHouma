package model;

import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import dao.ProduitDAO;
import ihm.AjouterFacture;

public class FactureModel_Ajouter  extends AbstractTableModel{

	private ArrayList <Object[]> data = new ArrayList <Object[]> ();
	private AjouterFacture aj = null;
	
	public FactureModel_Ajouter(AjouterFacture aj) {
		this.aj = aj;
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
		aj.textTotal.setText(Double.toString(getSomme()));
		this.fireTableDataChanged();
		
	}

	public void supprimerLigne(int l) {
		
		data.remove(l);
		aj.textTotal.setText(Double.toString(getSomme()));
		this.fireTableDataChanged();
		
	}
	
	public void ajouter(long codeBar,String designation,double qteStock,double quantite,double prixUnitaire) {

		ProduitDAO pd = new ProduitDAO();
		ResultSet rs = pd.rechercherId((int)codeBar);
		for(int i=0; i<data.size();i++) {
			if (Long.parseLong(""+data.get(i)[0]) == codeBar) {
								data.get(i)[2]=quantite+Double.parseDouble(data.get(i)[2].toString());
				data.get(i)[4] = (double)data.get(i)[2]*(double)data.get(i)[3];
				aj.textTotal.setText(Double.toString(getSomme()));
				this.fireTableDataChanged();
				return;
			}
		}
		double prix = prixUnitaire*quantite;
		data.add(new Object [] {codeBar,designation,quantite,prixUnitaire,prix});
		aj.textTotal.setText(Double.toString(getSomme()));
		this.fireTableDataChanged();
		
	}
	
	public ArrayList<Object[]> getData(){
		return data;
	}
	
	public double getSomme() {
		
		double s = 0;
		for(int i=0 ; i<data.size();i++) {
			s+= (double)data.get(i)[4];
		}
		return s;
		
	}

}