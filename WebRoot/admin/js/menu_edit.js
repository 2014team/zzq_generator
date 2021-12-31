/*更新*/
const UPDATE = getAminUrl('admin/CENTER/MENU/UPDATE');
/*保存*/
const SAVE = getAminUrl('admin/CENTER/MENU/SAVE');

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
	
	
	form.on('checkbox(batchDeleteFlag)', function (data) {
		　　console.log( data );　　//打印当前选择的信息
		    if( data.elem.checked){　　　　　　//判断当前多选框是选中还是取消选中
		       // alert('当前选中');
		        $(this).val(1)
		    }else{
		    	$(this).val(2)
		    	
		    }
		　//　var value = data.value;   //获取选中的value值
		});
	form.on('checkbox(addFlag)', function (data) {
		console.log( data );　　//打印当前选择的信息
		if( data.elem.checked){　　　　　　//判断当前多选框是选中还是取消选中
			//alert('当前选中');
			$(this).val(1)
		}else{
			$(this).val(2)
			
		}
		//　var value = data.value;   //获取选中的value值
	});
	form.on('checkbox(updateFlag)', function (data) {
		console.log( data );　　//打印当前选择的信息
		if( data.elem.checked){　　　　　　//判断当前多选框是选中还是取消选中
			//alert('当前选中');
			$(this).val(1)
		}else{
			$(this).val(2)
			
		}
		//　var value = data.value;   //获取选中的value值
	});
	form.on('checkbox(deleteFlag)', function (data) {
		console.log( data );　　//打印当前选择的信息
		if( data.elem.checked){　　　　　　//判断当前多选框是选中还是取消选中
			//alert('当前选中');
			$(this).val(1)
		}else{
			$(this).val(2)
			
		}
		//　var value = data.value;   //获取选中的value值
	});
	form.on('checkbox(searchFlag)', function (data) {
		console.log( data );　　//打印当前选择的信息
		if( data.elem.checked){　　　　　　//判断当前多选框是选中还是取消选中
			//alert('当前选中');
			$(this).val(1)
		}else{
			$(this).val(2)
			
		}
		//　var value = data.value;   //获取选中的value值
	});
	
	

	//保存
	form.on('submit(editSave)', function(obj) {
		
		var reqData = obj.field;
		reqPostHasParameter(checkSave() ? UPDATE : SAVE, reqData, function(result) {
			if (result.code == 200) {
				layer.msg(result.msg, {
					icon : 1,
					time : 1000
				}, function() {
					x_admin_close();

					/*//检查是否保存还是修改操作
					if (checkSave()) {
						//修改,更新行数据
						window.parent.updateRowData(obj);
					} else {
						//保存，重载列表
						window.parent.updateTableData();
					}*/

					//保存，重载列表
					window.parent.updateTableData();
					
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
	var menuId = $("#menuId").val();
	return menuId;
};

//回显Select选值
function echoSelect(){
	echoSelectData("validFlag",$("#validFlag").attr('value'));
	echoSelectData("menuType",$("#menuType").attr('value'));
	echoSelectData("parentId",$("#parentId").attr('value'));
}


