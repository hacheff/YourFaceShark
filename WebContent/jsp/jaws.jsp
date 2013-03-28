<%@ page import="social.User"%>
<%@ page import="bdd.Profil"%>
<jsp:include page="header.jsp"/>
<span class="span2"></span>

	<%	
	User user = (User) request.getSession().getAttribute("user");
	if(user != null){
		if(request.getParameter("id") != null){ // PROFIL D'UN SHARK
			int id = Integer.parseInt(request.getParameter("id"));
			User shark = Profil.getUserById(id);
			boolean b = false; // TESTER SI IMAGE EXISTE EN BASE
			out.println("<span class='span1 photoProfile'>");
			if(b){
				
			}else{
				out.println("<img src='../img/aileron.png' alt='Profile' class='img-rouded bgProfile' />");
			}	
			out.println("</span>");
			out.println("<div class='span nomProfile'>");
			
			
			if(!user.isFriend(shark)){
				out.println("<a href='../SSocial?param=add&id="+ shark.getId() +"' class='btn btn-info'><i class='icon-user icon-white'></i></a>");
			}else{ 
				out.println("<a href='' class='btn btn-danger'><i class='icon-remove icon-white'></i></a>");
			} 
			
			out.println(shark.getPrenom() + " " + shark.getNom());
			out.println("</div>");
			if(user.isFriend(shark)){
				out.println("<span class='clear span2'></span><a class='span' href='shark.jsp'>Information supplémentaires</a>");
			}
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
			<a href="shark.jsp">Information supplémentaires</a>
<%
		}
	}
	else{
		response.sendRedirect("connexion.jsp");
	}
%>
<jsp:include page="footer.jsp"/>
