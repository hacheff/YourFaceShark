<%@page import="java.util.Calendar"%>
		<div class="clear"></div>
		<div class="navbar navbar-fixed-bottom hidden-phone">
			<div class="footer" id="footer"><br/>			
				<span class="copyright">
					©Copyright <% out.print(Calendar.getInstance().get(Calendar.YEAR)); %> YourFaceShark
				</span>
				<img alt="logo" src="../img/logoFooter.png" id="logoFooter" class="logoFooter" />
			</div>
		</div>
	</body>
</html>