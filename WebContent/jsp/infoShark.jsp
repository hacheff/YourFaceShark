<%@ page import="social.User"%>
<%@ page import="bdd.Profil" %>
<%@ page import="java.util.List;" %>

<jsp:include page="header.jsp"/>
	<script type="text/javascript">
			$(document).ready(function() {
			 	$("#ddn").datepicker();
			});
	</script>
	<form method="post" action="../SInfoProfil" class="infoSharkModif">
	<%
		User user = (User) request.getSession().getAttribute("user");
		if(user != null){
			out.println("<label for=\"nom\" class=\"span2\">Nom :</label><input type=\"text\" id=\"nom\" name=\"nom\" class=\"span3\" value=" + user.getNom() +" /><br/>");
			out.println("<label for=\"prenom\" class=\"span2\">Pr&eacute;nom :</label><input type=\"text\" id=\"prenom\" name=\"prenom\" class=\"span3\" value=" + user.getPrenom() +" /><br/>");
			out.println("<label for=\"civ\" class=\"span2\">Civilit&eacute; :</label><select class=\"span3\" name=\"civ\" id=\"civ\">");
			if(user.getSexe() == 'm'){
				out.println("<option value=\"f\">Femme</option><option value=\"m\" selected =\"selected\">Homme</option></select><br />");
			} else{
				out.println("<option value=\"f\" selected=\"selected\">Femme</option><option value=\"m\">Homme</option></select><br />");
			}
			out.println("<label for=\"ddn\" class=\"span2\">Date de naissance :</label><input id=\"ddn\" type=\"text\" name=\"ddn\" class=\"span3\" value=" + Profil.reverseDate(user.getDate().toString()) +" /><br/>");
			out.println("<label for=\"mail\" class=\"span2\">Mail :</label><input type=\"text\" id=\"mail\" name=\"mail\" class=\"span3\" value=" + user.getMail() +" /><br/>");
			out.println("<label for=\"mdp\" class=\"span2\">Mot de passe :</label><input type=\"password\" id=\"mdp\" name=\"mdp\" class=\"span3\" value=" + user.getMdp() +" /><br/>");
		
		%>
			<input type="submit" class="btn btn-info" value="Modifier" style="float:center"/><a style="float:right" href="uploadPhoto.jsp">Modifiez votre photo de profil</a>
		</form>
		<div class="infoSharkModif">
		<%
			List<User> list = Profil.selectShark(user.getId(), 0);
			for(User u : list){
				if(u != null){
		%>
					<div class="listUsers">
						<div class="span1 hiddenPhone"></div>
						<div class="left">
							<a href="../SSocial?param=remove&id=<%= u.getId() %>" class="btn btn-danger"><i class="icon-remove icon-white"></i></a>
						</div>
						<a href="<%= "jaws.jsp?id="+u.getId() %>"><%= u.getPrenom() + " " + u.getNom()%></a>					
					</div><br />
		<%
				}
			}
		%>
		</div>
		<%
			
		}
	%>
<jsp:include page="footer.jsp"/>