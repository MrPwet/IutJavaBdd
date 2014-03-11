<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="http://yui.yahooapis.com/pure/0.4.2/pure-min.css">
<link rel="stylesheet" href="assets/base.css">
<title>${title}</title>
</head>
<body>

<c:url var="articleUrl" value="/Article"></c:url>
<c:url var="article2Url" value="/Article2"></c:url>
<c:url var="panierUrl" value="/Panier"></c:url>

<header>
	<h1>Java Iut BDD</h1>
	<nav>
		<div class="pure-menu pure-menu-open pure-menu-horizontal">
			<ul>
				<li><a href="${articleUrl }">Article</a></li>
				<li><a href="${article2Url }">Article2</a></li>
				<li><a href="${panierUrl }">Panier</a></li>
			</ul>
		</div>
	</nav>
</header>
