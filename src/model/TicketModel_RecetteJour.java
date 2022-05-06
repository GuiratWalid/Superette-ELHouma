package model;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import dao.TicketDAO;

public class TicketModel_RecetteJour extends AbstractTableModel{

	private ArrayList <Object[]> data = new ArrayList <Object[]> ();
	private double somme = 0;
	
	public TicketModel_RecetteJour() {
		
		TicketDAO td = new TicketDAO();
		ResultSet rs = td.ticketJour();
		try {
				while(rs.next()) {
					Object[] ligne = new Object[2];
					ligne[0] = rs.getLong(1);
					ligne[1] = rs.getDouble(7);
					data.add(ligne);
					somme += (double)ligne[1];
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

		return 2;
		
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
			return "Identifiant du Ticket";
		else
			return "Montant du Ticket";
	}
	
	public double getSomme() {
		return somme;
	}
	
}
