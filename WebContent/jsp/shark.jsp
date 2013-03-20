<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="social.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>YourFaceShark - Profil</title>
</head>
<body>
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
</body>
</html> 