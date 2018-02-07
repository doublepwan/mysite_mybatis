<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link
	href="${pageContext.request.contextPath }/assets/css/guestbook.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>

<title>방명록 ajax</title>
</head>
<body>

	<div id="container">

		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />

		<div id="wrapper">
			<div id="content">
				<div id="guestbook">

					<form action="${pageContext.request.contextPath }/guestbook/insert"
						method="post">
						<!-- <input type="hidden" name="a" value="insert"> -->

						<table>
							<tr>
								<td>이름</td>
								<td><input type="text" name="name" /></td>
								<td>비밀번호</td>
								<td><input type="password" name="password" /></td>
							</tr>
							<tr>
								<td colspan=4><textarea name="content" id="content"></textarea></td>
							</tr>
							<tr>
								<td colspan=4 align=right><input type="submit" VALUE=" 확인 " /></td>
							</tr>
						</table>
					</form>

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

</body>

<script type="text/javascript">
		var page = 1;
	$(document).ready(function() {
		
		
		$.ajax({

			url : "${pageContext.request.contextPath }/guest/api/listajax",
			type : "post",
			//contentType : "application/json",
			data : {page: page},

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

	});
	
	
	$("#btnNext").on("click", function(){
		page = page + 1;
		console.log(page);
		
		$.ajax({

			url : "${pageContext.request.contextPath }/guest/api/listajax",
			type : "post",
			//contentType : "application/json",
			data : {page: page},

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
		
	});
	

	function render(GuestBookVo, upDown) {

		var str = "";
		str += "<li>";
		str += "	<table>";
		str += "		<tr>";
		str += "			<td>" + GuestBookVo.no + "</td>";
		str += "			<td>" + GuestBookVo.name + "</td>";
		str += "			<td>" + GuestBookVo.regDate + "</td>";
		str += "			<td><a href=''>삭제</a></td>";
		str += "		</tr>";
		str += "		<tr>";
		str += "			<td colspan=4>" + GuestBookVo.content + "</td>";
		str += "		</tr>";
		str += "	</table>";
		str += "<br>";
		str += "</li>";
		
		if(upDown == "up"){
			$("#listArea").prepend(str);
		}
		else if (upDown == "down"){
			$("#listArea").append(str);
		}
		else{
			console.log("upDown 오류");
		}
	}
</script>

</html>