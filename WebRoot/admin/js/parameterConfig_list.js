/*列表数据*/
const LIST = getAminUrl('admin/CENTER/PARAMETERCONFIG/LIST')
/*列表删除*/
const DELETE = getAminUrl('admin/CENTER/PARAMETERCONFIG/DELETE');
/*编辑*/
const EDIT = getAminUrl('admin/CENTER/PARAMETERCONFIG/EDIT');
/*批量删除*/
const BATCH_DELETE = getAminUrl('admin/CENTER/PARAMETERCONFIG/BATCH/DELETE');
/*查找单个对象*/
const GET = getAminUrl('admin/CENTER/PARAMETERCONFIG/GET');
//行对象
var rowObj = "";

/*初始化layui*/
layui.use([ 'table', 'form', 'laydate' ], function() {
	    var table  = layui.table,
		form = layui.form,
		laydate = layui.laydate;

  //年月范围选择
	laydate.render({
		elem : '#createDateStr'
		,type: 'datetime'
		,range : '~'
	});
	
	/*日历选择器*/
	laydate.render({
		elem : '#startDate',
		done : function(value, date) { //监听日期被切换
			$("#startDate").val(value)
		}
	});
	laydate.render({
		elem : '#endDate', //指定元素
		done : function(value, date, endDate) {
			$("#endDate").val(value)
		}
	});

	table.render({
		elem : '#table_list',
		url : LIST,
		toolbar : '#toolbar',
		method : "post",
		page : { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
			layout : [ 'limit', 'count', 'prev', 'page', 'next', 'skip' ], //自定义分页布局 //,curr: 5 //设定初始在第 5 页
			limit : 10, //每页显示的条数
			groups : 5, //步长
			first : '首页', //不显示首页
			last : '尾页', //不显示尾页
			prev : '上一页',
			next : '下一页'
		},
		cols : [ [
			{
				checkbox : true
			},
			{
				field : 'indexId',
				title : '序号',
				type : 'numbers',
				width : 75,
				sort : true,
			}
			,{
				field : 'remarks' ,
				title : '备注名称' ,
			}
				,{
					field : 'paramKey' ,
					title : '参数key' ,
				}
				,{
					field : 'paramValue' ,
					title : '参数value' ,
				}
				,{
					field : 'projectConfigIdName' ,
					title : '所属项目' ,
				}
				,{
					field : 'orderId' ,
					title : '排序号' ,
				}
				,{
					field : 'status' ,
					title : '状态' ,
					templet : function(d) {
						 if(d.status == 0){
							 return "有效";
						 }else if(d.status == 1){
							 return "无效";
						 }else{
							 return ""
						 }
					    }
				}
				
				, {
					field : 'createDate' ,
					title : '创建日期' ,
					templet : function(d) {
					return date.toDateString(d.createDate, 'yyyy-MM-dd HH:mm:ss');
				    }
				}
				, {
					field : 'updateDate' ,
					title : '更新日期' ,
					templet : function(d) {
					return date.toDateString(d.updateDate, 'yyyy-MM-dd HH:mm:ss');
				    }
				}
			
			, {
				align : 'left',
				toolbar : '#operateBarTpl',
				title : '操作'
			}

		] ],done:function(){
			
			table.resize('tableId');
		}
		  
		  ,id: 'tableId'
	});
	
	//监听行工具条 
	table.on('tool(table_list)', function(obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
		rowObj = obj;
		var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
		var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）
		if (layEvent === 'edit') {
			edit(obj)//列表编辑
		} else if (layEvent === 'del') {
			del(obj);//列表删除
		}
	});
	
	
	/*搜索*/
	 form.on('submit(searchFilter)', function (data) {
			data = JSON.parse(JSON.stringify(data.field));
		      //执行重载
		      table.reload( 'tableId',{
		      	method:"post",
		        page: {
		          curr: 1 //重新从第 1 页开始
		        }
		        ,where: data
		      }, 'data');
  
    });
    
});


/*删除*/
function del(obj) {
	var id = obj.data.id;
	layer.confirm("确认要删除吗？", function(index) {
		reqPostHasParameter(DELETE, {
			"id" : id
		}, function(result) {
			if (result.code == 200) {
				layer.msg(result.msg, {
					icon : 1,
					time : 1000
				},function(){
					obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
					layer.close(index);		
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
	});
};

/*编辑*/
function edit(obj) {
	 
	var url = EDIT;
	var title = '参数配置';
	if(obj){
		var id = obj.data.id;
		url = EDIT + "?id=" + id;
		 title = '参数配置';
	}	
	x_admin_show(title, url);
};

/*批量删除*/
function batchDel() {
	var selectData =layui.table.checkStatus('tableId').data;
	if(selectData.length < 1){	
		layer.msg('请选择要删除的数据！', {icon: 2});
		return false;
	}
	layer.confirm('确认要删除吗？', function(index) {
		var array = new Array();
		$.each(selectData,function(i,e){
			array.push(e.id);
		 })
		reqPostHasParameter(BATCH_DELETE, {"idArr":array},function(result) {
			if (result.code == 200) { //这个是从后台取回来的状态值
				layer.msg(result.msg, {
					icon : 1,
					time : 1000
				},function(){
					layui.table.reload('tableId');
					layer.close(index);	
				});
			}else{
				layer.msg(result.msg, {
					icon : 2,
					time : 1000
				});
			}
			
		}, function(e) {
			console.log(e);
		}) 
		
	});
		
   }	


/*更新行数据*/
function updateRowData(obj){
	var reqData = obj.field;
	 reqPostHasParameter(GET, {"id":reqData.id}, function(result) {
		 reqData = result.data.entity;
		 rowObj.update({
				 paramKey: reqData.paramKey,
				 paramValue: reqData.paramValue,
				 remarks: reqData.remarks,
				 createDate: reqData.createDate,
				 updateDate: reqData.updateDate,
				 orderId: reqData.orderId,
				 projectConfigIdName: reqData.projectConfigIdName,
				 status: reqData.status,
			});	
	 }, function(e) {
		 console.log(e);
	 })
}

/*表格重载*/
function updateTableData(){
	layui.table.reload('tableId', {
	       page: {
	         curr:1 //重新从第 1 页开始
	       }
	     }, 'data'); 
}

