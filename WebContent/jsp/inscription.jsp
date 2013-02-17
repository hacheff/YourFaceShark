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
			$(document).ready(function() {
			 	$("#ddn").datepicker();
			 	console.log("ok");
			});
			function validMail(email) { 
			    var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
			    return re.test(email);
			}
			function validDate(date){
				var re = new RegExp("^[0-9]{2}[/]{1}[0-9]{2}[/]{1}[0-9]{4}$","g");
				return re.test(date);
			}
			function validFormulaire(){
				var b = true;
				var text = "<div class='alert alert-error'>";
				
				if(document.getElementById('nom').value == ""){
					text += "Le nom doit être renseigné <br />";
					b = false;
				}
				if(document.getElementById('prenom').value == ""){
					text += "Le prénom doit être renseigné <br />";
					b = false;
				}
				if(!validMail(document.getElementById('mail').value)){
					text += "L'email n'est pas valide <br />";
					b = false;
				}
				if(!validDate(document.getElementById('ddn').value)){
					text += "La date de naissance n'est pas valide <br />";
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
		<form method="post" action="../SInscription" class="inscription" onsubmit="return validFormulaire();">
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