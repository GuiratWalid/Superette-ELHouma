package ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.Produit_TicketDAO;
import dao.TicketDAO;
import model.TicketModel_AfficherTout;

public class AfficherToutTicket extends JFrame {

	private JLabel labelName;
	TicketModel_AfficherTout model = new TicketModel_AfficherTout();
	private JScrollPane sspane;

	public AfficherToutTicket() {
		
		// Conception de la page
		this.setTitle("Liste des tickets");
		this.setSize(new Dimension(700,400));
		this.setBackground(Color.GRAY);
		this.setDefaultCloseOperation(this.HIDE_ON_CLOSE);
		this.setLocation(350, 150);
		this.setVisible(true);
		
		// Layout
		this.setLayout(new BorderLayout());
		
		// Titre
		labelName = new JLabel("Liste des tickets");
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
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e ) {
				if(e.getButton()==e.BUTTON3) {
					JPopupMenu pop = new JPopupMenu();
					JMenuItem sup = new JMenuItem("supprimer");
					pop.add(sup);
					pop.show(table,e.getX(),e.getY());
					
					sup.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {

							model.supprimerLigne(table.getSelectedRow());
							
						}
					});
				}
			}
		}  );
	}
	
}
