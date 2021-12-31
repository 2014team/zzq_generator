<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
  <head>
  	<%@include file="/WEB-INF/page/admin/common/head_layui.jsp" %>
  	<script type="text/javascript" src="/admin/js/${table.className?uncap_first}_edit.js?t=<%=new java.util.Date().getTime() %>"></script>
  </head>
   <body>
    <div class="x-body">
        <div class="layui-form">
        
         <input type="hidden" id="${table.key_fields[0].java_field_Name}" name="${table.key_fields[0].java_field_Name}" value="${"$"}{entity.${table.key_fields[0].java_field_Name}}" />
			
		<#list (table.common_fields) as field>
		<#if (field.java_field_Name != 'createDate' && field.java_field_Name != 'updateDate')>
			<div class="layui-form-item">
			<label for="L_pass" class="layui-form-label"> 
			<span class="x-red">*</span>${field.field_comment}：
			</label>
			<div class="layui-input-inline">
				<input type="text" id="${field.java_field_Name}" name="${field.java_field_Name}"
					value="${"$"}{entity.${field.java_field_Name}}"   lay-verify="required"
					autocomplete="off" class="layui-input">
			</div>
			<div class="layui-form-mid layui-word-aux">必选项</div>
		</div>
		
		</#if>
		</#list>
			  
          <div class="layui-form-item">
              <label for="L_repass" class="layui-form-label">
              </label>
               <button class="layui-btn" lay-submit lay-filter="editSave"> 保存</button>
          </div>
      </div>
    </div>
  </body>
  
</html>