package ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

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

import dao.CatégorieDAO;
import dao.ClientDAO;
import dao.EmployeDAO;
import dao.FactureDAO;
import dao.FournisseurDAO;
import dao.ProduitDAO;
import dao.TicketDAO;
import model.FactureModel_Ajouter;
import oo.Categorie;
import oo.Client;
import oo.Controle;
import oo.Employe;
import oo.Fournisseur;
import oo.Produit;

public class AjouterFacture extends JFrame implements ActionListener,WindowListener{

	
	private JLabel labelName;
	private JButton btAjouter;
	private JButton btAnnuler;
	private JLabel labelEmploye;
	private JTable table;
	private JPanel pb;
	private JLabel labelProduits;
	private JPanel pCenter;
	private FactureModel_Ajouter model;
	private JTextField codeBarText;
	private JTextField quantiteText;
	private JButton btAjouterProduit;
	private JComboBox<String> idEmploye;
	private JLabel labelDate1;
	private JLabel labelDate2;
	private JLabel labelFournisseur;
	private JComboBox<String> idFournisseur;
	private JLabel labelCash;
	private JTextField cashText;
	private JLabel labelTotal;
	public JTextField textTotal;

	public AjouterFacture() {
		
		// Conception de la page
		this.setTitle("Ajouter une Facture");
		this.setSize(new Dimension(1050,400));
		this.setBackground(Color.GRAY);
		this.setDefaultCloseOperation(this.HIDE_ON_CLOSE);
		this.setLocation(150, 150);
		this.setVisible(true);
		
		// Layout
		JPanel p = new JPanel();
		this.setLayout(new BorderLayout());
		JPanel pn = new JPanel();
		pn.setLayout(new GridLayout(6,2,5,5));
		pn.setBounds(5, 5, 100, 800);
		p.add(pn);
		
		// Titre
		labelName = new JLabel("Ajouter une Facture");
		labelName.setPreferredSize(new Dimension(200,100));
		labelName.setHorizontalAlignment(JLabel.CENTER);
		labelName.setVerticalAlignment(JLabel.CENTER);
		labelName.setForeground(new Color(178,35,35));
		labelName.setFont(new Font("Arial",Font.BOLD,30));
		this.add(labelName,BorderLayout.NORTH);
		
		// Date
		labelDate1 = new JLabel("Date");
		labelDate1.setFont(new Font("Arial",Font.ITALIC,15));
		labelDate1.setHorizontalAlignment(JLabel.CENTER);
		pn.add(labelDate1);
		
		labelDate2 = new JLabel(new Date().toLocaleString());
		labelDate2.setFont(new Font("Arial",Font.ITALIC,15));
		labelDate2.setHorizontalAlignment(JLabel.CENTER);
		pn.add(labelDate2);

		pn.add(new JLabel());
		pn.add(new JLabel());
		
		// Fournisseur
		labelFournisseur = new JLabel("Fournisseur");
		labelFournisseur.setFont(new Font("Arial",Font.ITALIC,15));
		labelFournisseur.setHorizontalAlignment(JLabel.CENTER);
		pn.add(labelFournisseur);
		
		idFournisseur = new JComboBox<String>();
		//remplir la liste à partir de la BD
		pn.add(idFournisseur);
		
		// Employé
		labelEmploye = new JLabel("Employé");
		labelEmploye.setFont(new Font("Arial",Font.ITALIC,15));
		labelEmploye.setHorizontalAlignment(JLabel.CENTER);
		pn.add(labelEmploye);
		
		idEmploye = new JComboBox<String>();
		//remplir la liste à partir de la BD
		pn.add(idEmploye);
		
		// Cash
		labelCash = new JLabel("Cash");
		labelCash.setFont(new Font("Arial",Font.ITALIC,15));
		labelCash.setHorizontalAlignment(JLabel.CENTER);
		pn.add(labelCash);
		
		cashText = new JTextField(6);
		pn.add(cashText);
		
		pn.add(new JLabel());
		pn.add(new JLabel());
		
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
		codeBarText.setToolTipText("Code à Bar");
		pSaisi.add(codeBarText);
		quantiteText = new JTextField(15);
		quantiteText.setToolTipText("Quantité");
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
		
		model = new FactureModel_Ajouter(this);
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
		pb.setPreferredSize(new Dimension(500,40));
		btAjouter = new JButton("Ajouter");
		pb.add(btAjouter,BorderLayout.SOUTH);
		btAjouter.addActionListener(this);
		btAnnuler = new JButton("Annuler");
		pb.add(btAnnuler);
		btAnnuler.addActionListener(this);
		this.add(pb,BorderLayout.SOUTH);
		
		this.addWindowListener(this);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btAjouter) {
			
			Fournisseur f = null;
			if(idFournisseur.getSelectedItem().toString() != "") {
			int idFour = Integer.parseInt(idFournisseur.getSelectedItem().toString().substring(0,idFournisseur.getSelectedItem().toString().indexOf("-")));
			String nomFour = idFournisseur.getSelectedItem().toString().substring(idFournisseur.getSelectedItem().toString().indexOf("-")+1);
				FournisseurDAO sd = new FournisseurDAO();
				ResultSet rs2 = sd.rechercherId(idFour);
				try {
					if (rs2.next()) { 
						f = new Fournisseur(rs2.getInt(1),rs2.getString(2),rs2.getString(3),rs2.getLong(4));
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
				JOptionPane.showMessageDialog(null, "Le champ Cash doit être un réel positive !");
				return;
			}
			double cash = Double.parseDouble(cashText.getText());
			FactureDAO td = new FactureDAO();
			oo.Facture fac = new oo.Facture(f,emp,cash,model.getSomme());
			ArrayList<Object[]> data = model.getData();
			for(int i=0 ;i<data.size();i++) {
				ProduitDAO prd = new ProduitDAO();
				ResultSet rs4 = prd.rechercherId(Integer.parseInt(""+data.get(i)[0]));
					try {
						if (rs4.next()) {
							CatégorieDAO cad = new CatégorieDAO();
							ResultSet rs5 = cad.rechercherId(rs4.getInt(5));
							Categorie cat = null;
							if(rs5.next()) {
								cat = new Categorie(rs5.getInt(1),rs5.getString(2));
							}
							Produit p1 = new Produit(Long.parseLong(""+data.get(i)[0]),rs4.getString(2),Double.parseDouble((""+data.get(i)[2])),rs4.getDouble(4),cat,rs4.getString(6),rs4.getString(7));
							fac.ajouterProduit(p1);
							Produit p2 = new Produit(rs4.getLong(1),rs4.getString(2),Double.parseDouble((""+data.get(i)[2]))+rs4.getDouble(3),rs4.getDouble(4),cat,rs4.getString(6),rs4.getString(7));
							prd.modifier(p2);
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
			if (fac.getTotal()>fac.getCash()) {
				JOptionPane.showMessageDialog(null, "Le Cash doit être supérieur au total");	
				return ;
			}
			oo.Facture s = td.ajouter(fac);
			if (s != null) {
				JOptionPane.showMessageDialog(null, "Nouvelle Facture est ajouté avec succès");	
			}
			else
				JOptionPane.showMessageDialog(null, "Problème d’ajout du Facture");	
			}
			
			else if (e.getSource() == btAnnuler) {
				this.dispose();
			}
		
			else if (e.getSource() == btAjouterProduit) {
				
				if (!Controle.isNumeric(codeBarText.getText()) || codeBarText.getText().length() != 9) {
					JOptionPane.showMessageDialog(null, "Le champ Code à Bar ne doit pas être vide et il doit contenir 9 des chiffres !");
					return;
				}
				ProduitDAO prd = new ProduitDAO();
				ResultSet rs4 = prd.rechercherId(Integer.parseInt(codeBarText.getText()));
					try {
						if (rs4.next()) {
							CatégorieDAO cad = new CatégorieDAO();
							ResultSet rs5 = cad.rechercherId(rs4.getInt(5));
							Categorie cat = null;
							if(rs5.next()) {
								cat = new Categorie(rs5.getInt(1),rs5.getString(2));
							}
							if (quantiteText.getText().isEmpty() || !Controle.isDoublePositive(quantiteText.getText())) {
								JOptionPane.showMessageDialog(null, "Le champ Quantité doit être un réel positive !");
								return;
							} 
							Produit p = new Produit(rs4.getLong(1),rs4.getString(2),Double.parseDouble(quantiteText.getText()),rs4.getDouble(4),cat,rs4.getString(6),rs4.getString(7));
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
		else if (e.getSource() == btAnnuler) {
			this.dispose();
		}
	}

	@Override
	public void windowOpened(WindowEvent e) {
		
		EmployeDAO cd = new EmployeDAO();
		ResultSet rs = cd.afficherTout();
		try {
			while(rs.next()) {
				idEmploye.addItem(rs.getInt(1)+"-"+rs.getString(2)+" "+rs.getString(3));
			}
			cd.fermerConnexion();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		FournisseurDAO fd = new FournisseurDAO();
		ResultSet rs1 = fd.afficherTout();
		try {
			while(rs1.next()) {
				idFournisseur.addItem(rs1.getInt(1)+"-"+rs1.getString(2)+" "+rs1.getString(3));
			}
			fd.fermerConnexion();
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
