var rem = Number(getComputedStyle(document.querySelector("html"),null).getPropertyValue("font-size").replace("px",""))
var element = {
	products:document.querySelector("#products"),
	stProducts:document.querySelector("#products_news .st-products > img"),
	stProductsSP:document.querySelector("#products .st-products > img"),
	times:document.querySelector("#times"),
	stTimes:document.querySelector(".st-times > img"),
	sns:document.querySelector("#SNS"),
	stSns:document.querySelector(".st-sns > img"),
	iframe:document.querySelector(".iframe"),
	newsbox:document.querySelector(".news_list-box"),
	upBtn:document.querySelector(".up-btn"),
	downBtn:document.querySelector(".down-btn"),
}

/*ニュースボタンスクロール*/
var iframe = {}
iframe.me = element.iframe;
iframe.wrap = element.newsbox;
iframe.prePage = -1;
iframe.nowPage = 0;
iframe.nextPage = 1;
function iframeScroll(showNum){
	element.newsbox.setAttribute("class","news_list-box active");
	// iframe.showNum = showNum;
	iframe.height = iframe.me.children[0].clientHeight;
	iframe.me.style.height = iframe.height + 'px';

	iframe.li = iframe.me.children[0].querySelectorAll("li");
	iframe.pageNum = iframe.li.length;
	iframe.split = {};
	iframe.split[0] = 0;
	for(var i=1;i < iframe.li.length; i++){
		// console.dir(iframe.li[i].offsetTop)
		iframe.split[i] = iframe.li[i].offsetTop
	}
	
}


iframe.me.onload = function(){
	iframeScroll();
}
// iframeScroll();
// iframe.me.contentDocument.body.onload = iframeScroll();
element.upBtn.addEventListener("click",function(){
	iframeScroll();
	var buff = iframe.pageNum >= iframe.nowPage ? iframe.nowPage + 1 : iframe.nowPage;
	if(typeof iframe.split[buff] != "undefined"){
		iframe.nextPage = buff;
		iframe.me.setAttribute("style","height:"+iframe.height+"px;transform:translateY(-"+iframe.split[iframe.nextPage]+"px)");
		iframe.nowPage = iframe.nextPage;
	}
	
})
element.downBtn.addEventListener("click",function(){
	iframeScroll();
	var buff = 1 <= iframe.nowPage ? iframe.nowPage - 1 : iframe.nowPage;
	if(typeof iframe.split[buff] != "undefined"){
		iframe.prePage = buff;
		iframe.me.setAttribute("style","height:"+iframe.height+"px;transform:translateY(-"+iframe.split[iframe.prePage]+"px)")
		iframe.nowPage = iframe.prePage;
	}
})





var es = new Eswitch("menuflag");
es.elementsBtn.forEach(function(btn){
	btn.addEventListener("click",function(){
		es.switching();
	});
});


/*resize*/
// var timeout;
var resize = (function(){
	var ww = window.innerWidth;
	var wh = window.innerHeight;

	return function(){
		ww = window.innerWidth;
		wh = window.innerHeight;
		//CSSの1remとリンク
		rem = Number(getComputedStyle(document.querySelector("html"),null).getPropertyValue("font-size").replace("px",""));
		iframeScroll();
		iframe.me.setAttribute("style","height:"+iframe.height+"px;transform:translateY(-"+iframe.split[iframe.nowPage]+"px)")

		if(769 < ww){
			//PC
			es.switching({setValue:"off"});
			// m.move();
		}else{
			//SP
		}
	}
})();
resize();
window.addEventListener("resize",resize);
/*end resize*/


function scPoint(element){ return element.getBoundingClientRect().top + window.pageYOffset; }

function fixedText(section,target){
	var wtop = window.pageYOffset;
	// if(wtop > scPoint(section)){
	// 	if(wtop < scPoint(section) + section.clientHeight){
	// 		if(wtop + target.clientHeight < scPoint(section) + section.clientHeight){
	// 			target.style.transform = "translateY("+(wtop - scPoint(section))+"px)";
	// 		}
	// 	}
	// }
}
scroll();
window.addEventListener("scroll",scroll);
function scroll(){
	var wtop = window.pageYOffset;
	var p = (window.pageYOffset / document.body.clientHeight) * 100;
	if(10 < p){
		document.querySelector(".pageTop-btn > a").setAttribute("class","active");
	}else{
		document.querySelector(".pageTop-btn > a").removeAttribute("class");
	}


	if(769 < window.innerWidth){
		//pc
		fixedText(element.products,element.stProducts);
	}else{
		//sp
		rem4 = rem * 4;
		wtop = wtop - rem4;
		if(wtop > scPoint(element.products)){
			if(wtop < scPoint(element.products) + element.products.clientHeight){
				if(wtop + element.stProductsSP.clientHeight + rem4 < scPoint(element.products) + element.products.clientHeight){
					element.stProductsSP.style.transform = "translateY("+(wtop - scPoint(element.products))+"px)";
				}
			}
		}
	}
	fixedText(element.times,element.stTimes);
	fixedText(element.sns,element.stSns);
}

/*smooth scroll*/
new SmoothScroll('[data-smooth]', {
	speed:500,
	easing: 'easeInOutQuad',
	updateURL:false,
	speedAsDuration:true
});
document.addEventListener("scrollStop",function(){
	//スムーススクロール終了時
	es.switching({setValue:"off"});
});
/*end smooth scroll*/