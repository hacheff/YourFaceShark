package ws;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import social.User;

import bdd.Connexion;

/**
 * Servlet implementation class WebService
 */
@WebServlet("/WebService")
public class WebService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WebService() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// URL DE TEST: http://brain.fuck.free.fr/YFS/
		String mail = request.getParameter("mail");
		String mdp = request.getParameter("mdp");
		String url = request.getParameter("url");
		StringBuffer xml = new StringBuffer();
		xml.append("<YFS>");
		
		if(!Connexion.connectUser(mail, mdp, request.getSession())){
			xml.append("<ERROR>connection db failed</ERROR>");
		}
		else{
			User user = (User) request.getSession().getAttribute("user");

			if(user != null){				
				xml.append("<USER>");
				xml.append("<ID>" + user.getId() + "</ID>");
				xml.append("<SEXE>" + user.getSexe() + "</SEXE>");
				xml.append("<NOM>" + user.getNom() + "</NOM>");
				xml.append("<PRENOM>" + user.getPrenom() + "</PRENOM>");
				xml.append("<MAIL>" + user.getMail() + "</MAIL>");
				xml.append("<MDP>" + user.getMdp() + "</MDP>");
				xml.append("<MDP>" + user.getMdp() + "</MDP>");
				xml.append("<DATENAISSANCE>" + user.getDate() + "</DATENAISSANCE>");
				xml.append("</USER>");
			}
			else {
				xml.append("<ERROR>connection failed</ERROR>");
			}
		}
		
		xml.append("</YFS>");
		
		response.sendRedirect(url + "?yfs=true&xml="+xml);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
