<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %>

<div class="container mt-3">
	<form>
		<div class="mt-3 mb-3">
			<label for="title">Title</label>
			<input type="text" id="title" class="form-control" name="title" placeholder="Enter title">
		</div>
		<div class="mb-3">
			<label for="content">Content</label>
			<textarea rows="5" id="content" class="form-control"></textarea>
		</div>
	</form>
	<button id="btn-postup" class="btn btn-secondary">Postup</button>
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