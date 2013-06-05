package social;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bdd.Recherche;

/**
 * Servlet implementation class SRecherche
 */
@WebServlet("/SRecherche")
public class SRecherche extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SRecherche() {
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
		String recherche = request.getParameter("recherche");
		User u = (User) request.getSession().getAttribute("user");
		if(u != null){
			List<User> users = Recherche.selectRecherche(recherche, u.getId(), 0);		
			request.getSession().setAttribute("recherche", users);
			response.sendRedirect("jsp/recherche.jsp");
		}else{
			response.sendRedirect("jsp/connexion.jsp");
		}
	}

}
