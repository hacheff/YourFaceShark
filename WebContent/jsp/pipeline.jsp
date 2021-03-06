<%@ page import="social.User"%>
<%@ page import="bdd.Actualite"%>
<%@ page import="bdd.Profil"%>
<%@ page import="bdd.Like"%>
<%@ page import="java.sql.ResultSet"%>
<jsp:include page="header.jsp"/>
<script>
function displayCommentaire(n){
	var id = "idCommentaireAdd"+n;
	$("#" + id).toggle();
}
function afficherCommentaire(n, idPost){
	var id = "idCommentaireList"+n;
	 $.post("../SCommentaireListAjax", { idPost: idPost}, function(data) {
		 document.getElementById(id).innerHTML = data;
	});
	$("#" + id).toggle();
}
function afficherPost(){
	var id = "addPost";
	$("#" + id).toggle();
}
function addLike(idpost, idlikeur){
	$.post("../SLikeAjax", { idPost: idpost, idLikeur: idlikeur, choix: 1}, function(data) {
		var html = document.getElementById("nblike"+idpost).innerHTML;
		html = html.replace("(", "");
		html = html.replace(")", "");
		n = parseInt(html);
		n++;
		document.getElementById("nblike"+idpost).innerHTML = "("+n+")";	
		setTimeout(location.reload(),500);		
	});
}
function addUnLike(idpost, idlikeur){
	$.post("../SLikeAjax", { idPost: idpost, idLikeur: idlikeur, choix: 0}, function(data) {
		var html = document.getElementById("nbunlike"+idpost).innerHTML;
		html = html.replace("(", "");
		html = html.replace(")", "");
		n = parseInt(html);
		n++;
		document.getElementById("nbunlike"+idpost).innerHTML = "("+n+")";	
		setTimeout(location.reload(),500);	
	});
}
</script>
<span class="pipelinePostAdd" onclick="afficherPost()">
	<span class="btn btn-info"><i class="icon-edit icon-white"></i></span>
</span>
<div id="addPost" style="display: none;">
	<form action="../SPost" method="post" class="pipelinePostAdd">
		<textarea class="span8" name="post"></textarea><br />
		<input type="hidden" value="jsp/pipeline.jsp" name="return" />
		<input type="submit" class="btn btn-info span8" value="Valider" />
	</form>
</div><div class="clear"></div>
<% 
	User user = (User) session.getAttribute("user");
	if(user == null){
		response.sendRedirect("connexion.jsp");
	}
	else{
		int id = user.getId();
		int cpt = 0;
		ResultSet rs = Actualite.selectActu(id, 0);
		while(rs.next()) {
%>
			<fieldset class="pipelineFieldset">
				<legend class="pipelineLegend"><a href="jaws.jsp?id=<%= rs.getString("id") %>"><%= rs.getString("prenom") + " " + rs.getString("nom") +" "+ Profil.reverseDate(rs.getString("date").split(" ")[0])%></a></legend>
				<div class="pipelinePost"><%= rs.getString("text") %>
					<br />
					<span class='left'>
						<%if(rs.getInt("likeuser") == 0){ %>
							<span id='nblike<%= rs.getInt("id")%>'>(<%= rs.getInt("nblike") %>)</span><span class='icon-circle-arrow-up' onclick='addLike(<%= rs.getInt("id")%>, <%= user.getId()%>)'></span>
						<%}else{%>
							<span id='nblike<%= rs.getInt("id")%>'>(<%= rs.getInt("nblike") %>)</span><span class='icon-circle-arrow-up'></span>
						<%}
						if(rs.getInt("unlikeuser") == 0){%>
							<span class='icon-circle-arrow-down' onclick='addUnLike(<%= rs.getInt("id")%>, <%= user.getId()%>)'></span><span id="nbunlike<%= rs.getInt("id")%>">(<%= rs.getInt("nbunlike") %>)</span>
						<%
						}else{%>
							<span class='icon-circle-arrow-down'></span><span id="nbunlike<%= rs.getInt("id")%>">(<%= rs.getInt("nbunlike") %>)</span>
						<% } %>
					</span>
					<div class="clear"></div>
					<span class="left" onclick="displayCommentaire('<%= cpt %>');" >
						<img src="../img/commentaires.png" alt="commentaires" /><br />					
					</span>	
					<span class="left" onclick="afficherCommentaire('<%= cpt %>', '<%= rs.getString("idPost") %>')">
						<i class="icon-plus"></i>(<%= rs.getString("nbCom") %>)						
					</span>
					<div class="clear"></div>
					<div id="idCommentaireAdd<%= cpt %>" style="display: none;">
						<form action="../SCommentaire" method="POST">
							<input type="hidden" name="urlReturn" value="jsp/pipeline.jsp" />
							<input type="hidden" name="idPost" value="<%= rs.getString("idPost") %>" />
							<input type="text" class="champCommentaire" name="commentaire" />
						</form>
					</div>
					<div id="idCommentaireList<%= cpt %>" style="display: none;">
					<!-- REMPLIE EN AJAX -->
					</div>
				</div>	
			</fieldset><br />
<%			cpt++;
		}
	}
%>
<jsp:include page="footer.jsp"/>