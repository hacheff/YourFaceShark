package connexion;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SConnexion() {
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
		
		PrintWriter out = response.getWriter();
		if(erreur){
			
			request.getSession().setAttribute("erreur", "<div class='alert alert-error'>"+messageErreur.toString()+"</div>");
			response.sendRedirect("jsp/connexion.jsp"); 
		}
		else{
			Connexion.connectUser(mail, mdp, request.getSession());	
			User user = (User) request.getSession().getAttribute("user");
			if(user != null){
				out.println(user.getId());
				out.println(user.getNom());
				out.println(user.getPrenom());
				out.println(user.getSexe());
				out.println(user.getMail());
				out.println(user.getDate());
				out.println(user.getMdp());
			}
			else{
				request.getSession().setAttribute("erreur", "<div class='alert alert-error'>Identifiants incorrects</div>");
				response.sendRedirect("jsp/connexion.jsp");
			}
		}
	}

}