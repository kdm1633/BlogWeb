<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %>

<div class="container mt-3">
	<form>
		<input type="hidden" id="num" value="${post.num}">
		<div class="mt-3 mb-3">
			<label for="title">Title</label>
			<input type="text" id="title" class="form-control" name="title" value="${post.title}">
		</div>
		<div class="mb-3">
			<label for="content">Content</label>
			<textarea rows="5" id="content" class="form-control">${post.content}</textarea>
		</div>
	</form>
	<button class="btn btn-secondary" onclick="history.back()">Back</button>
	<div class="float-end">
		<button id="btn-edit" class="btn btn-warning">Save</button>
	</div>
</div>

<%@ include file="../layout/footer.jsp" %>

<script src="/js/post.js"></script>

<script>
	$(document).ready(function() {
	  $('#content').summernote({
		  height: 300
	  });
	});
</script>