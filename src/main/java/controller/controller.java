package controller;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.apache.catalina.connector.Response;
import org.mindrot.bcrypt.BCrypt;

import BusinessLayer.BusinessLayer;
import jakarta.security.auth.message.callback.PrivateKeyCallback.Request;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.produit;
import model.user;

/**
 * Servlet implementation class controller
 */
@WebServlet("/controler")
public class controller extends HttpServlet {
	static ArrayList<produit> produitFinal=new ArrayList<>();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		
		
		if(request.getParameter("redirigerVersLogin")!= null) {
			request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
		}
		if(request.getParameter("redirigerVersInscription")!= null) {
			request.getRequestDispatcher("WEB-INF/inscription.jsp").forward(request, response);
		}
		

		if(request.getParameter("inscription")!= null) {
			String login=request.getParameter("login");
			String mdp=request.getParameter("motdepasse");
			String email=request.getParameter("email");
			String nom=request.getParameter("nom");
			String message ="Veuillez remplir tous les champs";
			
		if(nom.equals("") || login.equals("") || mdp.equals("") || email.equals("")) {
				request.setAttribute("message",message);
				request.getRequestDispatcher("WEB-INF/inscription.jsp").forward(request, response);
			}
		else {
				message=inscrire(login,BCrypt.hashpw(mdp,BCrypt.gensalt()),email,nom);
				if(message.equals("Vous étes bien inscrit")) {
					BusinessLayer b=new BusinessLayer();
					user u=b.getUser(login);
					HttpSession session = request.getSession();
					session.setAttribute("login",login);
					session.setAttribute("email",u.getEmail());
					session.setAttribute("nom",u.getNom());
					out.println("<h1>Connecté</h1>");
					request.getRequestDispatcher("initialiserP").forward(request, response);
					
				}else {
				request.setAttribute("message",message);
				request.getRequestDispatcher("WEB-INF/inscription.jsp").forward(request, response);	
				}
			}
		}
		if(request.getParameter("produit")!=null) {
			int id=Integer.parseInt(request.getParameter("produit"));
			Cookie[] listeCookie=request.getCookies();
			ArrayList<String> listePC=new ArrayList<String>();
			int test=0;
			for(Cookie c:listeCookie) {
				if(c.getName().equals("produitConsulte")) {
					String tousProduitsConsultes=c.getValue();
					String[] tab=tousProduitsConsultes.split("-");
					for(int i=0;i<tab.length;i++) {
						listePC.add(tab[i]);
					}
					for(int i=0;i<listePC.size();i++) {
						if(listePC.get(i).equals(""+id)) { listePC.remove(i);break;}
					}
					listePC.add(""+id);
					
					if(listePC.size()>=10)listePC.remove(0);
					String nvListe=listePC.get(0);
					for(int i=1;i<listePC.size();i++) {
						nvListe=nvListe+"-"+listePC.get(i);
					}
					Cookie nvCookie=new Cookie("produitConsulte", nvListe);
					response.addCookie(nvCookie);
					test=1;
				}
			}
			if(test==0) {
				String var=""+id+"-";
				Cookie c=new Cookie("produitConsulte",var);response.addCookie(c);
				
			}
			
			BusinessLayer b=new BusinessLayer();
			ArrayList<produit> liste=b.getProduits();
			for(int i=0;i<liste.size();i++) {

				if(liste.get(i).getId()==id) {
				
				produit p=new produit(id,liste.get(i).getNom(),liste.get(i).getDescription(), liste.get(i).getCategorie(), liste.get(i).getPrix(),liste.get(i).getImage(),liste.get(i).getQuantite());
				request.setAttribute("p", p);
				ArrayList<produit> listeProduitConsulte=produitsConsultes(request,response);
				request.setAttribute("produitConsulte", listeProduitConsulte);
				request.getRequestDispatcher("WEB-INF/produit.jsp").forward(request, response);
				}
			}
			
		}

		if(request.getParameter("panier")!=null) {
			String id=request.getParameter("panier");
			HttpSession session = request.getSession(true);
			String login=(String) session.getAttribute("login");
			if(login==null) {
				request.setAttribute("message","Vous devez vous connecter pour ajouter le produit au panier");
				request.setAttribute("idProduit",id);
				request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
			
			}
			
			else {
				float somme=0;
				Cookie c=new Cookie(id,id);
				response.addCookie(c);
				ArrayList<produit> produit=produitDansPanier(request, response);
				for(int i=0;i<produit.size();i++) {
					somme+=produit.get(i).getQuantite()*produit.get(i).getPrix();
				}
				
				request.setAttribute("somme",somme);
				request.setAttribute("p", produit);
				String page=request.getParameter("page");
				String message="Produit bien ajouté";
				request.getRequestDispatcher("initialiser").forward(request, response);

				
			}
	
		}
		
		if(request.getParameter("AffPanier")!=null) {
			float somme=0;
			ArrayList<produit> produit=produitDansPanier(request, response);
			for(int i=0;i<produit.size();i++) {
				somme+=produit.get(i).getQuantite()*produit.get(i).getPrix();
			}
			
			request.setAttribute("somme",somme);
			request.setAttribute("p", produit);
			request.getRequestDispatcher("WEB-INF/cart.jsp").forward(request, response);
				
			}
			
			
			
		
		
		
		
	
		
		if(request.getParameter("deconnecter")!=null) {
			HttpSession session = request.getSession(true);
			session.invalidate(); 
			request.getRequestDispatcher("initialiserP").forward(request, response);	

			
}
		

		
		if(request.getParameter("connecter")!= null) {
			String login=request.getParameter("login");
			String mdp=request.getParameter("motdepasse");
			if(connecter(login,mdp)) {
				if(login.equals("admin")) {
					BusinessLayer b=new BusinessLayer();
					user u=b.getUser(login);
					HttpSession session = request.getSession();
					
					session.setAttribute("login",login);
					session.setAttribute("email",u.getEmail());
					session.setAttribute("nom",u.getNom());
					
					request.getRequestDispatcher("WEB-INF/adminIndex.jsp").forward(request, response);
				}else {
				BusinessLayer b=new BusinessLayer();
				user u=b.getUser(login);
				HttpSession session = request.getSession();
				session.setAttribute("login",login);
				session.setAttribute("email",u.getEmail());
				session.setAttribute("nom",u.getNom());
				request.getRequestDispatcher("initialiserP").forward(request, response);
				}
			}
			else {
				String message="Mot de passe ou Login incorrect";
				request.setAttribute("message",message);
				request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);	
			}
			
			
			
		}
		if(request.getParameter("supprimer")!=null) {
			String id=request.getParameter("supprimer");
			Cookie[] list=request.getCookies();
			
			
				for(Cookie co :list) {
					String idd=co.getValue();
					if(idd.equals(id)) { 
						co.setMaxAge(0);
						response.addCookie(co);
					}
				}
			
			float somme=0;
			ArrayList<produit> produit=produitDansPanier(request, response);
			for(int i=0;i<produit.size();i++) {
				somme+=produit.get(i).getQuantite()*produit.get(i).getPrix();
			}
			
			request.setAttribute("somme",somme);
			request.setAttribute("p", produit);
			request.getRequestDispatcher("WEB-INF/cart.jsp").forward(request, response);
		}
		
		if(request.getParameter("modifierCart")!=null) {
			float somme=0;
			int qte=0;
			ArrayList<String> message=new ArrayList<String>();
			BusinessLayer b=new BusinessLayer();
			ArrayList<produit> lp=b.getProduits();
			ArrayList<produit> listeProduit=produitDansPanier(request, response);
			for(int i=0;i<listeProduit.size();i++) {
				for(int j=0;j<lp.size();j++) {
					if(lp.get(j).getId()==listeProduit.get(i).getId()) { qte=lp.get(j).getQuantite();
					break;
					}
				}
				int qteIm=Integer.parseInt(request.getParameter("quantite"+i));
				if(qteIm>qte) {
					String m="Quantite "+qteIm+" du produit '"+listeProduit.get(i).getNom()+"' n'est pas disponible dans le stock"; 
					message.add(m);
				}else {
				listeProduit.get(i).setQuantite(Integer.parseInt(request.getParameter("quantite"+i)));
				}
			}
			for(int i=0;i<listeProduit.size();i++) {
				somme+=listeProduit.get(i).getQuantite()*listeProduit.get(i).getPrix();
			}
			if(!message.isEmpty())request.setAttribute("message",message);
			
			request.setAttribute("somme",somme);
			request.setAttribute("p", listeProduit);
			request.getRequestDispatcher("WEB-INF/cart.jsp").forward(request, response);
			
		}
		if(request.getParameter("procedureAchat")!=null) {
			float expedition=5;
			float somme=0;
			ArrayList<produit> listeProduit=produitDansPanier(request, response);
			for(int i=0;i<listeProduit.size();i++) {
				listeProduit.get(i).setQuantite(Integer.parseInt(request.getParameter("quantite"+i)));
			}
			for(int i=0;i<listeProduit.size();i++) {
				somme+=listeProduit.get(i).getQuantite()*listeProduit.get(i).getPrix();
			}
			produitFinal=listeProduit;
			request.setAttribute("expedition",expedition);
			request.setAttribute("somme",somme);
			request.setAttribute("p", listeProduit);
			request.getRequestDispatcher("WEB-INF/checkout.jsp").forward(request, response);
		}
		
		if(request.getParameter("validerPayement")!=null) {
			
			ArrayList<produit> listeProduit=produitFinal;
			String pays=request.getParameter("pays");
			String adresse=request.getParameter("adresse");
			String telephone=request.getParameter("telephone");
			String carte=request.getParameter("carte");
			String code=request.getParameter("code");
			if(pays.equals("") || adresse.equals("") || telephone.equals("") ||carte.equals("") || code.equals("")) {
				float somme=0;
				float expedition=5;
				for(int i=0;i<listeProduit.size();i++) {
					somme+=listeProduit.get(i).getQuantite()*listeProduit.get(i).getPrix();
				}
				String message="Veuillez remplir tous les champs";
				request.setAttribute("expedition",expedition);
				request.setAttribute("somme",somme);
				request.setAttribute("p", listeProduit);
				request.setAttribute("message", message);
				request.getRequestDispatcher("WEB-INF/checkout.jsp").forward(request, response);
			}else {

			if(enregistrerAchat(pays,adresse,telephone,BCrypt.hashpw(carte,BCrypt.gensalt()),BCrypt.hashpw(code,BCrypt.gensalt()),request)) {
				Cookie[] list=request.getCookies();
				for(int i=0;i<produitFinal.size();i++) {
					for(Cookie co :list) {
						int id=produitFinal.get(i).getId();
						String idd=co.getValue();
						if(idd.equals(id+"")) { 
							co.setMaxAge(0);
							response.addCookie(co);}
					}
					
				}

					produitFinal.clear();
					
				}
					String message="Votre achat a ete effectue avec succes";
					request.setAttribute("message", message);
					request.getRequestDispatcher("initialiserP").forward(request, response);

			}
				
			}
			
		if(request.getParameter("vitrine")!=null) {
			String c=request.getParameter("vitrine");
			BusinessLayer b=new BusinessLayer();
			ArrayList<produit> listeProduits=new ArrayList<produit>();
			ArrayList<produit> allProduct=b.getProduits();
			for(int i=0;i<allProduct.size();i++) {
				if(allProduct.get(i).getCategorie().equals(c)) {
					listeProduits.add(allProduct.get(i));
				}
			}
			request.setAttribute("listeProduits",listeProduits);
			ArrayList<produit> listeProduitConsulte=produitsConsultes(request,response);
			request.setAttribute("produitConsulte", listeProduitConsulte);
			request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
		}
	
			
		}

			
		

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	
	}
	private ArrayList<produit> produitDansPanier(HttpServletRequest request, HttpServletResponse response) {
		ArrayList<produit> mesProduits=new ArrayList<produit>();
		ArrayList<produit> listeProduit=new ArrayList<produit>();
		ArrayList<produit> produits=new ArrayList<produit>();
		BusinessLayer b=new BusinessLayer();
		listeProduit=b.getProduits();

		for(int i=0;i<listeProduit.size();i++) {
		produits.add(new produit(listeProduit.get(i).getId(),listeProduit.get(i).getNom(),listeProduit.get(i).getDescription(),listeProduit.get(i).getCategorie(),listeProduit.get(i).getPrix(),listeProduit.get(i).getImage(),listeProduit.get(i).getQuantite()));	
		}
		
		Cookie[] list=request.getCookies();
		
		for(int i=0;i<produits.size();i++) {
			for(Cookie co :list) {
				String idd=co.getValue();
				if(idd.equals(produits.get(i).getId()+"")) mesProduits.add(produits.get(i));
			}
			
			
		}
		for(int j=0;j<produits.size();j++) {
			produits.get(j).setQuantite(1);
		}
		return mesProduits;
	}
	
	private String inscrire(String l,String m,String e,String n) {
		user u=new user(l,m,e,n);
		BusinessLayer b=new BusinessLayer();
		String message=b.addUser(u);
		return message;
	}

	private boolean enregistrerAchat(String pays,String adresse, String telephone,String carte,String code,HttpServletRequest request) {
		BusinessLayer b=new BusinessLayer();
		HttpSession session = request.getSession();
		String login=(String) session.getAttribute("login");
		 for(int i=0;i<produitFinal.size();i++) {
			
			 b.addAchat(produitFinal.get(i).getId(),login, produitFinal.get(i).getQuantite(), pays, adresse, telephone, carte, code);
		 }
		return true;
	}
	public ArrayList<produit> produitsConsultes(HttpServletRequest request, HttpServletResponse response) {
		BusinessLayer b=new BusinessLayer();
		ArrayList<produit> produitConsulte=new ArrayList<>();
		ArrayList<produit> nvProduitConsulte=new ArrayList<>();
		Cookie[] listeCookie=request.getCookies();
		for(Cookie c:listeCookie) {
			if(c.getName().equals("produitConsulte")) {
				String tousProduitsConsultes=c.getValue();
				String[] tab=tousProduitsConsultes.split("-");
				ArrayList<produit> tousProduits=b.getProduits();
				for(int i=0;i<tab.length;i++) {
					int id=Integer.parseInt(tab[i]);
					for(int j=0;j<tousProduits.size();j++) {
						if(tousProduits.get(j).getId()==id){
							produitConsulte.add(tousProduits.get(j));
						}
						
					}
					
				}
				for(int z=produitConsulte.size()-1;z>=0;z--) {
					nvProduitConsulte.add(produitConsulte.get(z));
				}
			}
			}
		return nvProduitConsulte;
	}
	
	
	private boolean connecter(String login,String mdp) {
		BusinessLayer b=new BusinessLayer();
		return b.estInscris(login, mdp);
	}

}
