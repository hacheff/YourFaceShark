package connexion;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SInscription
 */
@WebServlet("/SInscription")
public class SInscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String mail = request.getParameter("mail");
		String ddn = request.getParameter("ddn");
		String mdp = request.getParameter("mdp");
		
		PrintWriter out = response.getWriter();
		out.println("Nom: " + nom);
		out.println("Prénom: " + prenom);
		out.println("DDN: " + ddn);
		out.println("mAIL: " + mail);
		out.println("mdp: " + mdp);
	}

}
