<%@ page import="social.User"%>
<%@ page import="bdd.Profil"%>
<jsp:include page="header.jsp"/>
<span class="span2"></span>

	<%	
	User user = (User) request.getSession().getAttribute("user");
	if(user != null){
		if(request.getParameter("id") != null){
			int id = Integer.parseInt(request.getParameter("id"));
			User shark = Profil.getUserById(id);
			boolean b = false; // TESTER SI IMAGE EXISTE EN BASE
			out.println("<span class='span1 photoProfile'>");
			if(b){
				
			}else{
				// DANS CE CAS ELLE PEUT MODIFIER SON IMAGE
				out.println("<img src='../img/aileron.png' alt='Profile' class='img-rouded bgProfile' />");
			}	
			out.println("</span>");
			out.println("<div class='span nomProfile'>");
			out.println(shark.getPrenom() + " " + shark.getNom());
			out.println("</div>");
		}else{
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
<jsp:include page="footer.jsp"/>