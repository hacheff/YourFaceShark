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
					text += "Le login doit être renseigné <br />";
					b = false;
				}			
				if(document.getElementById('mdp').value == ""){
					text += "Votre mot de passe ne peut être vide <br />";
					b = false;
				}
				
				document.getElementById('message').innerHTML = text;
				text += "</div>";
				return b;
			}
		</script>
		<form method="post" action="../SConnexion" class="inscription" onsubmit="return validFormulaire();">
			<span id="message"></span>
			<label for="login" class="span2">E-Mail: </label><input type="text" id="mail" name="mail" class="span3" /><br />
			<label for="mdp" class="span2">Mot de passe: </label><input type="password" id="mdp" name="mdp" class="span3" /><br />
			<span class="btn-group">
				<input class="btn btn-info" type="submit" value="Valider" />
				<input class="btn btn-info" type="reset" value="Reinitialiser" />
			</span><br /><br />
			<a href="inscription.jsp">Inscrivez-vous</a>		
		</form>		
	</body>
</html>
