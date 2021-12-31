<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
  	<%@include file="/WEB-INF/page/admin/common/head_layui.jsp" %>
  	<link rel="stylesheet" href="/admin/css/list.css?t=<%=new java.util.Date().getTime() %>">
  	<script type="text/javascript" src="/admin/js/user_list.js?t=<%=new java.util.Date().getTime() %>"></script>
  </head>
  
   <body>
    <div class="x-nav">
      <span class="layui-breadcrumb">
        <a href="">首页</a>
        <a href="/admin/user/list/ui">管理员管理</a>
        <a>
          <cite>列表</cite></a>
      </span>
      <a class="layui-btn layui-btn-primary layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" href="javascript:location.replace(location.href);" title="刷新">
        <i class="layui-icon" style="line-height:38px">&#xe669;</i></a>
    </div>
    <div class="x-body">
      <div class="layui-row">
        <div class="layui-form layui-col-md12 x-so">
        	<r:auth menuName="管理员列表/查询" menuUrl="管理员列表/查询"> 	
	          <div class="layui-input-inline">
	          <input class="layui-input" placeholder="开始日" name="startDate" id="startDate" readonly>
	          <input class="layui-input" placeholder="截止日" name="endDate" id="endDate" readonly>
	         </div>
	          <div class="layui-input-inline">
		             <select id="roleId" lay-search>
		                 <option value="" >请选择角色</option>
		              	  <c:forEach items="${roleDtoList}" var="item">
		                  	<option value="${item.roleId}" >${item.roleName}</option>
		                  </c:forEach>	
		            </select>
		          </div>
	           <div class="layui-input-inline">
	          	<input type="text" id="search_input" placeholder="请输入用户名" autocomplete="off" class="layui-input">
	           </div>
	           <div class="layui-input-inline">
	          	<button class="layui-btn"  id="search_id"><i class="layui-icon">&#xe615;</i></button>
	          </div>
          </r:auth>
        </div>
      </div>
      
       <!-- 列表 -->	
      <table class="layui-hide" id="table_list" lay-filter="table_list" ></table>
    </div>
    
    
    <script type="text/html" id="toolbar">
      <div class="layui-btn-container toolbar">
		<r:auth menuName="管理员列表/批量删除" menuUrl="管理员列表/批量删除">
         <button class="layui-btn layui-btn-sm layui-btn-danger" onclick="batchDel()" >批量删除</button>
    		</r:auth>
         <r:auth menuName="管理员列表/增加" menuUrl="管理员列表/增加">
			<button class="layui-btn layui-btn-sm"  onclick="edit()" ><i class="layui-icon"></i>增加</button>
    		</r:auth>
     </div>
	</script>
   
   
    <!-- 操作模板 -->
    <script type="text/html" id="operateBarTpl">
		<div class="td-manage">
			<r:auth menuName="管理员列表/状态修改" menuUrl="管理员列表/状态修改">
              <a title="状态修改" lay-event="updateValidFlag" href="javascript:;">
					<i class="layui-icon"  style="font-size: 20px;">&#xe620;</i>
			  </a>
     		</r:auth>
			<r:auth menuName="管理员列表/编辑" menuUrl="管理员列表/编辑">
              <a title="编辑"  lay-event="edit" href="javascript:;">
                <i class="layui-icon" style="font-size: 20px;">&#xe642;</i>
              </a>
     		</r:auth>
			<r:auth menuName="管理员列表/删除" menuUrl="管理员列表/删除">
              <a title="删除"  lay-event="del" href="javascript:;">
                <i class="layui-icon" style="font-size: 20px;">&#xe640;</i>
              </a>
     		</r:auth>
		</div>
	</script>

	<!-- 状态模板 -->
    <script type="text/html" id="validFlagTpl">
		{{# if(d.validFlag == 0){ }}
			启用	
		{{#}else if(d.validFlag == 1){ }}
			停用		
		{{#  }	}}
	</script>
  </body>
</html>
