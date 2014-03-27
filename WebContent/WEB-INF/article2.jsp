<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/header.jsp" %>
	<c:url var="nom" value="/Article2">
		<c:param name="critere" value="nomArticle"></c:param>
	</c:url>
	<c:url var="prix" value="/Article2">
		<c:param name="critere" value="prixArticle"></c:param>
	</c:url>
	<c:url var="categorie" value="/Article2">
		<c:param name="critere" value="categorieArticle"></c:param>
	</c:url>
	<table class="pure-table pure-table-horizontal">
		<caption>Liste des articles en vente</caption>
		<thead>
			<tr>
				<td><a href="${nom }">Nom</a></td>
				<td><a href="${prix }">Prix</a></td>
				<td>Disponibilité</td>
				<td><a href="${categorie }">Catégorie</a></td>
				<td>Action</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="article" items="${lst}">
				<tr>
					<td><c:out value="${article.nomArticle }"></c:out></td>
					<td><c:out value="${article.prixArticle }"></c:out>€</td>
					<td><c:out value="${article.disponibiliteArticle }"></c:out></td>
					<td><c:out value="${article.categorieArticle }"></c:out></td>
					<td>
						<form method="GET" action="/IutJavaBdd/Article2">
							<input type="hidden" name="id" value="${article.idArticle }"/>
							<input type="number" name="qte" placeholder="qte" min="0" max="${article.disponibiliteArticle}"/>
							<input type="submit" value="Ajouter au panier"/>
						</form>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
<%@ include file="/WEB-INF/footer.jsp" %>