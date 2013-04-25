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
					out.println("<a href='jaws.jsp?id="+ shark.getId() +"'><h2>" + shark.getPrenom()+ " " + shark.getNom() + "</h2></a>");
					if(shark.getSexe() == 'm'){
						out.println("<div class='infoshark'>Homme</div><br/>");
						out.println("<div class='infoshark'>N&eacute; le : " + shark.getDate() + "</div><br/>");
					} else {
						out.println("<div class='infoshark'>Femme</div><br/>");
						out.println("<div class='infoshark'>N&eacute;e le : " + shark.getDate() + "</div><br/>");
					}
				}else{
					response.sendRedirect("jaws.jsp?id=" + shark.getId());
				}
				
			}else{ // INFORMATION DE L'UTILISATEUR CONNECTE
				out.println("<a href='jaws.jsp'><h2>" + user.getPrenom()+ " " + user.getNom() + "</h2></a>");		
				String url = Profil.getUrlPhotoProfile(user.getUrl());
				if(!url.equals("")){
					out.println("<img class='photoProfile photoshark' src='" + url + "' alt='profile' /><br/>");
				}else{
					out.println("<div class='infoshark'>Aucune photo de profil</div><br/>");
				}				
				out.println("<a href='infoShark.jsp'><input type='submit' class='btn btn-info' value='Modifier votre profil' style='float:center'/></a><br/>");
				if(user.getSexe() == 'm'){
					out.println("<div class='infoshark'>Homme</div><br/>");
					out.println("<div class='infoshark'>N&eacute; le : " + user.getDate() + "</div><br/>");
				} else {
					out.println("<div class='infoshark'>Femme</div><br/>");
					out.println("<div class='infoshark'>N&eacute;e le : " + user.getDate() + "</div><br/>");
				}
			}
			
		}else{
			response.sendRedirect("connexion.jsp");
		}
	%>
	</p>
<jsp:include page="footer.jsp"/>