<%@ page import="social.User"%>
<%@ page import="neo4j.SuggestRel"%>
<%@ page import="neo4j.N4JUser"%>
<%@ page import="org.neo4j.graphdb.Node"%>
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
				out.println("<a href='infoShark.jsp'><input type='submit' class='btn btn-info' value='Modifier votre profil' style='float:center'/></a><br/>");
				if(user.getSexe() == 'm'){
					out.println("<div class='infoshark'>Homme</div><br/>");
					out.println("<div class='infoshark'>N&eacute; le : " + user.getDate() + "</div><br/>");
				} else {
					out.println("<div class='infoshark'>Femme</div><br/>");
					out.println("<div class='infoshark'>N&eacute;e le : " + user.getDate() + "</div><br/>");					
				}
				
				out.println(N4JUser.graphe(user));
			}
			
		}else{
			response.sendRedirect("connexion.jsp");
		}
	%>
	</p>
<jsp:include page="footer.jsp"/>