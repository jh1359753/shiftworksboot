<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/header.jsp"%>
<%@taglib uri="http://www.springframework.org/security/tags"
		  prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
	<script type="text/javascript" src="/resources/js/task.js"></script>
<link rel="stylesheet" href="/resources/css/task.css">
<link rel='stylesheet'
	href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css'>
<meta charset="UTF-8">
<title>업무 등록</title>
</head>
<body>

<div class="container">

<!-- 업무 등록을 위한 입력 폼 -->
	<div class="newContainer">
		<div class="mb-3">
			<select class="form-select dept" aria-label="Default select example">
  				<option selected>부서를 선택하세요.</option>
  				<option value="DEPT1">인사</option>
  				<option value="DEPT2">회계</option>
  				<option value="INFO-SECU">보안</option>
			</select>
			<input type="hidden" class="form-control"
			id="dept_id" value="">
		</div>
		<div class="mb-3">
			<label for="task_title" class="form-label">제목</label>
			<input type="text" class="form-control"
			id="task_title" placeholder="글 제목">
		</div>
		<div class="mb-3 form-check">
			<div>
				<input class="form-check-input" type="radio" name="notification"
					id="notificationY" checked> <label
					class="form-check-label" for="notificationY"> E-mail 알림 허용 </label>
			</div>
			<div>
				<input class="form-check-input" type="radio" name="notification"
					id="notificationN"> <label class="form-check-label"
					for="notificationN"> E-mail 알림 거부 </label>
			</div>
		</div>
		<div class="mb-3 form-check">
			<div>
				<input class="form-check-input" type="radio" name="t_private"
					id="t_privateN" checked> <label
					class="form-check-label" for="t_privateN"> 전체공개 </label>
			</div>
			<div>
				<input class="form-check-input" type="radio" name="t_private"
					id="t_privateY"> <label class="form-check-label"
					for="t_privateY"> 부서원에게만 공개 </label>
			</div>
		</div>
		<div class="mb-3">
			<label for="task_content" class="form-label">내용</label>
			<textarea class="form-control" id="task_content" rows="10"></textarea>
		</div>
		<div class="mb-3">
			<label for="formFileSm" class="form-label file">첨부파일</label>
			<input class="form-control form-control-sm" id="formFileSm"
				type="file" name="uploadFile" multiple>
		</div>
		<!-- 첨부파일 업로드 결과 -->
		<div class="uploadResult">
			<ul>
				
			</ul>
		</div>

		<div class="confirm">
			<button id="submitBtn" type="submit" class="btn btn-primary mb-3">작성</button>
			<button id="resetBtn" type="reset"
				class="btn btn-outline-primary mb-3">취소</button>
		</div>
	</div>
</div>


	<script type="text/javascript">
		$(document).ready(function() {
			
			// 부서 선택 시 input value에 값 입력
			$('.dept').on("change", function(e){
				$('#dept_id').val($(this).val());
			});
			
			// 폼 제출(작성) 버튼 클릭 시 이벤트
			$('#submitBtn').on("click", function(e) {
				e.preventDefault();
				
				// 항목 체크에 따른 값 저장
				let isPrivate;
				let isNotification;
				if($('#t_privateY').is(":checked")) {
					isPrivate = 'Y';
				} else {
					isPrivate = 'N';
				}
				if($('#notificationY').is(":checked")) {
					isNotification = 'Y';
				} else {
					isNotification = 'N';
				}
				
				// 업로드 파일 존재 시
				var fileList = [];
				$('.uploadResult ul li').each(function(i, obj) {
					fileList.push({
						uuid: $(obj).data('uuid'),
						file_name: $(obj).data('file_name'),
						file_src: $(obj).data('file_src'),
					});
				}); // end li each
				
				// 폼 입력값 객체에 대입
				var newTask = {
					dept_id: $('#dept_id').val(),
					task_title: $('#task_title').val(),
					task_content: $('#task_content').val(),
					t_private: isPrivate,
					notification: isNotification,
					fileList: fileList
				}

				// 객체 전달하여 DB에 저장 후 페이지 이동
				taskService.insertTask(newTask, function(result){
					// href 대신 replace 이용하여 히스토리 남지 않게 처리
					location.replace("/task/pages/" + newTask.dept_id.toLowerCase() + "/empty/empty/1");
				});

			}); // end submit click event
			
			
			// 취소 버튼 클릭 시 이전 페이지(업무 목록)로 이동
			$('#resetBtn').on("click", function(){
				
				history.back();
				
			}); // end reset click event
			
			
			/* * * * * * * * * * * * * * * * * * *
						첨부파일 업로드
			* * * * * * * * * * * * * * * * * * */
			
			// 첨부파일 확장자, 크기 검증에 쓰이는 변수
			var regex = new RegExp("(.*?)\.(exe|zip)$"); // exe, zip 타입인지 확인
			var maxSize = 5242880; // 5MB 이상인지 확인
			
			// 첨부파일 확장자, 크기를 검증하는 함수
			function checkExtension(fileName, fileSize) {
				
				// 파일 사이즈 검증
				if(fileSize >= maxSize) { //5MB 초과 시 실행
					alert("업로드 가능 최대 크기를 초과하였습니다.");
					return false;
				}
				
				// 파일 확장자 검증
				if (regex.test(fileName)) { // exe, zip 파일의 경우 실행
					alert("업로드 불가능한 파일입니다.");
					return false;
				}
				
				// 위의 검사에 걸리지 않으면 true 리턴
				return true;
			} //end checkExtension()
			
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
					var filePath = encodeURIComponent(obj.file_src + "/" + obj.uuid + "_" + obj.file_name);
					
					str += "<li data-uuid='"+ obj.uuid +"' data-file_name='" + obj.file_name + "' ";
					str += "data-file_src='" + obj.file_src + "'>";
					str += obj.file_name;
					str += "<span data-file=\'" + filePath + "\'> [x] </span>";
					str += "</li>";

				});
				
				$('.uploadResult ul').append(str);
				
			} // end showUploadResult()
			
			// 파일 업로드 후 file 태그 내용 초기화를 위해 빈 상태에서 clone
			var cloneObj = $('.uploadDiv').clone();
			
			// 첨부파일 input 태그에 생기는 변화 감지
			$('input[type="file"]').change(function(e){
				// 토큰 정보 받아오기
				var csrf_token = $("meta[name='_csrf']").attr("content");
				var csrf_header = $("meta[name='_csrf_header']").attr("content");
				
				// 파일 전송을 위한 객체 생성
				var formData = new FormData();
				var inputFile = $('input[name="uploadFile"]');
				var files = inputFile[0].files;
				
				// 업로드 파일 개수만큼 객체 생성
				for(let i=0; i<files.length; i++) {
					
					// 파일 확장자가 exe, zip이거나 파일크기가 5MB를 넘으면 false 리턴
					if(!checkExtension(files[i].name, files[i].size)) {
						return false;
					}
					
					// 조건에 맞는 파일인 경우 파일 객체 생성
					formData.append("uploadFile", files[i]);
					
				}// end for
				
				
				// 파일 업로드 Ajax
				$.ajax({
					url: '/task/uploadFile',
					processData: false,	// 객체를 String으로 변환하지 않도록 설정
					contentType: false,	// Multipart data로 전송되도록 설정
					data: formData,
					beforeSend : function(xhr){ // csrf 토큰 전달
		                xhr.setRequestHeader(csrf_header, csrf_token);
		            },
					type: 'post',
					dataType: 'JSON',
					success: function(result) {
						console.log('file');
						showUploadResult(result);
						$('.uploadDiv').html(cloneObj.html());
					}
				}); // end ajax
				
			}); // end input file - change()
			
			
			// 업로드 파일을 x 버튼으로 삭제
			$('.uploadResult').on("click", "span", function(e) {
				// 토큰 정보 받아오기
				var csrf_token = $("meta[name='_csrf']").attr("content");
				var csrf_header = $("meta[name='_csrf_header']").attr("content");
				
				var fileName = $(this).data("file");
				var selectedLi = $(this).parent("li");
				
				console.log(fileName);
				$.ajax({
					url: '/task/deleteFile',
					beforeSend : function(xhr){ // csrf 토큰 전달
		                xhr.setRequestHeader(csrf_header, csrf_token);
		            },
		            type: 'delete',
		            data: JSON.stringify(
		            		{file_name: fileName}
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
			

		}); // end document ready function
	</script>

</body>
</html>