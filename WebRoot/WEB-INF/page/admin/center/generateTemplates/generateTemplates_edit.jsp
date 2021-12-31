<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
  <head>
  	<%@include file="/WEB-INF/page/admin/common/head_layui.jsp" %>
  	<script type="text/javascript" src="/admin/js/generateTemplates_edit.js?t=<%=new java.util.Date().getTime() %>"></script>

  </head>
   <body>
    <div class="x-body">
        <div class="layui-form">
        
         <input type="hidden" id="id" name="id" value="${entity.id}" />
		
		<div class="layui-form-item">
		<label for="L_repass" class="layui-form-label">
                  <span class="x-red">*</span>项目名称
              </label>
               <div class="layui-input-inline">
		              <select name="uniteConfigId" id="uniteConfigId" value="${entity.uniteConfigId}" lay-verify="required"  lay-search>
		                 <option value="" >请选择项目名称</option>
		                 <c:forEach items="${projectConfigList}" var="item">
		                 <option value="${item.id }" >${item.projectName }</option>
		                 </c:forEach>
		              </select>
			  </div>
			  <div class="layui-form-mid layui-word-aux">必选项</div>
		</div> 
		
		
		
			<div class="layui-form-item">
			<label for="L_pass" class="layui-form-label"> 
			<span class="x-red">*</span>表名：
			</label>
			<div class="layui-input-inline">
				<input type="text" id="tableName" name="tableName"
					value="${entity.tableName}"   lay-verify="required"
					autocomplete="off" class="layui-input">
			</div>
			<div class="layui-form-mid layui-word-aux">必选项</div>
		</div>
		
		
		<div class="layui-form-item layui-form-text">
  扩展参数:</label>
    <div class="layui-input-block">
      <textarea  id="extendParam" name="extendParam" placeholder="请输入内容" class="layui-textarea">${entity.extendParam}</textarea>
    <div class="layui-form-mid layui-word-aux">json格式</div>
    </div>
  </div>
	
			<div class="layui-form-item layui-form-text">
  替换参数:</label>
    <div class="layui-input-block">
      <textarea id="pathParam" name="pathParam" placeholder="请输入内容" class="layui-textarea">${entity.pathParam}</textarea>
    <div class="layui-form-mid layui-word-aux">json格式</div>
    </div>
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