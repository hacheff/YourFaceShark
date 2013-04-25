<%@ page import="java.util.List"%>
<%@ page import="social.Post" %>
<%@ page import="social.User"%>
<%@ page import="bdd.Profil"%>
<%@ page import="bdd.Image"%>

<jsp:include page="header.jsp"/>
<span class="span2"></span>
<script>
function displayCommentaire(n){
	var id = "idCommentaireAdd"+n;
	$("#" + id).toggle();
}
function afficherCommentaire(n, idPost){
	var id = "idCommentaireList"+n;
	console.log(idPost);
	 $.post("../SCommentaireListAjax", { idPost: idPost}, function(data) {
		 document.getElementById(id).innerHTML = data;
	});
	$("#" + id).toggle();
}
</script>
	<%	
	User user = (User) request.getSession().getAttribute("user");
	if(user != null){
		if(request.getParameter("id") != null){ // PROFIL D'UN SHARK
			int id = Integer.parseInt(request.getParameter("id"));
			User shark = Profil.getUserById(id);		
			out.println("<span class='span1 photoProfile'>");
			if(shark.getUrl() != 0){
				
			}else{
				out.println("<img src='../img/aileron.png' alt='Profile' class='img-rouded bgProfile' />");
			}	
			out.println("</span>");
			out.println("<div class='span nomProfile'>");
			
			
			if(!user.isFriend(shark)){
				out.println("<a href='../SSocial?param=add&id="+ shark.getId() +"' class='btn btn-info'><i class='icon-user icon-white'></i></a>");
			}else{ 
				out.println("<a href='../SSocial?param=remove&id="+ shark.getId() +"' class='btn btn-danger'><i class='icon-remove icon-white'></i></a>");
			}
				
			out.println(shark.getPrenom() + " " + shark.getNom());
			out.println("</div>");
			if(user.isFriend(shark)){
				out.println("<span class='clear span2'></span><a class='span' href='shark.jsp?id="+shark.getId()+"'>Information supplémentaires</a>");
				out.println("<div class='clear'></div>");
				List<Post> liste = Profil.selectPost(shark.getId(), 0);
				for(Post p:liste){
			%>
			
	
		<fieldset class="pipelineFieldset">
			<legend class="pipelineLegend"><%= shark.getPrenom() + " " + shark.getNom() +"    "+ Profil.reverseDate(p.getDate().toString()) %> </legend>
			<div class="pipelinePost"><%= p.getTexte() %></div>	
			<br />
			<span class="left" onclick="displayCommentaire('<%= p.getIdPost() %>');" >
				<img src="../img/commentaires.png" alt="commentaires" /><br />					
			</span>	
			<span class="left" onclick="afficherCommentaire('<%= p.getIdPost() %>', '<%= p.getIdPost() %>')">
				<i class="icon-plus"></i>(<%= p.getNbCom() %>)
			</span><div class="clear"></div>
			<div id="idCommentaireAdd<%= p.getIdPost() %>" style="display: none;">
				<form action="../SCommentaire" method="POST">
					<input type="hidden" name="urlReturn" value="jsp/jaws.jsp?id=<%= id %>" />
					<input type="hidden" name="idPost" value="<%= p.getIdPost() %>" />
					<input type="text" class="champCommentaire" name="commentaire" />
				</form>
			</div>
			<div id="idCommentaireList<%= p.getIdPost() %>" style="display: none;">
			<!-- REMPLIE EN AJAX -->
			</div>	
		</fieldset><br />
			
			<% }
			}
			
		}else{ // PROFIL DE LA PERSONNE CONNECTE
			out.println("<span class='span1 photoProfile'>");
			if(user.getUrl() != 0){
				out.println("<img src='images.jsp?idPhoto=" + user.getUrl() + "' alt='Profile' class='img-rouded bgProfile' />");
			}else{
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
					<input type="hidden" value="jsp/jaws.jsp" name="return" />
					<input type="submit" class="btn btn-info span8" value="Valider" />
				</form>
			</div>
			<a href="shark.jsp">Information supplémentaires</a>
			
		
			
			<% List<Post> liste = Profil.selectPost(user.getId(), 0);
				for(Post p:liste){
			%>
			
	
		<fieldset class="pipelineFieldset">
			<legend class="pipelineLegend"><%= user.getPrenom() + " " + user.getNom()+"    "+ Profil.reverseDate(p.getDate().toString()) %> </legend>
			<div class="pipelinePost"><%= p.getTexte() %></div>	
			<br />
			<span class="left" onclick="displayCommentaire('<%= p.getIdPost() %>');" >
				<img src="../img/commentaires.png" alt="commentaires" /><br />					
			</span>	
			<span class="left" onclick="afficherCommentaire('<%= p.getIdPost() %>', '<%= p.getIdPost() %>')">
				<i class="icon-plus"></i>(<%= p.getNbCom() %>)
			</span><div class="clear"></div>
			<div id="idCommentaireAdd<%= p.getIdPost() %>" style="display: none;">
				<form action="../SCommentaire" method="POST">
					<input type="hidden" name="urlReturn" value="jsp/jaws.jsp" />
					<input type="hidden" name="idPost" value="<%= p.getIdPost() %>" />
					<input type="text" class="champCommentaire" name="commentaire" />
				</form>
			</div>
			<div id="idCommentaireList<%= p.getIdPost() %>" style="display: none;">
			<!-- REMPLIE EN AJAX -->
			</div>	
		</fieldset><br />
			
			<% } %>		
			
<%
		}
	}
	else{
		response.sendRedirect("connexion.jsp");
	}
%>
<jsp:include page="footer.jsp"/>
