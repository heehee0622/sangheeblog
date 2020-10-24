<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="layout/header.jsp"%>
<div class="container">
<h4 class="card-title"> 게시판 목록 </h4>
	<c:forEach var="board" items="${boards}">
		<div class="card m-2">
			<div class="card-body">
				<span class="label warning">게시판 이름 : ${board.name}</span>
				<span class="label success">게시판 설명 : ${board.description}</span>
				<a href="${pageContext.request.contextPath}/v1/board/${board.boardSeq}/${principal.user.seq}?name=${board.name}"   class="btn btn-primary">상세 보기</a>
			</div>
		</div>
	</c:forEach>
	${boardName}
	<h4 class="card-title"> 해커 뉴스 </h4>
	<c:forEach var="hacker" items="${hackers}">
		<div class="card m2">
			<div class="card-body">
				<a href="${hacker.url}"> ${hacker.title}</a>
			</div>
		</div>
	</c:forEach>
</div>

<%@ include file="layout/footer.jsp"%>
