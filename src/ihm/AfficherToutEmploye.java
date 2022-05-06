package ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.EmployeDAO;



public class AfficherToutEmploye extends JFrame implements  WindowListener{

	private JLabel labelName;
	DefaultTableModel model = new DefaultTableModel();

	public AfficherToutEmploye() {
		
		// Conception de la page
		this.setTitle("Liste des employés");
		this.setSize(new Dimension(700,400));
		this.setBackground(Color.GRAY);
		this.setDefaultCloseOperation(this.HIDE_ON_CLOSE);
		this.setLocation(350, 150);
		this.setVisible(true);
		
		// Layout
		this.setLayout(new BorderLayout());
		
		// Titre
		labelName = new JLabel("Liste des employés");
		labelName.setPreferredSize(new Dimension(200,100));
		labelName.setHorizontalAlignment(JLabel.CENTER);
		labelName.setVerticalAlignment(JLabel.CENTER);
		labelName.setForeground(new Color(178,35,35));
		labelName.setBackground(Color.white);
		labelName.setFont(new Font("Arial",Font.BOLD,30));
		this.add(labelName,BorderLayout.NORTH);	
		
		// Table
		JTable table = new JTable (model);
		table.setBackground(Color.white);
		table.setRowHeight(30);
		table.setSelectionBackground(Color.MAGENTA);
		add(new JScrollPane(table),BorderLayout.CENTER);
		this.addWindowListener(this);
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		
		EmployeDAO cd = new EmployeDAO();
		ResultSetMetaData rsmd = cd.colonne();
		int nbCol = 0;
		try {
			nbCol = rsmd.getColumnCount();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		ResultSet rs = cd.afficherTout();
		for(int i = 1; i <= nbCol;i++) {
			try {
					model.addColumn(rsmd.getColumnName(i));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}
		try {
			while(rs.next()) {
				Object obj [] = new Object[nbCol];
				for(int i=1;i<=nbCol;i++) {
					obj[i-1]=rs.getObject(i);
				}
				model.addRow(obj);
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
