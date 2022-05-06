package oo;

import java.util.ArrayList;
import java.util.Date;

public class Ticket {
	
	// Attributs
	private static int id = 1;
	private int idTicket;
	private Magasin magasin;
	private Date date = new Date();
	private ArrayList<Produit> produits = new ArrayList<Produit>();
	private Client client;
	private Employe employe;
	private double cash,total;
	
	// constructeur
	public Ticket(Employe employe,Magasin magasin,double cash,double total) {
		idTicket = id++;
		this.employe=employe;
		this.magasin = magasin;
		this.cash = cash;
	}
	
	public Ticket(Client client,Employe employe,Magasin magasin,double cash,double total) {
		idTicket = id++;
		this.client=client;
		this.employe=employe;
		this.magasin = magasin;
		this.cash = cash;
		this.total = total;
	}
	
	public Ticket(int id,Client client,Employe employe,Magasin magasin,double cash,double total) {
		idTicket = id;
		this.client=client;
		this.employe=employe;
		this.magasin = magasin;
		this.cash = cash;
		this.total = total;
	}

	// Getters and Setters
	public double getCash() {
		return cash;
	}
	public void setCash(double cash) {
		this.cash = cash;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public Magasin getMagasin() {
		return magasin;
	}
	public void setMagasin(Magasin magasin) {
		this.magasin = magasin;
	}
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public int getIdTicket() {
		return idTicket;
	}

	public void setIdTicket(int idTicket) {
		this.idTicket = idTicket;
	}

	public Employe getEmploye() {
		return employe;
	}

	public void setEmploye(Employe employe) {
		this.employe = employe;
	}

	public ArrayList<Produit> getProduits() {
		return produits;
	}

	public void setProduits(ArrayList<Produit> produits) {
		this.produits = produits;
	}
	
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
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
		return "Ticket [idTicket=" + idTicket + ", Magasin= " + magasin +", Employé= "+employe+", Client= "+client+", date="
				+ date + ", produits=" + produits + "]";
	}
	
	
}
