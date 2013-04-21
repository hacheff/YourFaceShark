<%@ page import="social.User"%>
<%@ page import="bdd.Actualite"%>
<%@ page import="bdd.Profil"%>
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
</script>
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
					<span class="left" onclick="displayCommentaire('<%= cpt %>');" >
						<img src="../img/commentaires.png" alt="commentaires" /><br />					
					</span>	
					<span class="left" onclick="afficherCommentaire('<%= cpt %>', '<%= rs.getString("idPost") %>')">
						<i class="icon-plus"></i>(<%= rs.getString("nbCom") %>)
					</span><div class="clear"></div>
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