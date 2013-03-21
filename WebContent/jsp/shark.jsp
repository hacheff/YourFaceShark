<%@ page import="social.User"%>
<jsp:include page="header.jsp"/>
	<p>
	<%
		User user = (User) request.getSession().getAttribute("user");
		if(user != null){
			out.println("<h2>" + user.getPrenom()+ " " + user.getNom() + "</h2>");
			if(user.getSexe() == 'm'){
				out.println("<label>Homme</label><br/>");
				out.println("<label>Né le : " + user.getDate() + "</label><br/>");
			} else {
				out.println("<label>Femme</label><br/>");
				out.println("<label>Née le : " + user.getDate() + "</label><br/>");
			}
			out.println("<img src='" + user.getUrl() + "' height='120' width='80'>");
		}
	%>
	<a href="infoShark.jsp">Modifier votre profil</a>
	</p>
<jsp:include page="footer.jsp"/>