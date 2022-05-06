package ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.Produit_TicketDAO;
import model.TicketModel_Rechercher;

public class Ticket extends JFrame {

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
	private JLabel t;
	private TicketModel_Rechercher model;
	private JTable table;
	private JPanel pCenter;
	private JPanel foot;
	private JLabel labelTotale1;
	private JLabel labelTotale2;
	private JLabel labelCash1;
	private JLabel labelCash2;
	private JLabel labelChange1;
	private JLabel labelChange2;
	private JPanel p4;
	private JPanel p5;
	private JLabel labelMerci;
	private JLabel t1;
	private JLabel labelTicket;
	private double somme = 0;

	public Ticket(oo.Ticket element) {
		
		// Conception de la page
		this.setSize(new Dimension(480,750));
		this.setBackground(Color.GRAY);
		this.setLocation(400, 0);
		this.setLayout(new FlowLayout());
		this.setVisible(true);
		
		// Entete
		p1 = new JPanel();
		p2 = new JPanel();
		p3 = new JPanel();
		p = new JPanel();
		p2.setLayout(new GridLayout(2,1,5,5));
		p2.setPreferredSize(new Dimension(410,75));
		p.setLayout(new BorderLayout());
		p.setPreferredSize(new Dimension(410,250));
		labelName = new JLabel("Superette El Houma");
		labelName.setPreferredSize(new Dimension(200,100));
		labelName.setHorizontalAlignment(JLabel.CENTER);
		labelName.setVerticalAlignment(JLabel.CENTER);
		labelName.setForeground(new Color(178,35,35));
		labelName.setFont(new Font("Arial",Font.BOLD,20));
		p2.add(labelName);	
		labelTicket = new JLabel("Ticket N°"+element.getIdTicket());
		labelTicket.setHorizontalAlignment(JLabel.CENTER);
		p2.add(labelTicket);
		p1.setLayout(new GridLayout(3,2,5,5));
		labelAdresse1 = new JLabel("Adresse du Magasin : ");
		p1.add(labelAdresse1);
		labelAdresse2 = new JLabel(element.getMagasin().getAdresseMagasin());
		labelAdresse2.setHorizontalAlignment(JLabel.RIGHT);
		p1.add(labelAdresse2);
		labelTel1 = new JLabel("Numéro de Téléphone : ");
		p1.add(labelTel1);
		labelTel2 = new JLabel(Long.toString(element.getMagasin().getNumTel()));
		labelTel2.setHorizontalAlignment(JLabel.RIGHT);
		p1.add(labelTel2);
		labelDate1 = new JLabel("Date : ");
		p1.add(labelDate1);
		labelDate2 = new JLabel(element.getDate().toString());
		labelDate2.setHorizontalAlignment(JLabel.RIGHT);
		p1.add(labelDate2);
		t = new JLabel("____________________________________________________________");
		t.setHorizontalAlignment(JLabel.CENTER);
		p3.add(t);
		p.add(p2,BorderLayout.NORTH);
		p.add(p1,BorderLayout.CENTER);
		p.add(p3,BorderLayout.SOUTH);
		this.add(p);
		
		// Produits
		pCenter = new JPanel();
		pCenter.setPreferredSize(new Dimension(470,200));
		model = new TicketModel_Rechercher(element.getProduits());
		table = new JTable(model);
		table.setRowHeight(20);
		table.setBounds(0, 0, 120, 100);
		pCenter.add(new JScrollPane(table),BorderLayout.CENTER);
		
		this.add(pCenter,BorderLayout.CENTER);
		double somme=model.somme();
		
		// Footer
		foot = new JPanel();
		foot.setPreferredSize(new Dimension(410,200));
		p4 = new JPanel();
		p4.setPreferredSize(new Dimension(400,100));
		p5 = new JPanel();
		p4.setLayout(new GridLayout(3,2,5,5));
		t1 = new JLabel("____________________________________________________________");
		t1.setHorizontalAlignment(JLabel.CENTER);
		foot.add(t1,BorderLayout.NORTH);
		labelTotale1 = new JLabel("Total : ");
		p4.add(labelTotale1);
		labelTotale2 = new JLabel(Double.toString(somme));
		labelTotale2.setHorizontalAlignment(JLabel.RIGHT);
		p4.add(labelTotale2);
		labelCash1 = new JLabel("Cash : ");
		p4.add(labelCash1);
		labelCash2 = new JLabel(Double.toString(element.getCash()));
		labelCash2.setHorizontalAlignment(JLabel.RIGHT);
		p4.add(labelCash2);
		labelChange1 = new JLabel("Change : ");
		p4.add(labelChange1);
		labelChange2 = new JLabel(Double.toString(element.getCash()-somme));
		labelChange2.setHorizontalAlignment(JLabel.RIGHT);
		p4.add(labelChange2);
		foot.add(p4,BorderLayout.CENTER);
		labelMerci = new JLabel("Merci");
		labelMerci.setPreferredSize(new Dimension(200,100));
		labelMerci.setHorizontalAlignment(JLabel.CENTER);
		labelMerci.setVerticalAlignment(JLabel.CENTER);
		labelMerci.setForeground(Color.GRAY);
		labelMerci.setFont(new Font("Arial",Font.BOLD,20));
		p5.add(labelMerci,BorderLayout.CENTER);
		foot.add(p5,BorderLayout.SOUTH);
		this.add(foot,BorderLayout.SOUTH);
		
	}
}
