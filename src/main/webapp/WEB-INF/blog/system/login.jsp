<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<script src="/js/login.js"></script>

<div class="container mt-3">
	<form>
		<div class="mb-3 mt-3">
			<label for="id"><spring:message code="user.login.form.userid"/>:</label>
			<input type="text" class="form-control" id="id" name="id" placeholder="Enter userid">
		</div>
		<div class="mb-3">
			<label for="password"><spring:message code="user.login.form.password"/>:</label>
			<input type="password" class="form-control" id="password" name="password" placeholder="Enter password">
		</div>
	</form>
	<button id="btn-login" class="btn btn-secondary"><spring:message code="user.login.form.loginBtn"/></button>
</div>

<%@ include file="../layout/footer.jsp"%>