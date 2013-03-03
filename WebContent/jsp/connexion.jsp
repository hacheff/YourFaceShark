<%@page import="bdd.Connexion"%>
<%@page import="connexion.SConnexion"%>
<%@page import="social.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>YourFaceShark</title>
		<script type="text/javascript" src="../js/jquery/jquery-1.9.1.js"></script>
		<script type="text/javascript" src="../js/jquery/jquery-ui-1.10.1.custom.js"></script>
		
		<link href="../css/jquery/jquery-ui-1.10.1.custom.css"	title="Style" rel="stylesheet" type="text/css" />
		<link href="../css/style.css"	title="Style" rel="stylesheet" type="text/css" />
	</head>
	<body>
		<script type="text/javascript">
			function validFormulaire(){
				var b = true;
				var text = "<div class='alert alert-error'>";

				if(document.getElementById('mail').value == ""){
					text += "Le login doit �tre renseign� <br />";
					b = false;
				}			
				if(document.getElementById('mdp').value == ""){
					text += "Votre mot de passe ne peut �tre vide <br />";
					b = false;
				}
				
				document.getElementById('message').innerHTML = text;
				text += "</div>";
				return b;
			}
		</script>
		<%
			// CONNEXION AUTOMATIQUE SI COOKIE
			// Faire une fonction a part �a serrais mieux
			Cookie[] cookies = request.getCookies();
			if(cookies != null){
				for(int i = 0; i < cookies.length; i++){
					if(SConnexion.COOKIE_NAME.equals(cookies[i].getName())){
						if(Connexion.connectUser(Integer.parseInt(cookies[i].getValue()), request.getSession())){
							User user = (User) request.getSession().getAttribute("user");
							if(user != null){
								response.sendRedirect("jaws.jsp");							
							}		
						}
						else{
							request.getSession().setAttribute("erreurCatch", "Erreur Connexion: probleme BDD <br/> connexion.jsp - Connexion COOKIE");
							response.sendRedirect("error.jsp");
						}
					}
				}
			}
		
		
		%>
		<form method="post" action="../SConnexion" class="inscription" onsubmit="return validFormulaire();">
			<span id="message"><%
				if(request.getSession().getAttribute("erreur") != null){
					out.println(request.getSession().getAttribute("erreur"));
					request.removeAttribute("erreur");
				}
			%></span>
			<label for="login" class="span2">E-Mail: </label><input type="text" id="mail" name="mail" class="span3" /><br />
			<label for="mdp" class="span2">Mot de passe: </label><input type="password" id="mdp" name="mdp" class="span3" /><br />
			<span class="span4"><input type="checkbox" name="cookie" value="true" /> Connexion automatique</span><br /><br />
			<span class="btn-group">
				<input class="btn btn-info" type="submit" value="Valider" />
				<input class="btn btn-info" type="reset" value="Reinitialiser" />
			</span><br /><br />
			<a href="inscription.jsp">Inscrivez-vous</a>		
		</form>		
	</body>
</html>