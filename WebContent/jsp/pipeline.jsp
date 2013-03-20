<%@ page import="social.User"%>
<%@ page import="bdd.Actualite"%>
<%@ page import="java.sql.ResultSet"%>
<jsp:include page="header.jsp"/>
<% 
	User user = (User) session.getAttribute("user");
	int id = user.getId();
	
	ResultSet rs = Actualite.selectActu(id, 0);
	while(rs.next()) {
%>
	<fieldset>
		<legend><%= rs.getString("nom") + " " + rs.getString("prenom") %> </legend>
		<%= rs.getString("text") %>		
	</fieldset><br />
<%
	}
%>
<jsp:include page="footer.jsp"/>