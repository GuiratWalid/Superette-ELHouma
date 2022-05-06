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

import dao.Cat�gorieDAO;
import dao.FournisseurDAO;
import dao.MagasinDAO;
import oo.Controle;
import oo.Fournisseur;
import oo.Magasin;

public class ModifierMagasin extends JFrame implements ActionListener,WindowListener,ItemListener{

	private JLabel labelName;
	private JLabel labelNom;
	private JTextField textNom;
	private JLabel labelAdresse;
	private JTextField textAdresse;
	private JLabel labelTel;
	private JTextField textTel;
	private JButton btAnnuler;
	private JButton btModifier;
	private JLabel labelId;
	private JComboBox<Integer> idMagasin;
	public ModifierMagasin() {
		
		// Conception de la page
		this.setTitle("Modifier un fournisseur");
		this.setSize(new Dimension(700,300));
		this.setBackground(Color.GRAY);
		this.setDefaultCloseOperation(this.HIDE_ON_CLOSE);
		this.setLocation(350, 150);
		this.setVisible(true);
		
		// Layout
		this.setLayout(new BorderLayout());
		
		// Titre
		labelName = new JLabel("Modifier un Magasin");
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
		labelId = new JLabel("ID Magasin");
		labelId.setFont(new Font("Arial",Font.ITALIC,15));
		labelId.setHorizontalAlignment(JLabel.CENTER);
		pAPr.add(labelId);
		
		idMagasin = new JComboBox<Integer>();
		//remplir la liste � partir de la BD
		idMagasin.addItemListener(this);
		pAPr.add(idMagasin);
		
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
		
		// T�l�phone
		labelTel = new JLabel("Num�ro de t�l�phone");
		labelTel.setFont(new Font("Arial",Font.ITALIC,15));
		labelTel.setHorizontalAlignment(JLabel.CENTER);
		pAPr.add(labelTel);
		
		textTel = new JTextField();
		textTel.setPreferredSize(new Dimension(150,50));
		pAPr.add(textTel,BorderLayout.CENTER);
		
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
			int id = Integer.parseInt(idMagasin.getSelectedItem().toString());
			String nom = textNom.getText();
			long tel = 0;
			String adresse = textAdresse.getText();
			if (!Controle.isLetters(nom) || nom.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Le champ Nom ne doit pas �tre vide et il doit contenir seulement des lettres !");
				return;
			}
			else if (adresse.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Le champ Adresse ne doit pas �tre vide !");
				return;
			}
			else if (textTel.getText().length() != 8 || !Controle.isNumeric(textTel.getText())) {
				JOptionPane.showMessageDialog(null, "Le champ T�l�phone doit contenir 8 chiffres !");
				return;
			}
			else {
				tel = Long.parseLong(textTel.getText());
				Magasin c = new Magasin(id,nom,adresse,tel);
				MagasinDAO cd = new MagasinDAO();
				Magasin s = cd.modifier(c);
				if (s != null) {
					JOptionPane.showMessageDialog(null, "Magasin est modifi� avec succ�s");				
			}
			else
					JOptionPane.showMessageDialog(null, "Probl�me de Modification du Magasin");
				cd.fermerConnexion();
			}
		}
		else if (e.getSource() == btAnnuler) {
			this.dispose();
		}
	}

	@Override
	public void windowOpened(WindowEvent e) {
		
		// TODO Auto-generated method stub
		MagasinDAO cd = new MagasinDAO();
		ResultSet rs = cd.afficherTout();
		try {
			while(rs.next()) {
				idMagasin.addItem(rs.getInt(1));
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
		// TODO Auto-generated method stub
		
		int id = Integer.parseInt(idMagasin.getSelectedItem().toString());
		MagasinDAO cd = new MagasinDAO();
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
	
}
