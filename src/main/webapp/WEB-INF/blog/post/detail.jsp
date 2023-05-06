<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %>

<div class="container border mt-4">
	<div class="my-4">
		<h3>${post.title}</h3>
	</div>
	<div class="my-4">
		<div>${post.content}</div>
	</div>
	<div class="my-4">
		Post number: <span id="id"><i>${post.id}</i></span><br>
		Writer: ${post.user.id}<br>
	</div>
	<hr>
	<div class="mb-3">
		<button class="btn btn-secondary" onclick="history.back()">Back</button>
		<c:if test="${post.user.id == sessionScope.loginUser.id}">
		<div class="float-end">
			<a href="/post/edit/${post.id}" class="btn btn-warning">Edit</a>
			<button id="btn-delete" class="btn btn-danger">Delete</button>
		</div>
		</c:if>
	</div>
</div>

<%@ include file="../layout/footer.jsp" %>

<script src="/js/post.js"></script>