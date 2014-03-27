<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/header.jsp"%>

<c:url var="urlConnection" value="/Connection"></c:url>

<form class="pure-form pure-form-aligned" action="${urlConnection }" method="POST">
	<fieldset>
			<div class="pure-control-group">
				<label for="username">Username</label>
				<input type="text" name="username" placeholder="Username"/>
			</div>
		
			<div class="pure-control-group">
				<label for="pass">Password</label>
				<input type="password" name="pass" placeholder="Password"/>
			</div>
			
			<button type="submit" class="pure-button pure-button-primary">Se Connecter</button>
	</fieldset>
</form>

<p> Bonjour ${sessionScope.userSigned }</p>

<%@ include file="/WEB-INF/footer.jsp"%>