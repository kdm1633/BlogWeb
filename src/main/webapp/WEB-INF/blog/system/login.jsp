<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<script src="/js/login.js"></script>

<div class="container mt-3">
	<form>
		<div class="mb-3 mt-3">
			<label for="id">Id:</label>
			<input type="text" class="form-control" id="id" placeholder="Enter username" name="id">
		</div>
		<div class="mb-3">
			<label for="password">Password:</label>
			<input type="password" class="form-control" id="password" placeholder="Enter password" name="password">
		</div>
	</form>
	<button id="btn-login" class="btn btn-secondary">Login</button>
</div>

<%@ include file="../layout/footer.jsp"%>