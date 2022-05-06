package model;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import dao.FactureDAO;
import dao.TicketDAO;

public class FactureModel_AfficherTout extends AbstractTableModel{

	private ArrayList <Object[]> data = new ArrayList <Object[]> ();
	private FactureDAO ptd = new FactureDAO();
	
	public FactureModel_AfficherTout() {
		
		try {
			FactureDAO ptd = new FactureDAO();
			ResultSet rs = ptd.afficherTout();
			while(rs.next()) {
				Object[] ligne = new Object[4];
				ligne[0] = rs.getInt(1);
				ligne[1] = rs.getString(2);
				ligne[2] = rs.getInt(3);
				ligne[3] = rs.getInt(4);
				data.add(ligne);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public int getRowCount() {

		return data.size();
		
	}

	@Override
	public int getColumnCount() {

		return 4;
		
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
			return "Identifiant Facture";
		else if (c == 1)
			return "Date";
		else if (c == 2)
			return "Identifiant Fournisseur";
		else 
			return "Identifiant Employé";
		
	}

	public void supprimerLigne(int l) {
		
		ptd.supprimer((int)data.get(l)[0]);
		data.remove(l);
		this.fireTableDataChanged();
		
	}

}
