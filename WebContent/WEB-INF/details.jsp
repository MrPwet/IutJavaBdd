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
			</tr>
		</thead>
		<tbody>
			<c:forEach var="fp" items="${lstArticle}">
				<tr>
					<td><c:out value="${fp.article.nomArticle}"></c:out></td>
					<td><c:out value="${fp.article.prixArticle}"></c:out>€</td>
					<td><c:out value="${fp.quantite }"></c:out></td>
					<td><c:out value="${fp.prixTotal }"></c:out>€</td>
					<td><c:out value="${fp.prixTotalTTC }"></c:out>€</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
<%@ include file="/WEB-INF/footer.jsp" %>
