package controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import BusinessLayer.BusinessLayer;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.produit;

/**
 * Servlet implementation class initialiserP
 */
@WebServlet("/initialiserP")
public class initialiserP extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public initialiserP() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		BusinessLayer b=new BusinessLayer();
		ArrayList<produit> listeProduitAEnvoyer=new ArrayList<produit>();
		ArrayList<produit> listeProduits=b.getProduits();
		int begin=listeProduits.size()-1;
		int end=listeProduits.size()-20;
		if(end<0) end=0;
		for(int i=begin;i>=end;i--) {
			listeProduitAEnvoyer.add(listeProduits.get(i));
		}
		request.setAttribute("listeProduits",listeProduitAEnvoyer);
		ArrayList<produit> listeProduitConsulte=produitsConsultes(request,response);
		request.setAttribute("produitConsulte", listeProduitConsulte);
		request.getRequestDispatcher("welcom.jsp").forward(request, response);
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public ArrayList<produit> produitsConsultes(HttpServletRequest request, HttpServletResponse response) {
		BusinessLayer b=new BusinessLayer();
		ArrayList<produit> produitConsulte=new ArrayList<>();
		ArrayList<produit> nvProduitConsulte=new ArrayList<>();
		Cookie[] listeCookie=request.getCookies();
		if(listeCookie==null)return null;
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

}
