package connexion;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bdd.Connexion;

/**
 * Servlet implementation class SInscription
 */
@WebServlet("/SInscription")
public class SInscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String MAIL_REGEX = "([_A-Za-z0-9-]+)(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})";
    private static final String DDN_REGEX = "\\d{2}/\\d{2}/\\d{4}";
    		
	/**
     * @see HttpServlet#HttpServlet()
     */
    public SInscription() {
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
		String sexe = request.getParameter("civ");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String mail = request.getParameter("mail");
		String ddn = request.getParameter("ddn");
		String mdp = request.getParameter("mdp");
		
		StringBuffer messageErreur = new StringBuffer("");
		boolean erreur = false;
		
		if(nom.isEmpty()){
			messageErreur.append("Le nom ne peut �tre vide<br />");
			erreur = true;
		}
		if(prenom.isEmpty()){
			messageErreur.append("Le pr�nom ne peut �tre vide<br />");
			erreur = true;
		}
		if(mail.isEmpty() || !mail.matches(MAIL_REGEX)){
			messageErreur.append("L'adresse mail n'est pas correct<br />");
			erreur = true;
		}
		if(ddn.isEmpty() || !ddn.matches(DDN_REGEX)){
			messageErreur.append("Le date de naisance doit �tre au format: jj/mm/yyyy<br />");
			erreur = true;
		}		
		if(mdp.isEmpty()){
			messageErreur.append("Votre mot de passe est inexistant<br />");
			erreur = true;
		}	
		PrintWriter out = response.getWriter();
		if(erreur){			
			request.getSession().setAttribute("erreur", "<div class='alert alert-error'>"+messageErreur.toString()+"</div>");
			response.sendRedirect("jsp/inscription.jsp"); 
		}
		else{
			if(!Connexion.insertUser(nom, prenom, sexe, mail, ddn, mdp)){
				request.getSession().setAttribute("erreurCatch", "Erreur Inscription: probleme BDD <br/> SInscription - DoPost");
				response.sendRedirect("jsp/error.jsp");
			}
			response.sendRedirect("jsp/connexion.jsp");
		}
	}
}