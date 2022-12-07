<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<!DOCTYPE html>
<html>
<head>
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
<script type="text/javascript" src="/resources/js/schedule.js"></script>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.js"></script>
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.css">
<link rel="stylesheet" href="/resources/css/schedule.css">
<link rel='stylesheet'
	href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css'>
<meta charset="UTF-8">
<title>일정 관리</title>
</head>
<body class="container">

	<div class="search" role="search">
		<input class="form-control me-2" type="search" placeholder="일정 검색"
			aria-label="Search">
		<ul class="searchResult">

		</ul>
	</div>

	<div class="table-responsive">
		<table>
			<tr>
				<td id="mainTable">
					<table class="table align-middle">
						<thead id="calendarHead">
							<tr>
								<td class="noPadding" colspan="7">
									<div>
										<ul class="nav nav-tabs">
											<li class="nav-item"><a id="month"
												class="nav-link active" aria-current="page" href="/month">월별</a></li>
											<li class="nav-item" aria-current="page"><a id="week"
												class="nav-link" href="/week">주별</a></li>
											<li class="nav-item" aria-current="page"><a id="day"
												class="nav-link" href="/day">일별</a></li>
										</ul>
									</div>
									<div class="dropdown">
										<button
											class="btn btn-outline-secondary dropdown-toggle sch_group"
											type="button" data-bs-toggle="dropdown" aria-expanded="false"
											value="all">그룹 선택</button>
										<ul class="dropdown-menu">
											<li><a class="dropdown-item group" href="my">내 일정</a></li>
											<li><a class="dropdown-item group" href="dept">부서 일정</a></li>
											<li><a class="dropdown-item group" href="comp">회사 일정</a></li>
										</ul>
									</div>
									<div class="newSchedule">
										<button type="button" class="btn btn-outline-secondary">일정
											등록</button>
									</div>
									<nav aria-label="Page navigation">
										<ul class="pagination">
											<li class="page-item"><a class="page-link" href="#"
												aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
											</a></li>
											<li class="page-item refDate"><a class="page-link"
												href="#"></a>
												<p></p></li>
											<li class="page-item"><a class="page-link" href="#"
												aria-label="Next"> <span aria-hidden="true">&raquo;</span>
											</a></li>
										</ul>
									</nav>
								</td>

							</tr>
							<tr class="dayOfWeek"></tr>
						</thead>
						<tbody id="calendarBody">

						</tbody>

					</table>
				</td>
				<td id="memoTable">
						<label for="memoArea" class="form-label">메모</label>
						<textarea class="form-control" id="memoArea"
							rows="25"></textarea>
				<td>
			</tr>

		</table>

	</div>



	<!-- 일정 Modal -->
	<div class="modal fade" id="scheduleModal" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
				</div>
				<div class="modal-body">
					<label>일정 그룹 &nbsp;</label>
					<div class="form-group">
						<input type="hidden" class="form-control"
							name="sch_id" id="sch_id" value=""/>
					</div>
					<button
						class="btn btn-outline-secondary dropdown-toggle selectedGroup"
						type="button" data-bs-toggle="dropdown" aria-expanded="false"
						value="my">내 일정</button>
					<ul class="dropdown-menu">
						<li><a class="dropdown-item addGroup" href="my">내 일정</a></li>
						<li><a class="dropdown-item addGroup" href="dept">부서 일정</a></li>
						<li><a class="dropdown-item addGroup" href="comp">회사 일정</a></li>
					</ul>
					<div class="form-group">
						<label>시작일</label> <input type="text" class="form-control"
							name="start_date" id="start_date">
						<label>시작시간</label> <input type="text" class="form-control"
							name="start_time" id="start_time" placeholder="ex) 09:30">
					</div>
					<div class="form-group">
						<label>종료일</label> <input type="text" class="form-control"
							name="end_date" id="end_date">
						<label>종료시간</label> <input type="text" class="form-control"
							name="end_time" id="end_time" placeholder="ex) 21:30">
					</div>
					<div class="form-group">
						<label>제목</label> <input class="form-control" name="sch_title">
					</div>
					<div class="form-group">
						<label>내용</label> <input class="form-control" name="sch_content">
					</div>
				</div>
				<div class="modal-footer">
					<button id='schInsertBtn' type="button" class="btn btn-primary">등록</button>
					<button id='schUpdateBtn' type="button" class="btn btn-primary">수정</button>
					<button id='schDeleteBtn' type="button" class="btn btn-danger">삭제</button>
					<button id='modalCloseBtn' type="button"
						class="btn btn-outline-secondary">Close</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->

<script type="text/javascript">
// 일정 CRUD 모달 관련
$(document).ready(function() {
			
    /* * * * * * * * * * * * * * * * * * *
              일정 CRUD, 검색
    * * * * * * * * * * * * * * * * * * */
    
    // 폼 선택자를 변수에 저장
    var sch_id = $('.modal').find("input[name='sch_id']");
    var sch_group = $('.modal').find(".selectedGroup");
    var start_date= $('.modal').find("input[name='start_date']");
    var start_time= $('.modal').find("input[name='start_time']");
    var end_date= $('.modal').find("input[name='end_date']");
    var end_time= $('.modal').find("input[name='end_time']");
    var sch_title= $('.modal').find("input[name='sch_title']");
    var sch_content = $('.modal').find("input[name='sch_content']");
    
    // 시작일, 종료일을 캘린더를 통해 선택 가능하도록 함
    $(function(){
    	// 날짜를 yyyy-MM-dd 형태로 출력하도록 지정
    	$('#start_date').datepicker({
    		dateFormat: 'yy-mm-dd',
    		onSelect: function (date) {
    			// start_date 이전 날짜 선택 불가하게 지정
    			var endDate = $('#end_date');
    			var startDate = $(this).datepicker('getDate');
    			var minDate = $(this).datepicker('getDate');
    			endDate.datepicker('setDate', minDate);
    			startDate.setDate(startDate.getDate() + 30);
    			endDate.datepicker('option', 'maxDate', startDate);
    			endDate.datepicker('option', 'minDate', minDate)
    		}
    	});
    	
    	$('#end_date').datepicker({ dateFormat: 'yy-mm-dd' });
    	
    	// 시작시간, 종료시간 선택 timepicker
        $('#start_time').timepicker({
        	// 오전, 오후로 나누지 않고 24시 모두 출력 가능
        	timeFormat: 'HH:mm',
        	interval: 60,
       		minTime: '00:00am',
        	maxTime: '23:00pm',
        	defaultTime: '09',
        	startTime: '00:00',
        	dynamic: true,
        	dropdown: true,
        	scrollbar: true
    	});
        $('#end_time').timepicker({
        	timeFormat: 'HH:mm',
        	interval: 60,
       		minTime: '00:00am',
        	maxTime: '23:00pm',
        	defaultTime: '09',
        	startTime: '00:00',
        	dynamic: true,
        	dropdown: true,
        	scrollbar: true
    	});
    }); // 시작일, 종료일 선택 datepicker, timepicker
    
    
    

    // 일정 등록 버튼 클릭 시 이벤트
    $('.newSchedule').on("click", function() {
        
        // 불필요한 버튼 숨김
        $('.modal-footer *').show();
        $('.modal-footer *').not('#modalCloseBtn').not('#schInsertBtn').hide();
        
        // 모달창 내용 비우고 현재 시간 기입
        $('.modal').find("input").val("");
        $('input[name*="date"]').val($('.refDate p').text());
        
        // 참가자 목록에 자신을 기본으로 넣도록 함
        
        // 모달창 출력
        $('.modal').modal("show");

    }); // end newSchedule onclick
    // 일정 등록 요청 생성
    $('#schInsertBtn').on("click", function() {
        
        let schedule = {
            sch_group: sch_group.val(),
            start_date: start_date.val() + ' ' + start_time.val(),
            end_date: end_date.val() + ' ' + end_time.val(),
            sch_title: sch_title.val(),
            sch_content: sch_content.val()
        }
        
        console.log(schedule.start_date);
        
        scheduleService.insertSchedule(schedule, function(result){
            
            
            // 등록 완료 시 모달 숨김
            $('.modal').modal("hide");
            
         	// 등록 성공 안내
            if (result == 'success') {
                alert('정상적으로 등록되었습니다.');
            }
         	
         	// 등록 스케쥴의 시작일로 선택일자 변경
         	selectedDate = schedule.start_date;
            
            // 등록한 일정 표시를 위해 캘린더영역 새로 불러오기
            $("#month").trigger("click");
            
         	
            
        }) // end insertSchedule
        
    }); // end schInsertBtn onclick
    
    
    // 개별 일정 클릭 시 이벤트
    // eachSch onclick
    $('#calendarBody').on("click", '.eachSch', function(e) {
    	$('.modal').find("input").val("");
        
        // 해당 일정 PK값 변수에 저장
        let selected_sch_id = $(this).attr("id");
        
        // 불필요한 버튼 숨긴 후 모달 표시
        $('.modal-footer *').show();
        $('#schInsertBtn').hide();
        $('.modal').modal("show");
        
        // 선택한 일정 상세보기
        scheduleService.getSchedule(selected_sch_id, function(result){
            
        	sch_id.val(selected_sch_id);
            sch_group.val(result.sch_group);
            start_date.val((result.start_date.split(" "))[0]);
            start_time.val((result.start_date.split(" "))[1].substr(0, 5));
            end_date.val((result.end_date.split(" "))[0]);
            end_time.val((result.end_date.split(" "))[1].substr(0, 5));
            sch_title.val(result.sch_title);
            sch_content.val(result.sch_content);
            
        }) // end getSchedule
        
        
        
    }); // end each onclick
    
 	// 일정 수정하기
    $('#schUpdateBtn').on("click", function(e) {
        
    	console.log(sch_id);
        let schedule = {
                sch_id: eval(sch_id.val()),
                sch_group: sch_group.val(),
                start_date: start_date.val(),
                end_date: end_date.val(),
                sch_title: sch_title.val(),
                sch_content: sch_content.val()
        }
        
        scheduleService.updateSchedule(schedule, function(result) {
            
            // 수정 완료 시 모달 숨김
            $('.modal').modal("hide");
            
         	// 수정 성공 안내
            if (result == 'success') {
                alert('정상적으로 수정되었습니다.');
            }
            
            // 수정 반영을 위해 캘린더영역 새로 불러오기
            $("#month").trigger("click");
            
        });// end updateSchedule
        
    }); // end schUpdate onclick
    
    $('#schDeleteBtn').on("click", function(){
        
        scheduleService.deleteSchedule(eval(sch_id.val()), function(result) {
             
             // 작업 완료 시 모달 숨김
             $('.modal').modal("hide");
             
          	// 삭제 성공 안내
             if (result == 'success') {
                 alert('정상적으로 삭제되었습니다.');
             }
             
             // 삭제 반영을 위해 캘린더영역 새로 불러오기
             $("#month").trigger("click");
             
          	
         });// end deleteSchedule
         
     });
    
    
    
 	// 일정 모달에서 그룹 선택 시 값 변경
    $('.addGroup').on("click", function(e){
        e.preventDefault();

        // 그룹 선택 결과 반영
        $('.selectedGroup').val($(this).attr("href"));
        $('.selectedGroup').text($(this).text());

    });
    
    
    // 모달창 닫기 이벤트
    $("#modalCloseBtn").on("click", function(e){
        $('.modal').modal("hide");
    });
    $(".close").on("click", function(e){
        $('.modal').modal("hide");
    });
    
    
    
    // 일정 검색 기능
    $('.search input').on("keyup", function(e){
    	//기본 이벤트 취소처리
    	e.preventDefault();
    	
    	// 입력값 저장
    	var text = $(this).val();
    	
    	// 입력값이 없을 경우 function 종료
    	if(text == '' || text == null) {
    		$('.searchResult').html('');
    		return;
		}
    	
    	// 검색 결과값을 저장할 변수 선언
    	let resultList = '';
    	
    	// 입력값을 검색어로 검색 실행
    	scheduleService.search(text, function(result){
    	
    		if (result.length >= 1) {
    			result.forEach((item) => {
    				
    				// 일정 시작일 저장
    				let date = new Date(item.start_date);
	    			
    				// 일정 PK, 일정 제목, 일정 시작일 출력
	    			resultList += '<li class="' + item.start_date + '">';
	    			resultList += item.sch_title + ' ' + date.getFullYear() + '년 '+ (date.getMonth()+1) + '월 ' 
	    							+ date.getDate() + '일';
	    			resultList += '</li>';
	    			
	    		}); // end forEach()
	    	
    		} // end if
    		
    	// 결과 목록을 출력
    	$('.searchResult').html(resultList);
    	
    	}) // end search()
    }); // end input keyup event

    
    /* * * * * * * * * * * * * * * * * * *
        버튼 클릭 시 직원 스케쥴 출력 이벤트
    * * * * * * * * * * * * * * * * * * */
        $('#calendarBody').on("click", "a", function(e) {
            e.preventDefault();
    
            let content = '';

            let deptid = null;
            scheduleService.getWorkerList(deptid, function(result){
               
                result.forEach((item) => {
    
                    let startarr1 = item.start_time.split(' ');
                    let startarr2 = startarr1[1].split(':');
                    let endarr1 = item.end_time.split(' ');
                    let endarr2 = endarr1[1].split(':');
    
                    content += item.name + ' (' + startarr2[0] + ':' + startarr2[1] + '-' + endarr2[0] + ':' + endarr2[1] + ') ';
                    
                }); // end forEach 
    
                $('#workers').attr("data-bs-content", content);
                $('#workers').popover("show");
                
            }); // end getWorkerList()      
    
        }); // 버튼 클릭 시 직원 스케쥴 출력
    
    
});// 일정 CRUD 모달


	// 메모 작성 및 저장
	$(document).ready(function(){
		
		// 페이지 로드 시 DB에서 메모 불러오기
		scheduleService.getMemo(function(result){
			$('#memoArea').val(result);
		});
		
		// 메모장에 메모 입력 시 저장 버튼 없이도 데이터 저장(업데이트)
		$('#memoArea').on("keyup", function(){
			var memo = $(this).val();
			
			scheduleService.updateMemo(memo, function (result){
				
			});
			
		});
		
		
	}); // 메모 작성 및 저장
</script>


</body>
<%@ include file="/WEB-INF/views/includes/footer.jsp"%>
</html>