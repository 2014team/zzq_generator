function Eswitch(label){
	this.label = label;
	this.selectorBtn = "data-btn-"+label;
	this.elementsBtn = Array.prototype.slice.call(document.querySelectorAll("["+this.selectorBtn+"]"),0);
	this.selector = "data-"+label;
	this.elements = Array.prototype.slice.call(document.querySelectorAll("["+this.selector+"]"),0);
}
Eswitch.prototype = {
	switching:function(arg){
		var me = this;
		arg = typeof arg == "object" ? arg : {};
		arg.btn = typeof arg.btn == "undefined" ? "all" : arg.btn;
		var flag = typeof arg.setValue == "undefined" ? true : false;
		if(flag){
			arg.setValue = "off";
			/*btn*/
			if(arg.btn == "one"){
				arg.setValue = arg.event.currentTarget.getAttribute(me.selectorBtn) == "off" ? "on" : "off";
				console.log(arg.event.currentTarget.getAttribute(me.selectorBtn));
				arg.event.currentTarget.setAttribute(me.selectorBtn,arg.setValue);
			}
			if(arg.btn == "all"){
				me.elementsBtn.forEach(function(item){
					arg.setValue = item.getAttribute(me.selectorBtn) == "off" ? "on" : "off";
					item.setAttribute(me.selectorBtn,arg.setValue);
				});
			}
			/*not btn*/
			me.elements.forEach(function(item){
				arg.setValue = item.getAttribute(me.selector) == "off" ? "on" : "off";
				item.setAttribute(me.selector,arg.setValue);
			});
		}else{
			/*btn*/
			if(arg.btn == "one"){
				arg.event.currentTarget.setAttribute(me.selectorBtn,arg.setValue);
			}
			if(arg.btn == "all"){
				me.elementsBtn.forEach(function(item){
					item.setAttribute(me.selectorBtn,arg.setValue);
				});
			}
			/*not btn*/
			me.elements.forEach(function(item){
				item.setAttribute(me.selector,arg.setValue);
			});
		}
	}
}