package model;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import dao.ProduitDAO;

public class ProduitPerimeModel extends AbstractTableModel{

	private ResultSetMetaData rsmd;
	
	ArrayList <Object[]> data = new ArrayList <Object[]> ();
	ProduitDAO pd = new ProduitDAO();
	
	public ProduitPerimeModel() {
		
		try {
			ResultSet rs = pd.produitPerime();
			rsmd = rs.getMetaData();
			while(rs.next()) {
				Object[] ligne = new Object[rsmd.getColumnCount()];
				
				for(int i=1;i<=rsmd.getColumnCount();i++) {
					ligne[i-1] = rs.getObject(i);
				}
				
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

		try {
			return rsmd.getColumnCount();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		
	}

	@Override
	public Object getValueAt(int l, int c) {

		return data.get(l)[c];
	}
	
	@Override
	public String getColumnName(int c) {
		
		try {
			return rsmd.getColumnName(c+1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	public int nomToIndice(String ch) {
		
		int a = -1;
		try {
			for ( int i = 0 ; i < rsmd.getColumnCount() ; i++ ) {
				if( getColumnName(i).equals(ch) ) 
					return i;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
		
	}
	
	@Override
	public boolean isCellEditable(int l, int c) {
		
		return false;
		
	}

	public void supprimerLigne(int selectedRow) {
		
		pd.supprimer(Integer.parseInt(""+data.get(selectedRow)[nomToIndice("codeBar")] ));
		data.remove(selectedRow);
		this.fireTableDataChanged();
		
	}
	
}
