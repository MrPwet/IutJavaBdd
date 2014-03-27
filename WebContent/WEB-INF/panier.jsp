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
				<td>Prix total HTC</td>
				<td>Prix total TTC</td>
				<td>Action</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="fp" items="${lstArticle}">
				<c:url var="delete" value="/Panier">
					<c:param name="user" value="user1"></c:param>
					<c:param name="id" value="${fp.article.idArticle}"></c:param>
				</c:url>
				<tr>
					<td><c:out value="${fp.article.nomArticle}"></c:out></td>
					<td><c:out value="${fp.article.prixArticle}"></c:out>€</td>
					<td><c:out value="${fp.quantite }"></c:out></td>
					<td><c:out value="${fp.prixTotal }"></c:out>€</td>
					<td><c:out value="${fp.prixTotalTTC }"></c:out>€</td>
					<td><a href="${delete }">Supprimer</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<p>Prix total HTC : ${totalHTC}€</p>
	<p>Prix total TTC : ${totalTTC }€</p>

<%@ include file="/WEB-INF/footer.jsp" %>