$(document).ready(function() {
	$("#btnWrite").click(function() {
		var formData = new FormData($("#writeForm")[0]);
		var subject = $("#writeName").val();
		var content = $("#writeContent").val();
		var price = parseInt($("#writePrice").val());
		
		if (subject == "" || subject == null) {
			alert("상품 이름을 입력해주세요.");
			return;
		}
		if (price == "" || price < 0) {
			alert("가격을 확인해주세요.");
			return;
		}
		if (content == "" || content == null) {
			alert("세부 사항을 입력 해주세요.");
			return;
		}
		
		$.ajax({
			type : "post",
			url : "form",
			data : formData,
			processData : false,
		    contentType : false,
			success : function(response) {
				alert(response);
				location.href = "/main";
			},
			error : function(response){
				alert(response.responseText);
			}
		});
	});
});
function onlyNumber(event){
    event = event || window.event;
    var keyID = (event.which) ? event.which : event.keyCode;
    if ( (keyID >= 48 && keyID <= 57) || (keyID >= 96 && keyID <= 105) || keyID == 8 || keyID == 46 || keyID == 37 || keyID == 39 ) 
        return;
    else
        return false;
}
 
function removeChar(event) {
    event = event || window.event;
    var keyID = (event.which) ? event.which : event.keyCode;
    if ( keyID == 8 || keyID == 46 || keyID == 37 || keyID == 39 ) 
        return;
    else
        event.target.value = event.target.value.replace(/[^0-9]/g, "");
}