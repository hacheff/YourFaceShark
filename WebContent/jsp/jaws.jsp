<%@ page import="social.User"%>
<%	
	User user = (User) request.getSession().getAttribute("user");
	if(user != null){
		out.println(user.getId());
		out.println(user.getNom());
		out.println(user.getPrenom());
		out.println(user.getSexe());
		out.println(user.getMail());
		out.println(user.getDate());
		out.println(user.getMdp());	
	}
	else{
		out.println("FAIL");	
	}
%>