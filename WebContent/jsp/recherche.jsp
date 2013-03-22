<%@ page import="social.User"%>
<%@ page import="java.util.List"%>
<jsp:include page="header.jsp"/>
		<%
		List<User> users = (List<User>) session.getAttribute("recherche");
		if(users.isEmpty()){
			out.println("Votre recherche ne retourne aucun résultat");
		}
		else{
			for(User u : users){
				out.println(u.getNom() + " " + u.getPrenom() + " <br/>");
			}
		}
		%>
<jsp:include page="footer.jsp"/>