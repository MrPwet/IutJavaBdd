<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/header.jsp" %>

	<p>${messageInfo }</p>

	<table class="pure-table pure-table-horizontal">
		<caption>Historique des commandes</caption>
		<thead>
			<tr>
				<td>N° Commande</td>
				<td>Prix total ttc</td>
				<td>Date de la commande</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="commande" items="${lstCommande}">
				<c:url var="urlCommande" value="/Details">
					<c:param name="idCommande" value="${commande.idCommande}"></c:param>
				</c:url>
				
				<tr>
					<td><a href="${urlCommande }"><c:out value="${commande.idCommande }"></c:out></a></td>
					<td><c:out value="${commande.prixTotal }"></c:out>€</td>
					<td><c:out value="${commande.dateCommande}"></c:out></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

<%@ include file="/WEB-INF/footer.jsp" %>