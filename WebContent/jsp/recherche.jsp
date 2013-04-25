<%@ page import="social.User"%>
<%@ page import="java.util.List"%>
<jsp:include page="header.jsp"/>
		<%
		User user = (User) session.getAttribute("user");
		if(user == null){
			response.sendRedirect("connexion.jsp");
		}else{
			
			List<User> users = (List<User>) session.getAttribute("recherche");
			if(users.isEmpty() || users.size() == 1){
				out.println("<div class='rechercheNoResult'>Votre recherche ne retourne aucun résultat</div>");
			}
			else{
				boolean amis = true;
				for(User u : users){
					if(u != null){
			%>
						<div class="listUsers">
							<div class="span4 hiddenPhone"></div>
							<div class="left">
							<%if(!amis){%>
								<a href="../SSocial?param=add&id=<%= u.getId() %>" class="btn btn-info"><i class="icon-user icon-white"></i></a>
							<%}else{ %>
								<a href="../SSocial?param=remove&id=<%= u.getId() %>" class="btn btn-danger"><i class="icon-remove icon-white"></i></a>
							<%} %>
							</div>
							<a href="<%= "jaws.jsp?id="+u.getId() %>"><%= u.getPrenom() + " " + u.getNom()%></a>					
						</div><br />
			<%
					}
					else{
						amis = false;
					}
				}
			}
		}
		%>
<jsp:include page="footer.jsp"/>