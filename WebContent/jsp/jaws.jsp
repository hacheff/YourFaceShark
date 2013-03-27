<<<<<<< HEAD
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
	}
	else{
		response.sendRedirect("connexion.jsp");
	}
%>
<a href='shark.jsp'>Vos infos</a>
<jsp:include page="footer.jsp"/>
=======
<%@ page import="social.User"%>
<%@ page import="bdd.Profil"%>
<jsp:include page="header.jsp"/>
<span class="span2"></span>

	<%	
	User user = (User) request.getSession().getAttribute("user");
	if(user != null){
		if(request.getParameter("id") != null){ // PROFIL D'UN SHARH
			int id = Integer.parseInt(request.getParameter("id"));
			User shark = Profil.getUserById(id);
			boolean b = false; // TESTER SI IMAGE EXISTE EN BASE
			out.println("<span class='span1 photoProfile'>");
			if(b){
				
			}else{
				out.println("<img src='../img/aileron.png' alt='Profile' class='img-rouded bgProfile' />");
				out.println("amis = " + user.isFriend(shark));
			}	
			out.println("</span>");
			out.println("<div class='span nomProfile'>");
			out.println(shark.getPrenom() + " " + shark.getNom());
			out.println("</div>");
		}else{ // PROFIL DE LA PERSONNE CONNECTE
			out.println("<span class='span1 photoProfile'>");
			boolean b = false; // TESTER SI IMAGE EXISTE EN BASE
			if(b){
				
			}else{
				// DANS CE CAS ELLE PEUT MODIFIER SON IMAGE
				out.println("<img src='../img/aileron.png' alt='Profile' class='img-rouded bgProfile' />");
			}
			out.println("</span>");
			out.println("<div class='span clear nomProfile'>");
			out.println(user.getPrenom() + " " + user.getNom());
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
		}
	}
	else{
		response.sendRedirect("connexion.jsp");
	}
%>
<a href="shark.jsp">Vos infos</a>
<jsp:include page="footer.jsp"/>
>>>>>>> 5598287589507064c17a97ddecf8c3ded5f8f59a
