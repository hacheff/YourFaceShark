<%@ page import="social.User"%>
<%@ page import="java.util.List"%>
<jsp:include page="header.jsp"/>
		<%
		List<User> users = (List<User>) session.getAttribute("recherche");
		if(users.isEmpty()){
			out.println("<div class='rechercheNoResult'>Votre recherche ne retourne aucun résultat</div>");
		}
		else{
			for(User u : users){
		%>
				<div class="span4 hiddenPhone"></div><a href="<%= "jaws.jsp?id="+u.getId() %>"><%= u.getPrenom() + " " + u.getNom()%></a></br>
		<%
			}
		}
		%>
<jsp:include page="footer.jsp"/>