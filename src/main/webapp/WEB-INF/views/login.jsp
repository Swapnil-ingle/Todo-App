<%@ include file="../common/header.jspf" %>

<body>
		<nav class="navbar navbar-default">
			<a href="/list-todo.do" class="navbar-brand">Swap's</a>
			
			<ul class="nav navbar-nav">
				<li> <a href="list-todo.do">Home</a></li>
				<li> <a href="add-todo.do">Add Todo</a></li>
				<li> <a href="https://www.github.com/Swapnil-ingle">My Github</a></li>
			</ul>
			
			<ul class="nav navbar-nav navbar-right">
				<li><a href="login.do">Login</a></li>
			</ul>
		</nav>

		<div class="container">
		<form action="login.do" method="post">
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
