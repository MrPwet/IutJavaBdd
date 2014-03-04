<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/header.jsp" %>

<form method="POST" action="/IutJavaBdd/ChangeArticle" class="pure-form pure-form-aligned">
	<fieldset>
		
		<div class="pure-control-group">
			<input type="hidden" name="id" value="${article.idArticle}">
		</div>
		
		<div class="pure-control-group">
			<label for="nom">Nom</label>
			<input type="text" name="nom" value="${article.nomArticle}"/>
		</div>
			
		<div class="pure-control-group">
			<label for="prix">Prix</label>
			<input type="text" name="prix" value="${article.prixArticle}"/>
		</div>
			
		<div class="pure-control-group">
			<label for="dispo">Disponibilité</label>
			<input type="text" name="dispo" value="${article.disponibiliteArticle}"/>
		</div>
			
		<div class="pure-control-group">
			<label for="cat">Catégorie</label>
			<input type="text" name="cat" value="${article.categorieArticle}"/>
		</div>
			
		<div class="pure-controls">
			<button type="submit" class="pure-button pure-button-primary">Modifier</button>
		</div>	
	</fieldset>
</form>

<%@ include file="/WEB-INF/footer.jsp" %>