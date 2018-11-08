<%@ include file="../common/header.jspf" %>
<%@ include file="../common/navigation.jspf" %>

		<div class="container">
		<form action="/login.do" method="post">
			<p>
				<font class="text-danger" color="red"> ${error} </font>
			</p>
			<fieldset class="form-group">
				<label>Name:</label> <input class="form-control" type="text" name="name" />
			</fieldset>  
			<fieldset class="form-group">
				<label>Password:</label> <input class="form-control" type="password" name="password">  
			</fieldset>
			<input class="btn btn-success" type="submit" value="Login"/>
		</form>
	</div>
	
<%@ include file="../common/footer.jspf" %>
