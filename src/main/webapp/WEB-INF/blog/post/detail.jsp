<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %>

<!-- Post -->
<div class="container border mt-4">
	<div class="my-4">
		<h3>${post.title}</h3>
	</div>
	<div class="my-4">
		<div>${post.content}</div>
	</div>
	<div class="my-4">
		Post number: <span><i>${post.num}</i></span><br>
		Writer: ${post.user.username}<br>
	</div>
	<hr>
	<div class="mb-3">
		<button class="btn btn-secondary" onclick="history.back()">Back</button>
		<c:if test="${post.user.num == sessionScope.loginUser.num}">
		<div class="float-end">
			<a href="/post/edit/${post.num}" class="btn btn-warning">Edit</a>
			<button id="btn-delete" class="btn btn-danger">Delete</button>
		</div>
		</c:if>
	</div>
	
	<!-- Comment list -->
	<c:if test="${!empty post.commentList}">
	<div class="container mt-3">
		<table class="table">
			<thead>
				<th>Writer</th>
				<th>Content</th>
				<th></th>
			</thead>
			<tbody>
				<c:forEach var="comment" items="${post.commentList}">
				<tr>
					<td>${comment.user.username}</td>
					<td>${comment.content}</td>
					<td>
						<c:if test="${comment.user.num!=null && loginUser.num==comment.user.num}">
						<button onclick="commentObject.deleteComment(${post.num}, ${comment.num})">Delete</button>
						</c:if>
					</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	</c:if>
	
	<!-- Comment writing form -->
	<div class="container mt-3">
		<input type="hidden" id="postNum" value="${post.num}">
		<table class="table">
			<thead>
				<tr>
					<th><h4>Comment</h4></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>
						<textarea id="comment-content" class="form-control" rows="1"></textarea>
						<button id="btn-comment" class="btn btn-secondary float-end mt-2">Comment</button>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</div>

<%@ include file="../layout/footer.jsp" %>

<link href="/css/post/detail.css" rel="stylesheet">
<script src="/js/post.js"></script>
<script src="/js/comment.js"></script>