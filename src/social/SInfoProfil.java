package social;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bdd.Connexion;
import bdd.Profil;

/**
 * Servlet implementation class SProfil
 */
@WebServlet("/SInfoProfil")
public class SInfoProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String MAIL_REGEX = "([_A-Za-z0-9-]+)(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})";
    private static final String DDN_REGEX = "\\d{2}/\\d{2}/\\d{4}";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SInfoProfil() {
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
		int id = ((User) request.getSession().getAttribute("user")).getId();
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String mail = request.getParameter("mail");
		String mdp = request.getParameter("mdp");
		String ddn = request.getParameter("ddn");
		String sexe = request.getParameter("civ");
		
		StringBuffer messageErreur = new StringBuffer("");
		boolean erreur = false;
		
		if(nom.isEmpty()){
			messageErreur.append("Le nom ne peut être vide<br />");
			erreur = true;
		}
		if(prenom.isEmpty()){
			messageErreur.append("Le prénom ne peut être vide<br />");
			erreur = true;
		}
		if(mail.isEmpty() || !mail.matches(MAIL_REGEX)){
			messageErreur.append("L'adresse mail n'est pas correct<br />");
			erreur = true;
		}
		if(ddn.isEmpty() || !ddn.matches(DDN_REGEX)){
			messageErreur.append("Le date de naisance doit être au format: jj/mm/yyyy<br />");
			erreur = true;
		}		
		if(mdp.isEmpty()){
			messageErreur.append("Votre mot de passe est inexistant<br />");
			erreur = true;
		}	
		PrintWriter out = response.getWriter();
		if(erreur){			
			request.getSession().setAttribute("erreur", "<div class='alert alert-error'>"+messageErreur.toString()+"</div>");
			response.sendRedirect("jsp/infoShark.jsp"); 
		} else{
			if(!Profil.modifyUser(id, nom, prenom, sexe, mail, ddn, mdp)){
				request.getSession().setAttribute("erreurCatch", "Erreur Inscription: probleme BDD <br/> SInscription - DoPost");
				response.sendRedirect("jsp/error.jsp");
			}
			User user = (User) request.getSession().getAttribute("user");
			java.util.Date utilDate = new java.util.Date(ddn);
			user.setDate(new java.sql.Date(utilDate.getTime()));
			user.setNom(nom);
			user.setPrenom(prenom);
			user.setMail(mail);
			user.setMdp(mdp);
			user.setSexe(sexe.charAt(0));
			
			response.sendRedirect("jsp/shark.jsp");
		}
	}

}
