package com.generator.code.service.impl;

import com.generator.code.service.GeneratorCode;

/**
 * sqlserver代码生成器
 *
 */
public class SqlserverGeneratorImpl extends GeneratorCode{

	@Override
	public String getTemplatePath() {
		return "sqlserver";
	}

}
