<%@ include file="../common/header.jspf" %>
<%@ include file="../common/navigation.jspf" %>
		
		<div class="container">
			<H1>Hi, ${name}!</H1>
			
			Your Todo's are:
			
			<table class="table table-hover">
				<caption></caption>
				<thead>
					<th>Description</th>
					<th>Category</th>
					<th>Action</th>
				</thead>
				<tbody>
					<form action="delete-todo.do" method="POST">
						<c:forEach items="${todos}" var="todo">
							<tr>
								<td>${todo.name}</td>
								<td>${todo.category}</td>
								<td><Button class="btn btn-danger" type="input" value="${todo.id}" name="todoId">Remove</Button></td>
							</tr>
						</c:forEach>
					</form>
				</tbody>
			</table>
			
			<p>
				<font color="red">${error}</font>
			</p>
			<a class="btn btn-success" href="add-todo.do">Add new todo</a>
		</div>
		
<%@ include file="../common/footer.jspf" %>