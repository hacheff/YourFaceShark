package connexion;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import social.User;

import bdd.Connexion;
/**
 * Servlet implementation class connexion
 */
@WebServlet("/SConnexion")
public class SConnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String MAIL_REGEX = "([_A-Za-z0-9-]+)(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})";
	
	public static final String COOKIE_NAME = "YourFaceShark";
	private static final int COOKIE_DUREE = 2678400; // 1 mois
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SConnexion() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mail = request.getParameter("mail");
		String mdp = request.getParameter("mdp");
		
		StringBuffer messageErreur = new StringBuffer();
		boolean	 erreur = false;
		
		if(mail.isEmpty() || !mail.matches(MAIL_REGEX)){
			messageErreur.append("Votre adrese mail est vide<br />");
			erreur = true;
		}
		if(mdp.isEmpty()){
			messageErreur.append("Votre mot de passe est vide<br />");
			erreur = true;
		}
		
		if(erreur){
			
			request.getSession().setAttribute("erreur", "<div class='alert alert-error'>"+messageErreur.toString()+"</div>");
			response.sendRedirect("jsp/connexion.jsp");
		}
		else{
			if(!Connexion.connectUser(mail, mdp, request.getSession())){
				request.getSession().setAttribute("erreurCatch", "Erreur Connexion: probleme BDD <br/> SConnexion - DoPost");
				response.sendRedirect("jsp/error.jsp");
			}
			else{
				User user = (User) request.getSession().getAttribute("user");
				if(user != null){
					if(request.getParameter("cookie") != null){
						// AJOUT DU COOKIE
						Cookie auChocolat = new Cookie(COOKIE_NAME, String.valueOf(user.getId()));
						auChocolat.setMaxAge(COOKIE_DUREE);
						response.addCookie(auChocolat);
					}
					response.sendRedirect("jsp/jaws.jsp"); 
				}
				else{
					request.getSession().setAttribute("erreur", "<div class='alert alert-error'>Identifiants incorrects</div>");
					response.sendRedirect("jsp/connexion.jsp");
				}
			}
		}
	}

}