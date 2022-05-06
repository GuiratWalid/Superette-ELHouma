package oo;

import java.util.Date;

public class Client extends Personne {
	
	// Atributs
	private static int id = 1;
	private int idClient;
	private double bonus;
	private Date dateInscription = new Date();
	
	// Constructeur
	public Client(String nom, String prenom, Long tel, String adresse) {
		super(nom,prenom,tel,adresse);
		idClient = id;
		id++;
		bonus = 1;
	}
	
	public Client(int id, String nom, String prenom, long tel, String adresse) {

		super(nom,prenom,tel,adresse);
		idClient = id;
		
	}

	public Client(int id, String nom, String prenom, long tel, String adresse, double bonus) {
		super(nom,prenom,tel,adresse);
		this.bonus = bonus;
		this.idClient = id;
	}

	// Getters and Setters
	public Date getDateInscription() {
		return dateInscription;
	}
	
	public void setDateInscription(Date dateInscription) {
		this.dateInscription = dateInscription;
	}
	
	public double getBonus() {
		return bonus;
	}
	
	public void setBonus(double bonus) {
		this.bonus = bonus;
	}
	
	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	// Methodes
	@Override
	public String toString() {
		return "Client [idClient=" + idClient + ", nom=" + nom + ", prenom=" + prenom + ", tel=" + tel + ", adresse="
				+ adresse +", bonus="+bonus+"]";
	}
	
	
}
