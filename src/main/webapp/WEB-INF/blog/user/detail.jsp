<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %>

<div class="container mt-3">
	<form>
		<input type="hidden" id="num" value="${principal.user.num}">
		<div class="mt-3 mb-3">
			<label for="username">Username:</label>
			<input type="text" id="username" class="form-control" name="username" value="${principal.user.username}" placeholder="Enter username">
		</div>
		<c:if test="${principal.user.oauth == 'BLOG'}">
		<div class="mb-3">
			<label for="password">Password:</label>
			<input type="password" id="password" class="form-control" name="password" placeholder="Enter password">
		</div>
		</c:if>
		<div class="mb-3">
			<label for="email">Email:</label>
			<input type="text" id="email" class="form-control" name="email" value="${principal.user.email}" placeholder="Enter email">
		</div>
	</form>
	<button id="btn-update" class="btn btn-secondary">Update</button>
</div>

<script src="/js/user.js"></script>

<%@ include file="../layout/footer.jsp" %>
