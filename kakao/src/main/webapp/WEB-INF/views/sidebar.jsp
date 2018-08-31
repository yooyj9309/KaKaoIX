<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script src="https://unpkg.com/vue"></script>
<!-- Sidebar -->
<div id="sidebar">
	<div class="inner">

<!-- Section -->
	
		<!-- Menu -->
		<nav id="menu">
			<header class="major">
				<h2>Menu</h2>
			</header>
			<ul>
				<li><h3><a href="${path}/main">홈으로 가기</a></h3></li>
				<c:choose>
					<c:when test="${sessionScope.userId != null}">
						<c:if test="${sessionScope.userId eq 'admin123'}">
						<li><h3><a href="${path}/form">물건 게시하기</a></h3></li>
						</c:if>
						<li><h3><a href="${path}/mypage">마이 페이지</a></h3></li>
						<li><h3><a href="${path}/logout">로그 아웃</a></h3></li>
						
					</c:when>
					<c:otherwise>
						<li><h3><a href="${path}/login">로그인</a></h3></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</nav>

<!-- Menu -->

		<!-- Footer -->
		<footer id="footer">
			<p class="copyright">
				&copy; Untitled. All rights reserved. Demo Images: <a
					href="https://unsplash.com">Unsplash</a>. Design: <a
					href="https://html5up.net">HTML5 UP</a>.
			</p>
		</footer>

	</div>
</div>


	<script src="${path}/template/assets/js/jquery.min.js"></script>
	<script src="${path}/template/assets/js/skel.min.js"></script>
	<script src="${path}/template/assets/js/util.js"></script>
	<script src="${path}/template/assets/js/main.js"></script>
	

