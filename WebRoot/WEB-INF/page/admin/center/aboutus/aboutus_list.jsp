<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
  	<%@include file="/WEB-INF/page/admin/common/head_layui.jsp" %>
  	<script type="text/javascript" src="/js/UEditor/ueditor.config.js"></script>
  	<script type="text/javascript" src="/js/UEditor/ueditor.all.js"></script>
  	 <script type="text/javascript" charset="utf-8" src="/js/UEditor/lang/zh-cn/zh-cn.js"></script>
  	<link rel="stylesheet" href="/admin/css/list.css?t=<%=new java.util.Date().getTime() %>">
  	<script type="text/javascript" src="/admin/js/aboutus_list.js?t=<%=new java.util.Date().getTime() %>"></script>
  </head>
  
   <body>
    <div class="x-nav">
      <span class="layui-breadcrumb">
        <a href="">首页</a>
        <a href="/admin/user/list/ui">关于我们管理</a>
      </span>
      <a class="layui-btn layui-btn-primary layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" href="javascript:location.replace(location.href);" title="刷新">
        <i class="layui-icon" style="line-height:38px">&#xe669;</i></a>
    </div>
    <div class="x-body">
      <div class="layui-row">
        <div class="layui-form layui-col-md12 x-so">
        	<div class="layui-form">
          
          <div class="layui-form-item">
            
            <input type="hidden" id="aboutusId" name="aboutusId" value="${entity.aboutusId}" />
          <!-- 加载编辑器的容器 -->
			<script id="container" name="content" type="text/plain">${entity.content}</script>
          </div>
           
           
          <r:auth menuName="关于我们/编辑" menuUrl="关于我们/编辑"> 
          <div class="layui-form-item">
            <div class="layui-input-block" style="margin-left:0;">
              <div class="layui-footer" style="left: 0;">
                <button class="layui-btn" lay-submit lay-filter="editSave">保存</button>
  
              </div>
            </div>
          </div>
          </r:auth>
        </div>
        </div>
      </div>
      
    
    </div>
    
  </body>
</html>
