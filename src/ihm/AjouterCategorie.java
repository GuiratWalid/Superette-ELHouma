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

import dao.Cat�gorieDAO;
import oo.Categorie;

public class AjouterCategorie extends JFrame implements ActionListener{
	
	
	private JLabel labelName,labelDesignation;
	private JTextField textDesignation;
	private JButton btAjouter;
	private JButton btAnnuler;

	public AjouterCategorie() {
		
		// Conception de la page
		this.setTitle("Ajouter un cat�gorie");
		this.setSize(new Dimension(700,300));
		this.setBackground(Color.GRAY);
		this.setDefaultCloseOperation(this.HIDE_ON_CLOSE);
		this.setLocation(350, 150);
		this.setVisible(true);
		
		// Layout
		this.setLayout(new BorderLayout());
		
		// Titre
		labelName = new JLabel("Ajouter un cat�gorie");
		labelName.setPreferredSize(new Dimension(200,100));
		labelName.setHorizontalAlignment(JLabel.CENTER);
		labelName.setVerticalAlignment(JLabel.CENTER);
		labelName.setForeground(new Color(178,35,35));
		labelName.setFont(new Font("Arial",Font.BOLD,30));
		this.add(labelName,BorderLayout.NORTH);	
		
		// Formulaire
		JPanel pAPr = new JPanel();
		pAPr.setLayout(new GridLayout(2,2,20,100));
		pAPr.setBorder(new EmptyBorder(5,5,5,5));
		this.add(pAPr);
		
		// D�signation
		labelDesignation = new JLabel("Designation");
		labelDesignation.setFont(new Font("Arial",Font.ITALIC,15));
		labelDesignation.setHorizontalAlignment(JLabel.CENTER);
		pAPr.add(labelDesignation);
		
		textDesignation = new JTextField();
		textDesignation.setPreferredSize(new Dimension(150,50));
		pAPr.add(textDesignation,BorderLayout.CENTER);
		
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
			String des = textDesignation.getText();
			if (des.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Le champ D�signation ne doit pas �tre vide !");
				return;
			}
			else {
				Categorie c = new Categorie(des);
				Cat�gorieDAO cd = new Cat�gorieDAO();
				Categorie s = cd.ajouter(c);
				if (s != null)
					JOptionPane.showMessageDialog(null, "Nouveau Cat�gorie est ajout� avec succ�s");				
				else
					JOptionPane.showMessageDialog(null, "Probl�me d�ajout du Cat�gorie");
				cd.fermerConnexion();
			}
		}
		else if (e.getSource() == btAnnuler) {
			this.dispose();
		}
	}
}
