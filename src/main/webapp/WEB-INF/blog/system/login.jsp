<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<!-- <script src="/js/login.js"></script> -->

<div class="container mt-3">
	<form method="post" action="/login">
		<div class="mb-3 mt-3">
			<label for="username"><spring:message code="user.login.form.username"/>:</label>
			<input type="text" class="form-control" id="username" name="username" placeholder="Enter username">
		</div>
		<div class="mb-3">
			<label for="password"><spring:message code="user.login.form.password"/>:</label>
			<input type="password" class="form-control" id="password" name="password" placeholder="Enter password">
		</div>
		<button id="btn-login" class="btn btn-secondary"><spring:message code="user.login.form.loginBtn"/></button>
		<a href="https://kauth.kakao.com/oauth/authorize?client_id=6d081046584937b4c5ebbb5add56be18&redirect_uri=http://localhost:8080/oauth/kakao&response_type=code"><img height="40px" src="/image/kakao_login_btn.png"></a>
	</form>
</div>

<%@ include file="../layout/footer.jsp"%>