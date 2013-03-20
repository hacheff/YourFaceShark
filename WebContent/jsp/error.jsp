<jsp:include page="header.jsp"/>
	<div class="inscription">
	<%
	if(request.getSession().getAttribute("erreurCatch") != null){
		out.println(request.getSession().getAttribute("erreurCatch"));
		request.removeAttribute("erreurCatch");
	}else{
		response.sendRedirect("jsp/connexion.jsp");
	}
	%>
	</div>
<jsp:include page="footer.jsp"/>