package connexion;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
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
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

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
		
		if(nom.isEmpty()){
			messageErreur.append("Le nom ne peut être vide<br />");
		}
		if(prenom.isEmpty()){
			messageErreur.append("Le prénom ne peut être vide<br />");
		}
		if(mail.isEmpty() || !mail.matches(MAIL_REGEX)){
			messageErreur.append("L'adresse mail n'est pas correct<br />");
		}
		if(ddn.isEmpty() || !ddn.matches(DDN_REGEX)){
			messageErreur.append("Le date de naisance doit être au format: jj/mm/yyyy<br />");
		}		
		if(mdp.isEmpty()){
			messageErreur.append("Votre mot de passe est inexistant<br />");
		}	
		PrintWriter out = response.getWriter();
		if(!messageErreur.equals("")){
			request.getSession().setAttribute("erreur", messageErreur.toString());
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/inscription.jsp");
			rd.include(request,response);
		}
		else{
			Connexion.insertUser(nom, prenom, sexe, mail, ddn, mdp);	
			out.println("Inscription effectué");
		}
	}
}