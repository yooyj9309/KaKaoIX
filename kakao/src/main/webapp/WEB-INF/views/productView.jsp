<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../favicon.ico">

<title>KAKAO FRIENDS</title>

<!-- Bootstrap core CSS -->
<link href="../css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link rel="stylesheet" href="${path}/template/assets/css/main.css" />

<script src="https://unpkg.com/vue"></script>

</head>

<body>
	<div id="wrapper">

		<!-- Main -->
		<div id="main">
			<div class="inner">

				<!-- Header -->
				<header id="header">
					<a href="${path}/main" class="logo"><strong>KAKAO FRIENDS</strong></a>
				</header>

				<h1 class="page-header">${product.productName}</h1>
				<section id="banner">
					<div class="content">
						<span class="image object"> <img class="image" width="500"
							height="350" src="${product.imgPath}" alt="" />
						</span>
						<header>
							<h2>가격 : ${product.productPrice}</h2>
						</header>
						<h2>세부 사항</h2>

						<textarea style="resize: none; overflow: auto" readonly="readonly"
							cols="40" rows="10">${product.productDescribe}</textarea>
						<ul class="actions">
							<c:if test="${(sessionScope.userId != null)}">
								<input type="button" id="btnBuy" value="구매하기" class="button big float-left">
							</c:if>
						</ul>

					</div>

				</section>
			</div>
		</div>

		<%@ include file="sidebar.jsp"%>

	</div>

	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="../js/bootstrap.min.js"></script>
	<!-- Just to make our placeholder images work. Don't actually copy the next line! -->

	<script src="${path}/template/assets/js/jquery.min.js"></script>
	<script src="${path}/template/assets/js/skel.min.js"></script>
	<script src="${path}/template/assets/js/util.js"></script>
	<script src="${path}/template/assets/js/main.js"></script>
	<script>
	$(document).ready(function() {
		$("#btnBuy").click(function() {
		
			var productId = "${product.productId}";
			var url = "../buy/"+productId;
			if (confirm("구매하시겠습니까?")){
				$.ajax({
					type : "post",
					url : url,
					processData : false,
					contentType : false,
					success : function(response) {
						alert(response);
						location.href = "/main";
					},
					error : function(response) {
						alert(response.responseText);
					}
				});
			}
		});
	});
	</script>
</body>
</html>
