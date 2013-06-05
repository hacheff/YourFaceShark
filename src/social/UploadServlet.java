package social;

import java.io.*;
import java.util.*;
import java.util.regex.*;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import bdd.Image;
import bdd.Profil;

/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (!isMultipart) {
			System.out.println("File Not Uploaded");
		} else {
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			List items = null;
			try {
				items = upload.parseRequest(request);
			} catch (FileUploadException e) {
				e.printStackTrace();
			}
			Iterator itr = items.iterator();
			while (itr.hasNext()) {
				FileItem item = (FileItem) itr.next();
				if (!item.isFormField()) {
					try {
						String itemName = item.getName();
						Random generator = new Random();
						int r = Math.abs(generator.nextInt());
						String reg = "[.*]";
						String replacingtext = "";
						Pattern pattern = Pattern.compile(reg);
						Matcher matcher = pattern.matcher(itemName);
						StringBuffer buffer = new StringBuffer();
						while (matcher.find()) {
							matcher.appendReplacement(buffer, replacingtext);
						}
						int IndexOf = itemName.indexOf(".");
						String domainName = itemName.substring(IndexOf);
						String finalimage = buffer.toString() + "_" + r + domainName;
						String path = request.getServletContext().getRealPath("");
						int index = path.lastIndexOf("\\");
						path = path.substring(0, index) + "\\images\\";
						File savedFile = new File(path + finalimage);
						item.write(savedFile);
						System.out.println(savedFile.toString());
						
						//insere la photo dans la base
						User user = (User)request.getSession().getAttribute("user");
						boolean res = Image.insertPhoto(savedFile.toString(), user.getId());
						//fait le lien entre la photo ins�r�e et la colonne de la tabl user
						if(res){
							Profil.setPhotoProfil(user.getId());
							response.sendRedirect("jsp/jaws.jsp");
						}else{
							System.out.println("faaaiiiiiillll");
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

}
