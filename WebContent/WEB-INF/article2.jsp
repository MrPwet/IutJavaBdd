<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/header.jsp" %>
	<table class="pure-table pure-table-horizontal">
		<caption>Liste des articles en ventes</caption>
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
				<tr>
					<td><c:out value="${article.nomArticle }"></c:out></td>
					<td><c:out value="${article.prixArticle }"></c:out></td>
					<td><c:out value="${article.disponibiliteArticle }"></c:out></td>
					<td><c:out value="${article.categorieArticle }"></c:out></td>
					<td>
						<form method="GET" action="/IutJavaBdd/Article2">
							<input type="hidden" name="id" value="${article.idArticle }"/>
							<input type="number" name="qte" min="0" max="${article.disponibiliteArticle}"/>
							<input type="submit" value="Ajouter au panier"/>
						</form>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
<%@ include file="/WEB-INF/footer.jsp" %>