package oo;

public class Produit {
	
	// Attributs
	private long codeBar;
	private String designation;
	private double quantite;
	private double prixUnitaire;
	private Categorie categorie;
	private String dateFabrication , dateExpiration;
	
	// Constructeur
	public Produit(Long codeBar2, String designation, double quantite, double prixUnitaire, Categorie  categorie,String dateFabrication,String dateExpiration) {
		this.codeBar = codeBar2;
		this.designation = designation;
		this.quantite = quantite;
		this.prixUnitaire = prixUnitaire;
		this.categorie = categorie;
		this.dateFabrication = dateFabrication;
		this.dateExpiration = dateExpiration;
	}
	
	// Getters and Setters
	public long getCodeBar() {
		return codeBar;
	}
	public void setCodeBar(long codeBar) {
		this.codeBar = codeBar;
	}
	public String getDateFabrication() {
		return dateFabrication;
	}
	public void setDateFabrication(String dateFabrication) {
		this.dateFabrication = dateFabrication;
	}
	public String getDateExpiration() {
		return dateExpiration;
	}
	public void setDateExpiration(String dateExpiration) {
		this.dateExpiration = dateExpiration;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public double getQuantite() {
		return quantite;
	}
	public void setQuantite(double quantite) {
		this.quantite = quantite;
	}
	public double getPrixUnitaire() {
		return prixUnitaire;
	}
	public void setPrixUnitaire(double prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
	}
	public Categorie getCategorie() {
		return categorie;
	}
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}
	
	// Methodes
	@Override
	public String toString() {
		return "Produit [codeBar=" + codeBar + ", designation=" + designation + ", quantite=" + quantite
				+ ", prixUnitaire=" + prixUnitaire + ", categorie=" + categorie + "], dateFabrication="+dateFabrication+
				" ,dateExpiration"+dateExpiration;
	}
	
	
}
