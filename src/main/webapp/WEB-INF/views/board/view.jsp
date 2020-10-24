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
			<label for="title">제목</label> <input type="text" class="form-control" placeholder="Enter title" id="title" value="${content.title}"
				readonly>
		</div>
		<div class="form-group">
			<label for="content">내용</label>
			<textarea class="form-control summernote" rows="5" id="content" readonly>${content.content}</textarea>
		</div>
		<span class="label warning">by ${content.member.name}</span>
		<c:if test="${content.member.rank eq 0}">
			<span class="label info">순위 없음</span>
		</c:if>
		<c:if test="${content.member.rank ne 0}">
			<span class="label info">${content.member.rank}위</span>
		</c:if>
	</form>
	<br /> <label for="comment">댓글 등록</label>
	<div class="input-group mb-3">
		<input id="comment" type="text" class="form-control" maxlength="100" placeholder="댓글 입력">
		<div class="input-group-append">
			<button id="btn_save" class="btn btn-success">등록</button>
		</div>
	</div>
	<br /> <label for="comment">댓글 내역</label>

	<c:forEach var="comment" items="${content.commentList}" varStatus="status">
		<div class="input-group mb-3">
			<c:if test="${comment.writer eq principal.user.seq}">
				<input  id="comment_${status.index}" type="text"  maxlength="100" class="form-control" value="${comment.comments}" >
			</c:if>
			<c:if test="${comment.writer ne principal.user.seq}">
				<input  type="text" class="form-control" value="${comment.comments}" readonly >
			</c:if>
			<div class="input-group-append">
				<c:if test="${comment.writer eq principal.user.seq}">
					<button id="btn_update${status.index}" onclick="comment.update(${comment.commentSeq}, ${status.index})" class="btn btn-success">수정</button>
					<button id="btn_delete${status.index}" onclick="comment.delete(${comment.commentSeq})" class="btn btn-danger">삭제</button>
					<label for="comment"></label>
				</c:if>
					<span class="label warning">by ${comment.member.name}</span> 	
					<c:if test="${comment.member.rank eq 0}">
					<span class="label info">순위 없음</span>
					</c:if>
					<c:if test="${comment.member.rank ne 0}">
					<span class="label info">${comment.member.rank}위</span>
					</c:if>
			</div>
		</div>
	</c:forEach>
	<div class="input-group mb-3">
		 <a href="${pageContext.request.contextPath}/v1/board/${boardSeq}/${principal.user.seq}/?name=${boardName}" class="btn btn-primary">닫기</a>
	</div>
</div>
<script src="${pageContext.request.contextPath}/js/board/comment.js"></script>
<script>
	$('.summernote').summernote('disable');
</script>
<%@ include file="../layout/footer.jsp"%>

