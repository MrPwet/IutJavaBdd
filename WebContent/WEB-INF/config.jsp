<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/header.jsp" %>

<p>${messageInfo }</p>

<form action="/IutJavaBdd/Config" method="post" class="pure-form pure-form-aligned">
	<fieldset>
		<div class="pure-control-group">
			<label for="url">URL</label>
			<input type="text" name="url" placeholder="adresse:port/nomBase"/>
		</div>
		
		<div class="pure-control-group">
			<label for="userConf">Nom d'utilisateur</label>
			<input type="text" name="userConf"/>
		</div>
		
		<div class="pure-control-group">
			<label for="passConf">Mot de passe</label>
			<input type="password" name="passConf"/>
		</div>
		
		<button>Sauvegarder</button>
	</fieldset>
</form>

<%@ include file="/WEB-INF/footer.jsp" %>