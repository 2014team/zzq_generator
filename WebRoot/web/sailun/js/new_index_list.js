
$(function(){
	
	var reqUrl = "/news/search/index";
	var limit = 10;
	
	//	初始化分页参数
	initPageUrl(reqUrl,limit);
	searchNews();

	function searchNews(){
		var page = $("#page").val();
		initPageUrl(reqUrl,limit);
		searchByPage(page)
	}
	
})


