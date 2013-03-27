package social;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.regex.*;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		System.out.println("request: " + request);
		if (!isMultipart) {
			System.out.println("File Not Uploaded");
		} else {
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			List items = null;
			try {
				items = upload.parseRequest(request);
				System.out.println("items: " + items);
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
						System.out.println("Text before replacing is:-" + itemName);
						Pattern pattern = Pattern.compile(reg);
						Matcher matcher = pattern.matcher(itemName);
						StringBuffer buffer = new StringBuffer();
						while (matcher.find()) {
							matcher.appendReplacement(buffer, replacingtext);
						}
						int IndexOf = itemName.indexOf(".");
						String domainName = itemName.substring(IndexOf);
						System.out.println("domainName: " + domainName);
						String finalimage = buffer.toString() + "_" + r + domainName;
						System.out.println("Final Image===" + finalimage);
						File savedFile = new File("C:/apache-tomcat-6.0.16/webapps/example/" + "images\\" + finalimage);
						item.write(savedFile);
						out.println("<html>");
						out.println("<body>");
						out.println("<table><tr><td>");
						out.println("<img src=images/" + finalimage + ">");
						out.println("</td></tr></table>");
						Connection conn = null;
						String url = "jdbc:mysql://localhost:3306/";
						String dbName = "thundercatz";
						String driver = "com.mysql.jdbc.Driver";
						String username = "root";
						String userPassword = "";
						String strQuery = null;
						String strQuery1 = null;
						String imgLen = "";
						try {
							System.out.println("itemName::::: " + itemName);
							Class.forName(driver).newInstance();
							conn = DriverManager.getConnection(url + dbName, username, userPassword);
							Statement st = conn.createStatement();
							strQuery = "insert into testimage values image='" + finalimage + "'";
							int rs = st.executeUpdate(strQuery);
							System.out.println("Query Executed Successfully++++++++++++++");
							out.println("image inserted successfully");
							out.println("</body>");
							out.println("</html>");
						} catch (Exception e) {
							System.out.println(e.getMessage());
						} finally {
							conn.close();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

}
