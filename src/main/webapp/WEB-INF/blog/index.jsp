<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css"/>
<script src="/webjars/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="/webjars/jquery/jquery.min.js"></script>
</head>
<body>

<%@ include file="./layout/header.jsp" %>

<div class="container-fluid mt-3">
	<div class="card">
		<div class="card-body">
			<h4 class="card-title">Post title</h4>
			<a href="#" class="btn btn-secondary">Card link</a>
		</div>
	</div>
</div>

<%@ include file="./layout/footer.jsp" %>

</body>
</html>