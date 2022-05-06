package model;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import dao.ClientDAO;
import dao.EmployeDAO;
import dao.MagasinDAO;
import dao.Produit_TicketDAO;
import dao.TicketDAO;
import oo.Client;
import oo.Controle;
import oo.Employe;
import oo.Magasin;
import oo.Ticket;

public class TicketModel_AfficherTout extends AbstractTableModel{

	private ResultSetMetaData rsmd;
	private ArrayList <Object[]> data = new ArrayList <Object[]> ();
	private TicketDAO ptd = new TicketDAO();
	
	public TicketModel_AfficherTout() {
		
		try {
			TicketDAO ptd = new TicketDAO();
			ResultSet rs = ptd.afficherTout();
			rsmd = rs.getMetaData();
			while(rs.next()) {
				Object[] ligne = new Object[6];
				ligne[0] = rs.getInt(1);
				ligne[1] = rs.getString(2);
				ligne[2] = rs.getInt(3);
				ligne[3] = rs.getInt(4);
				ligne[4] = rs.getInt(5);
				ligne[5] = rs.getDouble(6);
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
			return "Identifiant Ticket";
		else if (c == 1)
			return "Date";
		else if (c == 2)
			return "Identifiant Client";
		else if (c == 3)
			return "Identifiant Employé";
		else if (c == 4)
			return "Identifiant Magasin";
		else 
			return "Cash";
		
	}

	public void supprimerLigne(int l) {
		
		ptd.supprimer((int)data.get(l)[0]);
		data.remove(l);
		this.fireTableDataChanged();
		
	}

}
