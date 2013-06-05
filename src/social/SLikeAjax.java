package social;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bdd.Like;

/**
 * Servlet implementation class SLikeAjax
 */
@WebServlet("/SLikeAjax")
public class SLikeAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLikeAjax() {
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
		int idPost = Integer.parseInt(request.getParameter("idPost"));
		int idLikeur = Integer.parseInt(request.getParameter("idLikeur"));
		int choix = Integer.parseInt(request.getParameter("choix"));
		
		Like.insertLike(idPost, idLikeur, choix);
	}

}
