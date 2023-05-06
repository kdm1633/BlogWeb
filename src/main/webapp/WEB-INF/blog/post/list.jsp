<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="../layout/header.jsp" %>

<div class="container-fluid mt-3">
	<c:if test="${!empty postList}">
	<c:forEach var="post" items="${postList.content}">
	<div class="card mb-2">
		<div class="card-body">
			<h4 class="card-title">${post.title}</h4><br>
			<a href="/post/${post.id}" class="btn btn-secondary">Detail</a>
		</div>
	</div>
	</c:forEach>
	<ul class="pagination justify-content-center">
    <li class="page-item <c:if test="${postList.first}">disabled</c:if>"><a class="page-link" href="?page=${postList.number - 1}">Previous</a></li>
    <c:forEach begin="0" end="${postlist.totalPages + 1}" step="1" var="i">
    <c:set var="isCurPage" value="${postList.number == i}"/>
    <li class="page-item ${isCurPage ? 'active' : ''}">
    	<${isCurPage ? 'span' : 'a'} class="page-link" href="?page=${i}">${i+1}</a>
    </li>
    </c:forEach>
    <li class="page-item <c:if test="${postList.last}">disabled</c:if>"><a class="page-link" href="?page=${postList.number + 1}">Next</a></li>
  </ul>
	</c:if>
</div>

<%@ include file="../layout/footer.jsp" %>

</body>
</html>