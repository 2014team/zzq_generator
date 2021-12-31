package com.generator.code.service.impl;

import com.generator.code.service.GeneratorCode;

/**
 * mysql代码生成器
 * @author dengzw
 * 2018-7-20 上午11:01:55
 *
 */
public class MysqlGeneratorImpl extends GeneratorCode{

	@Override
	public String getTemplatePath() {
		return "mysql";
	}

}
