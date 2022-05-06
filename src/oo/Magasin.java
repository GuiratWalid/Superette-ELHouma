package oo;

public class Magasin {

	// Attributs
	private static int id = 1;
	private int idMagasin;
	private String nomMagasin = "Superette Chef";
	private String adresseMagasin = "Elhay Tijeri M'saken Sousse 4070" ;
	private long numTel = 99062534 ;
	
	// Methodes
	@Override
	public String toString() {
		return "Magasin [idMagasin=" + idMagasin + ", nomMagasin=" + nomMagasin + ", adresseMagasin=" + adresseMagasin
				+ ", numTel=" + numTel + "]";
	}

	// Constructeurs
	public Magasin(int idMagasin, String nomMagasin, String adresseMagasin, Long numTel) {
		super();
		this.idMagasin = idMagasin;
		this.nomMagasin = nomMagasin;
		this.adresseMagasin = adresseMagasin;
		this.numTel = numTel;
	}
	
	public Magasin( String nomMagasin, String adresseMagasin, Long numTel) {
		super();
		this.idMagasin = id++;
		this.nomMagasin = nomMagasin;
		this.adresseMagasin = adresseMagasin;
		this.numTel = numTel;
	}

	// Getters and Setters
	public int getIdMagasin() {
		return idMagasin;
	}

	public void setIdMagasin(int idMagasin) {
		this.idMagasin = idMagasin;
	}

	public String getNomMagasin() {
		return nomMagasin;
	}

	public void setNomMagasin(String nomMagasin) {
		this.nomMagasin = nomMagasin;
	}

	public String getAdresseMagasin() {
		return adresseMagasin;
	}

	public void setAdresseMagasin(String adresseMagasin) {
		this.adresseMagasin = adresseMagasin;
	}

	public Long getNumTel() {
		return numTel;
	}

	public void setNumTel(Long numTel) {
		this.numTel = numTel;
	}
	
	
	
	
}
