//console.log("Post module..");

var postService = (function(){
	
	//글 등록하기
	function add(post, callback, error) {

		
		$.ajax({
			type : 'post',
			url : '/board/new',
			data : JSON.stringify(post),
			beforeSend : function(xhr){
                 xhr.setRequestHeader(post.csrf_header, post.csrf_token);
				 },
			contentType : "application/json; charset=utf-8",
			success : function(result, status, xhr) {
				if (callback) {
					callback(result);
				}
			},
			error : function(xhr, status, er) {
				if (error) {
					error(er);
				}
			}
		})
	}
	
	//글 스크랩하기
	function scrapPost(post, callback, error) {
		
		console.log(post.post_id);
		
		$.ajax({
			type : 'post',
			url : '/board/scrap',
			data : JSON.stringify(post),
			beforeSend : function(xhr){
                xhr.setRequestHeader(post.csrf_header, post.csrf_token);
            },
			contentType : "application/json; charset=utf-8",
			success : function(result, status, xhr) {
				if (callback) {
					callback(result);
				}
			},
			error : function(request, status, error) {
					alert("이미 스크랩되었습니다");
				
			}
		})	
	}
	
	
	//글 임시저장하기
	function temporalPost(post, callback, error) {
		
	
		$.ajax({
			type : 'post',
			url : '/board/temporal',
			data : JSON.stringify(post),
			beforeSend : function(xhr){
                xhr.setRequestHeader(post.csrf_header, post.csrf_token);
            },
			contentType : "application/json; charset=utf-8",
			success : function(result, status, xhr) {
				if (callback) {
					callback(result);
				}
			},
			error : function(xhr, status, er) {
				if (error) {
					error(er);
				}
			}
		})
	}
	
	
	//글 임시저장불러오기
	function temporalSelect(callback, error) {
		
		$.ajax({
			type : 'get',
			url : '/board/temporal'+".json",
			contentType : "application/json; charset=utf-8",
			success : function(result, status, xhr) {
				if (callback) {
					callback(result);
				}
			},
			error : function(xhr, status, er) {
				if (error) {
					error(er);
				}
			}
		})
	}
	
	


	//읽음 테이블에 추가하기
	function insertHistory(post, callback, error) {
		
		
		$.ajax({
			type : 'post',
			url : '/board/history/' +post.post_id,
			contentType : "application/json; charset=utf-8",
			beforeSend : function(xhr){
                xhr.setRequestHeader(post.csrf_header, post.csrf_token);
            },
			success : function(result, status, xhr) {
	
			},
			error : function(xhr, status, er) {
				if (error) {
					error(er);
				}
			}
		})
	}
	
	
	//읽음 테이블에서 리스트 가져오기
	function getHistory(callback, error) {
		
		$.ajax({
			type : 'get',
			url : '/board/history' +".json",
			contentType : "application/json;charset=utf-8",
			success : function(result, status, xhr) {
				if (callback) {
					callback(result);
				}
			},
			error : function(xhr, status, er) {
				if (error) {
					error(er);
				}
			}
		})
	}
	
	
	//글 리스트 가져오기
	function listEntity(param, callback,error) {
		
		
		$.ajax({
			type : 'get',
			url : '/board/listEntity/'+param.pageNum+"/"+param.type+"/"+param.keyword+".json",
			contentType : "application/json; charset=utf-8",
			success : function(result, status, xhr) {
				if (callback) {
					callback(result);
				}
			},
			error : function(xhr, status, er) {
				if (error) {
					error(er);
				}
			}
		})
	}
		
	
	
	
	//글 수정하기 
	function updatePost(post,callback, error) {
		
		
		$.ajax({
			type : 'post',
			url : '/board/modify',
			data : JSON.stringify(post),
			beforeSend : function(xhr){
                xhr.setRequestHeader(post.csrf_header, post.csrf_token);
            },
			contentType : "application/json; charset=utf-8",
			success : function(result, status, xhr) {
				if (callback) {
					callback(result);
				}
			},
			error : function(xhr, status, er) {
				if (error) {
					error(er);
				}
			}
		})
	}
	
	
	//글 삭제하기
	function deletePost(post,callback, error) {
		
		
		$.ajax({
			type : 'delete',
			url : '/board/'+post.post_id,
			data : JSON.stringify(post),
			beforeSend : function(xhr){
                xhr.setRequestHeader(post.csrf_header, post.csrf_token);
            },
			contentType : "application/json; charset=utf-8",
			success : function(result, status, xhr) {
				if (callback) {
					callback(result);
				}
			},
			error : function(xhr, status, er) {
				if (error) {
					error(er);
				}
			}
		})
	}
	
	
	//이전글 불러오기
	function selectPrev(post,callback, error) {
		
		$.ajax({
			type : 'get',
			url : '/board/selectPrev/'+post.post_id,
			contentType : "application/json; charset:UTF-8",
			success : function(result, status, xhr) {
				if (callback) {
					callback(result);
				}
			},
			error : function(xhr, status, er) {
				if (error) {
					error(er);
				}
			}
		})
	}
	
	
	//다음글 불러오기
	function selectNext(post,callback, error) {
		
		$.ajax({
			type : 'get',
			url : '/board/selectNext/'+post.post_id,
			contentType : "application/json; charset:UTF-8",
			success : function(result, status, xhr) {
				if (callback) {
					callback(result);
				}
			},
			error : function(xhr, status, er) {
				if (error) {
					error(er);
				}
			}
		})
	}
	
	
	
	//게시판 생성하기
	function insertNewBoard(post, callback, error) {

		
		$.ajax({
			type : 'post',
			url : '/board/newBoard',
			data : JSON.stringify(post),
			beforeSend : function(xhr){
                xhr.setRequestHeader(post.csrf_header, post.csrf_token);
            },
			contentType : "application/json; charset=utf-8",
			success : function(result, status, xhr) {
				if (callback) {
					callback(result);
				}
			},
			error : function(xhr, status, er) {
				if (error) {
					error(er);
				}
			}
		})
	}
	
	
	//공개 게시판 불러오기
	function boardList(callback, error) {
		
		$.ajax({
			type : 'get',
			url : '/board/boardList',
			contentType : "application/json; charset:UTF-8",
			success : function(result, status, xhr) {
				if (callback) {
					callback(result);
					//console.log(result);
				}
			},
			error : function(xhr, status, er) {
				if (error) {
					error(er);
				}
			}
		})
	}
	
	
	//게시판 불러오기
	function allBoardList(callback, error) {
		
		$.ajax({
			type : 'get',
			url : '/board/allBoardList',
			contentType : "application/json; charset:UTF-8",
			success : function(result, status, xhr) {
				if (callback) {
					callback(result);
				}
			},
			error : function(xhr, status, er) {
				if (error) {
					error(er);
				}
			}
		})
	}
	
	
	
	
	//게시판 삭제하기
	function deleteBoard(post, callback, error) {
		
		$.ajax({
			type : 'delete',
			url : '/board/deleteBoard/'+post.b_id + ".json",
			contentType : "application/json; charset:UTF-8",
			beforeSend : function(xhr){
                xhr.setRequestHeader(post.csrf_header, post.csrf_token);
            },
			success : function(result, status, xhr) {
				if (callback) {
					callback(result);
				}
			},
			error : function(xhr, status, er) {
				if (error) {
					error(er);
				}
			}
		})
	}
	
	
	
	return {
		add:add,
		scrapPost:scrapPost,
		temporalPost:temporalPost,
		temporalSelect:temporalSelect,
		insertHistory:insertHistory,
		updatePost:updatePost,
		getHistory:getHistory,
		listEntity:listEntity,
		deletePost:deletePost,
		selectPrev:selectPrev,
		selectNext:selectNext,
		insertNewBoard:insertNewBoard,
		boardList:boardList,
		allBoardList:allBoardList,
		deleteBoard:deleteBoard
	}
	
	
	
	
	
})();