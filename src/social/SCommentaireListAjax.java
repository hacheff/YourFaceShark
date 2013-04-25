package social;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bdd.Profil;

/**
 * Servlet implementation class SCommentaireListAjax
 */
@WebServlet("/SCommentaireListAjax")
public class SCommentaireListAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SCommentaireListAjax() {
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
		response.setContentType("text/html");
		ServletOutputStream out = response.getOutputStream();		
		StringBuffer html = new StringBuffer();
		int idPost = Integer.parseInt(request.getParameter("idPost"));
		List<Commentaire> commentaires = Profil.selectCommentairesByIdPost(idPost, 0);
		
		for(Commentaire c : commentaires){
			html.append("<div class='span10 offset1'>");
			User u = c.getUser();
			User sessionUser = (User) request.getSession().getAttribute("user");			
			
			if(u.getId() == sessionUser.getId()){
				html.append(User.stringToHTMLString(u.getPrenom() + " " + u.getNom() + " : " + c.getCommentaire()) + "<br />");
			}
			else{
				html.append("<a href='jaws.jsp?id=" + u.getId() + "'>" + User.stringToHTMLString(u.getPrenom()) + " " + User.stringToHTMLString(u.getNom()) + "</a> : " + User.stringToHTMLString(c.getCommentaire()) + "<br />");
			}
			html.append("</div>");
		}
		
		out.println(html.toString());
	}

}
