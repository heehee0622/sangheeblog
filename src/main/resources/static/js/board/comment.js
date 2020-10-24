let comment = {
	init: function(){
		$("#btn_save").on("click",() =>{ 
			this.save();
		});
	},
		
	save: function(){
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		let data = {
			comment:$("#comment").val(),
			contentSeq: contentSeq,
			memberSeq: memberSeq
		}
		$('#btn_save').attr("disabled",true);
		var url = contextPath+"/v1/board/content/"+contentSeq + "/comment"
			$.ajax({
				beforeSend : function(xhr)
                {   
                          xhr.setRequestHeader(header, token);
                 },
				type:"POST",
				url:url,
				data:JSON.stringify(data),
				contentType:"application/json; charset=utf-8", 
				dataType:"json" 
			}).done(function(res){
				var code = res.code;
				var message = res.message;
				if(res.code == 200){
					location. reload();
				}else{
					alert(message);
					$('#btn_save').attr("disabled",false);
					}
			}).fail(function(error){
				alert(JSON.stringify(error));
				$('#btn_save').attr("disabled",false);
			});
		
	},
	
	update: function(commentSeq, index){
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		var comment = $('#comment_'+index).val();
		let data = {
			comment: comment,
			contentSeq: contentSeq,
			commentSeq: commentSeq,
			memberSeq: memberSeq
		}
		var url = contextPath+"/v1/board/content/comment/"+commentSeq;
			$.ajax({
				beforeSend : function(xhr)
                {   
                          xhr.setRequestHeader(header, token);
                 },
				type:"PUT",
				url: url,
				data:JSON.stringify(data),
				contentType:"application/json; charset=utf-8", 
				dataType:"json" 
			}).done(function(res){
				var code = res.code;
				var message = res.message;
				if(res.code == 200){
					location. reload();
				}else
					alert(message);
			}).fail(function(error){
				alert(JSON.stringify(error))
			});
	},
	
	delete: function(commentSeq){
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		var url = contextPath+"/v1/board/content/"+ contentSeq +"/comment/"+ commentSeq+ "?memberSeq="+memberSeq;
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
					location. reload();
				}else
					alert(message);
			}).fail(function(error){
				alert(JSON.stringify(error))
			});
	}
}

comment.init();