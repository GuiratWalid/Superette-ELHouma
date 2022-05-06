package ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableModel;

import model.FactureModel_RecetteJour;
import model.TicketModel_RecetteJour;

public class RecetteJour extends JFrame {

	private JLabel labelName;
	private JLabel labelFactures;
	private TicketModel_RecetteJour tmrj = new TicketModel_RecetteJour();
	private FactureModel_RecetteJour fmrj = new FactureModel_RecetteJour();
	private JLabel labelTickets;
	private JLabel totalTicket;
	private JLabel totalFacture;
	private JLabel total;
	private JTextField totalTicket2;
	private JTextField totalFacture2;
	private JTextField total2;

	public RecetteJour() {
		
		// Conception de la page
		this.setTitle("Recette du jour");
		this.setSize(new Dimension(950,750));
		this.setBackground(Color.GRAY);
		this.setDefaultCloseOperation(this.HIDE_ON_CLOSE);
		this.setLocation(150, 0);
		this.setVisible(true);
		
		// Layout
		this.setLayout(new BorderLayout());
		
		// Titre
		labelName = new JLabel("Recette du jour");
		labelName.setPreferredSize(new Dimension(200,100));
		labelName.setHorizontalAlignment(JLabel.CENTER);
		labelName.setVerticalAlignment(JLabel.CENTER);
		labelName.setForeground(new Color(178,35,35));
		labelName.setFont(new Font("Arial",Font.BOLD,30));
		this.add(labelName,BorderLayout.NORTH);	

		JPanel p = new JPanel();
		p.setLayout(new GridLayout(1,2,10,10));
		p.setPreferredSize(new Dimension(900,400));
		this.add(p,BorderLayout.CENTER);
		
		// Facture
		JPanel p1 = new JPanel();
		p1.setLayout(new FlowLayout());
		p.add(p1);
		labelFactures = new JLabel("Liste des factures");
		labelFactures.setFont(new Font("Arial",Font.ITALIC,15));
		labelFactures.setHorizontalAlignment(JLabel.CENTER);
		labelFactures.setPreferredSize(new Dimension(200,40));
		p1.add(labelFactures);
		
		JTable tableFacture = new JTable(fmrj);
		p1.add(new JScrollPane(tableFacture));
		
		// Ticket
		JPanel p2 = new JPanel();
		p.add(p2);
		p2.setLayout(new FlowLayout());
		labelTickets = new JLabel("Liste des tickets");
		labelTickets.setFont(new Font("Arial",Font.ITALIC,15));
		labelTickets.setHorizontalAlignment(JLabel.CENTER);
		labelTickets.setPreferredSize(new Dimension(200,40));
		p2.add(labelTickets);
		
		JTable tableTicket = new JTable(tmrj);
		p2.add(new JScrollPane(tableTicket));
		
		// Total
		JPanel p3 = new JPanel();
		this.add(p3,BorderLayout.SOUTH);
		p3.setPreferredSize(new Dimension(900,100));
		p3.setLayout(new GridLayout(2,3,0,0));

		totalFacture = new JLabel("Total des Factures");
		totalFacture.setFont(new Font("Arial",Font.ITALIC,15));
		totalFacture.setHorizontalAlignment(JLabel.CENTER);
		totalFacture.setPreferredSize(new Dimension(200,40));
		p3.add(totalFacture);
		
		totalTicket = new JLabel("Total des Tickets");
		totalTicket.setFont(new Font("Arial",Font.ITALIC,15));
		totalTicket.setHorizontalAlignment(JLabel.CENTER);
		totalTicket.setPreferredSize(new Dimension(200,40));
		p3.add(totalTicket);
		
		total = new JLabel("Total");
		total.setFont(new Font("Arial",Font.ITALIC,15));
		total.setHorizontalAlignment(JLabel.CENTER);
		total.setPreferredSize(new Dimension(200,40));
		p3.add(total);
		
		double totalFacture = fmrj.getSomme();
		totalFacture2 = new JTextField(Double.toString(totalFacture));
		totalFacture2.setHorizontalAlignment(JLabel.CENTER);
		p3.add(totalFacture2);
		
		double totalTicket = tmrj.getSomme();
		totalTicket2 = new JTextField(Double.toString(totalTicket));
		totalTicket2.setHorizontalAlignment(JLabel.CENTER);
		p3.add(totalTicket2);
		
		total2 = new JTextField(Double.toString(totalTicket-totalFacture));
		total2.setHorizontalAlignment(JLabel.CENTER);
		p3.add(total2);
		
	}
	
}
