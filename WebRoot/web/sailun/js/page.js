var page_url="";
var limit="";
var params="";
function initPageUrl(page_url,limit,param){
	this.page_url = page_url;
	this.limit = limit;
	this.params = param;
}
function searchByPage(page){
	$.ajax({
		url : this.page_url,
		type : "post",
		data:{"page":page,"limit":this.limit,"searchKey":this.params},
		cache:false,
		dataType : "text",
		success : function(data) {
			$("#list_div").html(data);
		},
		error : function(err) {
			console.log(err)
		}
	});
	
}