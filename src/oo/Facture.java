package oo;

import java.util.ArrayList;
import java.util.Date;

public class Facture {
	
		// Attributs
		private static int id = 1;
		private int idFacture;
		private Fournisseur fournisseur;
		private Date date = new Date();
		private ArrayList<Produit> produits = new ArrayList<Produit>();
		private Employe employe;
		private double cash,total = 0;
		
		// Constructeur
		public Facture(Fournisseur fournisseur,ArrayList<Produit> produits,Employe employe,double cash) {
			idFacture = id++;
			this.fournisseur = fournisseur;
			this.produits = produits;
			this.employe = employe;
			this.cash = cash;
		}
		
		public Facture(Fournisseur fournisseur,Employe employe,double cash,double total) {
			idFacture = id++;
			this.fournisseur = fournisseur;
			this.employe = employe;
			this.cash = cash;
			this.total = total;
		}
		
		public Facture(int id,Fournisseur fournisseur,Employe employe,double cash,double total) {
			idFacture = id;
			this.fournisseur = fournisseur;
			this.employe = employe;
			this.cash = cash;
			this.total = total;
		}

		// Getters and Setters
		public double getTotal() {
			return total;
		}
		public void setTotal(double total) {
			this.total = total;
		}
		public double getCash() {
			return cash;
		}
		public void setCash(double cash) {
			this.cash = cash;
		}
		public Employe getEmploye() {
			return employe;
		}

		public void setEmploye(Employe employe) {
			this.employe = employe;
		}

		public int getIdFacture() {
			return idFacture;
		}

		public void setIdFacture(int idFacture) {
			this.idFacture = idFacture;
		}

		public Fournisseur getFournisseur() {
			return fournisseur;
		}

		public void setFournisseur(Fournisseur fournisseur) {
			this.fournisseur = fournisseur;
		}

		public Date getDate() {
			return date;
		}

		public void setDate(Date date) {
			this.date = date;
		}

		public ArrayList<Produit> getProduits() {
			return produits;
		}

		public void setProduits(ArrayList<Produit> produits) {
			this.produits = produits;
		}
		
		// Methodes
		public void ajouterProduit(Produit p) {
			produits.add(p);
		}
		
		public void supprimerProduit(Produit p) {
			produits.remove(p);
		}
		
		public double calculerTotal() {
			double sum = 0;
			for(int i=0;i<produits.size();i++)
			{
				sum += produits.get(i).getQuantite()*produits.get(i).getPrixUnitaire();
			}
			return sum;
		}

		@Override
		public String toString() {
			return "Facture [idFacture=" + idFacture + ", fournisseur=" + fournisseur + ", date=" + date + ", produits="
					+ produits + "]";
		}

		
}
