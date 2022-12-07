<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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

	<table class="table">
		<thead>
			<tr>
				<td colspan="5">
					<div class="dropdown">
						<button class="btn btn-outline-secondary dropdown-toggle selectedDept" type="button"
							data-bs-toggle="dropdown" aria-expanded="false"
							value='<c:out value="${ dto.cri.dept_id }"></c:out>'>
							부서
						</button>
						<ul class="dropdown-menu deptList">
							<li><a class="dropdown-item dept" href="#">all</a></li>
							<li><a class="dropdown-item dept" href="#">dept1</a></li>
							<li><a class="dropdown-item dept" href="#">dept2</a></li>
							<li><a class="dropdown-item dept" href="#">dept3</a></li>
						</ul>
						<button class="btn btn-outline-secondary dropdown-toggle searchType" type="button"
							data-bs-toggle="dropdown" aria-expanded="false" value="T">
							제목</button>
						<ul class="dropdown-menu typeList">
							<li><a class="dropdown-item type" href="t">제목</a></li>
							<li><a class="dropdown-item type" href="c">내용</a></li>
							<li><a class="dropdown-item type" href="w">작성자</a></li>
						</ul>
						<form class="d-flex" role="search">
						<input id="searchForm" class="form-control me-2" type="search"
							placeholder="Search" aria-label="Search">
						<button id="searchBtn" class="btn btn-outline-secondary"
							type="submit">검색</button>
					</form>
					</div>
					<button type="button" class="btn btn-outline-primary new">작성</button>
				</td>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td class="no">글 번호</td>
				<td class="dept">부서</td>
				<td class="title">제목</td>
				<td class="writer">작성자</td>
				<td class="writer">비공개여부</td>
			</tr>
			<c:forEach items="${tasks}" var="task">
				<tr class="goDetail" id="${task.task_id}">
					<td><c:out value="${task.task_id}"/></td>
					<td><c:out value="${task.dept_id}"/></td>
					<td><c:out value="${task.task_title}"/></td>
					<td>홍길동</td>
					<td><c:out value="${task.t_private}"/></td>
				</tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="5">
					<nav aria-label="Page navigation example">
						<ul class="pagination justify-content-center">
							<!-- 이전버튼 출력 -->
							<c:if test="${ taskPage.number ne 0 }">
								<li class="page-item"><a class="page-link prev" href="#">이전</a></li>
							</c:if>
							<!-- 검색조건에 맞는 게시글 총량에 따라 페이징처리 -->
							<c:forEach begin="${ 0 }"
							 			end="${ totalCount }"
									   var="i">
								<li class="page-item"><a class="page-link pageNum" href="#">${ (i + 1) }</a></li>
							</c:forEach>
							<!-- 다음버튼 출력 -->
							<c:if test="${ taskPage.number ne totalCount }">
								<li class="page-item"><a class="page-link next" href="#">다음</a></li>
							</c:if>
						</ul>
					</nav>
				</td>
			</tr>
		</tfoot>
	</table>
	
	</div>

<script type="text/javascript">

	$(document).ready(function () {
		
		/* 부서 목록부분 변경 시 함께 변경하기 */
		// 선택된 부서 존재 시 해당 항목으로 출력
		if($('.selectedDept').val() == 'dept1') {
			$('.selectedDept').text('dept1');
		}
		if($('.selectedDept').val() == 'dept2') {
			$('.selectedDept').text('dept2');
		}
		if($('.selectedDept').val() == 'dept3') {
			$('.selectedDept').text('dept3');
		}
		
		// model attribute 값을 이용하기 위해 변수에 저장
		var dept_id = '${dto.cri.dept_id}' || 'all';
		var pageNum = '${dto.cri.pageNum}' || '1';
		var type = '${dto.cri.type}' || 'empty';
		var keyword = '${dto.cri.keyword}' || 'empty';
		var startPage = '${ dto.startPage }';
		var endPage = '${ dto.endPage }';
		
		// 작성 버튼 클릭 시 새 글(업무) 작성 화면으로 이동
		$('.new').on("click", function(){
			location.href="/task/new";
		});
		
		// 업무 게시글 로우 클릭 시 해당 게시글 상세 페이지로 이동
		$('tbody').on("click", ".goDetail", function(){
			// 부서별 조회 기능, 검색 기능, 페이지 넘버링 추가 시 변경 필요
			location.href="/task/pages/"+ dept_id +"/" + type + "/" + keyword + "/" + pageNum + "/" + $(this).attr("id");
		});
		
		// 페이지 넘버 이동 시 해당 페이지로 이동
		$('.pageNum').on("click", function(){
			location.href="/task/pages/"+ dept_id +"/" + type + "/" + keyword + "/" + $(this).text();
		});
		// 이전, 이후 버튼 클릭 시 이동
		$('.prev').on("click", function(e){
			e.preventDefault();
			pageNum = (eval(startPage) - 1);
			location.href="/task/pages/"+ dept_id +"/" + type + "/" + keyword + "/" + pageNum;
		})
		$('.next').on("click", function(e){
			e.preventDefault();
			pageNum = (eval(endPage) + 1);
			location.href="/task/pages/"+ dept_id +"/" + type + "/" + keyword + "/" + pageNum;
		})
		
		// 부서 버튼 클릭 시 해당 부서 결과만 검색
		$('.dept').on("click", function(){
			location.href="/task/pages/"+ $(this).text() + "/" + type + "/" + keyword + "/" + pageNum;
		});
		
		// 검색 타입 선택 반영(디폴트는 제목(T)으로 설정)
		$('.type').on("click", function(e){
			// 기본이벤트 취소
			e.preventDefault();
			
			// 탭에 해당 값 보이도록 반영, 검색 타입값 value에 저장하여 검색 시 값이 함께 넘어가도록 함
			$('.searchType').text($(this).text());
			$('.searchType').val($(this).attr("href"));
		});
		
		
		// 검색 버튼 클릭 시 이벤트
		$('#searchBtn').on("click", function(e) {
			
			e.preventDefault();
			location.href="/task/pages/"+ dept_id + "/" + $('.searchType').val() + "/"
							+ $('#searchForm').val() + "/" + pageNum;
		});
		
		
	}); //end document ready

</script>

</body>
</html>