package model;

public class produit {

	private int id;
	private String nom;
	private String description;
	private String categorie;
	private float prix;
	private String image;
	private int quantite;
	
	


	
	public produit(int id,String nom, String description, String categorie,float prix, String image,int quantite) {
		super();
		this.id=id;
		this.nom = nom;
		this.categorie=categorie;
		this.description = description;
		this.prix = prix;
		this.image = image;
		this.quantite=quantite;
	}
	
	

	public produit(String nom2, String description2, String categorie2, Float prix2, String imageName, int quantite2) {
		this.nom = nom2;
		this.categorie=categorie2;
		this.description = description2;
		this.prix = prix2;
		this.image = imageName;
		this.quantite=quantite2;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getCategorie() {
		return categorie;
	}



	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}



	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getPrix() {
		return prix;
	}

	public void setPrix(float prix) {
		this.prix = prix;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	
	
}
