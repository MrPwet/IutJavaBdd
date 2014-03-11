<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/header.jsp" %>

	<table class="pure-table pure-table-horizontal">
		<caption>Liste des articles dans le panier</caption>
		<thead>
			<tr>
				<td>Nom</td>
				<td>Prix unitaire</td>
				<td>Quantité</td>
				<td>Prix total</td>
				<td>Action</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="article" items="${lstArticle}">
				<c:url var="delete" value="/Panier">
					<c:param name="user" value="user1"></c:param>
					<c:param name="id" value="${article.key.idArticle }"></c:param>
				</c:url>
				<tr>
					<td><c:out value="${article.key.nomArticle}"></c:out></td>
					<td><c:out value="${article.key.prixArticle}"></c:out></td>
					<td><c:out value="${article.value }"></c:out></td>
					<td></td>
					<td><a href="${delete }">Supprimer</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

<%@ include file="/WEB-INF/footer.jsp" %>