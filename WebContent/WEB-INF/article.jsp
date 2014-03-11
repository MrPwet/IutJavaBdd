<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/header.jsp" %>
	<p><c:out value="${info}"></c:out></p>
	<table class="pure-table pure-table-horizontal">
		<caption>Liste des articles en vente</caption>
		<thead>
			<tr>
				<td>Nom</td>
				<td>Prix</td>
				<td>Disponibilité</td>
				<td>Catégorie</td>
				<td>Action</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="article" items="${lst}">
				<c:url var="urlDel" value="/Article">
					<c:param name="action" value="delete"></c:param>
					<c:param name="id" value="${article.idArticle}"></c:param>
				</c:url>
				<c:url var="urlChg" value="/ChangeArticle">
					<c:param name="id" value="${article.idArticle}"></c:param>
				</c:url>
				<tr>
					<td><c:out value="${article.nomArticle }"></c:out></td>
					<td><c:out value="${article.prixArticle }"></c:out></td>
					<td><c:out value="${article.disponibiliteArticle }"></c:out></td>
					<td><c:out value="${article.categorieArticle }"></c:out></td>
					<td><a href="<c:out value="${urlChg}"></c:out>">Modifier</a>
					<a href="<c:out value="${urlDel}"></c:out>">Supprimer</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<p>Ajouter un article</p>
	<form method="GET" action="/IutJavaBdd/Article" class="pure-form pure-form-aligned">
		<fieldset>
			<div>
				<input type="hidden" name="action" value="add">
			</div>
		
			<div class="pure-control-group">
				<label for="nom">Nom</label>
				<input type="text" name="nom" placeholder="Nom"/>
			</div>
			
			<div class="pure-control-group">
				<label for="prix">Prix</label>
				<input type="text" name="prix" placeholder="prix"/>
			</div>
			
			<div class="pure-control-group">
				<label for="dispo">Disponibilité</label>
				<input type="text" name="dispo" placeholder="Disponibilité"/>
			</div>
			
			<div class="pure-control-group">
				<label for="cat">Catégorie</label>
				<input type="text" name="cat" placeholder="Catégorie"/>
			</div>
			
			<div class="pure-controls">
				<button type="submit" class="pure-button pure-button-primary">Ajouter</button>
			</div>	
		</fieldset>
	</form>
<%@ include file="/WEB-INF/footer.jsp" %>