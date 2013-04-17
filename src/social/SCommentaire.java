package social;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bdd.Profil;

/**
 * Servlet implementation class SCommentaire
 */
@WebServlet("/SCommentaire")
public class SCommentaire extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SCommentaire() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String commentaire = request.getParameter("commentaire");
		String urlReturn = request.getParameter("urlReturn");
		int idPost = Integer.parseInt(request.getParameter("idPost"));
		User user = (User) request.getSession().getAttribute("user");
		int idUser = user.getId();
		
		Profil.insertComentaire(idPost, idUser, commentaire);
		
		response.sendRedirect(urlReturn);
	}

}
