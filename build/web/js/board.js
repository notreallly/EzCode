$(document).ready(function() {
	$('.modify_publish').on('click', function() {
		var td =$(this).parent().siblings().eq(2);
		var a = td.find("a");
		var title = td.find("input");
		console.log(a);
		console.log(title);
		console.log(td);
		$(a).hide();
		$(title).show();
		$(this).attr("type", "hidden");
		$(".submit_publish").attr("type", "submit");
	});
});
