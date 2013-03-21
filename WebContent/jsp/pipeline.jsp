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
	<fieldset class="pipelineFieldset">
		<legend class="pipelineLegend"><%= rs.getString("nom") + " " + rs.getString("prenom") %> </legend>
		<div class="pipelinePost"><%= rs.getString("text") %></div>		
	</fieldset><br />
<%
	}
%>
<jsp:include page="footer.jsp"/>