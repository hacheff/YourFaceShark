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
						out.println("<p>Homme</p><br/>");
						out.println("<p>N&eacute; le : " + shark.getDate() + "</p><br/>");
					} else {
						out.println("<p>Femme</p><br/>");
						out.println("<p>N&eacute;e le : " + shark.getDate() + "</p><br/>");
					}
				}else{
					response.sendRedirect("jaws.jsp?id=" + shark.getId());
				}
				
			}else{ // INFORMATION DE L'UTILISATEUR CONNECTE
				out.println("<h2 style='position:center'>" + user.getPrenom()+ " " + user.getNom() + "</h2>");
				if(user.getSexe() == 'm'){
					out.println("<p style='position:center'>Homme</p><br/>");
					out.println("<p style='position:center'>N&eacute; le : " + user.getDate() + "</p><br/>");
				} else {
					out.println("<p style='float:center'>Femme</p><br/>");
					out.println("<p style='float:center'>N&eacute;e le : " + user.getDate() + "</p><br/>");
				}		
				String url = Profil.getUrlPhotoProfile(user.getUrl());
				if(!url.equals("")){
					out.println("<img style='float:center' class='photoProfile' src='" + url + "' alt='profile' />");
				}else{
					out.println("Aucune photo de profil");
				}				
				out.println("<br/><a style='float:center' href='infoShark.jsp'><input type='submit' class='btn btn-info' value='Modifier votre profil' style='float:center'/></a>");
			}
			
		}else{
			response.sendRedirect("connexion.jsp");
		}
	%>
	</p>
<jsp:include page="footer.jsp"/>