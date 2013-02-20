<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>YourFaceShark</title>
		<script type="text/javascript" src="../js/bootstrap.js"></script>
		<script type="text/javascript" src="../js/bootstrap.min.js"></script>
		<link href="../css/bootstrap.css"	title="Style" rel="stylesheet" type="text/css" />
		<link href="../css/bootstrap-responsive.css"	title="Style" rel="stylesheet" type="text/css" />
		<link href="../css/style.css"	title="Style" rel="stylesheet" type="text/css" />
		
		<link rel="shortcut icon" href="../img/logo.png">		
	</head>
	<body>

		<form class="form-search" method="post">
		  <input type="text" class="input-medium search-query">
		  <button type="submit" class="btn btn-info">Rechercher</button>
		</form>
		<form class="form-inline" action="../SConnexion" method="post">
		  <input type="text" class="input-small search-query" placeholder="Email">
		  <input type="password" class="input-small search-query" placeholder="Password">
		  <button type="submit" class="btn btn-info"><i class="icon-ok-white"></i> Connexion</button>
		</form>
		
	</body>
</html>