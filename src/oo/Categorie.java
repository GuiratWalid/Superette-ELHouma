package oo;

public class Categorie {

	// Attributs
	private static int id = 1;
	private int idCategorie;
	private String designation;
	
	// Constucteur
	public Categorie(String designation) {
		this.idCategorie = id++;
		this.designation = designation;
	}
	
	public Categorie(int idCategorie,String designation) {
		this.idCategorie=idCategorie;
		this.designation = designation;
	}
	
	// Getters and Setters
	public int getIdCategorie() {
		return idCategorie;
	}
	
	public void setIdCategorie(int idCategorie) {
		this.idCategorie = idCategorie;
	}

	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	
	// Methodes
	@Override
	public String toString() {
		return "Categorie [idCategorie=" + idCategorie + ", designation=" + designation + "]";
	}
	
}
