package oo;


public class Employe extends Personne {
	
	// Atributs
	private static int id = 1;
	private int idEmploye;
	private double salaire;
	private String dateEmbauche,dateNaissance;
	
	// Constructeur
	public Employe(String nom, String prenom, Long tel, String adresse,double salaire,String dateEmbauche ,String dateNaissance) {
		super(nom,prenom,tel,adresse);
		idEmploye = id++;
		this.salaire = salaire;
		this.dateEmbauche = dateEmbauche;
		this.dateNaissance = dateNaissance;
	}
	
	public Employe(int id,String nom, String prenom, Long tel, String adresse,double salaire,String dateEmbauche,String dateNaissance) {
		super(nom,prenom,tel,adresse);
		idEmploye = id;
		this.salaire = salaire;
		this.dateEmbauche = dateEmbauche;
		this.dateNaissance = dateNaissance;
	}
	
	// Getters and Setters
	public int getIdEmploye() {
		return idEmploye;
	}
	public void setIdEmploye(int idEmploye) {
		this.idEmploye = idEmploye;
	}

	public double getSalaire() {
		return salaire;
	}

	public void setSalaire(double salaire) {
		this.salaire = salaire;
	}

	public String getDateEmbauche() {
		return dateEmbauche;
	}

	public void setDateEmbauche(String dateEmbauche) {
		this.dateEmbauche = dateEmbauche;
	}

	public String getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(String dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	// Methodes
	@Override
	public String toString() {
		return "Employe [idEmploye=" + idEmploye + ", salaire=" + salaire + ", dateEmbauche=" + dateEmbauche
				+ ", dateNaissance=" + dateNaissance + ", nom=" + nom + ", prenom=" + prenom + ", tel=" + tel
				+ ", adresse=" + adresse + "]";
	}
	
	

	
}