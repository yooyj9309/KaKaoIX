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
					<a href="${path}/main" class="logo"> 
					<strong>KAKAO FRIENDS</strong></a>
				</header>

				<c:if test="${sessionScope.userId != null}">
					<h3 class="page-header">구매 내역</h3>
					<table class="table table-striped">
						<thead>
							<tr>
								<th>상품명</th>
								<th>가격</th>
								<th>갯수</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${!empty productList}">
								<c:forEach var="product" items="${productList}">
									<tr>
										<td><a href="/product/${product.productId}">${product.productName}
										</a></td>
										<td>${product.productPrice}</td>
										<td>${product.amount}</td>
									</tr>
								</c:forEach>
							</c:if>

						</tbody>
					</table>
					<table class="table table-striped">
						<c:if test="${empty productList}">
							<tr>
								<td>구매하신 내역이 없습니다.</td>
							</tr>
						</c:if>
					</table>

				</c:if>

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

</body>
</html>
