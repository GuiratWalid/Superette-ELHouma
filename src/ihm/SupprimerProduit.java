package ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dao.CatégorieDAO;
import dao.ProduitDAO;
import oo.Categorie;
import oo.Controle;
import oo.Produit;

public class SupprimerProduit extends JFrame implements ActionListener,WindowListener,ItemListener {
	private JLabel labelName;
	private JLabel labelDesignation;
	private JTextField textDesignation;
	private JButton btAnnuler;
	private JLabel labelCategorie;
	private JComboBox<String> idCategorie;
	private JLabel labelCodeBar;
	private JLabel labelPrix;
	private JTextField textPrix;
	private JLabel labelQuantite;
	private JTextField textQuantite;
	private JComboBox<String> CodeBar;
	private JButton btSupprimer;
	private JLabel lb_fabrication;
	private JPanel dnFab;
	private Integer[] jourFab;
	private JComboBox<Integer> jour_list_Fab;
	private JLabel slash1;
	private Integer[] moisFab;
	private JComboBox mois_list_Fab;
	private JLabel slash2;
	private Integer[] anneeFab;
	private JComboBox<Integer> annee_list_Fab;
	private JLabel lb_expiration;
	private JPanel dnExp;
	private Integer[] jourExp;
	private JComboBox<Integer> jour_list_Exp;
	private JLabel slash3;
	private Integer[] moisExp;
	private JComboBox mois_list_Exp;
	private JLabel slash4;
	private Integer[] anneeExp;
	private JComboBox<Integer> annee_list_Exp;

	public SupprimerProduit() {
		
		// Conception de la page
		this.setTitle("Supprimer un produit");
		this.setSize(new Dimension(700,500));
		this.setBackground(Color.GRAY);
		this.setDefaultCloseOperation(this.HIDE_ON_CLOSE);
		this.setLocation(350, 150);
		this.setVisible(true);
		
		// Layout
		this.setLayout(new BorderLayout());
		
		// Titre
		labelName = new JLabel("Supprimer un produit");
		labelName.setPreferredSize(new Dimension(200,100));
		labelName.setHorizontalAlignment(JLabel.CENTER);
		labelName.setVerticalAlignment(JLabel.CENTER);
		labelName.setForeground(new Color(178,35,35));
		labelName.setFont(new Font("Arial",Font.BOLD,30));
		this.add(labelName,BorderLayout.NORTH);	
		
		// Formulaire
		JPanel pAPr = new JPanel();
		pAPr.setLayout(new GridLayout(8,2,20,10));
		pAPr.setBorder(new EmptyBorder(5,5,5,5));
		this.add(pAPr);
		
		// Code à Bar
		labelCodeBar = new JLabel("Code à Bar");
		labelCodeBar.setFont(new Font("Arial",Font.ITALIC,15));
		labelCodeBar.setHorizontalAlignment(JLabel.CENTER);
		pAPr.add(labelCodeBar);
		
		CodeBar = new JComboBox<String>();
		//remplir la liste à partir de la BD
		CodeBar.addItemListener(this);
		pAPr.add(CodeBar);
		
		// Désignation
		labelDesignation = new JLabel("Designation");
		labelDesignation.setFont(new Font("Arial",Font.ITALIC,15));
		labelDesignation.setHorizontalAlignment(JLabel.CENTER);
		pAPr.add(labelDesignation);
		
		textDesignation = new JTextField();
		textDesignation.setPreferredSize(new Dimension(150,50));
		pAPr.add(textDesignation,BorderLayout.CENTER);
		
		// Quantité
		labelQuantite = new JLabel("Quantité");
		labelQuantite.setFont(new Font("Arial",Font.ITALIC,15));
		labelQuantite.setHorizontalAlignment(JLabel.CENTER);
		pAPr.add(labelQuantite);
		
		textQuantite = new JTextField();
		textQuantite.setPreferredSize(new Dimension(150,50));
		pAPr.add(textQuantite,BorderLayout.CENTER);
		
		// Prix
		labelPrix = new JLabel("Prix");
		labelPrix.setFont(new Font("Arial",Font.ITALIC,15));
		labelPrix.setHorizontalAlignment(JLabel.CENTER);
		pAPr.add(labelPrix);
		
		textPrix = new JTextField();
		textPrix.setPreferredSize(new Dimension(150,50));
		pAPr.add(textPrix,BorderLayout.CENTER);
		
		// Catégorie
		labelCategorie = new JLabel("Catégorie");
		labelCategorie.setFont(new Font("Arial",Font.ITALIC,15));
		labelCategorie.setHorizontalAlignment(JLabel.CENTER);
		pAPr.add(labelCategorie);
		
		idCategorie = new JComboBox<String>();
		//remplir la liste à partir de la BD
		pAPr.add(idCategorie);
		
		// Date de fabrication
		lb_fabrication= new JLabel("Date de production");
		lb_fabrication.setFont(new Font("Arial",Font.ITALIC,15));
		lb_fabrication.setHorizontalAlignment(JLabel.CENTER);
		pAPr.add(lb_fabrication);
		
		dnFab = new JPanel();//Panel Date de naissance ComboBoxes
		dnFab.setLayout(new GridLayout());
		
		jourFab = new Integer[31];//liste des jours
		for(int i=1;i<=31;i++)
			jourFab[i-1]=i;
		jour_list_Fab = new JComboBox<Integer>(jourFab);
		dnFab.add(jour_list_Fab);
		
		slash1 = new JLabel("/",JLabel.CENTER);
		slash1.setFont(new Font("Arial",Font.BOLD,20));
		dnFab.add(slash1);

		moisFab = new Integer[12];//liste des mois
		for(int i=1;i<=12;i++)
			moisFab[i-1]=i;
		mois_list_Fab = new JComboBox<>(moisFab);
		dnFab.add(mois_list_Fab);

		slash2 = new JLabel("/",JLabel.CENTER);
		slash2.setFont(new Font("Arial",Font.BOLD,20));
		dnFab.add(slash2);
		
		anneeFab = new Integer [100];//liste des mois
		for(int i=Year.now().getValue()-10;i < Year.now().getValue()+10;i++)
			anneeFab[i-(Year.now().getValue()-10)]=i;
		annee_list_Fab = new JComboBox<Integer>(anneeFab);
		dnFab.add(annee_list_Fab);
		dnFab.setBounds(0,10,340,20);
		pAPr.add(dnFab);
		
		// Date d'expiration
		lb_expiration= new JLabel("Date d'expiration");
		lb_expiration.setFont(new Font("Arial",Font.ITALIC,15));
		lb_expiration.setHorizontalAlignment(JLabel.CENTER);
		pAPr.add(lb_expiration);
		
		dnExp = new JPanel();//Panel Date de naissance ComboBoxes
		dnExp.setLayout(new GridLayout());
		
		jourExp = new Integer[31];//liste des jours
		for(int i=1;i<=31;i++)
			jourExp[i-1]=i;
		jour_list_Exp = new JComboBox<Integer>(jourExp);
		dnExp.add(jour_list_Exp);
		
		slash3 = new JLabel("/",JLabel.CENTER);
		slash3.setFont(new Font("Arial",Font.BOLD,20));
		dnExp.add(slash3);

		moisExp = new Integer[12];//liste des mois
		for(int i=1;i<=12;i++)
			moisExp[i-1]=i;
		mois_list_Exp = new JComboBox<>(moisExp);
		dnExp.add(mois_list_Exp);

		slash4 = new JLabel("/",JLabel.CENTER);
		slash4.setFont(new Font("Arial",Font.BOLD,20));
		dnExp.add(slash4);
		
		anneeExp = new Integer [100];//liste des mois
		for(int i=Year.now().getValue()-10;i < Year.now().getValue()+10;i++)
			anneeExp[i-(Year.now().getValue()-10)]=i;
		annee_list_Exp = new JComboBox<Integer>(anneeExp);
		dnExp.add(annee_list_Exp);
		dnExp.setBounds(0,10,340,20);
		pAPr.add(dnExp);
		
		// Les boutons
		btSupprimer = new JButton("Supprimer");
		pAPr.add(btSupprimer);
		btSupprimer.addActionListener(this);
		btAnnuler = new JButton("Annuler");
		pAPr.add(btAnnuler);
		btAnnuler.addActionListener(this);
		
		this.addWindowListener(this);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btSupprimer) {
			long codeBar = Long.parseLong(CodeBar.getSelectedItem().toString());
			String designation = textDesignation.getText();
			double quantite = Double.parseDouble(textQuantite.getText());
			double prix = Double.parseDouble(textPrix.getText());
			int idCat = Integer.parseInt(idCategorie.getSelectedItem().toString().substring(0,idCategorie.getSelectedItem().toString().indexOf("-")));
			String designationCategorie = idCategorie.getSelectedItem().toString().substring(idCategorie.getSelectedItem().toString().indexOf("-")+1);
			Categorie c = new Categorie(idCat,designationCategorie);
			int j1 = Integer.parseInt(jour_list_Fab.getSelectedItem().toString());
			int m1 = Integer.parseInt(mois_list_Fab.getSelectedItem().toString());
			int a1 = Integer.parseInt(annee_list_Fab.getSelectedItem().toString());
			int j2 = Integer.parseInt(jour_list_Exp.getSelectedItem().toString());
			int m2 = Integer.parseInt(mois_list_Exp.getSelectedItem().toString());
			int a2 = Integer.parseInt(annee_list_Exp.getSelectedItem().toString());
			String dateFabrication = a1+"-"+m1+"-"+j1;
			String dateExpiration = a2+"-"+m2+"-"+j2;
			Produit p = new Produit(codeBar,designation,quantite,prix,c,dateFabrication,dateExpiration);
			ProduitDAO pd = new ProduitDAO();
			Produit prod = pd.supprimer(p);
			if (prod != null) {
				JOptionPane.showMessageDialog(null, "Le Produit est supprimé avec succès");	
			}
			else
				JOptionPane.showMessageDialog(null, "Problème de suppression du produit");	
		}
		
		else if (e.getSource() == btAnnuler) {
			this.dispose();
		}
	}

	@Override
	public void windowOpened(WindowEvent e) {

		CatégorieDAO cd = new CatégorieDAO();
		ResultSet listeCat = cd.afficherTout();
		try {
			while(listeCat.next()) {
				idCategorie.addItem(listeCat.getInt(1)+"-"+listeCat.getString(2));
			}
			cd.fermerConnexion();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ProduitDAO pd = new ProduitDAO();
		ResultSet listeCodeBar = pd.afficherTout();
		try {
			while(listeCodeBar.next()) {
				CodeBar.addItem(Integer.toString(listeCodeBar.getInt(1)));
			}
			cd.fermerConnexion();
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

	@Override
	public void itemStateChanged(ItemEvent e) {
		
		ProduitDAO pd = new ProduitDAO();
		ResultSet rs = pd.rechercherId(Integer.parseInt(CodeBar.getSelectedItem().toString()));
		try {
			if(rs.next()) {
				textDesignation.setText(rs.getString(2));
				textQuantite.setText(Double.toString(rs.getDouble(3)));
				textPrix.setText(Double.toString(rs.getDouble(4)));
				CatégorieDAO cd = new CatégorieDAO();
				ResultSet listeCat = cd.rechercherId(rs.getInt(5));
				try {
					if(listeCat.next()) {
						idCategorie.setSelectedItem(listeCat.getInt(1)+"-"+listeCat.getString(2));
					}
					cd.fermerConnexion();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				int j1 = Integer.parseInt(rs.getString(6).substring(8,10));
				jour_list_Fab.setSelectedItem(j1);
				int m1 = Integer.parseInt(rs.getString(6).substring(5,7));
				mois_list_Fab.setSelectedItem(m1);
				int a1 = Integer.parseInt(rs.getString(6).substring(0,4));
				annee_list_Fab.setSelectedItem(a1);
				int j2 = Integer.parseInt(rs.getString(7).substring(8,10));
				jour_list_Exp.setSelectedItem(j2);
				int m2 = Integer.parseInt(rs.getString(7).substring(5,7));
				mois_list_Exp.setSelectedItem(m2);
				int a2 = Integer.parseInt(rs.getString(7).substring(0,4));
				annee_list_Exp.setSelectedItem(a2);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
		
}