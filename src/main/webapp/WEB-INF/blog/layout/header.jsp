<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal" var="principal"/>
</sec:authorize>

<!DOCTYPE html>
<html lang="en">
<head>
	<title>Blog</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="/webjars/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<script src="/webjars/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="/webjars/jquery/jquery.min.js"></script>
	<!-- summernote css/js -->
	<link href="/webjars/summernote/summernote-bs4.css" rel="stylesheet">
	<script src="/webjars/summernote/summernote-bs4.min.js"></script>
</head>
<body>
<nav class="navbar navbar-expand-sm navbar-dark bg-dark">
	<div class="container-fluid">
		<a class="navbar-brand" href="/">Main</a>
		<button type="button" class="navbar-toggler" 
			data-bs-toggle="collapse" data-bs-target="#mynavbar">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="mynavbar">
			<c:choose>
				<c:when test="${principal == null}">
				<ul class="navbar-nav me-auto">
					<li class="nav-item"><a class="nav-link" href="/login">Login</a></li>
					<li class="nav-item"><a class="nav-link" href="/signup">Signup</a></li>
				</ul>
				</c:when>
				<c:otherwise>
				<ul class="navbar-nav me-auto">
					<li class="nav-item"><a class="nav-link" href="/post/postup">Postup</a></li>
					<li class="nav-item"><a class="nav-link" href="/user/detail">User detail</a></li>
					<li class="nav-item"><a class="nav-link" href="/logout">Logout</a></li>
				</ul>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</nav>
</body>
</html>