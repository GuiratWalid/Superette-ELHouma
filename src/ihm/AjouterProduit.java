package ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dao.CatégorieDAO;
import dao.EmployeDAO;
import dao.ProduitDAO;
import oo.Categorie;
import oo.Controle;
import oo.Employe;
import oo.Produit;

public class AjouterProduit extends JFrame implements ActionListener,WindowListener{

	private JLabel labelName;
	private JLabel labelDesignation;
	private JTextField textDesignation;
	private JButton btAjouter;
	private JButton btAnnuler;
	private JLabel labelCategorie;
	private JComboBox<String> desCategorie;
	private JLabel labelCodeBar;
	private JTextField textCodeBar;
	private JLabel labelPrix;
	private JTextField textPrix;
	private JLabel labelQuantite;
	private JTextField textQuantite;
	private JPanel dnFab;
	private JLabel lb_fabrication;
	private Integer [] jourFab;
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
	private JComboBox<Integer> mois_list_Exp;
	private JLabel slash4;
	private Integer[] anneeExp;
	private JComboBox<Integer> annee_list_Exp;

	public AjouterProduit() {
		
		// Conception de la page
		this.setTitle("Ajouter un produit");
		this.setSize(new Dimension(700,500));
		this.setBackground(Color.GRAY);
		this.setDefaultCloseOperation(this.HIDE_ON_CLOSE);
		this.setLocation(350, 150);
		this.setVisible(true);
		
		// Layout
		this.setLayout(new BorderLayout());
		
		// Titre
		labelName = new JLabel("Ajouter un produit");
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
		
		textCodeBar = new JTextField();
		textCodeBar.setPreferredSize(new Dimension(150,50));
		pAPr.add(textCodeBar,BorderLayout.CENTER);
		
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
		
		desCategorie = new JComboBox<String>();
		//remplir la liste à partir de la BD
		pAPr.add(desCategorie);
		
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
		btAjouter = new JButton("Ajouter");
		pAPr.add(btAjouter);
		btAjouter.addActionListener(this);
		btAnnuler = new JButton("Annuler");
		pAPr.add(btAnnuler);
		btAnnuler.addActionListener(this);
		
		this.addWindowListener(this);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btAjouter) {
			
			long codeBar = 0;
			String designation = textDesignation.getText();
			double quantite = 0;
			double prix = 0;
			int idCategorie = 0;
			String designationCategorie;
			int j1 = Integer.parseInt(jour_list_Fab.getSelectedItem().toString());
			int m1 = Integer.parseInt(mois_list_Fab.getSelectedItem().toString());
			int a1 = Integer.parseInt(annee_list_Fab.getSelectedItem().toString());
			int j2 = Integer.parseInt(jour_list_Exp.getSelectedItem().toString());
			int m2 = Integer.parseInt(mois_list_Exp.getSelectedItem().toString());
			int a2 = Integer.parseInt(annee_list_Exp.getSelectedItem().toString());
			String dateFabrication = a1+"-"+m1+"-"+j1;
			String dateExpiration = a2+"-"+m2+"-"+j2;
			if (!Controle.isNumeric(textCodeBar.getText()) || textCodeBar.getText().length() != 9) {
				JOptionPane.showMessageDialog(null, "Le champ Code à Bar ne doit pas être vide et il doit contenir 9 des chiffres !");
				return;
			}
			else if (designation.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Le champ Designation ne doit pas être vide et il doit contenir seulement des lettres !");
				return;
			}
			else if (textQuantite.getText().isEmpty() || !Controle.isDoublePositive(textQuantite.getText())) {
				JOptionPane.showMessageDialog(null, "Le champ Quantité doit être un réel positive !");
				return;
			}
			else if (textPrix.getText().isEmpty() || !Controle.isDoublePositive(textPrix.getText())) {
				JOptionPane.showMessageDialog(null, "Le champ Quantité doit être un réel positive !");
				return;
			}
			else if (desCategorie.getSelectedItem() == null) {
				JOptionPane.showMessageDialog(null, "Il faut ajouter des catégories ! ");
				return;
			}
			else {
				codeBar = Long.parseLong(textCodeBar.getText());
				quantite = Double.parseDouble(textQuantite.getText());
				prix = Double.parseDouble(textPrix.getText());
				idCategorie = Integer.parseInt(desCategorie.getSelectedItem().toString().substring(0,desCategorie.getSelectedItem().toString().indexOf("-")));
				designationCategorie = desCategorie.getSelectedItem().toString().substring(desCategorie.getSelectedItem().toString().indexOf("-")+1);
				Categorie c = new Categorie(idCategorie,designationCategorie);
				Produit p = new Produit(codeBar,designation,quantite,prix,c,dateFabrication,dateExpiration);
				ProduitDAO pd = new ProduitDAO();
				Produit s = pd.ajouter(p);
				if (s != null) {
					JOptionPane.showMessageDialog(null, "Nouveau Produit est ajouté avec succès");	
				}
				else
					JOptionPane.showMessageDialog(null, "Problème d’ajout du produit");	
				}

			
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
				desCategorie.addItem(listeCat.getInt(1)+"-"+listeCat.getString(2));
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
}
