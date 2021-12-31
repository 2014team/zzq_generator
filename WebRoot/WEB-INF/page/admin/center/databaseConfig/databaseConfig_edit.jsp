<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
  <head>
  	<%@include file="/WEB-INF/page/admin/common/head_layui.jsp" %>
  	<script type="text/javascript" src="/admin/js/databaseConfig_edit.js?t=<%=new java.util.Date().getTime() %>"></script>
  </head>
   <body>
    <div class="x-body">
        <div class="layui-form">
        
         <input type="hidden" id="id" name="id" value="${entity.id}" />
			
			
			<div class="layui-form-item">
			<label for="L_pass" class="layui-form-label"> 
			<span class="x-red">*</span>备注名称：
			</label>
			<div class="layui-input-inline">
				<input type="text" id="remarks" name="remarks"
					value="${entity.remarks}"   lay-verify="required"
					autocomplete="off" class="layui-input">
			</div>
			<div class="layui-form-mid layui-word-aux">必选项</div>
		</div>
		
		
		<div class="layui-form-item">
			<label for="L_pass" class="layui-form-label"> 
			<span class="x-red">*</span>数据库名称：
			</label>
			<div class="layui-input-inline" >
				<input type="text" id="databaseName" name="databaseName" 
					value="${entity.databaseName}"   lay-verify="required"
					autocomplete="off" class="layui-input">
			</div>
			<div class="layui-form-mid layui-word-aux">必选项</div>
		</div>
		
		
			
			<div class="layui-form-item" >
			<label for="L_pass" class="layui-form-label"> 
			<span class="x-red">*</span>数据库url：
			</label>
			<div class="layui-input-inline" style="width: 60%">
				<input type="text" id="jdbcUrl" name="jdbcUrl"
					value="${entity.jdbcUrl}"   lay-verify="required"
					autocomplete="off" class="layui-input">
			</div>
			<div class="layui-form-mid layui-word-aux">必选项</div>
		</div>
		
			<div class="layui-form-item">
			<label for="L_pass" class="layui-form-label"> 
			<span class="x-red">*</span>数据库用户名：
			</label>
			<div class="layui-input-inline" >
				<input type="text" id="jdbcUser" name="jdbcUser" 
					value="${entity.jdbcUser}"   lay-verify="required"
					autocomplete="off" class="layui-input">
			</div>
			<div class="layui-form-mid layui-word-aux">必选项</div>
		</div>
		
			<div class="layui-form-item">
			<label for="L_pass" class="layui-form-label"> 
			<span class="x-red">*</span>数据库密码：
			</label>
			<div class="layui-input-inline">
				<input type="text" id="jdbcPassword" name="jdbcPassword"
					value="${entity.jdbcPassword}"   lay-verify="required"
					autocomplete="off" class="layui-input">
			</div>
			<div class="layui-form-mid layui-word-aux">必选项</div>
		</div>
		
			<div class="layui-form-item">
			<label for="L_pass" class="layui-form-label"> 
			<span class="x-red">*</span>驱动：
			</label>
			<div class="layui-input-inline" style="width: 60%">
				<input type="text" id="jdbcDriver" name="jdbcDriver" 
					value="${entity.jdbcDriver}"   lay-verify="required"
					autocomplete="off" class="layui-input">
			</div>
			<div class="layui-form-mid layui-word-aux">必选项</div>
		</div>
		
		
		<div class="layui-form-item">
		<label for="L_repass" class="layui-form-label">
                  <span class="x-red">*</span>数据库类型
              </label>
               <div class="layui-input-inline">
		              <select name="databaseType" id="databaseType" value="${entity.databaseType}" lay-verify="required"  lay-search>
		                 <option value="" >请选择数据库配置</option>
		                 <c:forEach items="${databaseTypeConfigList}" var="item">
		                 <option value="${item.code }" >${item.typeName }</option>
		                 </c:forEach>
		              </select>
			  </div>
			  <div class="layui-form-mid layui-word-aux">必选项</div>
		</div> 
		
			<div class="layui-form-item">
		<label for="L_repass" class="layui-form-label">
                  <span class="x-red">*</span>所属项目
              </label>
               <div class="layui-input-inline">
		              <select name="projectConfigId" id="projectConfigId" value="${entity.projectConfigId}" lay-verify="required"  lay-search>
		                 <option value="" >请选择所属项目</option>
		                 <c:forEach items="${projectConfigList}" var="item">
		                 <option value="${item.id }" >${item.projectName }</option>
		                 </c:forEach>
		              </select>
			  </div>
			  <div class="layui-form-mid layui-word-aux">必选项</div>
		</div> 
		
			  
          <div class="layui-form-item">
              <label for="L_repass" class="layui-form-label">
              </label>
               <button class="layui-btn" lay-submit lay-filter="editSave"> 保存</button>
          </div>
      </div>
    </div>
  </body>
  
</html>