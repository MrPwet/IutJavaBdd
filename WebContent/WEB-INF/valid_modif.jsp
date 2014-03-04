<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/header.jsp" %>

<c:url var="urlArt" value="/Article">
</c:url>

<p>${messageInfo}</p>
<p><a href="${urlArt}">Retour à la page Article</a>

<%@ include file="/WEB-INF/footer.jsp" %>