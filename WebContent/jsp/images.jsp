<%@ page import = "java.io.*" %>
<%@ page import="bdd.Image"%>

<%
int idPhoto ;

if ( request.getParameter("idPhoto") != null )
{

  	idPhoto = Integer.parseInt(request.getParameter("idPhoto"));

	try
	{ 		
		// get the image FROM the DATABASE
		byte[] imgData = Image.getBlob(idPhoto);   
		// display the image
		response.setContentType("image/gif");
		OutputStream o = response.getOutputStream();
		o.write(imgData);
		o.flush(); 
     	o.close();
	}
	catch (Exception e)
	{
	  e.printStackTrace();
	  throw e;
	}
}
%>