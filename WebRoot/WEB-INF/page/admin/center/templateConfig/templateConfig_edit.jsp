<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
  <head>
  	<%@include file="/WEB-INF/page/admin/common/head_layui.jsp" %>
  	<script type="text/javascript" src="/admin/js/templateConfig_edit.js?t=<%=new java.util.Date().getTime() %>"></script>
  </head>
   <body>
    <div class="x-body">
        <div class="layui-form">
        
         <input type="hidden" id="id" name="id" value="${entity.id}" />
			<div class="layui-form-item">
			<label for="L_pass" class="layui-form-label"> 
			<span class="x-red">*</span>备注名称：
			</label>
			<div class="layui-input-inline"  style="width: 60%">
				<input type="text" id="remarks" name="remarks"
					value="${entity.remarks}"   lay-verify="required"
					autocomplete="off" class="layui-input">
			</div>
			<div class="layui-form-mid layui-word-aux">必选项</div>
		</div>
		
			<div class="layui-form-item" >
			<label for="L_pass" class="layui-form-label"> 
			<span class="x-red">*</span>模板名称：
			</label>
			<div class="layui-input-inline"  style="width: 60%">
				<input type="text" id="templateName" name="templateName"
					value="${entity.templateName}"   lay-verify="required"
					autocomplete="off" class="layui-input">
			</div>
			<div class="layui-form-mid layui-word-aux">必选项</div>
		</div>
	
		
		
		<div class="layui-form-item layui-form-text">
    <label class="layui-form-label"><span class="x-red">*</span>模板内容:</label>
    <div class="layui-input-block">
      <textarea rows="20" lay-verify="required" id="templateContent" name="templateContent" placeholder="请输入内容" class="layui-textarea">${entity.templateContent}</textarea>
    <div class="layui-form-mid layui-word-aux">必选项</div>
    </div>
  </div>
		
		
			<div class="layui-form-item">
			<label for="L_pass" class="layui-form-label"> 
			<span class="x-red">*</span>生成路径：
			</label>
			<div class="layui-input-inline"  style="width: 60%">
				<input type="text" id="buildPath" name="buildPath"
					value="${entity.buildPath}"   lay-verify="required"
					autocomplete="off" class="layui-input">
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
                  <span class="x-red">*</span>状态
              </label>
               <div class="layui-input-inline">
		              <select name="status" id="status" value="${entity.status}" lay-verify="required"  lay-search>
		                 <option value="" >请选择状态</option>
		                 <option value="0" >有效</option>
		                 <option value="1" >无效</option>
		              </select>
			  </div>
			  <div class="layui-form-mid layui-word-aux">必选项</div>
		</div> 
		
		
		
			<div class="layui-form-item">
			<label for="L_pass" class="layui-form-label"> 
			<span class="x-red">*</span>生成文件后缀：
			</label>
			<div class="layui-input-inline" >
				<input type="text" id="suffix" name="suffix"
					value="${entity.suffix}"   lay-verify="required"
					autocomplete="off" class="layui-input">
			</div>
			<div class="layui-form-mid layui-word-aux">必选项</div>
		</div>
		
			<div class="layui-form-item">
			<label for="L_pass" class="layui-form-label"> 
			<span class="x-red">*</span>排序号：
			</label>
			<div class="layui-input-inline" >
				<input type="text" id="orderId" name="orderId"
					value="${empty entity.orderId ? 100 :  entity.orderId}"   lay-verify="required|number"
					autocomplete="off" class="layui-input">
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