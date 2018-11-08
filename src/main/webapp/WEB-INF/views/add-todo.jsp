<%@ include file="../common/header.jspf" %>
<%@ include file="../common/navigation.jspf" %>

		<div class="container">
			
			<H2>What would you like to-do?</H2> 
			
			<form method="POST" action="add-todo.do">
				<fieldset class="form-group">
					<label>Category:</label> <input name="category" type="text" class="form-control"/> </BR>
				</fieldset>	
				<fieldset class="form-group">
					<label>Description:</label> <input name="todo" type="text" class="form-control" /> </BR>
				</fieldset>
				<input name="add" type="submit" value="Add" class="btn btn-success">
			</form>
		</div>
		
<%@ include file="../common/footer.jspf" %>