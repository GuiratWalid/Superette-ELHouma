package ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Year;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dao.EmployeDAO;
import oo.Controle;
import oo.Employe;

public class AjouterEmploye extends JFrame implements ActionListener{

	private JLabel labelNom;
	private JTextField textNom;
	private JLabel labelName;
	private JLabel labelPrenom;
	private JTextField textPrenom;
	private JLabel labelTel;
	private JTextField textTel;
	private JLabel labelAdresse;
	private JTextField textAdresse;
	private JButton btAjouter;
	private JButton btAnnuler;
	private JPanel dn;
	private Integer[] jour;
	private JComboBox<Integer> jour_list;
	private JLabel slash1;
	private Integer[] mois;
	private JLabel slash2;
	private Integer[] annee;
	private JComboBox<Integer> annee_list;
	private JLabel lb_dateNais;
	private JLabel labelSalaire;
	private JTextField textSalaire;
	private JComboBox mois_list;
	private JLabel lb_embauche;
	private JPanel dnEmb;
	private Integer[] jourEmb;
	private JComboBox<Integer> jour_list_Emb;
	private JLabel slash3;
	private Integer[] moisEmb;
	private JComboBox mois_list_Emb;
	private JLabel slash4;
	private Integer[] anneeEmb;
	private JComboBox<Integer> annee_list_Emb;

	public AjouterEmploye() {
		
		// Conception de la page
		this.setTitle("Ajouter un employé");
		this.setSize(new Dimension(700,450));
		this.setBackground(Color.GRAY);
		this.setDefaultCloseOperation(this.HIDE_ON_CLOSE);
		this.setLocation(350, 150);
		this.setVisible(true);
		
		// Layout
		this.setLayout(new BorderLayout());
		
		// Titre
		labelName = new JLabel("Ajouter un employé");
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
		
		// Nom
		labelNom = new JLabel("Nom");
		labelNom.setFont(new Font("Arial",Font.ITALIC,15));
		labelNom.setHorizontalAlignment(JLabel.CENTER);
		pAPr.add(labelNom);
		
		textNom = new JTextField();
		textNom.setPreferredSize(new Dimension(150,50));
		pAPr.add(textNom,BorderLayout.CENTER);
		
		// Prénom
		labelPrenom = new JLabel("Prénom");
		labelPrenom.setFont(new Font("Arial",Font.ITALIC,15));
		labelPrenom.setHorizontalAlignment(JLabel.CENTER);
		pAPr.add(labelPrenom);
		
		textPrenom = new JTextField();
		textPrenom.setPreferredSize(new Dimension(150,50));
		pAPr.add(textPrenom,BorderLayout.CENTER);
		
		// Téléphone
		labelTel = new JLabel("Numéro de téléphone");
		labelTel.setFont(new Font("Arial",Font.ITALIC,15));
		labelTel.setHorizontalAlignment(JLabel.CENTER);
		pAPr.add(labelTel);
		
		textTel = new JTextField();
		textTel.setPreferredSize(new Dimension(150,50));
		pAPr.add(textTel,BorderLayout.CENTER);
		
		// Adresse
		labelAdresse = new JLabel("Adresse");
		labelAdresse.setFont(new Font("Arial",Font.ITALIC,15));
		labelAdresse.setHorizontalAlignment(JLabel.CENTER);
		pAPr.add(labelAdresse);
		
		textAdresse = new JTextField();
		textAdresse.setPreferredSize(new Dimension(150,50));
		pAPr.add(textAdresse,BorderLayout.CENTER);
		
		// Date de Naissance
		lb_dateNais = new JLabel("Date de naissance");
		lb_dateNais.setFont(new Font("Arial",Font.ITALIC,15));
		lb_dateNais.setHorizontalAlignment(JLabel.CENTER);
		pAPr.add(lb_dateNais);
		
		dn = new JPanel();//Panel Date de naissance ComboBoxes
		dn.setLayout(new GridLayout());
		
		jour = new Integer[31];//liste des jours
		for(int i=1;i<=31;i++)
			jour[i-1]=i;
		jour_list = new JComboBox<Integer>(jour);
		dn.add(jour_list);
		
		slash1 = new JLabel("/",JLabel.CENTER);
		slash1.setFont(new Font("Arial",Font.BOLD,20));
		dn.add(slash1);

		mois = new Integer[12];//liste des mois
		for(int i=1;i<=12;i++)
			mois[i-1]=i;
		mois_list = new JComboBox<>(mois);
		dn.add(mois_list);

		slash2 = new JLabel("/",JLabel.CENTER);
		slash2.setFont(new Font("Arial",Font.BOLD,20));
		dn.add(slash2);
		
		annee = new Integer [100];//liste des mois
		for(int i=Year.now().getValue()-60;i < Year.now().getValue();i++)
			annee[i-(Year.now().getValue()-60)]=i;
		annee_list = new JComboBox<Integer>(annee);
		dn.add(annee_list);
		dn.setBounds(0,10,340,20);
		pAPr.add(dn);
		
		// Salaire
		labelSalaire = new JLabel("Salaire");
		labelSalaire.setFont(new Font("Arial",Font.ITALIC,15));
		labelSalaire.setHorizontalAlignment(JLabel.CENTER);
		pAPr.add(labelSalaire);
				
		textSalaire = new JTextField();
		textSalaire.setPreferredSize(new Dimension(150,50));
		pAPr.add(textSalaire,BorderLayout.CENTER);
		
		// Date d'embauche
		lb_embauche = new JLabel("Date d'embauche");
		lb_embauche.setFont(new Font("Arial",Font.ITALIC,15));
		lb_embauche.setHorizontalAlignment(JLabel.CENTER);
		pAPr.add(lb_embauche);
		
		dnEmb = new JPanel();//Panel Date de naissance ComboBoxes
		dnEmb.setLayout(new GridLayout());
		
		jourEmb = new Integer[31];//liste des jours
		for(int i=1;i<=31;i++)
			jourEmb[i-1]=i;
		jour_list_Emb = new JComboBox<Integer>(jourEmb);
		dnEmb.add(jour_list_Emb);
		
		slash3 = new JLabel("/",JLabel.CENTER);
		slash3.setFont(new Font("Arial",Font.BOLD,20));
		dnEmb.add(slash3);

		moisEmb = new Integer[12];//liste des mois
		for(int i=1;i<=12;i++)
			moisEmb[i-1]=i;
		mois_list_Emb = new JComboBox<>(moisEmb);
		dnEmb.add(mois_list_Emb);

		slash4 = new JLabel("/",JLabel.CENTER);
		slash4.setFont(new Font("Arial",Font.BOLD,20));
		dnEmb.add(slash4);
		
		anneeEmb = new Integer [100];//liste des mois
		for(int i=Year.now().getValue()-42;i < Year.now().getValue();i++)
			anneeEmb[i-(Year.now().getValue()-42)]=i;
		annee_list_Emb = new JComboBox<Integer>(anneeEmb);
		dnEmb.add(annee_list_Emb);
		dnEmb.setBounds(0,10,340,20);
		pAPr.add(dnEmb);
		
		// Les boutons
		btAjouter = new JButton("Ajouter");
		pAPr.add(btAjouter);
		btAjouter.addActionListener(this);
		btAnnuler = new JButton("Annuler");
		pAPr.add(btAnnuler);
		btAnnuler.addActionListener(this);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btAjouter) {
			String nom = textNom.getText();
			String prenom = textPrenom.getText();
			long tel = 0;
			double salaire = 0;
			int j1 = Integer.parseInt(jour_list.getSelectedItem().toString());
			int m1 = Integer.parseInt(mois_list.getSelectedItem().toString());
			int a1 = Integer.parseInt(annee_list.getSelectedItem().toString());
			int j2 = Integer.parseInt(jour_list_Emb.getSelectedItem().toString());
			int m2 = Integer.parseInt(mois_list_Emb.getSelectedItem().toString());
			int a2 = Integer.parseInt(annee_list_Emb.getSelectedItem().toString());
			String dateNaissance = a1+"-"+m1+"-"+j1;
			String dateEmbauche = a2+"-"+m2+"-"+j2;
			String adresse = textAdresse.getText();
			if (!Controle.isLetters(nom) || nom.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Le champ Nom ne doit pas être vide et il doit contenir seulement des lettres !");
				return;
			}
			else if (!Controle.isLetters(prenom) || prenom.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Le champ Prénom ne doit pas être vide et il doit contenir seulement des lettres !");
				return;
			}
			else if (textTel.getText().length() != 8 || !Controle.isNumeric(textTel.getText())) {
				JOptionPane.showMessageDialog(null, "Le champ Téléphone doit contenir 8 chiffres !");
				return;
			}
			else if (adresse.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Le champ Adresse ne doit pas être vide !");
				return;
			}
			else if (!Controle.isDoublePositive(textSalaire.getText())) {
				JOptionPane.showMessageDialog(null, "Le champ Salaire doit être un réel !");
				return;
			}
			else {
				tel = Long.parseLong(textTel.getText());
				salaire = Double.parseDouble(textSalaire.getText());
				Employe c = new Employe(nom,prenom,tel,adresse,salaire,dateEmbauche,dateNaissance);
				EmployeDAO cd = new EmployeDAO();
				Employe s = cd.ajouter(c);
				if (s != null) {
					JOptionPane.showMessageDialog(null, "Nouveau Employé est ajouté avec succès");
				}
				else
					JOptionPane.showMessageDialog(null, "Problème d’ajout du Employé");
				cd.fermerConnexion();
			}
		}
		else if (e.getSource() == btAnnuler) {
			this.dispose();
		}
	}
	
}
