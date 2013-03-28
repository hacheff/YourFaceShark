<%@ page import="social.User"%>
<%@ page import="bdd.Profil"%>
<jsp:include page="header.jsp"/>
	<p>
	<%
		User user = (User) request.getSession().getAttribute("user");
		if(user != null){
			if(request.getParameter("id") != null){ // INFORMATIONS D'UN SHARK
				int id = Integer.parseInt(request.getParameter("id"));
				User shark = Profil.getUserById(id);
				if(user.isFriend(shark)){
					out.println("<h2>" + shark.getPrenom()+ " " + shark.getNom() + "</h2>");
					if(shark.getSexe() == 'm'){
						out.println("<label>Homme</label><br/>");
						out.println("<label>N&eacute; le : " + shark.getDate() + "</label><br/>");
					} else {
						out.println("<label>Femme</label><br/>");
						out.println("<label>N&eacute;e le : " + shark.getDate() + "</label><br/>");
					}
				}else{
					response.sendRedirect("jaws.jsp?id=" + shark.getId());
				}
				
			}else{ // INFORMATION DE L'UTILISATEUR CONNECTE
				out.println("<h2>" + user.getPrenom()+ " " + user.getNom() + "</h2>");
				if(user.getSexe() == 'm'){
					out.println("<label>Homme</label><br/>");
					out.println("<label>N&eacute; le : " + user.getDate() + "</label><br/>");
				} else {
					out.println("<label>Femme</label><br/>");
					out.println("<label>N&eacute;e le : " + user.getDate() + "</label><br/>");
				}
			}
			
			out.println("<a href='infoShark.jsp'>Modifier votre profil</a>");
		}else{
			response.sendRedirect("connexion.jsp");
		}
	%>
	</p>
<jsp:include page="footer.jsp"/>