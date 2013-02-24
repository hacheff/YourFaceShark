package connexion;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Servlet implementation class connexion
 */
@WebServlet("/SConnexion")
public class SConnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		
		if(mail.isEmpty()){
			messageErreur.append("Votre adrese mail est vide<br />");
		}
		if(mdp.isEmpty()){
			messageErreur.append("Votre mot de passe est vide<br />");
		}
		
		PrintWriter out = response.getWriter();
		if(!messageErreur.equals("")){
			
			request.getSession().setAttribute("erreur", "<div class='alert alert-error'>"+messageErreur.toString()+"</div>");
			response.sendRedirect("jsp/connexion.jsp"); 
		}
		else{
			// TODO Test Mail mot de passe	
			out.println("Connexion à tester");
		}
	}

}