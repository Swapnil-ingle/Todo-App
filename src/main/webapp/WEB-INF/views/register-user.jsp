<%@ include file="../common/header.jspf" %>
<%@ include file="../common/navigation.jspf" %>

		<div class="container">
			
			<H2>Enter your details:</H2> 
			
			<form method="POST" action="register-user.do">
				<fieldset class="form-group">
					<label>Name:</label> <input name="name" type="text" class="form-control"/> </BR>
				</fieldset>	
				<fieldset class="form-group">
					<label>Password:</label> <input name="password" type="password" class="form-control" /> </BR>
				</fieldset>
				<input name="add" type="submit" value="Add" class="btn btn-success">
			</form>
		</div>
		
<%@ include file="../common/footer.jspf" %>