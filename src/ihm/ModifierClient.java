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
import dao.ClientDAO;
import oo.Client;
import oo.Controle;

public class ModifierClient extends JFrame implements ActionListener,WindowListener,ItemListener{

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
	private JLabel labelId;
	private JComboBox<String> idClient;
	private JButton btModifier;
	private JLabel labelBonus;
	private JTextField textBonus;

	public ModifierClient() {
		
		// Conception de la page
		this.setTitle("Modifier un client");
		this.setSize(new Dimension(700,400));
		this.setBackground(Color.GRAY);
		this.setDefaultCloseOperation(this.HIDE_ON_CLOSE);
		this.setLocation(350, 150);
		this.setVisible(true);
		
		// Layout
		this.setLayout(new BorderLayout());
		
		// Titre
		labelName = new JLabel("Modifier un client");
		labelName.setPreferredSize(new Dimension(200,100));
		labelName.setHorizontalAlignment(JLabel.CENTER);
		labelName.setVerticalAlignment(JLabel.CENTER);
		labelName.setForeground(new Color(178,35,35));
		labelName.setFont(new Font("Arial",Font.BOLD,30));
		this.add(labelName,BorderLayout.NORTH);	
		
		// Formulaire
		JPanel pAPr = new JPanel();
		pAPr.setLayout(new GridLayout(7,2,20,10));
		pAPr.setBorder(new EmptyBorder(5,5,5,5));
		this.add(pAPr);
		
		// Identifiant
		labelId = new JLabel("ID client");
		labelId.setFont(new Font("Arial",Font.ITALIC,15));
		labelId.setHorizontalAlignment(JLabel.CENTER);
		pAPr.add(labelId);
		
		idClient = new JComboBox<String>();
		//remplir la liste à partir de la BD
		idClient.addItemListener(this);
		pAPr.add(idClient);
		
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
		
		// Bonus
		labelBonus = new JLabel("Bonus");
		labelBonus.setFont(new Font("Arial",Font.ITALIC,15));
		labelBonus.setHorizontalAlignment(JLabel.CENTER);
		pAPr.add(labelBonus);
		
		textBonus = new JTextField();
		textBonus.setPreferredSize(new Dimension(150,50));
		pAPr.add(textBonus,BorderLayout.CENTER);
		
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
			int id = Integer.parseInt(idClient.getSelectedItem().toString());
			String nom = textNom.getText();
			String prenom = textPrenom.getText();
			long tel = 0;
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
			else if (!Controle.isDoublePositive(textBonus.getText())) {
					JOptionPane.showMessageDialog(null, "Le champ Bonus doit être un réel positif !");
					return;
			}
			else {
				tel = Long.parseLong(textTel.getText());
				Client c = new Client(id,nom,prenom,tel,adresse);
				c.setBonus(Double.parseDouble(textBonus.getText()));
				ClientDAO cd = new ClientDAO();
				Client s = cd.modifier(c);
				if (s != null)
					JOptionPane.showMessageDialog(null, "Client est modifié avec succès");				
				else
					JOptionPane.showMessageDialog(null, "Problème de modification du Client");
				cd.fermerConnexion();
			}
		}
		else if (e.getSource() == btAnnuler) {
			this.dispose();
		}
	}

	@Override
	public void windowOpened(WindowEvent e) {

		ClientDAO cd = new ClientDAO();
		ResultSet rs = cd.afficherTout();
		try {
			while(rs.next()) {
				idClient.addItem(Integer.toString(rs.getInt(1)));
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

			ClientDAO cd = new ClientDAO();
			int id = Integer.parseInt(idClient.getSelectedItem().toString());
			ResultSet rs = cd.rechercherId(id);
			try {
				if(rs.next()) {
					textNom.setText(rs.getString(2));
					textPrenom.setText(rs.getString(3));
					textTel.setText(Long.toString(rs.getLong(4)));
					textAdresse.setText(rs.getString(5));
					textBonus.setText(Double.toString(rs.getDouble(6)));
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			cd.fermerConnexion();
		}
	
}
