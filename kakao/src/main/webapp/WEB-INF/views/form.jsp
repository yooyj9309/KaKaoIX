<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

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

				<form id="writeForm" encType="multipart/form-data">
					<div class="row uniform">
						<div class="12u$"></div>
						<div class="12u$">
							<input type="file" name="imgFile" id="productImg" placeholder="사진 업로드 하기">
						</div>
						<div class="12u$">
							<input type="text" name="productName" id="writeName" placeholder="이름을 입력해주세요." required />
						</div>
						<div class="12u$">
							<input type="text" onkeydown='return onlyNumber(event)'
								onkeyup='removeChar(event)' maxlength="10" id="writePrice"
								required="required" placeholder="가격을 선택해주세요 (1~10000)"
								name="productPrice" class="form-control">
						</div>

						<!-- Break -->
						<div class="12u$">
							<textarea name="productDescribe" style="resize: none;" id="writeContent" placeholder="세부사항을 입력해주세요.(1000자 이내)" maxlength="1000" cols="40" rows="10"></textarea>
						</div>
						<!-- Break -->
						<div class="12u$">
							<ul class="actions">
								<li><input type="button" id="btnWrite" value="게시하기"
									class="special" /></li>
								<li><input type="reset" value="리셋" /></li>
							</ul>
						</div>
					</div>
				</form>

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
	<script src="${path}/js/main/write.js"></script>
</body>
</html>
