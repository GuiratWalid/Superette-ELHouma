package ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dao.ClientDAO;
import dao.FournisseurDAO;
import dao.MagasinDAO;
import oo.Client;
import oo.Controle;
import oo.Fournisseur;
import oo.Magasin;

public class AjouterMagasin extends JFrame implements ActionListener{

	private JLabel labelName;
	private JLabel labelNom;
	private JTextField textNom;
	private JLabel labelAdresse;
	private JTextField textAdresse;
	private JLabel labelTel;
	private JTextField textTel;
	private JButton btAjouter;
	private JButton btAnnuler;
	public AjouterMagasin() {
		
		// Conception de la page
		this.setTitle("Ajouter un Magasin");
		this.setSize(new Dimension(700,300));
		this.setBackground(Color.GRAY);
		this.setDefaultCloseOperation(this.HIDE_ON_CLOSE);
		this.setLocation(350, 150);
		this.setVisible(true);
		
		// Layout
		this.setLayout(new BorderLayout());
		
		// Titre
		labelName = new JLabel("Ajouter un Magasin");
		labelName.setPreferredSize(new Dimension(200,100));
		labelName.setHorizontalAlignment(JLabel.CENTER);
		labelName.setVerticalAlignment(JLabel.CENTER);
		labelName.setForeground(new Color(178,35,35));
		labelName.setFont(new Font("Arial",Font.BOLD,30));
		this.add(labelName,BorderLayout.NORTH);	
		
		// Formulaire
		JPanel pAPr = new JPanel();
		pAPr.setLayout(new GridLayout(4,2,20,18));
		pAPr.setBorder(new EmptyBorder(5,5,5,5));
		this.add(pAPr);
		
		// Nom
		labelNom = new JLabel("Nom du Magasin");
		labelNom.setFont(new Font("Arial",Font.ITALIC,15));
		labelNom.setHorizontalAlignment(JLabel.CENTER);
		pAPr.add(labelNom);
		
		textNom = new JTextField();
		textNom.setPreferredSize(new Dimension(150,50));
		pAPr.add(textNom,BorderLayout.CENTER);
		
		// Adresse
		labelAdresse = new JLabel("Adresse du Magasin");
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
			long tel = 0;
			String adresse = textAdresse.getText();
			if (!Controle.isLetters(nom) || nom.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Le champ Nom ne doit pas être vide et il doit contenir seulement des lettres !");
				return;
			}
			else if (adresse.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Le champ Adresse ne doit pas être vide !");
				return;
			}
			else if (textTel.getText().length() != 8 || !Controle.isNumeric(textTel.getText())) {
				JOptionPane.showMessageDialog(null, "Le champ Téléphone doit contenir 8 chiffres !");
				return;
			}
			else {
				tel = Long.parseLong(textTel.getText());
				Magasin c = new Magasin(nom,adresse,tel);
				MagasinDAO cd = new MagasinDAO();
				Magasin s = cd.ajouter(c);
				if (s != null) {
					
					JOptionPane.showMessageDialog(null, "Nouveau Magasin est ajouté avec succès");				
				}
				else
					JOptionPane.showMessageDialog(null, "Problème d’ajout du Magasin");
				
				cd.fermerConnexion();
			}
		}
		else if (e.getSource() == btAnnuler) {
			this.dispose();
		}
	}
	
}
