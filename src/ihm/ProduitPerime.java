package ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import model.ProduitPerimeModel;

public class ProduitPerime extends JFrame{

	private JLabel labelName;
	private ProduitPerimeModel model;

	public ProduitPerime() {
		
		// Conception de la page
		this.setTitle("Liste des produits périmés en stock");
		this.setSize(new Dimension(700,400));
		this.setBackground(Color.white);
		this.setDefaultCloseOperation(this.HIDE_ON_CLOSE);
		this.setLocation(350, 150);
		this.setVisible(true);
		
		// Layout
		this.setLayout(new BorderLayout());
		
		// Titre
		labelName = new JLabel("Liste des produits");
		labelName.setPreferredSize(new Dimension(200,100));
		labelName.setHorizontalAlignment(JLabel.CENTER);
		labelName.setVerticalAlignment(JLabel.CENTER);
		labelName.setForeground(new Color(178,35,35));
		labelName.setBackground(Color.white);
		labelName.setFont(new Font("Arial",Font.BOLD,30));
		this.add(labelName,BorderLayout.NORTH);	
		
		// Table
		model = new ProduitPerimeModel();
		JTable table = new JTable (model);
		table.setBackground(new Color(178,35,35));
		table.setRowHeight(30);
		table.setSelectionBackground(Color.RED);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e ) {
				if(e.getButton()==e.BUTTON3) {
					JPopupMenu pop = new JPopupMenu();
					JMenuItem sup = new JMenuItem("supprimer");
					pop.add(sup);
					pop.show(table,e.getX(),e.getY());
					
					sup.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {

							model.supprimerLigne(table.getSelectedRow());
							
						}
					});
				}
			}
		}  );
		add(new JScrollPane(table),BorderLayout.CENTER);
		
		
	}

}
