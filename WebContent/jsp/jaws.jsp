<%@ page import="social.User"%>
<jsp:include page="header.jsp"/>
<span class="span2"></span>
<span class="span1 photoProfile">
	<%	
		boolean b = false; // TESTER SI IMAGE EXISTE EN BASE
		if(b){
			
		}else{
			// TESTE SI LA PERSONNE EST SUR SON COMPTE 
			// DANS CE CAS ELLE PEUT MODIFIER SON IMAGE
			out.println("<img src='../img/aileron.png' alt='Profile' class='img-rouded bgProfile' />");
		}
		
	%>
</span>

<%
	User user = (User) request.getSession().getAttribute("user");
	if(user != null){
		out.println("<div class='span clear nomProfile'>");
		out.println(user.getNom() + " " + user.getPrenom());
		out.println("</div>");
%>
		<div class="clear span2"></div>
		<div>
		<form action="../SPost" method="post">
			<textarea class="span8" name="post"></textarea>
			<input type="submit" class="btn btn-info span8" value="Valider" />
		</form>
		</div>
<%	

		/*out.println(user.getId());
		out.println(user.getNom());
		out.println(user.getPrenom());
		out.println(user.getSexe());
		out.println(user.getMail());
		out.println(user.getDate());
		out.println(user.getMdp());	*/
	}
	else{
		out.println("FAIL");	
	}
%>
<jsp:include page="footer.jsp"/>