package com.generator.service.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generator.annotation.AdminServiceLog;
import com.generator.code.service.GeneratorCode;
import com.generator.code.util.DataBaseUtil;
import com.generator.common.entity.AdminResultByPage;
import com.generator.common.service.impl.BaseServiceImpl;
import com.generator.dao.DatabaseConfigDao;
import com.generator.dao.GenerateTemplatesDao;
import com.generator.dao.ParameterConfigDao;
import com.generator.dao.ProjectConfigDao;
import com.generator.dao.TemplateConfigDao;
import com.generator.domain.dto.GenerateTemplatesDto;
import com.generator.domain.entity.DatabaseConfig;
import com.generator.domain.entity.GenerateTemplates;
import com.generator.domain.entity.ParameterConfig;
import com.generator.domain.entity.ProjectConfig;
import com.generator.domain.entity.TemplateConfig;
import com.generator.domain.vo.GenerateTemplatesVo;
import com.generator.generator.MyTable;
import com.generator.service.GenerateTemplatesService;
import com.generator.util.GsonUtil;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

/**
 * @ClassName: GenerateTemplatesServiceImpl
 * @Description: 模本生成管理
 * @author zhuzq
 * @date 2021年12月27日 09:37:26
 */
@Service
public class GenerateTemplatesServiceImpl extends BaseServiceImpl<GenerateTemplates,Integer>  implements GenerateTemplatesService {
	private static Logger logger = LoggerFactory.getLogger(GenerateTemplatesServiceImpl.class);
	@Autowired
	private GenerateTemplatesDao generateTemplatesDao;
	
	@Autowired
	private DatabaseConfigDao databaseConfigDao;
	@Autowired
	private ParameterConfigDao parameterConfigDao;
	@Autowired
	private TemplateConfigDao templateConfigDao;
	@Autowired
	private ProjectConfigDao projectConfigDao;




	/**
	 * @Title: saveGenerateTemplates
	 * @Description: 保存
	 * @author zhuzq
	 * @date 2021年12月27日 09:37:26
	 * @param generateTemplatesVo
	 * @return
	 */
	@AdminServiceLog(description="模本生成管理保存")
	@Override
	public boolean saveGenerateTemplates(GenerateTemplatesVo generateTemplatesVo) {
		// GenerateTemplatesVo转GenerateTemplates
		GenerateTemplates generateTemplates = convertGenerateTemplates(generateTemplatesVo);
		Integer result = generateTemplatesDao.save(generateTemplates);
		if (null != result && result > 0) {
			return true;
		}
		return false;
	}

	/**
	 * @Title: deleteGenerateTemplates
	 * @Description: 删除
	 * @author zhuzq
	 * @date 2021年12月27日 09:37:26
	 * @param generateTemplatesId
	 * @return
	 */
	@AdminServiceLog(description="模本生成管理 删除")
	@Override
	public boolean deleteGenerateTemplates(Integer id) {
		Integer result = generateTemplatesDao.delete(id);
		if (null != result && result > 0) {
			return true;
		}
		return false;
	}

	/**
	 * @Title: deleteByBatch
	 * @Description: 批量删除
	 * @author zhuzq
	 * @date 2021年12月27日 09:37:26
	 * @param generateTemplatesIdArr
	 * @return
	 */
	@AdminServiceLog(description="模本生成管理 批量删除")
	@Override
	public int deleteByBatch(Integer[] idArr) {
		List<Integer> idList = Arrays.asList(idArr);
		return generateTemplatesDao.deleteByBatch(idList);
	}

	/**
	 * @Title: updateGenerateTemplates
	 * @Description: 修改
	 * @author zhuzq
	 * @date 2021年12月27日 09:37:26
	 * @param generateTemplatesVo
	 * @return
	 */
	@AdminServiceLog(description="模本生成管理 批量修改")
	@Override
	public boolean updateGenerateTemplates(GenerateTemplatesVo generateTemplatesVo) {
		// GenerateTemplatesVo转GenerateTemplates
		GenerateTemplates generateTemplates = convertGenerateTemplates(generateTemplatesVo);
		Integer result = generateTemplatesDao.update(generateTemplates);
		if (null != result && result > 0) {
			return true;
		}
		return false;
	}

	/**
	 * @Title: getGenerateTemplates
	 * @Description: 根据generateTemplatesId获取模本生成管理
	 * @author zhuzq
	 * @date 2021年12月27日 09:37:26
	 * @param generateTemplatesId
	 * @return
	 */
	 @AdminServiceLog(description="模本生成管理根据generateTemplatesId获取模本生成管理")
	@Override
	public GenerateTemplatesDto getGenerateTemplates(Integer generateTemplatesId) {
		GenerateTemplatesDto generateTemplatesDTO = null;
		GenerateTemplates generateTemplates = generateTemplatesDao.get(generateTemplatesId);
		if (null != generateTemplates) {
			generateTemplatesDTO = convertGenerateTemplatesDto(generateTemplates);
		}
		return generateTemplatesDTO;
	}

	/**
	 * @Title: findByPage
	 * @Description: 分页查找
	 * @author zhuzq
	 * * @date 2021年12月27日 09:37:26
	 * @param generateTemplatesVo
	 * @param jsonResult
	 * @return
	 */
	@AdminServiceLog(description="模本生成管理分页查找")
	@Override
	public AdminResultByPage findByPage(GenerateTemplatesVo generateTemplatesVo, AdminResultByPage jsonResult) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("generateTemplatesVo", generateTemplatesVo);
		paramMap.put("page", jsonResult);

		int count = generateTemplatesDao.findByPageCount(paramMap);

		if (count > 0) {
			List<GenerateTemplatesDto> dataList = null;
			List<GenerateTemplates> generateTemplatesList = findByPage(paramMap);
			if (null != generateTemplatesList && generateTemplatesList.size() > 0) {
				dataList = new ArrayList<GenerateTemplatesDto>();
				for (GenerateTemplates generateTemplates : generateTemplatesList) {
					// GenerateTemplates转GenerateTemplatesDTO
					GenerateTemplatesDto generateTemplatesDTO = convertGenerateTemplatesDto(generateTemplates);
					dataList.add(generateTemplatesDTO);
				}
			}
			jsonResult.setData(dataList);
			jsonResult.setCount(count);
		}
		return jsonResult;
	}

	/**
	 * @Title: checkParam
	 * @Description: 参数验证
	 * @author zhuzq
	 * @date 2021年12月27日 09:37:26
	 * @param generateTemplatesVo
	 * @return
	 */
	@Override
	public String checkParam(GenerateTemplatesVo generateTemplatesVo) {
		Integer uniteConfigId = generateTemplatesVo.getUniteConfigId();
		if (null == uniteConfigId) {
			return "组合配置Id不能为空";
		}
	    String tableName = generateTemplatesVo.getTableName();
		if (StringUtils.isBlank(tableName)) {
			return "表名不能为空";
		}
		return null;
	}


	/**
	 * @Title: checkUnique
	 * @Description: 唯一性验证
	 * @author zhuzq
	 * @date 2021年12月27日 09:37:26
	 * @param generateTemplatesVo
	 * @return
	 */
	@Override
	public String checkUnique(GenerateTemplatesVo GenerateTemplatesVo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("uniteConfigId", GenerateTemplatesVo.getUniteConfigId());
		List<GenerateTemplates> generateTemplatesList = generateTemplatesDao.select(paramMap);
		if (null == generateTemplatesList || generateTemplatesList.size() < 1) {
			return null;
		}

		Integer id = GenerateTemplatesVo.getId();
		if (null != id) {
			for (GenerateTemplates entity : generateTemplatesList) {
				if (!entity.getId().equals(id) && entity.getUniteConfigId().equals(GenerateTemplatesVo.getUniteConfigId())) {
					return "组合配置Id已经存在";
				}
			}
		} else {
			return "组合配置Id已经存在";
		}
		return null;

	}

	/**
	 * @Title: convertGenerateTemplates
	 * @Description: GenerateTemplatesVo转GenerateTemplates
	 * @author zhuzq
	 * @date 2021年12月27日 09:37:26
	 * @param generateTemplatesVo
	 * @return
	 */
	private GenerateTemplates convertGenerateTemplates(GenerateTemplatesVo generateTemplatesVo) {
		GenerateTemplates generateTemplates = new GenerateTemplates();
		generateTemplates.setId(generateTemplatesVo.getId());
		generateTemplates.setUniteConfigId(generateTemplatesVo.getUniteConfigId());
		generateTemplates.setTableName(generateTemplatesVo.getTableName());
		generateTemplates.setCreateDate(generateTemplatesVo.getCreateDate());
		generateTemplates.setUpdateDate(generateTemplatesVo.getUpdateDate());
		generateTemplates.setPathParam(generateTemplatesVo.getPathParam());
		generateTemplates.setExtendParam(generateTemplatesVo.getExtendParam());
		return generateTemplates;
	}

	/**
	 * @Title: convertGenerateTemplatesDto
	 * @Description: GenerateTemplates转GenerateTemplatesDto
	 * @author zhuzq
	 * @date 2021年12月27日 09:37:26
	 * @param generateTemplates
	 * @return
	 */
	private GenerateTemplatesDto convertGenerateTemplatesDto(GenerateTemplates generateTemplates) {
		GenerateTemplatesDto dto = new GenerateTemplatesDto();
		dto.setId(generateTemplates.getId());
		dto.setUniteConfigId(generateTemplates.getUniteConfigId());
		dto.setTableName(generateTemplates.getTableName());
		dto.setCreateDate(generateTemplates.getCreateDate());
		dto.setUpdateDate(generateTemplates.getUpdateDate());
		dto.setPathParam(generateTemplates.getPathParam());
		dto.setExtendParam(generateTemplates.getExtendParam());
		ProjectConfig projectConfig = projectConfigDao.get(generateTemplates.getUniteConfigId());
		if(null != projectConfig){
			String uniteConfigIdName = projectConfig.getprojectName();
			dto.setUniteConfigIdName(uniteConfigIdName);
		}	
		
		return dto;
	}

	/**
	* @Title: execute
	* @Description: 执行
	* @author zhuzq
	* @date  2021年12月27日 上午11:16:48
	* @param generateTemplatesVo
	* @return
	*/
	@Override
	public String execute(Integer id) {
		GenerateTemplates totalConfig=generateTemplatesDao.get(id);
		if(null == totalConfig){
			logger.error("没有查到模本生成数据");
			return "没有查到模本生成数据";
		}
		
		
		Integer projectConfigId = totalConfig.getUniteConfigId();
	
		
		Map<String,Object> paramMap  = new HashMap<String, Object>();
		paramMap.put("projectConfigId", projectConfigId);
		//查询数据库配置
		DatabaseConfig databaseConfig = databaseConfigDao.getOneByMap(paramMap);
		if(null == databaseConfig){
			logger.error("没有查到 数据库配置");
			return "没有查到 数据库配置";
		}	
		
		
		
		// 查询参数配置
		List<ParameterConfig> parameterConfigList = parameterConfigDao.select(paramMap);
		
		// 获取模本
		paramMap.put("status", 0);
		List<TemplateConfig> templateConfigList = templateConfigDao.select(paramMap);
		if(null == templateConfigList || templateConfigList.size() <1 ){
			logger.error("没有查到 模本配置");
			return "没有查到 模本配置";
		}
		
		String generateCode = generateCode(totalConfig,databaseConfig,parameterConfigList,templateConfigList);
		if(StringUtils.isNotEmpty(generateCode)){
			logger.info(generateCode);
			return generateCode;
		}
		
		return null;
	}

	
	
	// 生成代码
	private String generateCode(GenerateTemplates totalConfig, DatabaseConfig databaseConfig,
			List<ParameterConfig> parameterConfigList, List<TemplateConfig> templateConfigList) {
		
		String result = null;
		
		String tableName = totalConfig.getTableName();
		
		// 初始化数据库
		String initDB = initDB(databaseConfig);
		if(StringUtils.isNotEmpty(initDB)){
			return "请检查数据库配置";
		}
		// 初始化参数
		Map<String , Object> paramMap = initParam(parameterConfigList);
		
		// 初始化扩展参数
		initExtendParam(paramMap,totalConfig);
		
		
		//获取表对象
		MyTable table = GeneratorCode.getTable(tableName);
		paramMap.put("dateTime", GeneratorCode.getCurrentDateFormat("yyyy年MM月dd日 HH:mm:ss"));
		paramMap.put("table", table);
		
		
		// 获取生成文件名
		String targetFileName = null;
		if(null != templateConfigList && templateConfigList.size() > 0){
			for (TemplateConfig templateConfig : templateConfigList) {
				
				//初始化路径替换参数
				initPathParam(templateConfig,totalConfig);
				
				// 把模本生成文件
				String generatorTemplate = generatorTemplate(totalConfig,templateConfig);
				if(StringUtils.isNotEmpty(generatorTemplate)){
					result = generatorTemplate;
					return result;
				}
				
				// 获取生成文件名
				targetFileName = getGenerateFileName(tableName,templateConfig);
				
				if(StringUtils.isEmpty(targetFileName)){
					result = "获取生成路径错误";
					return result;
				}
				// 获取模本
				Template template = getTemplate(targetFileName,totalConfig,templateConfig);
				if(null == template){
					result = "获取模本错误";
					return result;
				}
				
				// 创建
				String create = create(targetFileName,template,templateConfig,paramMap);
				if(StringUtils.isNotEmpty(create)){
					result = "生成文件错误";
					return result;
				}
			}
		}
		return result;
		
	}

	private void initPathParam(TemplateConfig templateConfig, GenerateTemplates totalConfig) {
		try {
			
			String buildPath = templateConfig.getBuildPath();
			
			String pathParam = totalConfig.getPathParam();
			Map<String,String> extendParamMap =  (Map<String, String>) GsonUtil.jsonToBean(pathParam, Map.class);
			if(null != extendParamMap && extendParamMap.size() > 0){
				 Iterator<Map.Entry<String,String>> it = extendParamMap.entrySet().iterator();
			        while (it.hasNext()) {
			            Map.Entry<String,String> entry = it.next();
			            if(buildPath.indexOf(entry.getKey()) != -1){
			            	buildPath = buildPath.replaceAll(entry.getKey(), entry.getValue());
			            	templateConfig.setBuildPath(buildPath);
			            }
			        }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void initExtendParam(Map<String, Object> paramMap, GenerateTemplates totalConfig) {
		try {
			String extendParam = totalConfig.getExtendParam();
			Map extendParamMap = (Map) GsonUtil.jsonToBean(extendParam, Map.class);
			if(null != extendParamMap){
				paramMap.putAll(extendParamMap);
			}
			
			// 包名包含参数替换
			String pathParam = totalConfig.getPathParam();
			Map<String,String> pathParamMap =  (Map<String, String>) GsonUtil.jsonToBean(pathParam, Map.class);
			
			
			if(null != paramMap && paramMap.size() > 0){
				Iterator<Entry<String, Object>> it = paramMap.entrySet().iterator();
				 while (it.hasNext()) {
					 Map.Entry<String,Object> entry = it.next();
					 String key = entry.getKey();
					 String value = null != entry.getValue() ?(String) entry.getValue(): null ;
					 
					 if(null != pathParamMap && pathParamMap.size() > 0){
						 Iterator<Entry<String, String>> it1 = pathParamMap.entrySet().iterator();
						 while (it1.hasNext()) {
							 Map.Entry<String,String> entry1 = it1.next();
							 String key1 = entry1.getKey();
							 String value1 = entry1.getValue();
							 if(null != value){
								 if(value.indexOf(key1) != -1){
									 value = value.replaceAll(key1, value1);
									 paramMap.put(key, value);
								 }
							 }
						 }
					 }
					 
					 
				 }
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	//创建
	private String create(String targetFileName ,Template template,TemplateConfig templateConfig,Map<String , Object> paramMap ) {
		String result = null;
		
		BufferedWriter writer =  null;
		try {
			
			String buildPath = templateConfig.getBuildPath();
			
			File target_file = new File(buildPath , targetFileName);
			
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(target_file)));
			//freemaker渲染并生成文件
			template.process(paramMap, writer);
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		} finally{
			if(writer != null)
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
					return e.getMessage();
				}
		}
		return result;
	}

	// 获取模本
	private Template getTemplate(String targetFileName,GenerateTemplates totalConfig, TemplateConfig templateConfig) {
		try {
			Integer primaryKey = totalConfig.getUniteConfigId();
			ProjectConfig uniteConfig = null;
			String projectName = null;
			if(null != primaryKey){
				uniteConfig = projectConfigDao.get(primaryKey);
				if(null != uniteConfig){
					projectName = uniteConfig.getprojectName();
				}
			}
			
			
			
			String projectPath = this.getClass().getResource("/").getPath();
			projectPath =projectPath.substring(1);
			projectPath = projectPath+File.separator +"config"+File.separator +"template";

			
			// 模板名称
			String templateName = templateConfig.getTemplateName();
			
			
			String buildPath = templateConfig.getBuildPath();
			
			
			File target_file = null;
			target_file = new File(buildPath , targetFileName);
			
			File target_file_dir = new File(buildPath);
			if(target_file.exists()){
				target_file.delete();
			}
			if(!target_file_dir.exists()){
				target_file_dir.mkdirs();
			}
			target_file.createNewFile();
			
			
			
			
			
			
			
			
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(target_file)));
			String template_path = projectPath +File.separator  + projectName ;
			File templateDirtory = new File(template_path);
			Configuration configuration = new Configuration();
			configuration.setDefaultEncoding("UTF-8");
			configuration.setDirectoryForTemplateLoading(templateDirtory);
			configuration.setObjectWrapper(new DefaultObjectWrapper());
			Template template = configuration.getTemplate(templateName);
			return template;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 生成模本
	private String generatorTemplate(GenerateTemplates totalConfig, TemplateConfig templateConfig) {
		Integer primaryKey = totalConfig.getUniteConfigId();
		ProjectConfig uniteConfig = null;
		String projectName = null;
		if(null != primaryKey){
			uniteConfig = projectConfigDao.get(primaryKey);
			if(null != uniteConfig){
				projectName = uniteConfig.getprojectName();
			}
		}
		try{//异常处理
			String projectPath = this.getClass().getResource("/").getPath();
			//String projectPath = this.getClass().getClassLoader().getResource("").getPath()+File.separator  + "config"+File.separator  +"template" +File.separator ;
			projectPath =projectPath.substring(1);
			projectPath = projectPath+File.separator +"config"+File.separator +"template";

			
			// 模板名称
			String templateName = templateConfig.getTemplateName();
			
			
			String dir = projectPath +File.separator  +  projectName ;
			File file=new File(dir);
			if(!file.exists()){//如果文件夹不存在
				file.mkdir();//创建文件夹
			}
			
	
			
			String templateContent = templateConfig.getTemplateContent();
			String fileName = dir +File.separator  +templateName;
			BufferedWriter bw=new BufferedWriter(new FileWriter(fileName));
			bw.write(templateContent);//在创建好的文件中写入"Hello I/O"
			bw.close();//一定要关闭文件
		}catch(Exception e){
			e.printStackTrace();
			return e.getMessage();
			
		}
		return null;
		
	}

	// 获取生成文件名
	private String getGenerateFileName(String tableName, TemplateConfig templateConfig) {
		String target_file_name  = DataBaseUtil.getClassName(tableName);
		if(null !=templateConfig){
			String suffix =templateConfig.getSuffix();
			if(StringUtils.isNotEmpty(suffix)){
				return target_file_name + suffix;
			}
		}
		
		return null;
	}

	// 初始化参数
	private Map<String, Object> initParam(List<ParameterConfig> parameterConfigList) {
		Map<String , Object> paramMap = new HashMap<String, Object>();
		
		if(null != parameterConfigList && parameterConfigList.size() > 0){
			for (ParameterConfig parameterConfig : parameterConfigList) {
				String key = parameterConfig.getParamKey();
				String value = parameterConfig.getParamValue();
				if(StringUtils.isNotEmpty(key) && StringUtils.isNotEmpty(value)){
					paramMap.put(key, value);
				}
			}
		}
		return paramMap;
	}

	// 初始化数据库
	private String initDB(DatabaseConfig databaseConfig) {
		DataBaseUtil.jdbc_driver = databaseConfig.getJdbcDriver();
		DataBaseUtil.jdbc_url = databaseConfig.getJdbcUrl();
		DataBaseUtil.jdbc_user = databaseConfig.getJdbcUser();
		DataBaseUtil.jdbc_password = databaseConfig.getJdbcPassword();
		return DataBaseUtil.init();//初始化数据库连接
	}
	
	
	
	
	
	
	
}
