package com.generator.util;

import java.io.File;
import java.util.Map;

import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

public class CreateFileUtil {

	/**
	* @Title: getTemplateHtml
	* @Description: 获取模本内容
	* @author zhuzq
	* @date  2021年4月13日 下午5:47:48
	* @param templateName
	* @param map
	* @return
	* @throws Exception
	*/
	public static String getTemplateHtml(String templateName, Map<String, Object> map) throws Exception {
		String html = "";
		Configuration config = new Configuration();
		String filepath = ToolsUtil.getWebRoot();
		File file = new File(filepath);
		config.setDirectoryForTemplateLoading(file);
		config.setObjectWrapper(new DefaultObjectWrapper());

		Template template = config.getTemplate(templateName, "UTF-8");
		html = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
		return html;
	}

}
