<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="../includes/header.jsp" %>

<!DOCTYPE html>
<html>
<head>

    <link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
    <script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>

    <!-- timePicker -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.js"></script>

    <!— Popperjs —>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.2/dist/umd/popper.min.js"
            crossorigin="anonymous"></script>
    <!— Tempus Dominus JavaScript —>
    <script src="https://cdn.jsdelivr.net/gh/Eonasdan/tempus-dominus@master/dist/js/tempus-dominus.js"
            crossorigin="anonymous"></script>

    <!— Tempus Dominus Styles —>
    <link href="https://cdn.jsdelivr.net/gh/Eonasdan/tempus-dominus@master/dist/css/tempus-dominus.css"
          rel="stylesheet" crossorigin="anonymous">

    <meta charset="UTF-8">
    <title>Book your meetings here</title>
</head>
<body>
<div class="container">
    <div class="col-12"><h2>회의실 등록</h2></div>


    <div class="insertForm" id="insertForm">
        <table>
            <div class="form-body">
                <tr>
                    <div class="form-group">
                        <td><label>등록직원 번호(삭제?)</label></td>
                        <%--<td><input class="form-control" name="emp_id" type="text" readonly="readonly"
                                   value="<sec:authentication property="principal.username"/>"></td>--%>
                        <!-- <input name="emp_id" type="text" readonly="readonly" value="사원번호 자동생성"></td> -->
                    </div>
                </tr>
                <tr>
                    <td>
                        <sec:authentication property="principal" var="pinfo"/>
                        <label>등록직원 부서(삭제?)</label></td>
                    <td><input class="form-control" name="dept_id" value="${pinfo.employee.dept_id }" readonly="readonly"/></td>
                </tr>

                <tr>
                    <td><label>회의실 이름</label></td>
                    <td><input class="form-control" name="rsc_name" type="text" placeholder="회의실 이름을 입력하세요"><br></td>
                </tr>
                <tr>
                    <td><label>회의실 위치</label></td>
                    <td><input class="form-control" name="rsc_loc" type="text" placeholder="회의실 위치를 입력하세요"><br></td>
                </tr>
                <tr>
                    <td><label>회의실 수용인원</label></td>
                    <td><input class="form-control" name="rsc_amount" type="text" placeholder="회의실 수용인원을 입력하세요"><br></td>
                </tr>

                <tr>
                    <td><input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"> </td>
                </tr>
        </table>

        <button id="submitBtn" type="button" class="btn btn-primary">등록</button>
    </div> <!-- end form-body class -->

</div> <!-- end insertForm class -->


<!-- <b><input id="datetimepicker" type="text" name="book_date"><br></b> -->
</div> <!-- end container -->



<!--javaScript 스크립트-->
<script type="text/javascript" src="/resources/js/booking.js"></script>
<script type="text/javascript">



    $(document).ready(function(){

        //console.log(bookingService);

        //날짜 선택 UI(datepicker)
        $(function() {
            $( "#Date" ).datepicker({
                dateFormat: "yy-mm-dd"
            });
        });



        //예약자원 등록폼 데이터 -> 업데이트 필요
        var form = $(".insertForm");
        var book_id = form.find("input[name='book_id']");
        var rsc_id = form.find("select[name='rsc_id']");
        var emp_id = form.find("input[name='emp_id']");
        var dept_id = form.find("input[name='dept_id']");
        var book_date = form.find("input[name='book_date']");
        var book_begin = form.find("select[name='book_begin']");
        var book_title = form.find("input[name='book_title']");
        var book_content = form.find("textarea[name='book_content']");

        var csrf_token = $("meta[name='_csrf']").attr("content");
        var csrf_header = $("meta[name='_csrf_header']").attr("content");

        //제출 버튼 클릭 시 bookingData에 데이터 JSON형식으로 저장, 예약 프로세스 진행 후 결과값 출력
        $('#submitBtn').on("click", function(e){
            var bookingData = {
                book_id: book_id.val(), //자동생성이라 삭제 예정
                rsc_id: rsc_id.val(),
                emp_id: emp_id.val(), //로그인 세션으로 구현
                dept_id: dept_id.val(), //로그인 세션으로 구현
                book_begin: book_begin.val(),
                book_date: book_date.val(),
                book_title: book_title.val(),
                book_content: book_content.val(),
                csrf_token:csrf_token,
                csrf_header:csrf_header
            };

            bookingService.insertBooking(bookingData, function(result){
                if(result == "success"){
                    alert("예약되었습니다");
                    $(location).attr('href', '/booking/calendar/view');
                }else if(result =="fail"){
                    alert("이미 예약된 시간입니다")
                }
            })//end insertBooking

        });

    });




</script>
</body>
</html>