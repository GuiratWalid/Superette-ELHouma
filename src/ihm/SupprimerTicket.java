package ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import dao.CatégorieDAO;
import dao.FactureDAO;
import dao.TicketDAO;
import oo.Categorie;
import oo.Ticket;

public class SupprimerTicket extends JFrame implements ActionListener,WindowListener{

	
	private JLabel labelName;
	private JButton btAnnuler;
	private JPanel pb;
	private JButton btSupprimer;
	private JLabel labelId;
	private JComboBox<Integer> idTicket;

	public SupprimerTicket() {
		
		// Conception de la page
		this.setTitle("Supprimer un Ticket");
		this.setSize(new Dimension(700,250));
		this.setBackground(Color.GRAY);
		this.setDefaultCloseOperation(this.HIDE_ON_CLOSE);
		this.setLocation(350, 150);
		this.setVisible(true);
		
		// Layout
		this.setLayout(new BorderLayout());
		JPanel pn = new JPanel();
		pn.setLayout(new GridLayout(2,2,5,5));
		
		// Titre
		labelName = new JLabel("Supprimer un Ticket");
		labelName.setPreferredSize(new Dimension(200,100));
		labelName.setHorizontalAlignment(JLabel.CENTER);
		labelName.setVerticalAlignment(JLabel.CENTER);
		labelName.setForeground(new Color(178,35,35));
		labelName.setFont(new Font("Arial",Font.BOLD,30));
		this.add(labelName,BorderLayout.NORTH);
		
		// Magasin
		labelId = new JLabel("Identifiant Ticket");
		labelId.setFont(new Font("Arial",Font.ITALIC,15));
		labelId.setHorizontalAlignment(JLabel.CENTER);
		labelId.setSize(new Dimension(100,30));
		pn.add(labelId);
				
		idTicket = new JComboBox<Integer>();
		//remplir la liste à partir de la BD
		idTicket.setSize(new Dimension(100,30));
		pn.add(idTicket);
		pn.add(new JLabel());
		pn.add(new JLabel());
		
		this.add(pn,BorderLayout.CENTER);
		
		// Les boutons
		pb = new JPanel();
		pb.setLayout(new GridLayout());
		pb.setPreferredSize(new Dimension(300,40));
		btSupprimer = new JButton("Supprimer");
		pb.add(btSupprimer,BorderLayout.SOUTH);
		btSupprimer.addActionListener(this);
		btAnnuler = new JButton("Annuler");
		pb.add(btAnnuler);
		btAnnuler.addActionListener(this);
		this.add(pb,BorderLayout.SOUTH);
		
		this.addWindowListener(this);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btSupprimer) {
			
			TicketDAO td = new TicketDAO();
			int id = Integer.parseInt(idTicket.getSelectedItem().toString());
			int s = td.supprimer(id);
			if (s != 0) {
				JOptionPane.showMessageDialog(null, "Ticket est supprimé avec succès");	
				}
			else
				JOptionPane.showMessageDialog(null, "Problème de suppression du Ticket");
			
			td.fermerConnexion();
			
		}
		else if (e.getSource() == btAnnuler) {
			this.dispose();
		}
	}

	@Override
	public void windowOpened(WindowEvent e) {
		
		TicketDAO td = new TicketDAO();
		ResultSet listeTic = td.afficherTout();
		try {
			while(listeTic.next()) {
				idTicket.addItem(listeTic.getInt(1));
			}
			td.fermerConnexion();
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
