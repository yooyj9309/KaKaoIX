/**
 * 
 */
$('.tabs .tab').click(function() {
	if ($(this).hasClass('signin')) {
		$('.tabs .tab').removeClass('active');
		$(this).addClass('active');
		$('.cont').hide();
		$('.signin-cont').show();
	}
	if ($(this).hasClass('signup')) {
		$('.tabs .tab').removeClass('active');
		$(this).addClass('active');
		$('.cont').hide();
		$('.signup-cont').show();
	}
});
$('.container .bg').mousemove(
		function(e) {
			var amountMovedX = (e.pageX * -1 / 30);
			var amountMovedY = (e.pageY * -1 / 9);
			$(this).css('background-position',
					amountMovedX + 'px ' + amountMovedY + 'px');
		});

$(document).ready(function() {
	
	$("#loginBtn").click(function() {

		var userLoginId = jQuery.trim($("#userLoginId").val());
		var userLoginPw = $("#userLoginPw").val();
		var param = $("#loginForm").serialize();
		
		if (!validate(userLoginId)) {
			alert("ID 혹은 비밀번호가 형식에 맞지 않습니다.");
			return;
		}
		if (userLoginId == "" || userLoginId == null) {
			alert("ID를 입력해주세요.");
			return;
		}
		if (userLoginPw == "" || userLoginPw == null) {
			alert("비밀번호를 입력해주세요.");
			return;
		}
		
		$.ajax({
			type : "post",
			url : "/login",
			data : param,
			success : function(response) {
				alert(response);
				location.href = "/main"
			},
			error : function(response) {
				alert(response.responseText);
			}
		});
	});

	$("#joinBtn").click(function() {

		var userJoinId = jQuery.trim($("#userJoinId").val());
		var userJoinPw = jQuery.trim($("#userJoinPw").val());
		var checkPw = jQuery.trim($("#checkPw").val());
		
		var param = $("#joinForm").serialize();

		if (userJoinId == "" || userJoinId == null) {
			alert("ID를 입력해주세요.");
			return;
		}
		if (userJoinPw == "" || userJoinPw == null) {
			alert("비밀번호를 입력해주세요.");
			return;
		}
		if(checkPw == "" || checkPw == null){
			alert("비밀번호 확인 칸을 입력해주세요.");
			return;
		}
		if(userJoinPw != checkPw){
			alert("비밀번호가 다릅니다.");
			return;
		}
		if(!validate(userJoinId)){
			alert("아이디 형식을 확인해주세요.");
			return;
		}

		$.ajax({
			type : "post",
			url : "/join",
			data : param,
			success : function(response) {
				alert(response);
				location.href="/login";
			},
			error : function(response) {
				alert(response.responseText);
			}
		});
		
	});
	
});

function validate(str) {
	var reg_pwd = /^.*(?=.{6,20})(?=.*[a-zA-Z]).*$/;
	if (!reg_pwd.test(str)) {
		return false;
	}
	return true;
}
