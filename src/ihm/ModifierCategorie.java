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

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dao.CatégorieDAO;
import oo.Categorie;

public class ModifierCategorie extends JFrame implements ActionListener,WindowListener,ItemListener {

	private JLabel labelName;
	private JLabel labelDesignation;
	private JTextField textDesignation;
	private JButton btAnnuler;
	private JLabel labelId;
	private JButton btModifier;
	private JComboBox<Integer> idCategorie;

	public ModifierCategorie() {
		
		// Conception
		this.setTitle("Modifier un catégorie");
		this.setSize(new Dimension(700,300));
		this.setBackground(Color.GRAY);
		this.setDefaultCloseOperation(this.HIDE_ON_CLOSE);
		this.setLocation(350, 150);
		this.setVisible(true);
		
		// Layout
		this.setLayout(new BorderLayout());
		
		// Titre
		labelName = new JLabel("Modifier un catégorie");
		labelName.setPreferredSize(new Dimension(200,100));
		labelName.setHorizontalAlignment(JLabel.CENTER);
		labelName.setVerticalAlignment(JLabel.CENTER);
		labelName.setForeground(new Color(178,35,35));
		labelName.setFont(new Font("Arial",Font.BOLD,30));
		this.add(labelName,BorderLayout.NORTH);	
		
		// Formulaire
		JPanel pAPr = new JPanel();
		pAPr.setLayout(new GridLayout(3,2,20,30));
		pAPr.setBorder(new EmptyBorder(5,5,5,5));
		this.add(pAPr);
		
		// Identifiant
		labelId = new JLabel("ID catégorie");
		labelId.setFont(new Font("Arial",Font.ITALIC,15));
		labelId.setHorizontalAlignment(JLabel.CENTER);
		pAPr.add(labelId);
		
		idCategorie = new JComboBox<Integer>();
		//remplir la liste à partir de la BD
		idCategorie.addItemListener(this);
		pAPr.add(idCategorie);
		
		// Désignation
		labelDesignation = new JLabel("Designation");
		labelDesignation.setFont(new Font("Arial",Font.ITALIC,15));
		labelDesignation.setHorizontalAlignment(JLabel.CENTER);
		pAPr.add(labelDesignation);
		
		textDesignation = new JTextField();
		textDesignation.setPreferredSize(new Dimension(150,50));
		pAPr.add(textDesignation,BorderLayout.CENTER);
		
		// Les boutons
		btModifier = new JButton("Modifier");
		pAPr.add(btModifier);
		btModifier.addActionListener(this);
		btAnnuler = new JButton("Annuler");
		pAPr.add(btAnnuler);
		btAnnuler.addActionListener(this);
		
		this.addWindowListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btModifier) {
			String des = textDesignation.getText();
			int idCat = Integer.parseInt(idCategorie.getSelectedItem().toString());
			if (des.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Le champ Désignation ne doit pas être vide !");
				return;
			}
			if (idCategorie.getSelectedItem() == null) {
				JOptionPane.showMessageDialog(null, "Il faut ajouter un Catégorie !");
				return;
			}
			else {
				Categorie c = new Categorie(idCat,des);
				CatégorieDAO cd = new CatégorieDAO();
				Categorie s = cd.modifier(c);
				if (s != null) {
					JOptionPane.showMessageDialog(null, "Catégorie est modifié avec succès");				
				}
				else
					JOptionPane.showMessageDialog(null, "Problème de modification du Catégorie");
				cd.fermerConnexion();
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
				idCategorie.addItem(listeCat.getInt(1));
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

		int idCat = Integer.parseInt(idCategorie.getSelectedItem().toString());
		CatégorieDAO cd = new CatégorieDAO();
		ResultSet listeCat = cd.rechercherId(idCat);
		try {
			while(listeCat.next()) {
				textDesignation.setText(listeCat.getString(2));
			}
			cd.fermerConnexion();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

}
