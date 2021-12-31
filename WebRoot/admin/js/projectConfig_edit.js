/*更新*/
const UPDATE = getAminUrl('admin/CENTER/PROJECTCONFIG/UPDATE');
/*保存*/
const SAVE = getAminUrl('admin/CENTER/PROJECTCONFIG/SAVE');


$(function(){
	//回显Select选值	
	echoSelect();
});

/*初始化layui*/
layui.use([ 'form', 'layer' ], function() {
	$ = layui.jquery;
	var form = layui.form,
		layer = layui.layer
	//自定义验证规则
	form.verify({
		integer : [
			/^[1-9]\d*$/
			, '只能输入正整数'
		]
	});

	//保存
	form.on('submit(editSave)', function(obj) {
		//加载动画
   		var loading = layer.load(2, {
   		    shade: [0.2, '#fff'],
   		    content:'保存中,请稍等操作...',
   		    success: function (layerContentStyle) {
   		        layerContentStyle.find('.layui-layer-content').css({
   		            'padding-top': '35px',
   		            'text-align': 'center',
   		            'background-position': 'center top',
   		            'width': 'auto'
   		        });
   		    }
   		});
	
		var reqData = obj.field;
		reqPostHasParameter(checkSave() ? UPDATE : SAVE, reqData, function(result) {
			//关闭动画
			layer.close(loading);
			if (result.code == 200) {
				layer.msg(result.msg, {
					icon : 1,
					time : 1000
				}, function() {
					x_admin_close();

					//检查是否保存还是修改操作
					if (checkSave()) {
						//修改,更新行数据
						window.parent.updateRowData(obj);
					} else {
						//保存，重载列表
						window.parent.updateTableData();
					}


				});

			} else {
				layer.msg(result.msg, {
					icon : 2,
					time : 1000
				});
			}
		}, function(e) {
			console.log(e);
		})

		return false;
	});
});

//检查是否保存还是修改操作
function checkSave() {
	var userId = $("#id").val();
	return userId;
};

//回显Select选值
function echoSelect(){
	//echoSelectData("validFlag",$("#validFlag").attr('value'));
}