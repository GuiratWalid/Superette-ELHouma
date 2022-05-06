package ihm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Acceuil extends JFrame implements ActionListener{
	
	private JMenuBar menuBar;
	private JMenu menuProduit;
	private JMenuItem itemAjoutProduit,itemSuppressionProduit,itemRechercheProduit,itemModificationProduit;
	private JMenu menuCategorie;
	private JMenuItem itemAjoutCategorie;
	private JMenuItem itemSuppressionCategorie;
	private JMenuItem itemRechercheCategorie;
	private JMenuItem itemModificationCategorie;
	private JMenu menuClient;
	private JMenuItem itemAjoutClient;
	private JMenuItem itemSuppressionClient;
	private JMenuItem itemRechercheClient;
	private JMenuItem itemModificationClient;
	private JMenu menuEmploye;
	private JMenuItem itemAjoutEmploye;
	private JMenuItem itemSuppressionEmploye;
	private JMenuItem itemRechercheEmploye;
	private JMenuItem itemModificationEmploye;
	private JMenu menuFournisseur;
	private JMenuItem itemAjoutFournisseur;
	private JMenuItem itemSuppressionFournisseur;
	private JMenuItem itemRechercheFournisseur;
	private JMenuItem itemModificationFournisseur;
	private JMenu menuFacture;
	private JMenuItem itemAjoutFacture;
	private JMenuItem itemSuppressionFacture;
	private JMenuItem itemRechercheFacture;
	private JMenuItem itemModificationFacture;
	private JMenu menuTicket;
	private JMenuItem itemAjoutTicket;
	private JMenuItem itemSuppressionTicket;
	private JMenuItem itemRechercheTicket;
	private JMenuItem itemModificationTicket;
	private JLabel labelName;
	private JMenuItem itemAfficherToutTicket;
	private JMenuItem itemAfficherToutFacture;
	private JMenuItem itemAfficherToutFournisseur;
	private JMenuItem itemAfficherToutEmploye;
	private JMenuItem itemAfficherToutClient;
	private JMenuItem itemAfficherToutCategorie;
	private JMenuItem itemAfficherToutProduit;
	private JMenu menuMagasin;
	private JMenuItem itemAjoutMagasin;
	private JMenuItem itemSuppressionMagasin;
	private JMenuItem itemRechercheMagasin;
	private JMenuItem itemAfficherToutMagasin;
	private JMenuItem itemModificationMagasin;
	private JMenu menuRecette;
	private JMenuItem itemAjoutRecette;
	private JMenuItem itemProduitPerime;
	
	public Acceuil() {
		
		// Conception de la fenêtre d'acceuil
		this.setTitle("Superette El Houma");
		this.setSize(new Dimension(600,500));
		this.setBackground(Color.GREEN);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setLayout(new GridLayout());
		this.setResizable(false);
		this.setLocation(400, 150);
		
		// Background 
		String image = new String("images/background.jpg");
		ImageIcon img_icon = new ImageIcon(image);
		JLabel lb_image = new JLabel(img_icon);
		lb_image.setBounds(0,0,900,800);
		this.add(lb_image);
		
		// Menu
		menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		
		// Menu Produit
		menuProduit = new JMenu("Produit");
		menuBar.add(menuProduit);
		itemAjoutProduit = new JMenuItem("Ajouter");
		itemAjoutProduit.addActionListener(this);
		menuProduit.add(itemAjoutProduit);
		itemSuppressionProduit = new JMenuItem("Supprimer");
		itemSuppressionProduit.addActionListener(this);
		menuProduit.add(itemSuppressionProduit);
		itemRechercheProduit = new JMenuItem("Rechercher");
		itemRechercheProduit.addActionListener(this);
		menuProduit.add(itemRechercheProduit);
		itemAfficherToutProduit = new JMenuItem("AfficherTout");
		itemAfficherToutProduit.addActionListener(this);
		menuProduit.add(itemAfficherToutProduit);
		itemProduitPerime = new JMenuItem("Produits Périmés");
		itemProduitPerime.addActionListener(this);
		menuProduit.add(itemProduitPerime);
		itemModificationProduit = new JMenuItem("Modifier");
		itemModificationProduit.addActionListener(this);
		menuProduit.add(itemModificationProduit);
		
		// Menu Catégorie
		menuCategorie = new JMenu("Catégorie");
		menuBar.add(menuCategorie);
		itemAjoutCategorie = new JMenuItem("Ajouter");
		itemAjoutCategorie.addActionListener(this);
		menuCategorie.add(itemAjoutCategorie);
		itemSuppressionCategorie = new JMenuItem("Supprimer");
		itemSuppressionCategorie.addActionListener(this);
		menuCategorie.add(itemSuppressionCategorie);
		itemRechercheCategorie = new JMenuItem("Rechercher");
		itemRechercheCategorie.addActionListener(this);
		menuCategorie.add(itemRechercheCategorie);
		itemAfficherToutCategorie = new JMenuItem("AfficherTout");
		itemAfficherToutCategorie.addActionListener(this);
		menuCategorie.add(itemAfficherToutCategorie);
		itemModificationCategorie = new JMenuItem("Modifier");
		itemModificationCategorie.addActionListener(this);
		menuCategorie.add(itemModificationCategorie);
		
		// Menu Client
		menuClient = new JMenu("Client");
		menuBar.add(menuClient);
		itemAjoutClient = new JMenuItem("Ajouter");
		itemAjoutClient.addActionListener(this);
		menuClient.add(itemAjoutClient);
		itemSuppressionClient = new JMenuItem("Supprimer");
		itemSuppressionClient.addActionListener(this);
		menuClient.add(itemSuppressionClient);
		itemRechercheClient = new JMenuItem("Rechercher");
		itemRechercheClient.addActionListener(this);
		menuClient.add(itemRechercheClient);
		itemAfficherToutClient = new JMenuItem("AfficherTout");
		itemAfficherToutClient.addActionListener(this);
		menuClient.add(itemAfficherToutClient);
		itemModificationClient = new JMenuItem("Modifier");
		itemModificationClient.addActionListener(this);
		menuClient.add(itemModificationClient);
		
		// Menu Employé
		menuEmploye = new JMenu("Employé");
		menuBar.add(menuEmploye);
		itemAjoutEmploye = new JMenuItem("Ajouter");
		itemAjoutEmploye.addActionListener(this);
		menuEmploye.add(itemAjoutEmploye);
		itemSuppressionEmploye = new JMenuItem("Supprimer");
		itemSuppressionEmploye.addActionListener(this);
		menuEmploye.add(itemSuppressionEmploye);
		itemRechercheEmploye = new JMenuItem("Rechercher");
		itemRechercheEmploye.addActionListener(this);
		menuEmploye.add(itemRechercheEmploye);
		itemAfficherToutEmploye = new JMenuItem("AfficherTout");
		itemAfficherToutEmploye.addActionListener(this);
		menuEmploye.add(itemAfficherToutEmploye);
		itemModificationEmploye = new JMenuItem("Modifier");
		itemModificationEmploye.addActionListener(this);
		menuEmploye.add(itemModificationEmploye);
		
		
		//Menu Fournisseur
		menuFournisseur = new JMenu("Fournisseur");
		menuBar.add(menuFournisseur);
		itemAjoutFournisseur = new JMenuItem("Ajouter");
		itemAjoutFournisseur.addActionListener(this);
		menuFournisseur.add(itemAjoutFournisseur);
		itemSuppressionFournisseur = new JMenuItem("Supprimer");
		itemSuppressionFournisseur.addActionListener(this);
		menuFournisseur.add(itemSuppressionFournisseur);
		itemRechercheFournisseur = new JMenuItem("Rechercher");
		itemRechercheFournisseur.addActionListener(this);
		menuFournisseur.add(itemRechercheFournisseur);
		itemAfficherToutFournisseur = new JMenuItem("AfficherTout");
		itemAfficherToutFournisseur.addActionListener(this);
		menuFournisseur.add(itemAfficherToutFournisseur);
		itemModificationFournisseur = new JMenuItem("Modifier");
		itemModificationFournisseur.addActionListener(this);
		menuFournisseur.add(itemModificationFournisseur);
		
		///Menu Magasin
		menuMagasin = new JMenu("Magasin");
		menuBar.add(menuMagasin);
		itemAjoutMagasin = new JMenuItem("Ajouter");
		itemAjoutMagasin.addActionListener(this);
		menuMagasin.add(itemAjoutMagasin);
		itemSuppressionMagasin = new JMenuItem("Supprimer");
		itemSuppressionMagasin.addActionListener(this);
		menuMagasin.add(itemSuppressionMagasin);
		itemRechercheMagasin = new JMenuItem("Rechercher");
		itemRechercheMagasin.addActionListener(this);
		menuMagasin.add(itemRechercheMagasin);
		itemAfficherToutMagasin = new JMenuItem("AfficherTout");
		itemAfficherToutMagasin.addActionListener(this);
		menuMagasin.add(itemAfficherToutMagasin);
		itemModificationMagasin = new JMenuItem("Modifier");
		itemModificationMagasin.addActionListener(this);
		menuMagasin.add(itemModificationMagasin);
		
		// Menu Facture
		menuFacture = new JMenu("Facture");
		menuBar.add(menuFacture);
		itemAjoutFacture = new JMenuItem("Ajouter");
		itemAjoutFacture.addActionListener(this);
		menuFacture.add(itemAjoutFacture);
		itemSuppressionFacture = new JMenuItem("Supprimer");
		itemSuppressionFacture.addActionListener(this);
		menuFacture.add(itemSuppressionFacture);
		itemRechercheFacture = new JMenuItem("Rechercher");
		itemRechercheFacture.addActionListener(this);
		menuFacture.add(itemRechercheFacture);
		itemAfficherToutFacture = new JMenuItem("AfficherTout");
		itemAfficherToutFacture.addActionListener(this);
		menuFacture.add(itemAfficherToutFacture);
		itemModificationFacture = new JMenuItem("Modifier");
		itemModificationFacture.addActionListener(this);
		menuFacture.add(itemModificationFacture);
		
		//Menu Ticket
		menuTicket = new JMenu("Ticket");
		menuBar.add(menuTicket);
		itemAjoutTicket = new JMenuItem("Ajouter");
		itemAjoutTicket.addActionListener(this);
		menuTicket.add(itemAjoutTicket);
		itemSuppressionTicket = new JMenuItem("Supprimer");
		itemSuppressionTicket.addActionListener(this);
		menuTicket.add(itemSuppressionTicket);
		itemRechercheTicket = new JMenuItem("Rechercher");
		itemRechercheTicket.addActionListener(this);
		menuTicket.add(itemRechercheTicket);
		itemAfficherToutTicket = new JMenuItem("AfficherTout");
		itemAfficherToutTicket.addActionListener(this);
		menuTicket.add(itemAfficherToutTicket);
		itemModificationTicket = new JMenuItem("Modifier");
		itemModificationTicket.addActionListener(this);
		menuTicket.add(itemModificationTicket);
		
		//Menu Recette
		menuRecette = new JMenu("Recette");
		menuBar.add(menuRecette);
		itemAjoutRecette = new JMenuItem("Recette du jour");
		itemAjoutRecette.addActionListener(this);
		menuRecette.add(itemAjoutRecette);
		

		this.setVisible(true);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == itemAjoutProduit ) {
			AjouterProduit c = new AjouterProduit();
		}
		else if(e.getSource() == itemSuppressionProduit ) {
			SupprimerProduit c = new SupprimerProduit();
		}
		else if(e.getSource() == itemModificationProduit ) {
			ModifierProduit c = new ModifierProduit();
		}
		else if(e.getSource() == itemAfficherToutProduit ) {
			AfficherToutProduit c = new AfficherToutProduit();
		}
		else if(e.getSource() == itemProduitPerime) {
			ProduitPerime c = new ProduitPerime();
		}
		else if(e.getSource() == itemRechercheProduit ) {
			RechercherProduit c = new RechercherProduit();
		}
		else if(e.getSource() == itemAjoutCategorie ) {
			AjouterCategorie c = new AjouterCategorie();
		}
		else if(e.getSource() == itemSuppressionCategorie ) {
			SupprimerCategorie c = new SupprimerCategorie();
		}
		else if(e.getSource() == itemModificationCategorie ) {
			ModifierCategorie c = new ModifierCategorie();
		}
		else if(e.getSource() == itemAfficherToutCategorie ) {
			AfficherToutCategorie c = new AfficherToutCategorie();
		}
		else if(e.getSource() == itemRechercheCategorie ) {
			RechercherCategorie c = new RechercherCategorie();
		}
		else if(e.getSource() == itemAjoutClient) {
			AjouterClient c = new AjouterClient();
		}
		else if(e.getSource() == itemSuppressionClient ) {
			SupprimerClient c = new SupprimerClient();
		}
		else if(e.getSource() == itemModificationClient ) {
			ModifierClient c = new ModifierClient();
		}
		else if(e.getSource() == itemRechercheClient ) {
			RechercherClient c = new RechercherClient();
		}
		else if(e.getSource() == itemAfficherToutClient) {
			AfficherToutClient c = new AfficherToutClient();
		}
		else if(e.getSource() == itemAjoutFacture ) {
			AjouterFacture c = new AjouterFacture();
		}
		else if(e.getSource() == itemSuppressionFacture ) {
			SupprimerFacture c = new SupprimerFacture();
		}
		else if(e.getSource() == itemModificationFacture ) {
			ModifierFacture c = new ModifierFacture();
		}
		else if(e.getSource() == itemRechercheFacture ) {
			RechercherFacture c = new RechercherFacture();
		}
		else if(e.getSource() == itemAfficherToutFacture ) {
			AfficherToutFacture c = new AfficherToutFacture();
		}
		else if(e.getSource() == itemAjoutTicket ) {
			AjouterTicket c = new AjouterTicket();
		}
		else if(e.getSource() == itemSuppressionTicket ) {
			SupprimerTicket c =new SupprimerTicket();
		}
		else if(e.getSource() == itemModificationTicket ) {
			ModifierTicket c = new ModifierTicket();
		}
		else if(e.getSource() == itemRechercheTicket ) {
			RechercherTicket c = new RechercherTicket();
		}
		else if(e.getSource() == itemAfficherToutTicket ) {
			AfficherToutTicket c = new AfficherToutTicket();
		}
		else if(e.getSource() == itemAjoutFournisseur) {
			AjouterFournisseur c = new AjouterFournisseur();
		}
		else if(e.getSource() == itemSuppressionFournisseur ) {
			SupprimerFournisseur c = new SupprimerFournisseur();
		}
		else if(e.getSource() == itemModificationFournisseur ) {
			ModifierFournisseur c = new ModifierFournisseur();
		}
		else if(e.getSource() == itemRechercheFournisseur ) {
			RechercherFournisseur c = new RechercherFournisseur();
		}
		else if(e.getSource() == itemAfficherToutFournisseur ) {
			AfficherToutFournisseur c = new AfficherToutFournisseur();
		}
		else if(e.getSource() == itemAjoutEmploye ) {
			AjouterEmploye c = new AjouterEmploye();
		}
		else if(e.getSource() == itemSuppressionEmploye ) {
			SupprimerEmploye c = new SupprimerEmploye();
		}
		else if(e.getSource() == itemModificationEmploye ) {
			ModifierEmploye c = new ModifierEmploye();
		}
		else if(e.getSource() == itemRechercheEmploye ) {
			RechercherEmploye c = new RechercherEmploye();
		}
		else if(e.getSource() == itemAfficherToutEmploye ) {
			AfficherToutEmploye c = new AfficherToutEmploye();
		}
		else if(e.getSource() == itemAjoutMagasin ) {
			AjouterMagasin c = new AjouterMagasin();
		}
		else if(e.getSource() == itemSuppressionMagasin ) {
			SupprimerMagasin c = new SupprimerMagasin();
		}
		else if(e.getSource() == itemModificationMagasin ) {
			ModifierMagasin c = new ModifierMagasin();
		}
		else if(e.getSource() == itemRechercheMagasin ) {
			RechercherMagasin c = new RechercherMagasin();
		}
		else if(e.getSource() == itemAfficherToutMagasin ) {
			AfficherToutMagasin c = new AfficherToutMagasin();
		}
		else if (e.getSource() == itemAjoutRecette) {
			RecetteJour c = new RecetteJour();
		}
		
	}
	

	public static void main(String [] args) {
		Acceuil e = new Acceuil();
	}
}
