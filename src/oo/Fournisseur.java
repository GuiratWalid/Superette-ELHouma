package oo;

public class Fournisseur {
	
	// Attributs
	private static int id = 1;
	private int idFournisseur;
	private String nomFournisseur ;
	private String adresseFournisseur ;
	private long numTel ;
	
	// Constructeur
	public Fournisseur(String nomFournisseur, String adresseFournisseur, long numTel) {
		idFournisseur = id++;
		this.nomFournisseur = nomFournisseur;
		this.adresseFournisseur = adresseFournisseur;
		this.numTel = numTel;
	}

	public Fournisseur(int id2, String nom, String adresse, long tel) {
		idFournisseur = id2;
		this.nomFournisseur = nom;
		this.adresseFournisseur = adresse;
		this.numTel = tel;
	}

	// Getters and Setters
	public int getIdFournisseur() {
		return idFournisseur;
	}

	public void setIdFournisseur(int idFournisseur) {
		this.idFournisseur = idFournisseur;
	}

	public String getNomFournisseur() {
		return nomFournisseur;
	}

	public void setNomFournisseur(String nomFournisseur) {
		this.nomFournisseur = nomFournisseur;
	}

	public String getAdresseFournisseur() {
		return adresseFournisseur;
	}

	public void setAdresseFournisseur(String adresseFournisseur) {
		this.adresseFournisseur = adresseFournisseur;
	}

	public long getNumTel() {
		return numTel;
	}

	public void setNumTel(long numTel) {
		this.numTel = numTel;
	}

	// Methodes
	@Override
	public String toString() {
		return "Fournisseur [idFournisseur=" + idFournisseur + ", nomFournisseur=" + nomFournisseur
				+ ", adresseFournisseur=" + adresseFournisseur + ", numTel=" + numTel + "]";
	}
	
	
	
}
