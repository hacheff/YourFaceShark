<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>YourFaceShark</title>
		<script type="text/javascript" src="../js/bootstrap.js"></script>
		<script type="text/javascript" src="../js/bootstrap.min.js"></script>
		<link href="../css/style.css"	title="Style" rel="stylesheet" type="text/css" />
	</head>
	<body>
		<script type="text/javascript">
			function validFormulaire(){
				var b = true;
				var text = "";
				
				if(document.getElementById('nom').value == ""){
					text += "Le nom doit être renseigné <br />";
					b = false;
				}
				
				document.getElementById('message').innerHTML = text;
				alert(text);
				return b;
			}
		</script>
		<form method="post" action="" class="inscription" onsubmit="return validFormulaire();">
			<span id="message"></span>
			<label for="nom" class="span2">Nom: </label><input type="text" id="nom" name="nom" class="span3" /><br />
			<label for="prenom" class="span2">Prénom: </label><input type="text" id="prenom" name="prenom" class="span3" /><br />
			<label for="mail" class="span2">Mail: </label><input type="text" id="mail" name="mail" class="span3" /><br />
			<label for="ddn" class="span2">Date de  naissance: </label><input id="ddn" type="text" name="ddn" class="span3" /><br />
			<label for="mdp" class="span2">Mot de passe: </label><input type="password" id="mdp" name="mdp" class="span3" /><br />
			<span class="btn-group">
				<input class="btn btn-info" type="submit" value="Valider" />
				<input class="btn btn-info" type="reset" value="Reinitialiser" />
			</span>			
		</form>
	</body>
</html>