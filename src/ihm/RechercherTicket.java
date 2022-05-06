package ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import dao.CatégorieDAO;
import dao.ClientDAO;
import dao.EmployeDAO;
import dao.MagasinDAO;
import dao.ProduitDAO;
import dao.Produit_TicketDAO;
import dao.TicketDAO;
import oo.Categorie;
import oo.Client;
import oo.Employe;
import oo.Magasin;
import oo.Produit;

public class RechercherTicket extends JFrame implements ActionListener,WindowListener{

	
	private JLabel labelName;
	private JButton btAnnuler;
	private JPanel pb;
	private JComboBox<Integer> idTicket;
	private JButton btRechercher;
	private JLabel labelId;

	public RechercherTicket() {
		
		// Conception de la page
		this.setTitle("Rechercher un Ticket");
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
		labelName = new JLabel("Rechercher un Ticket");
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
		btRechercher = new JButton("Rechercher");
		pb.add(btRechercher,BorderLayout.SOUTH);
		btRechercher.addActionListener(this);
		btAnnuler = new JButton("Annuler");
		pb.add(btAnnuler);
		btAnnuler.addActionListener(this);
		this.add(pb,BorderLayout.SOUTH);
		
		this.addWindowListener(this);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btRechercher) {
			
			TicketDAO td = new TicketDAO();
			Employe emp = null;
			Magasin m = null;
			Client c = null;
			ResultSet rs0 = td.rechercherId((int)idTicket.getSelectedItem());
			try {
				if(rs0.next()) {
					EmployeDAO ed = new EmployeDAO();
					ResultSet rs1 = ed.rechercherId(rs0.getInt(4));
					if(rs1.next()) {
						emp = new Employe(rs1.getInt(1), rs1.getString(2), rs1.getString(3), rs1.getLong(4), rs1.getString(5), rs1.getDouble(6), rs1.getString(7), rs1.getString(8));
					} 
					ed.fermerConnexion();
						
					ClientDAO cd = new ClientDAO();
					ResultSet rs2 = cd.rechercherId(rs0.getInt(3));
					if(rs2.next()) {
						c = new Client(rs2.getInt(1), rs2.getString(2), rs2.getString(3), rs2.getLong(4),rs2.getString(5));
					cd.fermerConnexion();
					
					MagasinDAO md = new MagasinDAO();
					ResultSet rs3 = md.rechercherId(rs0.getInt(5));
					if(rs3.next()) {
						m = new Magasin(rs3.getInt(1), rs3.getString(2), rs3.getString(3), rs3.getLong(4));
					}
					oo.Ticket t = new oo.Ticket((int)idTicket.getSelectedItem(), c, emp,m,rs0.getDouble(6),0);
					Produit_TicketDAO ptd = new Produit_TicketDAO();
					ResultSet rs = ptd.afficherTout((int)idTicket.getSelectedItem());
					ArrayList<Produit> produits = new ArrayList<Produit>();
					try {
						while(rs.next()) {
							ProduitDAO prd = new ProduitDAO();
							Produit p = null;
							ResultSet res = prd.rechercherId(rs.getInt(1));
								if (res.next()) {
									CatégorieDAO cad = new CatégorieDAO();
									ResultSet res1 = cad.rechercherId(res.getInt(5));
									Categorie cat = null;
									if(res1.next()) {
										cat = new Categorie(res1.getInt(1),res1.getString(2));
									} 
									p = new Produit(res.getLong(1), res.getString(2), rs.getDouble(3), res.getDouble(4), cat,res.getString(6) , res.getString(7));
									produits.add(p);
								}
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					t.setProduits(produits);
					Ticket tic = new Ticket(t);
					md.fermerConnexion();
					}
				}
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null, "Problème de Recherche du Ticket");
				e1.printStackTrace();
			}
			
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
