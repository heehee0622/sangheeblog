<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../layout/header.jsp"%>
<script>
	var boardSeq = '${boardSeq}';
	var boardName = '${boardName}';
	var memberSeq = '${principal.user.seq}';
	var contextPath = '${pageContext.request.contextPath}'

</script>
<div class="container">

	<h4 class="card-board-name">${boardName}게시판</h4>
	<a href="${pageContext.request.contextPath}/v1/board/${boardSeq}/content/save?boardName=${boardName}" class="btn btn-primary">게시글 등록 </a>
	<a href="${pageContext.request.contextPath}" class="btn btn-primary">홈으로 이동 </a>
	<c:forEach var="content" items="${contents.content}">
		<div class="card m-2">
			<div class="card-body">
				<h4 class="card-title">게시글 제목 : ${content.title}</h4>
				<a href="${pageContext.request.contextPath}/v1/board/${boardSeq}/content/${content.contentSeq}/detail?memberSeq=${principal.user.seq}&boardName=${boardName}"
					class="btn btn-primary">상세보기</a>
				<c:if test="${content.writer eq principal.user.seq}">
					<a href="${pageContext.request.contextPath}/v1/board/${boardSeq}/content/${content.contentSeq}?memberSeq=${principal.user.seq}&boardName=${boardName}"
						class="btn btn-primary">수정</a>
					<button id="btn_delete" onclick="board.delete(${content.contentSeq})" class="btn btn-primary">삭제</button>
				</c:if>
					<span class="label warning">by ${content.member.name}</span>
					<c:if test="${content.member.rank eq 0}">
						<span class="label info">순위 없음</span>
					</c:if>
					<c:if test="${content.member.rank ne 0}">
						<span class="label info">${content.member.rank}위</span>
					</c:if>
			</div>
		</div>
	</c:forEach>

	<ul class="pagination justify-content-center">
		<c:choose>
			<c:when test="${contents.first}">
				<li class="page-item disabled"><a class="page-link" href="?page=${contents.number -1}&name=${boardName}">Previous</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item"><a class="page-link" href="?page=${contents.number -1}&name=${boardName}">Previous</a></li>
			</c:otherwise>
		</c:choose>

		<c:choose>
			<c:when test="${contents.last}">
				<li class="page-item disabled"><a class="page-link" href="?page=${contents.number +1}&name=${boardName}">Next</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item"><a class="page-link" href="?page=${boards.number +1}&name=${boardName}">Next</a></li>
			</c:otherwise>
		</c:choose>
	</ul>

</div>
<script src="${pageContext.request.contextPath}/js/board/board.js"></script>
<%@ include file="../layout/footer.jsp"%>
