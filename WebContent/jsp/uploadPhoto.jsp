<jsp:include page="header.jsp"/>
	 <form action="../UploadServlet" method="post" enctype="multipart/form-data" name="uploadForm" id="uploadForm" class="inscription"><br><br>
		<label for="file">Emplacement de l'image : </label><input type="file" name="file" id="file">
		<input type="submit" value="Modifier" class="btn btn-info">
	</form>
<jsp:include page="footer.jsp"/>