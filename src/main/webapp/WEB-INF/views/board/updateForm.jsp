<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<script>
	var boardSeq = '${boardSeq}';
	var boardName = '${boardName}';
	var memberSeq = '${principal.user.seq}';
	var contentSeq = '${content.contentSeq}'
	var contextPath = '${pageContext.request.contextPath}'
</script>

<div class="container">
	<form>
		<div class="form-group">
			<label for="title">제목</label> <input type="text" class="form-control"  maxlength="20" placeholder="Enter title" id="title" value="${content.title}">
		</div>
		<div class="form-group">
			<label for="content">content</label>
			<textarea class="form-control summernote" maxlength="300" rows="5" id="content">${content.content}</textarea>
		</div>
		<span class="label warning">by ${content.member.name}</span>
		<c:if test="${content.member.rank eq 0}">
			<span class="label info">순위 없음</span>
		</c:if>
		<c:if test="${content.member.rank ne 0}">
			<span class="label info">${content.member.rank}위</span>
		</c:if>
	</form>
		<p/>
	<p/>
		<div class="input-group mb-3">
		<button id="btn_update" class="btn btn-primary">게시글 수정</button>&nbsp
		 <a href="${pageContext.request.contextPath}/v1/board/${boardSeq}/${principal.user.seq}/?name=${boardName}" class="btn btn-primary">닫기</a>
	</div>
</div>

<script>
	$('.summernote').summernote({
		placeholder : 'Hello Bootstrap 4',
		tabsize : 2,
		height : 300
	});
</script>
<script src="${pageContext.request.contextPath}/js/board/board.js">
	
</script>
<%@ include file="../layout/footer.jsp"%>
