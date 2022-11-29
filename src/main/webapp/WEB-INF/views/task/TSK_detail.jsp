<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
	<script type="text/javascript" src="/resources/js/task.js"></script>
<link rel="stylesheet" href="/resources/css/task.css">
	<meta charset="UTF-8">
<title>업무 관리</title>
</head>
<body>

<div class="container">

	<div class="newContainer">
		<div class="mb-3">
			<label for="task_id" class="form-label">글 번호</label>
			<input type="text" class="form-control" value="${task.task_id}"
			id="task_id" readonly>
		</div>
		<div class="mb-3">
		<!-- 부서 검색 가능하도록 변경 필요 -->
			<label for="dept_name" class="form-label">부서</label>
			<input type="text" class="form-control"
			id="dept_name" value="부서1" readonly>
			<input type="hidden" class="form-control"
			id="dept_id" value="${task.dept_id}">
		</div>
		<div class="mb-3">
			<label for="task_title" class="form-label">제목</label>
			<input type="text" class="form-control" value="${task.task_title}"
			id="task_title" placeholder="글 제목" readonly>
		</div>
		<div class="mb-3">
			<label for="name" class="form-label">작성자</label>
			<input class="form-control" type="text" value="${task.writer}"
				id="name" readonly>
			<input class="form-control" type="hidden" value="${task.createBy}"
				id="emp_id">
		</div>
		<div class="mb-3 form-check">
			<div>
				<input class="form-check-input" type="radio" name="t_private"
					id="t_private1" checked> <label
					class="form-check-label" for="t_private1"> 전체공개 </label>
			</div>
			<div>
				<input class="form-check-input" type="radio" name="t_private"
					id="t_private2"> <label class="form-check-label"
					for="t_private2"> 부서원에게만 공개 </label>
			</div>
		</div>
		<div class="mb-3">
			<label for="task_content" class="form-label">내용</label>
			<textarea class="form-control" id="task_content" rows="10" readonly>
				<c:out value="${task.task_content}"></c:out>
			</textarea>
		</div>
		<div class="mb-3">
			<label for="formFileSm" class="form-label file">첨부파일</label>
			<input class="form-control form-control-sm" id="formFileSm" type="file" readonly>
			<ul class="taskFiles">
				<c:forEach items="${ task.fileList }" var="f">
					<li data-uuid="${ f.uuid }" data-file_name="${ f.file_name }"
						data-file_src="${ f.file_src }">
						<a><c:out value="${ f.file_name }"/></a>
					</li>
				</c:forEach>
			</ul>
		</div>
		<div class="confirm">
			<!-- 해당 게시글의 작성자인 경우에만 수정/삭제 메뉴 출력 -->
			<button id="updateBtn" type="submit" class="own btn btn-warning mb-3">수정</button>
			<button id="updateSubmitBtn" type="submit" class="own btn btn-warning mb-3">수정완료</button>
			<button id="deleteBtn" type="submit" class="own btn btn-danger mb-3">삭제</button>
			<!-- 모든 사용자에게 목록 버튼 출력 -->
			<button id="resetBtn" type="reset"
				class="btn btn-outline-primary mb-3">목록</button>
		</div>
	</div>

</div>
	<script type="text/javascript">
		$(document).ready(function() {
			
			var taskEmp_id = "${task.createBy}";
			var principalEmp_id = "${emp.empId}";
			console.log(taskEmp_id);
			console.log(principalEmp_id);
			if(taskEmp_id != principalEmp_id) {
				$('.own').hide();
			}
			
			// 첨부파일 클릭 시 다운로드/삭제할 수 있도록 하는 url
			$('.taskFiles li').each(function(i, obj){
				var file = {
						uuid: $(obj).data('uuid'),
						file_name: $(obj).data('file_name'),
						file_src: $(obj).data('file_src'),
					};
				var filePath = encodeURIComponent(file.file_src + "/" + file.uuid + "_" + file.file_name);
				$(obj).attr("class", "file");
				$(obj).children('a').attr("href", "/task/download?fileName=" + filePath);
				$(obj).append("<span class='fileDelete' data-file=\'" + filePath + "\'> [x] </span>");
				$('.fileDelete').hide();
			}); // end li each
			
			// 게시글 상세 페이지 접속 시 공개여부 라디오버튼 숨김
			$('.form-check *').hide();
			// 수정 완료 게시글 버튼 숨김
			$('#updateSubmitBtn').hide();
		
			// 게시글 수정 버튼 클릭 이벤트
			$('#updateBtn').on("click", function(){
				
				// 수정 버튼 클릭 시 수정 가능한 사항들을 입력(수정) 가능한 상태로 바꿈
				$('.form-check *').show();
				$('#dept_id').attr("readonly", false);
				$('#task_title').attr("readonly", false);
				$('#task_content').attr("readonly", false);
				
				// 첨부파일의 다운로드는 불가, 삭제는 가능 상태로 만듦
				$('.taskFiles li a').attr("href", "");
				$('.fileDelete').show();
				
				// '수정' 버튼을 '수정 완료' 버튼으로 변경
				$('#updateBtn').hide();
				$('#updateSubmitBtn').show();
				
				
			}) // end update click
			
			// 수정 완료 버튼 클릭 시 이벤트
			$('#updateSubmitBtn').on("click", function(e) {
				e.preventDefault();
				
				// 항목 체크에 따른 값 저장
				let isPrivate;
				if($('#t_private1').is(":checked")) {
					isPrivate = 'Y';
				} else {
					isPrivate = 'N';
				}
				
				// 폼 입력값 객체에 대입
				var updateTask = {
					dept_id: $('#dept_id').val(),
					task_title: $('#task_title').val(),
					task_content: $('#task_content').val(),
					t_private: isPrivate,
					task_id: $('#task_id').val(),
					type: 'empty',
					keyword: 'empty',
					pageNum: 1
				}

				// 객체 전달하여 DB에 저장 후 페이지 이동
				taskService.updateTask(updateTask, function(result){
					// href 대신 replace 이용하여 히스토리 남지 않게 처리
					location.replace("/task/pages/" + updateTask.dept_id + "/" + updateTask.type + "/" + updateTask.keyword
				            + "/" + updateTask.pageNum + "/" + updateTask.task_id);
				});

			}); // end update data submit click event
			
			// 삭제 버튼 클릭 시 이벤트
			$('#deleteBtn').on("click", function(){
				taskService.deleteTask($('#task_id').val(), function(result) {
					location.replace("/task/pages/" + $('#dept_id').val() + "/empty/empty/1");
				}); 
				
			}); // end delete click
			
			// 업로드 파일을 x 버튼으로 삭제
			$('.taskFiles').on("click", "span", function(e) {
				// 토큰 정보 받아오기
				var csrf_token = $("meta[name='_csrf']").attr("content");
				var csrf_header = $("meta[name='_csrf_header']").attr("content");
				
				var fileName = $(this).data("file");
				var selectedLi = $(this).parent("li");
				var uuid = selectedLi.data("uuid");
				
				$.ajax({
					url: '/task/deleteFile',
					beforeSend : function(xhr){ // csrf 토큰 전달
		                xhr.setRequestHeader(csrf_header, csrf_token);
		            },
		            type: 'delete',
		            data: JSON.stringify(
		            		{file_name: fileName,
		            		uuid: uuid}
		            		),
		            contentType : "application/json; charset=utf-8",
		            dataType: 'text',
					success: function(result) {
						// 업로드 파일 삭제 성공 시 li 삭제
						selectedLi.remove();
					},
					error : function(xhr, status, er) {
						console.log('error');
					}
				})
			}); // 삭제 버튼 함수
			
			
			
			// 취소 버튼 클릭 시 이전 페이지(업무 목록)로 이동
			$('#resetBtn').on("click", function(){
				
				history.back();
				
			}); // end reset click event

		}); // end document ready function
	</script>

</body>
</html>