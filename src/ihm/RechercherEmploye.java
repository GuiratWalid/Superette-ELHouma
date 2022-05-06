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
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dao.EmployeDAO;

public class RechercherEmploye extends JFrame implements ActionListener,WindowListener{

	private JLabel labelNom;
	private JTextField textNom;
	private JLabel labelName;
	private JLabel labelPrenom;
	private JTextField textPrenom;
	private JLabel labelTel;
	private JTextField textTel;
	private JLabel labelAdresse;
	private JTextField textAdresse;
	private JButton btAnnuler;
	private JLabel labelSalaire;
	private JTextField textSalaire;
	private JLabel labelId;
	private JButton btRechercher;
	private JComboBox<String> idEmploye;private JPanel dn;
	private Integer[] jour;
	private JComboBox<Integer> jour_list;
	private JLabel slash1;
	private Integer[] mois;
	private JLabel slash2;
	private Integer[] annee;
	private JComboBox<Integer> annee_list;
	private JLabel lb_dateNais;
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
	private JLabel labelDateEmb;
	private JTextField textDateEmb;
	private JLabel labelDateNais;
	private JTextField textDateNais;

	public RechercherEmploye() {
		
		// Conception de la page
		this.setTitle("Rechercher un employé");
		this.setSize(new Dimension(700,450));
		this.setBackground(Color.GRAY);
		this.setDefaultCloseOperation(this.HIDE_ON_CLOSE);
		this.setLocation(350, 150);
		this.setVisible(true);
		
		// Layout
		this.setLayout(new BorderLayout());
		
		// Titre
		labelName = new JLabel("Rechercher un employé");
		labelName.setPreferredSize(new Dimension(200,100));
		labelName.setHorizontalAlignment(JLabel.CENTER);
		labelName.setVerticalAlignment(JLabel.CENTER);
		labelName.setForeground(new Color(178,35,35));
		labelName.setFont(new Font("Arial",Font.BOLD,30));
		this.add(labelName,BorderLayout.NORTH);	
		
		// Formulaire
		JPanel pAPr = new JPanel();
		pAPr.setLayout(new GridLayout(9,2,20,10));
		pAPr.setBorder(new EmptyBorder(5,5,5,5));
		this.add(pAPr);
		
		// Identifiant
		labelId = new JLabel("ID employé");
		labelId.setFont(new Font("Arial",Font.ITALIC,15));
		labelId.setHorizontalAlignment(JLabel.CENTER);
		pAPr.add(labelId);
				
		idEmploye = new JComboBox<String>();
		//remplir la liste à partir de la BD
		pAPr.add(idEmploye);
		
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
				
		textSalaire = new JTextField("Taper le Salaire");
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
		for(int i=Year.now().getValue()-42;i < Year.now().getValue();i++) {
			anneeEmb[i-(Year.now().getValue()-42)]=i;}
		annee_list_Emb = new JComboBox<Integer>(anneeEmb);
		dnEmb.add(annee_list_Emb);
		dnEmb.setBounds(0,10,340,20);
		pAPr.add(dnEmb);
		
		// Les boutons
		btRechercher = new JButton("Rechercher");
		pAPr.add(btRechercher);
		btRechercher.addActionListener(this);
		btAnnuler = new JButton("Annuler");
		pAPr.add(btAnnuler);
		btAnnuler.addActionListener(this);
		
		this.addWindowListener(this);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btRechercher) {
			EmployeDAO cd = new EmployeDAO();
			int id = Integer.parseInt(idEmploye.getSelectedItem().toString());
			ResultSet rs = cd.rechercherId(id);
			try {
				if(rs.next()) {
					textNom.setText(rs.getString(2));
					textPrenom.setText(rs.getString(3));
					textTel.setText(Long.toString(rs.getLong(4)));
					textAdresse.setText(rs.getString(5));
					textSalaire.setText(Double.toString(rs.getDouble(6)));
					int j1 = Integer.parseInt(rs.getString(7).substring(8,10));
					jour_list.setSelectedItem(j1);
					int m1 = Integer.parseInt(rs.getString(7).substring(5,7));
					mois_list.setSelectedItem(m1);
					int a1 = Integer.parseInt(rs.getString(7).substring(0,4));
					annee_list.setSelectedItem(a1);
					int j2 = Integer.parseInt(rs.getString(8).substring(8,10));
					jour_list_Emb.setSelectedItem(j2);
					int m2 = Integer.parseInt(rs.getString(8).substring(5,7));
					mois_list_Emb.setSelectedItem(m2);
					int a2 = Integer.parseInt(rs.getString(8).substring(0,4));
					annee_list_Emb.setSelectedItem(a2);
				}
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null, "Problème de recherche de l'Employé");
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			cd.fermerConnexion();
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
				idEmploye.addItem(rs.getString(1));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		cd.fermerConnexion();
		
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
