<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
  	<%@include file="/WEB-INF/page/admin/common/head_layui.jsp" %>
  	<link rel="stylesheet" href="/admin/css/list.css?t=<%=new java.util.Date().getTime() %>">
  	<script type="text/javascript" src="/admin/js/templateConfig_list.js?t=<%=new java.util.Date().getTime() %>"></script>
  	<%@ taglib uri="/WEB-INF/tag/ProjectConfig.tld" prefix="uc" %>
  </head>
  
   <body>
    <div class="x-nav">
      <span class="layui-breadcrumb">
        <a href="">首页</a>
        <a href="/admin/user/list/ui">模板配置管理</a>
        <a>
          <cite>列表</cite></a>
      </span>
      <a class="layui-btn layui-btn-primary layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" href="javascript:location.replace(location.href);" title="刷新">
        <i class="layui-icon" style="line-height:38px">&#xe669;</i></a>
    </div>
    <div class="x-body">
      <div class="layui-row">
        <div class="layui-form layui-col-md12 x-so">
        	<r:auth menuName="模板配置/查询" menuUrl="模板配置/查询">
        	
        		所属项目：
				   <div class="layui-inline">
				        <select id="projectConfigId" name="projectConfigId" lay-search>
		                <option value="">全部</option>
			          	<c:forEach items="${uc:getList()}" var="item">
			          		<option value="${item.id }" >${item.projectName}</option>
			          	</c:forEach>
		            </select>
			    	</div>
        		状态：
				   <div class="layui-inline">
				        <select id="status" name="status" lay-search>
		                <option value="">全部</option>
			          	<option value="0" >有效</option>
			          	<option value="1" >无效</option>
		            </select>
			    	</div>
			    	 	
	         	备注名称：
		          <div class="layui-inline">
				    <input class="layui-input" name="remarks" id="remarks" autocomplete="off">
				  </div>
				 	模板名称：
		          <div class="layui-inline">
				    <input class="layui-input" name="templateName" id="templateName" autocomplete="off">
				  </div>
				 	模板内容：
		          <div class="layui-inline">
				    <input class="layui-input" name="templateContent" id="templateContent" autocomplete="off">
				  </div>
				 	生成路径：
		          <div class="layui-inline">
				    <input class="layui-input" name="buildPath" id="buildPath" autocomplete="off">
				  </div>
				 
				
				
			<span class="layui-inline xbtpt10">
				<div class="layui-input-inline">
	          	  <button class="layui-btn" lay-submit lay-filter="searchFilter" >搜索</button>
	          </div>
	          </span>
			
          </r:auth>
        </div>
      </div>
      
       <!-- 列表 -->	
      <table class="layui-hide" id="table_list" lay-filter="table_list" ></table>
    </div>
    
    
    <script type="text/html" id="toolbar">
      <div class="layui-btn-container toolbar">
		<r:auth menuName="模板配置/批量删除" menuUrl="模板配置/批量删除">
         <button class="layui-btn layui-btn-sm layui-btn-danger" onclick="batchDel()" >批量删除</button>
    		</r:auth>
         <r:auth menuName="模板配置/增加" menuUrl="模板配置/增加">
			<button class="layui-btn layui-btn-sm"  onclick="edit()" ><i class="layui-icon"></i>增加</button>
    		</r:auth>
     </div>
	</script>
   
   
    <!-- 操作模板 -->
    <script type="text/html" id="operateBarTpl">
		<div class="td-manage">
			<r:auth menuName="模板配置/编辑" menuUrl="模板配置/编辑">
              <a title="编辑"  lay-event="edit" href="javascript:;">
                <i class="layui-icon" style="font-size: 20px;">&#xe642;</i>
              </a>
     		</r:auth>
			<r:auth menuName="模板配置/删除" menuUrl="模板配置/删除">
              <a title="删除"  lay-event="del" href="javascript:;">
                <i class="layui-icon" style="font-size: 20px;">&#xe640;</i>
              </a>
     		</r:auth>
		</div>
	</script>

  </body>
</html>
