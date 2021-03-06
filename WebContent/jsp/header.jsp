<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="social.User"%>
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
		
		<script type="text/javascript" src="../js/jquery/jquery-1.9.1.js"></script>
		<script type="text/javascript" src="../js/jquery/jquery-ui-1.10.1.custom.js"></script>
		
		<link href="../css/jquery/jquery-ui-1.10.1.custom.css"	title="Style" rel="stylesheet" type="text/css" />		
		<link rel="shortcut icon" href="../img/logo.png">		
	</head>
	<body>
	<% 	User u=(User) request.getSession().getAttribute("user");
			if(u==null){%>
		<div class="navbar navbar-inverse navbar-fixed-top header">
		  <div class="navbar-inner"><br/>
				<form class="form-search form centrer" method="post">
				  <input type="text" class="input-medium search-query">
				  <button type="submit" class="btn btn-info">Rechercher</button>
				</form>
				<form class="form-inline form" action="../SConnexion" method="post">
				  <input type="text" class="input-medium search-query" placeholder="Email" name="mail">
				  <input type="password" class="input-medium search-query" placeholder="Password" name="mdp">
				  <button type="submit" class="btn btn-info"><i class="icon-ok icon-white"></i> Connexion</button>
				</form>
				<img alt="logo" src="../img/logo.png" class="right logo" /><br/><br/>
		 </div>
		</div>
			<% }else{ %>
		<div class="navbar navbar-inverse navbar-fixed-top header">
		  <div class="navbar-inner"><br/>
				<div class="infos">
				<a href="../SDeconnexion"><i class="icon-off icon-white deconnexion"></i></a>
				<a href="jaws.jsp" class="aHeader"><% out.println(" "+u.getPrenom()+" "+u.getNom()); %></a>
				</div>
				<form class="form-search form centrer" method="post" action="../SRecherche">
				  <input type="text" class="input-medium search-query" name="recherche">
				  <button type="submit" class="btn btn-info">Rechercher</button>
				</form>
				<a href="pipeline.jsp"><img alt="logo" src="../img/logo.png" class="right logo" /></a><br/>
		 </div> 
		</div>
			<% } %>
		<div class="visible-desktop getDown"></div>