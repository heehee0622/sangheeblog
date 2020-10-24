<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<script>
	var boardSeq = '${boardSeq}';
	var boardName = '${boardName}';
	var memberSeq = '${principal.user.seq}';
	var contextPath = '${pageContext.request.contextPath}'
</script>

<div class="container">
	<form>
		<div class="form-group">
			<label for="title">Title</label> <input type="text" class="form-control"  maxlength="20" placeholder="Enter title" id="title" >
		</div>
		<div class="form-group">
			<label for="content">content</label>
			<textarea class="form-control summernote" maxlength="300" rows="5" id="content" readonly></textarea>
		</div>
	</form>
	<button id="btn_save" class="btn btn-primary">글쓰기 완료</button>
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
