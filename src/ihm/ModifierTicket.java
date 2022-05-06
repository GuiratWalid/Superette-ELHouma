package ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dao.Cat�gorieDAO;
import dao.ClientDAO;
import dao.EmployeDAO;
import dao.MagasinDAO;
import dao.ProduitDAO;
import dao.Produit_TicketDAO;
import dao.TicketDAO;
import model.TicketModel_Modifier;
import oo.Categorie;
import oo.Client;
import oo.Controle;
import oo.Employe;
import oo.Magasin;
import oo.Produit;
import oo.Ticket;

public class ModifierTicket extends JFrame implements ActionListener,WindowListener,ItemListener{

	
	private JLabel labelName;
	private JButton btModifier;
	private JButton btAnnuler;
	private JLabel labelClient;
	private JComboBox<String> idClient;
	private JLabel labelEmploye;
	private JTable table;
	private JPanel pb;
	private JLabel labelProduits;
	private JPanel pCenter;
	private TicketModel_Modifier model;
	private JComboBox<String> idEmploye;
	private JComboBox<String> idMagasin;
	private JLabel labelMagasin;
	private JTextField codeBarText;
	private JTextField quantiteText;
	private JButton btAjouterProduit;
	private JLabel labelTicket;
	private JComboBox<Integer> idTicket;
	private JLabel labelCash;
	private JTextField cashText;
	private JLabel labelTotal;
	public JTextField textTotal;

	public ModifierTicket() {
		
		// Conception de la page
		this.setTitle("Modifier un Ticket");
		this.setSize(new Dimension(1050,400));
		this.setBackground(Color.GRAY);
		this.setDefaultCloseOperation(this.HIDE_ON_CLOSE);
		this.setLocation(150, 150);
		this.setVisible(true);
		
		// Layout
		JPanel p = new JPanel();
		this.setLayout(new BorderLayout());
		JPanel pn = new JPanel();
		pn.setLayout(new GridLayout(5,2));
		pn.setBounds(5, 5, 100, 800);
		p.add(pn);
		
		// Titre
		labelName = new JLabel("Modifier un Ticket");
		labelName.setPreferredSize(new Dimension(200,100));
		labelName.setHorizontalAlignment(JLabel.CENTER);
		labelName.setVerticalAlignment(JLabel.CENTER);
		labelName.setForeground(new Color(178,35,35));
		labelName.setFont(new Font("Arial",Font.BOLD,30));
		this.add(labelName,BorderLayout.NORTH);
		
		// Ticket
		labelTicket = new JLabel("ID Ticket");
		labelTicket.setFont(new Font("Arial",Font.ITALIC,15));
		labelTicket.setHorizontalAlignment(JLabel.CENTER);
		pn.add(labelTicket);
		
		idTicket = new JComboBox<Integer>();
		//remplir la liste � partir de la BD
		idTicket.addItemListener(this);
		pn.add(idTicket);
		
		// Magasin
		labelMagasin = new JLabel("Magasin");
		labelMagasin.setFont(new Font("Arial",Font.ITALIC,15));
		labelMagasin.setHorizontalAlignment(JLabel.CENTER);
		pn.add(labelMagasin);
		
		idMagasin = new JComboBox<String>();
		//remplir la liste � partir de la BD
		pn.add(idMagasin);
		
		// Client
		labelClient = new JLabel("Client");
		labelClient.setFont(new Font("Arial",Font.ITALIC,15));
		labelClient.setHorizontalAlignment(JLabel.CENTER);
		pn.add(labelClient);
		
		idClient = new JComboBox<String>();
		//remplir la liste � partir de la BD
		pn.add(idClient);
		
		// Employ�
		labelEmploye = new JLabel("Employ�");
		labelEmploye.setFont(new Font("Arial",Font.ITALIC,15));
		labelEmploye.setHorizontalAlignment(JLabel.CENTER);
		pn.add(labelEmploye);
		
		idEmploye = new JComboBox<String>();
		//remplir la liste � partir de la BD
		pn.add(idEmploye);
		
		// Cash
		labelCash = new JLabel("Cash");
		labelCash.setFont(new Font("Arial",Font.ITALIC,15));
		labelCash.setHorizontalAlignment(JLabel.CENTER);
		pn.add(labelCash);
		
		cashText = new JTextField(6);
		pn.add(cashText);
		
		//Produits
		pCenter = new JPanel();
		pCenter.setLayout(new FlowLayout());
		labelProduits = new JLabel("Liste des produits");
		labelProduits.setFont(new Font("Arial",Font.ITALIC,15));
		labelProduits.setHorizontalAlignment(JLabel.CENTER);
		pCenter.add(labelProduits);
		
		JPanel pSaisi = new JPanel();
		pSaisi.setLayout(new GridLayout());
		codeBarText = new JTextField(15);
		codeBarText.setToolTipText("Code � Bar");
		pSaisi.add(codeBarText);
		quantiteText = new JTextField(15);
		quantiteText.setToolTipText("Quantit�");
		pSaisi.add(quantiteText);
		btAjouterProduit = new JButton("Ajouter le produit");
		btAjouterProduit.addActionListener(this);
		pSaisi.add(btAjouterProduit);
		pCenter.add(pSaisi);
		
		// Total
		JPanel jp = new JPanel();
		jp.setBounds(10, 5, 220, 40);
		jp.setAlignmentX(RIGHT_ALIGNMENT);
		jp.setLayout(new GridLayout(1,2,15,30));
		labelTotal = new JLabel("Total");
		labelTotal.setFont(new Font("Arial",Font.ITALIC,15));
		labelTotal.setPreferredSize(new Dimension(100,30));
		labelTotal.setHorizontalAlignment(JLabel.CENTER);
		jp.add(labelTotal);
		
		textTotal = new JTextField("0",15);
		jp.add(textTotal);
		pCenter.add(jp);
		
		model = new TicketModel_Modifier(this);
		table = new JTable(model);
		table.setRowHeight(30);
		table.setBounds(5, 5, 200, 300);
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
		pCenter.add(new JScrollPane(table));
		
		p.add(pCenter);
		p.setLayout(new GridLayout(1,2,10,10));
		this.add(p,BorderLayout.CENTER);
		
		// Les boutons
		pb = new JPanel();
		pb.setLayout(new GridLayout());
		pb.setPreferredSize(new Dimension(300,40));
		btModifier = new JButton("Modifier");
		pb.add(btModifier,BorderLayout.SOUTH);
		btModifier.addActionListener(this);
		btAnnuler = new JButton("Annuler");
		pb.add(btAnnuler);
		btAnnuler.addActionListener(this);
		this.add(pb,BorderLayout.SOUTH);
		
		this.addWindowListener(this);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btModifier) {
			
			int idMag = Integer.parseInt(idMagasin.getSelectedItem().toString().substring(0,idMagasin.getSelectedItem().toString().indexOf("-")));
			MagasinDAO md = new MagasinDAO();
			ResultSet rs1 = md.rechercherId(idMag);
			Magasin m = null;
			try {
				if (rs1.next()) {
					m = new Magasin(rs1.getInt(1),rs1.getString(2),rs1.getString(3),rs1.getLong(4));
				}
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

			Client c = null;
			if(idClient.getSelectedItem().toString() != "") {
				int idClt = Integer.parseInt(idClient.getSelectedItem().toString().substring(0,idClient.getSelectedItem().toString().indexOf("-")));
				String nomClt = idClient.getSelectedItem().toString().substring(idClient.getSelectedItem().toString().indexOf("-")+1);
				ClientDAO sd = new ClientDAO();
				ResultSet rs2 = sd.rechercherId(idClt);
				try {
					if (rs2.next()) {
							c = new Client(rs2.getInt(1),rs2.getString(2),rs2.getString(3),rs2.getLong(4),rs2.getString(5));
					}
				} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
				}
			}

			int idEmp = Integer.parseInt(idEmploye.getSelectedItem().toString().substring(0,idEmploye.getSelectedItem().toString().indexOf("-")));
			EmployeDAO ed = new EmployeDAO();
			ResultSet rs3 = ed.rechercherId(idEmp);
			Employe emp = null;
			try {
				if (rs3.next()) {
					emp = new Employe(rs3.getInt(1),rs3.getString(2),rs3.getString(3),rs3.getLong(4),rs3.getString(5),rs3.getDouble(6),rs3.getString(7),rs3.getString(8));
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			if (cashText.getText().isEmpty() || !Controle.isDoublePositive(cashText.getText())) {
				JOptionPane.showMessageDialog(null, "Le champ Cash doit �tre un r�el positive !");
				return;
			}
			int id = Integer.parseInt(idTicket.getSelectedItem().toString());
			TicketDAO td = new TicketDAO();
			oo.Ticket t = new oo.Ticket(id,c,emp,m,Double.parseDouble(cashText.getText()),model.getSomme());
			t.setProduits(model.produits());
			if (t.getTotal()>t.getCash()) {
				JOptionPane.showMessageDialog(null, "Le Cash doit �tre sup�rieur au total");	
				return ;
			}
			oo.Ticket tk = td.modifier(t);
			if (tk != null) {
				JOptionPane.showMessageDialog(null, "Ticket est modifi� avec succ�s");	
			}
			else
				JOptionPane.showMessageDialog(null, "Probl�me de modification du Ticket");	
			}
			
			else if (e.getSource() == btAnnuler) {
				this.dispose();
			}
		
			else if (e.getSource() == btAjouterProduit) {
				
				if (!Controle.isNumeric(codeBarText.getText()) || codeBarText.getText().length() != 9) {
					JOptionPane.showMessageDialog(null, "Le champ Code � Bar ne doit pas �tre vide et il doit contenir 9 des chiffres !");
					return;
				}
				ProduitDAO prd = new ProduitDAO();
				ResultSet rs4 = prd.rechercherId(Integer.parseInt(codeBarText.getText()));
				try {
					if (rs4.next()) {
						Cat�gorieDAO cad = new Cat�gorieDAO();
						ResultSet rs5 = cad.rechercherId(rs4.getInt(5));
						Categorie cat = null;
						if(rs5.next()) {
							cat = new Categorie(rs5.getInt(1),rs5.getString(2));
						}
						if (quantiteText.getText().isEmpty() || !Controle.isDoublePositive(quantiteText.getText())) {
							JOptionPane.showMessageDialog(null, "Le champ Quantit� doit �tre un r�el positive !");
							return;
						} 
						Produit p = new Produit(Long.parseLong(codeBarText.getText()), rs4.getString(2), Double.parseDouble(quantiteText.getText()), rs4.getDouble(4), cat,rs4.getString(6) , rs4.getString(7));
						model.ajouter(rs4.getLong(1),rs4.getString(2),rs4.getDouble(3), Double.parseDouble(quantiteText.getText()),rs4.getDouble(4));
					
					} 
					else {
						JOptionPane.showMessageDialog(null, "Le Produit n'existe pas !");
					}
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
	}
	
	@Override
	public void windowOpened(WindowEvent e) {

		TicketDAO td = new TicketDAO();
		ResultSet rs0 = td.afficherTout();
		try {
			while(rs0.next()) {
				idTicket.addItem(rs0.getInt(1));
			}
			td.fermerConnexion();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		EmployeDAO ed = new EmployeDAO();
		ResultSet rs = ed.afficherTout();
		try {
			while(rs.next()) {
				idEmploye.addItem(rs.getInt(1)+"-"+rs.getString(2)+" "+rs.getString(3));
			}
			ed.fermerConnexion();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


		ClientDAO cd = new ClientDAO();
		ResultSet res = cd.afficherTout();
		try {
			while(res.next()) {
				idClient.addItem(res.getInt(1)+"-"+res.getString(2)+" "+res.getString(3));
			}
			cd.fermerConnexion();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		MagasinDAO md = new MagasinDAO();
		ResultSet r = md.afficherTout();
		try {
			while(r.next()) {
				idMagasin.addItem(r.getString(1)+"-"+r.getString(2));
			}
			md.fermerConnexion();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
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

	@Override
	public void itemStateChanged(ItemEvent e) {
		
		model.Init(Integer.parseInt(idTicket.getSelectedItem().toString()));
		TicketDAO td = new TicketDAO();
		ResultSet rs0 = td.rechercherId((int)idTicket.getSelectedItem());
		try {
			if(rs0.next()) {
				EmployeDAO ed = new EmployeDAO();
				ResultSet rs1 = ed.rechercherId(rs0.getInt(4));
				if(rs1.next()) {
					idEmploye.setSelectedItem(rs1.getInt(1)+"-"+rs1.getString(2)+" "+rs1.getString(3));
				}
				ed.fermerConnexion();
					
				ClientDAO cd = new ClientDAO();
				ResultSet rs2 = cd.rechercherId(rs0.getInt(3));
				if(rs2.next()) {
					idClient.setSelectedItem(rs2.getInt(1)+"-"+rs2.getString(2)+" "+rs2.getString(3));
				}
				cd.fermerConnexion();
				
				MagasinDAO md = new MagasinDAO();
				ResultSet rs3 = md.rechercherId(rs0.getInt(5));
				if(rs3.next()) {
					idMagasin.setSelectedItem(rs3.getString(1)+"-"+rs3.getString(2));
				}
				md.fermerConnexion();
				Produit_TicketDAO ptd = new Produit_TicketDAO();
				ResultSet rs4 = ptd.afficherTout(Integer.parseInt(idTicket.getSelectedItem().toString()));
				try {
					while(rs4.next()) {
						model.afficher(rs4.getLong(1), rs4.getDouble(3));
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				cashText.setText(rs0.getString(6));
				textTotal.setText(rs0.getString(7));
			}	
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

}
