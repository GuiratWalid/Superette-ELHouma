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

import dao.FournisseurDAO;
import oo.Fournisseur;

public class SupprimerFournisseur extends JFrame implements ActionListener,WindowListener,ItemListener{

	private JLabel labelName;
	private JLabel labelNom;
	private JTextField textNom;
	private JLabel labelAdresse;
	private JTextField textAdresse;
	private JLabel labelTel;
	private JTextField textTel;
	private JButton btAnnuler;
	private JLabel labelId;
	private JComboBox<Integer> idFournisseur;
	private JButton btSupprimer;
	public SupprimerFournisseur() {
		
		// Conception de la page
		this.setTitle("Supprimer un fournisseur");
		this.setSize(new Dimension(700,300));
		this.setBackground(Color.GRAY);
		this.setDefaultCloseOperation(this.HIDE_ON_CLOSE);
		this.setLocation(350, 150);
		this.setVisible(true);
		
		// Layout
		this.setLayout(new BorderLayout());
		
		// Titre
		labelName = new JLabel("Supprimer un fournisseur");
		labelName.setPreferredSize(new Dimension(200,100));
		labelName.setHorizontalAlignment(JLabel.CENTER);
		labelName.setVerticalAlignment(JLabel.CENTER);
		labelName.setForeground(new Color(178,35,35));
		labelName.setFont(new Font("Arial",Font.BOLD,30));
		this.add(labelName,BorderLayout.NORTH);	
		
		// Formulaire
		JPanel pAPr = new JPanel();
		pAPr.setLayout(new GridLayout(5,2,20,10));
		pAPr.setBorder(new EmptyBorder(5,5,5,5));
		this.add(pAPr);
		
		// Identifiant 
		labelId = new JLabel("ID fournisseur");
		labelId.setFont(new Font("Arial",Font.ITALIC,15));
		labelId.setHorizontalAlignment(JLabel.CENTER);
		pAPr.add(labelId);
		
		idFournisseur = new JComboBox<Integer>();
		//remplir la liste à partir de la BD
		idFournisseur.addItemListener(this);
		pAPr.add(idFournisseur);
		
		// Nom
		labelNom = new JLabel("Nom du fournisseur");
		labelNom.setFont(new Font("Arial",Font.ITALIC,15));
		labelNom.setHorizontalAlignment(JLabel.CENTER);
		pAPr.add(labelNom);
		
		textNom = new JTextField();
		textNom.setPreferredSize(new Dimension(150,50));
		pAPr.add(textNom,BorderLayout.CENTER);
		
		// Adresse
		labelAdresse = new JLabel("Adresse du frounisseur");
		labelAdresse.setFont(new Font("Arial",Font.ITALIC,15));
		labelAdresse.setHorizontalAlignment(JLabel.CENTER);
		pAPr.add(labelAdresse);
		
		textAdresse = new JTextField();
		textAdresse.setPreferredSize(new Dimension(150,50));
		pAPr.add(textAdresse,BorderLayout.CENTER);
		
		// Téléphone
		labelTel = new JLabel("Numéro de téléphone");
		labelTel.setFont(new Font("Arial",Font.ITALIC,15));
		labelTel.setHorizontalAlignment(JLabel.CENTER);
		pAPr.add(labelTel);
		
		textTel = new JTextField();
		textTel.setPreferredSize(new Dimension(150,50));
		pAPr.add(textTel,BorderLayout.CENTER);
		
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
			
			int id = Integer.parseInt(idFournisseur.getSelectedItem().toString());
			String nom = textNom.getText();
			String adresse = textAdresse.getText();
			long tel = Long.parseLong(textTel.getText());
			Fournisseur fournisseur = new Fournisseur(id, nom, adresse,tel);
			FournisseurDAO fd = new FournisseurDAO();
			Fournisseur f = fd.supprimer(fournisseur);	
			if (fournisseur != null) {
				JOptionPane.showMessageDialog(null, "Fournisseur est supprimé avec succès");				
		}
		else
				JOptionPane.showMessageDialog(null, "Problème de Supprimer du Fournisseur");
			fd.fermerConnexion();
			
		}
		else if (e.getSource() == btAnnuler) {
			this.dispose();
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		
		FournisseurDAO cd = new FournisseurDAO();
		int id = Integer.parseInt(idFournisseur.getSelectedItem().toString());
		ResultSet rs = cd.rechercherId(id);
		try {
			while(rs.next()) {
				textNom.setText(rs.getString(2));
				textAdresse.setText(rs.getString(3));
				textTel.setText(rs.getString(4));
			}
			cd.fermerConnexion();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
		FournisseurDAO cd = new FournisseurDAO();
		ResultSet rs = cd.afficherTout();
		try {
			while(rs.next()) {
				idFournisseur.addItem(rs.getInt(1));
			}
			cd.fermerConnexion();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	}

	@Override
	public void windowClosing(WindowEvent e) {

		
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
