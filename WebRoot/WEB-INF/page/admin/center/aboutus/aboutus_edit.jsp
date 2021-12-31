<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
  <head>
  	<%@include file="/WEB-INF/page/admin/common/head_layui.jsp" %>
  	<script type="text/javascript" src="/js/UEditor/ueditor.config.js"></script>
  	<script type="text/javascript" src="/js/UEditor/ueditor.all.js"></script>
  	 <script type="text/javascript" charset="utf-8" src="/js/UEditor/lang/zh-cn/zh-cn.js"></script>
  	<script type="text/javascript" src="/admin/js/aboutus_edit.js?t=<%=new java.util.Date().getTime() %>"></script>
  </head>
   <body class="form-wrap">
   <div class="layui-fluid">
    <div class="layui-card">
      <div class="layui-card-header">表单组合</div>
      <div class="layui-card-body" style="padding: 15px;">
        <div class="layui-form">
          
          <div class="layui-form-item">
            <label class="layui-form-label">内容</label>
            <div class="layui-input-block">
          <!-- 加载编辑器的容器 -->
          <span id="daily_content" style="display: none">${entity.content}</span>
			<script id="container" name="content" type="text/plain"></script></div>
          </div>
           
          <div class="layui-form-item layui-layout-admin">
            <div class="layui-input-block">
              <div class="layui-footer" style="left: 0;">
                <button class="layui-btn" lay-submit lay-filter="editSave"> 保存</button>
  
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
   
  </body>
  
</html>