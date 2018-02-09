<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link href="${pageContext.request.contextPath }/assets/css/guestbook.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/bootstrap/js/bootstrap.min.js"></script>	

<title>방명록 ajax</title>
</head>
<body>

	<div id="container">

		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />

		<div id="wrapper">
			<div id="content">
				<div id="guestbook">

						<!-- <input type="hidden" name="a" value="insert"> -->

						<table>
							<tr>
								<td>이름</td>
								<td><input id="name" type="text" name="name" /></td>
								<td>비밀번호</td>
								<td><input id="password" type="password" name="password" /></td>
							</tr>
							<tr>
								<td colspan=4><textarea name="content" id="content"></textarea></td>
							</tr>
							<tr>
								<td colspan=4 align=right><input id="btnOk" type="submit" VALUE=" 확인 " /></td>
							</tr>
						</table>
					

					<ul id="listArea">

					</ul>
					<input type="button" value="다음글 5개 가져오기" id="btnNext" />

				</div>
				<!-- /guestbook -->
			</div>
			<!-- /content -->
		</div>
		<!-- /wrapper -->

		<c:import url="/WEB-INF/views/includes/footer.jsp" />

	</div>
	<!-- /container -->
	
	
	<!-- 삭제팝업(모달)창 -->
	<div class="modal fade" id="del-pop">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title">방명록삭제</h4>
				</div>
				<div class="modal-body">
					<label>비밀번호</label>
					<input type="password" name="modalPassword" id="modalPassword"><br>	
					<input type="text" name="modalPassword" value="" id="modalNo"> <br>	
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default"  data-dismiss="modal">취소</button>
					<button type="button" class="btn btn-danger" id="btn_del">삭제</button>
				</div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->

</body>

<script type="text/javascript">

		var page = 1;
		
	$(document).ready(function() {
		
		fetchList();

	});
	
	
	$("#btnNext").on("click", function(){
		page = page + 1;
		console.log(page);
		
		fetchList();
	});
	
	  /* $("#btnDel").on("click", function() {
		console.log("삭제클릭");
		$("#del-pop").modal();
	});  */
	
	 
	  $("ul").on("click",".btnDel" , function(){
		  var no = $(this).data("no");
			
			console.log(no);
			$("#del-pop").modal();
	  });
	  
	
	$("#btnOk").on("click", function(){
		console.log("확인클릭");
		var name = $("[name = name]").val();
		var password = $("[name = password]").val();
		var content = $("[name = content]").val();
		
		var guestBookVo = {
				name: name,
				password: password,
				content: content
				}
		
		$.ajax({

			url : "${pageContext.request.contextPath }/guest/api/insertajax",
			type : "post",
			contentType : "application/json",
			data :JSON.stringify(guestBookVo),

			dataType : "json",
			success : function(guestBookList) {
				/*성공시 처리해야될 코드 작성*/
				console.log(guestBookList);
					render(guestBookList[0], "up");
				
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
		
	});
		
	

	function render(GuestBookVo, upDown) {

		var str = "";
		str += "<li>";
		str += "	<table>";
		str += "		<tr>";
		str += "			<td>" + GuestBookVo.no + "</td>";
		str += "			<td>" + GuestBookVo.name + "</td>";
		str += "			<td>" + GuestBookVo.regDate + "</td>";
		str += "			<td>";
		str += "				<input type='button' class='btnDel' value='삭제' data-no='"+ GuestBookVo.no +"'>";
		str += "			</td>";
		str += "		</tr>";
		str += "		<tr>";
		str += "			<td colspan=4>" + GuestBookVo.content + "</td>";
		str += "		</tr>";
		str += "	</table>";
		str += "<br>";
		str += "</li>";

		//"<input type="button" id="btnDel" value="삭제">";

		if (upDown == "up") {
			$("#listArea").prepend(str);
		} else if (upDown == "down") {
			$("#listArea").append(str);
		} else {
			console.log("upDown 오류");
		}
	}

	function fetchList() {
		$.ajax({

			url : "${pageContext.request.contextPath }/guest/api/listajax",
			type : "post",
			//contentType : "application/json",
			data : {
				page : page
			},

			dataType : "json",
			success : function(guestBookList) {
				/*성공시 처리해야될 코드 작성*/
				console.log(guestBookList);

				for (var i = 0; i < guestBookList.length; i++) {
					render(guestBookList[i], "down");
				}
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	}
	
	
</script>

</html>