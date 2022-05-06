package ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import model.FactureModel_Rechercher;

public class Facture extends JFrame {
	
	private JPanel p1;
	private JLabel labelAdresse1,labelAdresse2;
	private JLabel labelTel1;
	private JLabel labelTel2;
	private JLabel labelDate1;
	private JLabel labelDate2;
	private JLabel labelName;
	private JPanel p2;
	private JPanel p;
	private JPanel p3;
	private FactureModel_Rechercher model;
	private JTable table;
	private JPanel pCenter;
	private JPanel foot;
	private Component labelTotale1;
	private JLabel labelTotale2;
	private JLabel labelCash1;
	private JLabel labelCash2;
	private JLabel labelChange1;
	private JLabel labelChange2;
	private JPanel p4;
	private JPanel p5;
	private JLabel labelTicket;
	private JLabel labelNom1;
	private JLabel labelNom2;
	private JLabel labelId1;
	private JLabel labelId2;
	private JLabel labelIDEmploye1;
	private JLabel labelIDEmploye2;
	private JLabel labelNomEmploye1;
	private JLabel labelNomEmploye2;
	private JLabel labelPrenomEmploye1;
	private JLabel labelPrenomEmploye2;
	private JLabel labelTVA1;
	private JLabel labelTVA2;
	private JLabel labelSig;

		public Facture(oo.Facture element) {
		
		// Conception de la page
		this.setSize(new Dimension(1050,750));
		this.setBackground(Color.GRAY);
		this.setLayout(new FlowLayout());
		this.setLocation(150, 0);
		this.setVisible(true);
		
		// Entete
		p1 = new JPanel();
		p1.setPreferredSize(new Dimension(320,100));
		p2 = new JPanel();
		p2.setPreferredSize(new Dimension(340,100));
		p3 = new JPanel();
		p3.setPreferredSize(new Dimension(370,100));
		p = new JPanel();
		p.setLayout(new BorderLayout());
		p2.setLayout(new GridLayout(2,1,5,5));
		labelName = new JLabel(element.getFournisseur().getNomFournisseur());
		labelName.setPreferredSize(new Dimension(200,100));
		labelName.setHorizontalAlignment(JLabel.CENTER);
		labelName.setVerticalAlignment(JLabel.CENTER);
		labelName.setForeground(new Color(178,35,35));
		labelName.setFont(new Font("Arial",Font.BOLD,20));
		p2.add(labelName);	
		labelTicket = new JLabel("Facture N°"+element.getIdFacture());
		labelTicket.setHorizontalAlignment(JLabel.CENTER);
		p2.add(labelTicket);
		p1.setLayout(new GridLayout(4,2,5,5));
		labelId1 = new JLabel("Identifiant du Fournisseur : ");
		p1.add(labelId1);
		labelId2 = new JLabel(Integer.toString(element.getFournisseur().getIdFournisseur()));
		labelId2.setHorizontalAlignment(JLabel.RIGHT);
		p1.add(labelId2);
		labelNom1 = new JLabel("Nom du Fournisseur : ");
		p1.add(labelNom1);
		labelNom2 = new JLabel(element.getFournisseur().getNomFournisseur());
		labelNom2.setHorizontalAlignment(JLabel.RIGHT);
		p1.add(labelNom2);
		labelAdresse1 = new JLabel("Adresse du Fournisseur : ");
		p1.add(labelAdresse1);
		labelAdresse2 = new JLabel(element.getFournisseur().getAdresseFournisseur());
		labelAdresse2.setHorizontalAlignment(JLabel.RIGHT);
		p1.add(labelAdresse2);
		labelTel1 = new JLabel("Numéro de Téléphone : ");
		p1.add(labelTel1);
		labelTel2 = new JLabel(Long.toString(element.getFournisseur().getNumTel()));
		labelTel2.setHorizontalAlignment(JLabel.RIGHT);
		p1.add(labelTel2);
		labelDate1 = new JLabel("Date : ");
		p3.add(labelDate1);
		labelDate2 = new JLabel(element.getDate().toString());
		labelDate2.setHorizontalAlignment(JLabel.RIGHT);
		p3.add(labelDate2);
		labelIDEmploye1 = new JLabel("ID Caissier : ");
		p3.add(labelIDEmploye1);
		labelIDEmploye2 = new JLabel(Integer.toString(element.getEmploye().getIdEmploye()));
		labelIDEmploye2.setHorizontalAlignment(JLabel.RIGHT);
		p3.add(labelIDEmploye2);
		labelNomEmploye1 = new JLabel("Nom Caissier : ");
		p3.add(labelNomEmploye1);
		labelNomEmploye2 = new JLabel(element.getEmploye().getNom());
		labelNomEmploye2.setHorizontalAlignment(JLabel.RIGHT);
		p3.add(labelNomEmploye2);
		labelPrenomEmploye1 = new JLabel("Prénom Caissier : ");
		p3.add(labelPrenomEmploye1);
		labelPrenomEmploye2 = new JLabel(element.getEmploye().getPrenom());
		labelPrenomEmploye2.setHorizontalAlignment(JLabel.RIGHT);
		p3.add(labelPrenomEmploye2);
		p3.setLayout(new GridLayout(4,2,5,5));
		p.add(p2,BorderLayout.CENTER);
		p.add(p1,BorderLayout.WEST);
		p.add(p3,BorderLayout.EAST);
		this.add(p);
		
		// Produits
		pCenter = new JPanel();
		model = new FactureModel_Rechercher(element.getProduits());
		table = new JTable(model);
		pCenter.setPreferredSize(new Dimension(1000,450));
		table.setSize(new Dimension(1000,400));
		table.setRowHeight(20);
		pCenter.add(new JScrollPane(table));
		
		this.add(pCenter);
		
		// Footer
		foot = new JPanel();
		p4 = new JPanel();
		p4.setPreferredSize(new Dimension(400,100));
		p5 = new JPanel();
		p4.setLayout(new GridLayout(4,2,5,5));
		labelTotale1 = new JLabel("Total : ");
		p4.add(labelTotale1);
		labelTotale2 = new JLabel(Double.toString(element.getTotal()));
		labelTotale2.setHorizontalAlignment(JLabel.RIGHT);
		p4.add(labelTotale2);
		labelCash1 = new JLabel("Cash : ");
		p4.add(labelCash1);
		labelCash2 = new JLabel(Double.toString(element.getCash()));
		labelCash2.setHorizontalAlignment(JLabel.RIGHT);
		p4.add(labelCash2);
		labelChange1 = new JLabel("Change : ");
		p4.add(labelChange1);
		labelChange2 = new JLabel(Double.toString(element.getCash()-element.getTotal()));
		labelChange2.setHorizontalAlignment(JLabel.RIGHT);
		p4.add(labelChange2);
		labelTVA1 = new JLabel("TVA : ");
		p4.add(labelTVA1);
		labelTVA2 = new JLabel("15%");
		labelTVA2.setHorizontalAlignment(JLabel.RIGHT);
		p4.add(labelTVA2);
		labelSig = new JLabel("Tampon et Signature");
		labelSig.setHorizontalAlignment(JLabel.CENTER);
		labelSig.setVerticalAlignment(JLabel.TOP);
		labelSig.setPreferredSize(new Dimension(200,50));
		labelSig.setHorizontalAlignment(JLabel.CENTER);
		labelSig.setVerticalAlignment(JLabel.CENTER);
		labelSig.setForeground(Color.BLACK);
		p5.setPreferredSize(new Dimension(600,100));
		p5.add(labelSig);
		foot.add(p5);
		foot.add(p4);
		this.add(foot);
		
	}
		
}
