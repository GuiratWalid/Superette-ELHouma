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
import oo.Client;
import oo.Controle;

public class AjouterClient extends JFrame implements ActionListener{

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

	public AjouterClient() {
		
		// Conception de la page
		this.setTitle("Ajouter un client");
		this.setSize(new Dimension(700,350));
		this.setBackground(Color.GRAY);
		this.setDefaultCloseOperation(this.HIDE_ON_CLOSE);
		this.setLocation(350, 150);
		this.setVisible(true);
		
		// Layout
		this.setLayout(new BorderLayout());
		
		// Titre
		labelName = new JLabel("Ajouter un client");
		labelName.setPreferredSize(new Dimension(200,100));
		labelName.setHorizontalAlignment(JLabel.CENTER);
		labelName.setVerticalAlignment(JLabel.CENTER);
		labelName.setForeground(new Color(178,35,35));
		labelName.setFont(new Font("Arial",Font.BOLD,30));
		this.add(labelName,BorderLayout.NORTH);	
		
		// Formulaire
		JPanel pAPr = new JPanel();
		pAPr.setLayout(new GridLayout(5,2,20,18));
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
		
		// Pr�nom
		labelPrenom = new JLabel("Pr�nom");
		labelPrenom.setFont(new Font("Arial",Font.ITALIC,15));
		labelPrenom.setHorizontalAlignment(JLabel.CENTER);
		pAPr.add(labelPrenom);
		
		textPrenom = new JTextField();
		textPrenom.setPreferredSize(new Dimension(150,50));
		pAPr.add(textPrenom,BorderLayout.CENTER);
		
		// T�l�phone
		labelTel = new JLabel("Num�ro de t�l�phone");
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
			String adresse = textAdresse.getText();
			if (!Controle.isLetters(nom) || nom.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Le champ Nom ne doit pas �tre vide et il doit contenir seulement des lettres !");
				return;
			}
			else if (!Controle.isLetters(prenom) || prenom.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Le champ Pr�nom ne doit pas �tre vide et il doit contenir seulement des lettres !");
				return;
			}
			else if (textTel.getText().length() != 8 || !Controle.isNumeric(textTel.getText())) {
				JOptionPane.showMessageDialog(null, "Le champ T�l�phone doit contenir 8 chiffres !");
				return;
			}
			else if (adresse.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Le champ Adresse ne doit pas �tre vide !");
				return;
			}
			else {
				tel = Long.parseLong(textTel.getText());
				Client c = new Client(nom,prenom,tel,adresse);
				ClientDAO cd = new ClientDAO();
				Client s = cd.ajouter(c);
				if (s != null)
					JOptionPane.showMessageDialog(null, "Nouveau Client est ajout� avec succ�s");				
				else
					JOptionPane.showMessageDialog(null, "Probl�me d�ajout du Client");
				cd.fermerConnexion();
			}
			
		}
		else if (e.getSource() == btAnnuler) {
			this.dispose();
		}
	}
	
}
