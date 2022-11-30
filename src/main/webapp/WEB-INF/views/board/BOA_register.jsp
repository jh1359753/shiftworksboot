<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%--<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>--%>

<%@include file="../includes/header.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
<link rel="stylesheet" href="/resources/css/post.css">

<meta charset="UTF-8">

<title></title>
</head>

<div class="container">
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">새로운 글 등록</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">

			<div class="panel-heading"></div>
			<!-- /.panel-heading -->
			<div class="panel-body">
				<button id="previewBtn" type="button" class="btn btn-primary">미리보기</button>
				<button id="temporalBtn" type="button" class="btn btn-primary">임시저장</button>

				<div class="row">
						<div>파일첨부</div>
							<div class="panel-body">
								<div class="form-group uploadDiv">
									<input type="file" name='uploadFile' multiple>
								</div>

								<div class='uploadResult'>
									<ul>

									</ul>
								</div>
						</div>
						<!--  end panel-body -->
				</div>
				<!-- /.row -->

				<form id="form">
					<div class="form-group">
							<label>게시판명</label>
							<select class="form-select" aria-label="Default select example" name="b_id">
								<option selected>------</option>
								
							</select>
						</div>

					<div class="form-group">
						<label>제목</label> 
						<input class="form-control" name='post_name' value="<c:out value="${post.post_name}" />" />
					</div>
					
					<%--<sec:authentication property="principal" var="pinfo"/>--%>
					<div class="form-group">
						<label>게시자</label>
						<input class="form-control" name='name' value="<c:out value="${post.employee_id}" />" />
						<%--<input class="form-control" name='name' value="<c:out value="${pinfo.employee.name}" />" readonly="readonly" />--%>
					</div>
					
					<%--<sec:authentication property="principal" var="pinfo"/>--%>
					<div class="form-group">
						<label>게시부서</label>
						<input class="form-control" name='dept_id' value="<c:out value="${post.dept_id}" />" />
						<%--<input class="form-control" name='dept_id' value="<c:out value="${pinfo.employee.dept_id}" />" readonly="readonly">--%>
					</div>

					<div class="form-group">
						<label>게시일</label> 
						<input class="form-control" name='post_regdate' value="" readonly="readonly" />
					</div>

					<div class="form-group">
						<label>내용</label>
						<textarea class="form-control" rows="20" cols="150" name='post_content' id='post_content'>
							<c:out value="${post.post_content}" />
						</textarea>
					</div>

					<div class="form-group">
						<label>수신부서</label> <input class="form-control"
							name='post_receivedept' value="<c:out value="" />" />
					</div>
					<div class="form-group">
						<input class="form-control" hidden="hidden" name='emp_id' value="<c:out value="${pinfo.username}" />" />
					</div>
				
				</form>

				<button id="registerBtn" type="button" class="btn btn-primary" value="">게시하기</button>
				<button id="listBtn" class="btn btn-primary">목록보기</button>

			</div>
			<!--  end panel-body -->

		</div>
		<!--  end panel-body -->
	</div>
	<!-- end panel -->
</div>
<!-- /.row -->
</div>

<script type="text/javascript" src="/resources/js/post.js"></script>
<script type="text/javascript">
$(document).ready(function () {
	
	var form = $("#form")
	var formInputBoard = form.find("select[name='b_id']");
	var formInputEmp = form.find("input[name='emp_id']");
	var formInputTitle = form.find("input[name='post_name']");
	var formInputDept = form.find("input[name='dept_id']");
	var formInputContent = form.find("textarea[name='post_content']");
	var formInputReceive = form.find("input[name='post_receivedept']");
	var formInputRegdate = form.find("input[name='post_regdate']");
	
	var today = new Date();
	formInputRegdate.attr("value",today);
	
	var csrf_token = $("meta[name='_csrf']").attr("content");
	var csrf_header = $("meta[name='_csrf_header']").attr("content");
	
	
	
	
	
	//글 등록 버튼 클릭 시 post db에 저장하기 
	  $("#registerBtn").on("click",function(e){
		  
		//게시판 번호가 입력되지 않을 시 임시저장할 수 없음
	    	if(formInputBoard.val()==="------"){
	    		alert("게시판 번호를 입력하세요");
	    		return;
	    	}
		  
		/*//각각의 파일을 list로 만들어서 post에도 이름을 저장하기 위함
			var fileList=[];
			$('.uploadResult ul li').each(function(i, obj) {
				fileList.push({
					uuid: $(obj).data('uuid'),
					file_name: $(obj).data('file_name'),
					file_src: $(obj).data('file_src'),
				});
			}); // end li each*/
		  
		  
	      var post = {
	            b_id: formInputBoard.val(),
	            pname:formInputTitle.val(),
	            //emp_id:formInputEmp.val(),
	            //dept_id:formInputDept.val(), 추후 수정 예정
	            content:formInputContent.val(),
	            receivedept:formInputReceive.val(),
			  	regdate:formInputRegdate.val(),
	            csrf_token:csrf_token,
	            csrf_header:csrf_header
	            //fileList: fileList
	          };
	      
	     	
			
	        postService.add(post, function(result){
	        
	        alert("게시글이 작성되었습니다.");
	        
	        form.find("input").val(""); 
	        form.find("textarea").val(""); 
	        $(".uploadResult ul").html('');
	        location.href ="/board/list?b_id=1";
	      });  
	      
	    });//end register
	    
	
	 
	    
	  //임시저장 버튼 클릭 시 임시저장 db저장하기 
	   var temporalBtn = $("#temporalBtn");
	 
	    temporalBtn.on("click",function(){
	    	
	    	//게시판 번호가 입력되지 않을 시 임시저장할 수 없음
	    	if(formInputBoard.val()==="------"){
	    		alert("게시판 번호를 입력하세요");
	    		return;
	    	}
	    	//console.log(typeof(formInputBoard.val()));
	    	
	    	 var post = {
	 	            b_id: formInputBoard.val(),
	 	            post_name:formInputTitle.val(),
	 	            emp_id:formInputEmp.val(), 
	 	            dept_id:formInputDept.val(),
	 	            post_content:formInputContent.val(),
	 	           	csrf_token:csrf_token,
		            csrf_header:csrf_header
	 	          };
	    	 
	    	 
	    	 postService.temporalPost(post, function(result){
	    		 alert("임시저장되었습니다");
	    		 form.find("input").val(""); 
	    		 form.find("textarea").val(""); 
	    		 location.href="/board/list";
	    	 }) 
	    })//end temporalregister
	    
	    
	    
	 	 // 첨부파일 업로드 결과를 출력하는 함수
		function showUploadResult(uploadResultArr) {
			
			// 업로드를 하지 않았거나, 업로드 실패 시 함수 종료
			if(!uploadResultArr || uploadResultArr.length == 0) {
				return;
			}
			
			// 결과물을 출력해야 하는 경우 아래 코드 실행
			var str = "";
			$(uploadResultArr).each(function(i, obj) {
				// 파일 경로와 이름을 저장하는 변수
				var filePath = encodeURIComponent(obj.uuid + "_" + obj.file_name);
				
				str += "<li data-uuid='"+ obj.uuid +"' data-file_name='" + obj.file_name + "'>";
				str += obj.file_name;
				str += "<span data-file=\'" + filePath + "\'> [x] </span>";
				str += "</li>";
			});
			
			$('.uploadResult ul').append(str);
			
		} // end showUploadResult()
	    
	    
		 //파일 업로드 후 file 태그 내용 초기화를 위해 빈 상태에서 clone
		var cloneObj = $('.uploadDiv').clone();
	    
	    
	    //파일 업로드
	    var regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
	    var maxSize = 5242880; //5MB
	    
	    function checkExtension(fileName, fileSize){
	      
	      if(fileSize >= maxSize){
	        alert("파일 사이즈 초과");
	        return false;
	      }
	      
	      if(regex.test(fileName)){
	        alert("해당 종류의 파일은 업로드할 수 없습니다.");
	        return false;
	      }
	      return true;
	    }
	    
	    $('input[type="file"]').change(function(e){

	      var formData = new FormData();
	      var inputFile = $("input[name='uploadFile']");
	      var files = inputFile[0].files;
	      
	      for(var i = 0; i < files.length; i++){

	        if(!checkExtension(files[i].name, files[i].size) ){
	          return false;
	        }
	        formData.append("uploadFile", files[i]);
	        
	      }
	   
	    
	$.ajax({
				url : '/board/uploadAjaxAction',
				processData : false,
				contentType : false,
				data : formData,
				beforeSend : function(xhr){
	                xhr.setRequestHeader(csrf_header, csrf_token);
	            },
				type : 'POST',
				dataType : 'json',
				success : function(result) {
					//console.log(result);
					showUploadResult(result); //업로드 결과 처리 함수 
					$('.uploadDiv').html(cloneObj.html());

				}
			}); //$.ajax
			
			

		});//end file upload
		
		
		// 업로드 파일을 x 버튼으로 삭제
		$('.uploadResult').on("click", "span", function(e) {
			// 토큰 정보 받아오기
			//console.log(csrf_token);
			
			var fileName = $(this).data("file");
			var selectedLi = $(this).parent("li");
			
			//console.log(fileName);
			$.ajax({
				url: '/board/deleteFile',
				beforeSend : function(xhr){ // csrf 토큰 전달
	                xhr.setRequestHeader(csrf_header, csrf_token);
	            },
	            type: 'delete',
	            data: fileName,
	            dataType: 'text',
				success: function(result) {
					//console.log(this);
					// 업로드 파일 삭제 성공 시 li 삭제
					selectedLi.remove();
				},
				error : function(xhr, status, er) {
					//console.log(er);
				}
			})
		}); // 삭제 버튼 함수
		
		
		
		
		//동적으로 게시판 메뉴 추가해주기
		postService.boardList(function(result){

			
			for(var i=0;i<result.length;i++){
				
				$(".form-select").append(
					"<option value='"+result[i].b_id+"'>"+result[i].name+"</option>"
				)
			}
			
		});
		
	    
	    
	  //list버튼 클릭 시 목록이동
		$('#listBtn').on("click", function(e) {
	
			location.href ="/board/list";
			
		});

	});
</script>
<%--
<%@include file="/WEB-INF/views/includes/footer.jsp"%>--%>
