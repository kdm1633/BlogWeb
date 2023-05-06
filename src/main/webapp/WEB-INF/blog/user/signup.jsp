<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %>

<div class="container mt-3">
	<form>
		<div class="mt-3 mb-3">
			<label for="id">Id:</label>
			<input type="text" id="id" class="form-control" name="id" placeholder="Enter id">
		</div>
		<div class="mb-3">
			<label for="password">Password:</label>
			<input type="password" id="password" class="form-control" name="password" placeholder="Enter password">
		</div>
		<div class="mb-3">
			<label for="email">Email:</label>
			<input type="text" id="email" class="form-control" name="email" placeholder="Enter email">
		</div>
	</form>
	<button id="btn-signup" class="btn btn-secondary">Signup</button>
</div>

<%@ include file="../layout/footer.jsp" %>

<script src="/js/signup.js"></script>