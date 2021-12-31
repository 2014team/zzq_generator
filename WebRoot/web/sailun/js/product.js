
$(function(){
	var reqUrl = "/product/search/by/page";
	var limit = 12;
	//	初始化分页参数
	initPageUrl(reqUrl,limit);
	
	$("#search_id").click(function(){
		var searchValue = $("#searchValue").val();
		var page = $("#page").val();
		initPageUrl(reqUrl,limit,searchValue);
		searchByPage(page)
	});
	

	//回车搜索
	$('#searchValue').bind('keydown', function (event) {
	  var event = window.event || arguments.callee.caller.arguments[0];
	  if (event.keyCode == 13){
		  var searchValue = $("#searchValue").val();
		  if(!searchValue){
			  return false;
		  }
			var page = $("#page").val();
			initPageUrl(reqUrl,limit,searchValue);
			searchByPage(page)
	  }
	});
	
})


