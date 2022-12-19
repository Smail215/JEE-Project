package BusinessLayer;

import java.sql.SQLException;
import java.util.ArrayList;

import org.mindrot.bcrypt.BCrypt;

import DataLayer.DataLayer;
import model.Commande;
import model.gestionProduitsInterface;
import model.gestionUsersInterface;
import model.produit;
import model.user;
public class BusinessLayer implements gestionUsersInterface,gestionProduitsInterface {
	static ArrayList<user> listeUserM=new ArrayList<user>();
	static ArrayList<produit> listeProduitM=new ArrayList<produit>();
    static ArrayList<user> listeUser = new ArrayList<user>();
    static ArrayList<produit> listeProduit = new ArrayList<produit>();
    static ArrayList<Commande> listeAchat = new ArrayList<>();

    DataLayer dataLayer = new DataLayer();

	
	
	public String addUser(user u) {
	
		if(this.listeUserM.isEmpty()) {
			this.listeUserM=getUsers();}
		
		for(int i=0;i<listeUserM.size();i++) {
			
			if(u.getLogin().equals(listeUserM.get(i).getLogin())) {return "Le login est déjà utilisé";}
			if(u.getEmail().equals(listeUserM.get(i).getEmail())) return "Email déjà utilisé";
		}
		DataLayer d;
		d = new DataLayer();
		if(d.addUser(u)) {
			listeUserM.add(u);
			return"Vous étes bien inscrit";
			
		}else {
			return"Erreur d'inscription";
		}		
	}

	public BusinessLayer() {
		
		
	}

	@Override
	public ArrayList<user> getUsers() {
		DataLayer d=new DataLayer();
		
		return d.getUsers();
	}

	@Override
	public boolean estInscris(String login, String pwd) {
		if(this.listeUserM.isEmpty()) {
			this.listeUserM=getUsers();}
		for(int i=0;i<listeUserM.size();i++) {
			if(listeUserM.get(i).getLogin().equals(login) && BCrypt.checkpw(pwd,listeUserM.get(i).getPwd())) return true;
		}
		return false;
	}
	public user getUser(String login) {
		if(this.listeUserM.isEmpty()) {
		this.listeUserM=getUsers();}
		for(int i=0;i<listeUserM.size();i++) {
			if(listeUserM.get(i).getLogin().equals(login)) return listeUserM.get(i);
		}
		return null;
		
		
		
	}

	@Override
	public ArrayList<produit> getProduits() {
		if(listeProduitM.isEmpty()) {
		DataLayer d=new DataLayer();
		this.listeProduitM=d.getProduits();
		}
		return listeProduitM;
	}
	public int addAchat(int id,String login ,int quantite,String pays,String adresse, String telephone,String carte,String code) {
		int nQte=0;
		DataLayer d=new DataLayer();
		for(int i=0;i<listeProduitM.size();i++) {
			
			if(id==listeProduitM.get(i).getId()) {
				nQte=listeProduitM.get(i).getQuantite()-quantite;
				listeProduitM.get(i).setQuantite(nQte);
			}
}
		d.modifierProduit(id, quantite);
		d.ajouterAchat(id,login,quantite,pays,adresse,telephone,carte,code);
		return 1;
	}
	
	
	@Override
    public ArrayList<produit> listAllProducts() {
        this.listeProduit = dataLayer.listAllProducts();
        return listeProduit;
    }

    @Override
    public produit selectProduct(int id) {
        return dataLayer.selectProduct(id);
    }

    @Override
    public boolean saveProduct(produit produit1) {
    	listeProduitM.add(produit1);
        return dataLayer.saveProduct(produit1);
    }

    public boolean deleteProduct(int id) {
        return dataLayer.deleteProduct(id);
    }

    @Override
    public void editProduct(produit product) {
        dataLayer.updateProduct(product);
    }

    @Override
    public ArrayList<produit> searchProduct(String key) {
        return dataLayer.searchProduct(key);
    }

    @Override
    public ArrayList<user> listAllUsers() {
        this.listeUser = dataLayer.listAllUsers();
        return listeUser;
    }

    @Override
    public user selectUser(String login) {
        return dataLayer.selectUser(login);
    }

    @Override
    public boolean saveUser(user user) {
        return dataLayer.saveUser(user);
    }

    @Override
    public boolean deleteUser(String login) {
        return dataLayer.deleteUser(login);
    }

    @Override
    public void editUser(user user) {
        dataLayer.updateUser(user);
    }


    public ArrayList<Commande> listeAllAchats() {

        this.listeAchat = dataLayer.listeAllAchats();
        return listeAchat;
    }

    public ArrayList<produit> listeAchatUser(String login) {
        this.listeProduit = dataLayer.listeAchatUser(login);
        return listeProduit;
    }

}
