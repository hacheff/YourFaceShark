<%@ page import="social.User"%>
<jsp:include page="header.jsp"/>
	<p>
	<%
		User user = (User) request.getSession().getAttribute("user");
		if(user != null){
			out.println("<h2>" + user.getPrenom()+ " " + user.getNom() + "</h2>");
			if(user.getSexe() == 'm'){
				out.println("<label>Homme</label><br/>");
				out.println("<label>N&eacute; le : " + user.getDate() + "</label><br/>");
			} else {
				out.println("<label>Femme</label><br/>");
				out.println("<label>N&eacute;e le : " + user.getDate() + "</label><br/>");
			}
		}
	%>
	<a href="infoShark.jsp">Modifier votre profil</a>
	</p>
<jsp:include page="footer.jsp"/>