<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal" var="principal" />
</sec:authorize>
<!DOCTYPE html>
<html lang="en">
<head>
<style>
.label {
	color: white;
	padding: 8px;
	font-family: Arial;
}

.success {
	background-color: #4CAF50;
} /* Green */
.info {
	background-color: #2196F3;
} /* Blue */
.warning {
	background-color: #ff9800;
} /* Orange */
.danger {
	background-color: #f44336;
} /* Red */
.other {
	background-color: #e7e7e7;
	color: black;
} /* Gray */
</style>
<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="_csrf" content="${_csrf.token}">
<meta name="_csrf_header" content="${_csrf.headerName}">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>
</head>
<body>

		<nav class="navbar navbar-expand-md bg-dark navbar-dark">
			<a class="navbar-brand" href="${pageContext.request.contextPath}">상희의 블로그</a>
			<ul class="navbar-nav">
			<li style="color:white;">${principal.user.email} </li>
			<li style="color:white;">(${principal.user.name} 님) </li>
			</ul>
			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="collapsibleNavbar">
				<c:choose>
					<c:when test="${empty principal}">
						<ul class="navbar-nav">
							<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/login">로그인</a></li>
						</ul>
					</c:when>
					<c:otherwise>
						<ul class="navbar-nav">
							<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/logout">로그아웃</a></li>
						</ul>
					</c:otherwise>
				</c:choose>
			</div>
		</nav>
		<br>