let board = {
	init: function(){
		$("#btn_save").on("click",() =>{ // function(){} => ()=> this 바인딩 하기 위해서 
			this.save();
		});
		$("#btn_update").on("click",() =>{ // function(){} => ()=> this 바인딩 하기 위해서 
			this.update();
		});
	},
		
	save: function(){
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		let data = {
			title:$("#title").val(),	
			content:$("#content").val(),
			boardSeq: boardSeq,
			memberSeq: memberSeq
		}
			$.ajax({
				beforeSend : function(xhr)
                {   
                          xhr.setRequestHeader(header, token);
                 },
				type:"POST",
				url:contextPath+"/v1/board/content",
				data:JSON.stringify(data),
				contentType:"application/json; charset=utf-8", //request type
				dataType:"json"  // response type
			}).done(function(res){
				var code = res.code;
				var message = res.message;
				if(res.code == 200){
					alert("글쓰기 완료 되었습니다.");
					location.href=contextPath+"/v1/board/"+boardSeq+"/"+memberSeq+"/?name="+boardName;
				}else
					alert(message);
			}).fail(function(error){
				alert(JSON.stringify(error))
			});
		
	},
	
	update: function(){
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		let data = {
			title:$("#title").val(),	
			content:$("#content").val(),
			contentSeq: contentSeq,
			memberSeq: memberSeq
		}
			$.ajax({
				beforeSend : function(xhr)
                {   
                          xhr.setRequestHeader(header, token);
                 },
				type:"PUT",
				url:contextPath+"/v1/board/content/"+contentSeq,
				data:JSON.stringify(data),
				contentType:"application/json; charset=utf-8", 
				dataType:"json" 
			}).done(function(res){
				var code = res.code;
				var message = res.message;
				if(res.code == 200){
					alert("게시글 수정 되었습니다.");
					location.href=contextPath+"/v1/board/"+boardSeq+"/"+memberSeq+"/?name="+boardName;
				}else
					alert(message);
			}).fail(function(error){
				alert(JSON.stringify(error))
			});
	},
	
	delete: function(contentSeq){
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		var url = contextPath+"/v1/board/content/"+ contentSeq + "?memberSeq="+memberSeq;
			$.ajax({
				beforeSend : function(xhr)
                {   
                          xhr.setRequestHeader(header, token);
                 },
				type:"DELETE",
				url:url,
				contentType:"application/json; charset=utf-8", 
				dataType:"json" 
			}).done(function(res){
				var code = res.code;
				var message = res.message;
				if(res.code == 200){
					alert("게시글 삭제 되었습니다.");
					location. reload();
				}else
					alert(message);
			}).fail(function(error){
				alert(JSON.stringify(error))
			});
	}
}

board.init();