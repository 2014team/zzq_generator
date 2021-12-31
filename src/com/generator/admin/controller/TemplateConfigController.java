package com.generator.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.generator.domain.dto.TemplateConfigDto;
import com.generator.domain.entity.ProjectConfig;
import com.generator.domain.vo.TemplateConfigVo;
import com.generator.service.ProjectConfigService;
import com.generator.service.TemplateConfigService;
import com.generator.common.entity.JsonResult;
import com.generator.constant.StatusEnum;
import com.generator.common.entity.AdminResultByPage;
import com.generator.annotation.AdminControllerLog;

/**
 * @ClassName: TemplateConfigController
 * @Description: 模板配置
 * @author zhuzq
 * @date 2021年12月23日 17:27:04
 */
@Controller
public class TemplateConfigController {

	@Autowired
	private TemplateConfigService templateConfigService;
	@Autowired
	private ProjectConfigService projectConfigService;
	/**
	 * @Title: save
	 * @Description: 保存
	 * @author zhuzq
	 * @date 2021年12月23日 17:27:04
	 * @param templateConfigVo
	 * @return
	 */
	@AdminControllerLog(description="模板配置保存")
	@ResponseBody
	@RequestMapping(value = "/admin/center/templateConfig/save", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult save(TemplateConfigVo templateConfigVo) {
		JsonResult result = new JsonResult();

		// 参数验证
		String errMsg = templateConfigService.checkParam(templateConfigVo);
		if (StringUtils.isNotBlank(errMsg)) {
			result.failure(errMsg);
			return result;
		}

		// 唯一性验证
		//errMsg = templateConfigService.checkUnique(templateConfigVo);
		//if (StringUtils.isNotBlank(errMsg)) {
		//	result.failure(errMsg);
		//	return result;
		//}
		// 保存
		boolean save = templateConfigService.saveTemplateConfig(templateConfigVo);
		if (save) {
			result.success();
		} else {
			result.failure();
		}

		return result;
	}

	/**
	 * @Title: delete
	 * @Description: 删除
	 * @author zhuzq
	 * @date 2021年12月23日 17:27:04
	 * @param templateConfigId
	 * @return
	 */
	@AdminControllerLog(description="模板配置删除")
	@ResponseBody
	@RequestMapping(value = "/admin/center/templateConfig/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult delete(Integer id) {
		JsonResult result = new JsonResult();

		// 验证参数
		if (null == id) {
			result.failure("主键不能为空");
			return result;
		}
		// 删除
		boolean delete = templateConfigService.deleteTemplateConfig(id);
		if (delete) {
			result.success();
		} else {
			result.failure();
		}

		return result;
	}

	/**
	 * @Title: batchDelete
	 * @Description: 批量删除
	 * @author zhuzq
	 * @date 2021年12月23日 17:27:04
	 * @param templateConfigIdArr
	 * @return
	 */
	@AdminControllerLog(description="模板配置批量删除")
	@ResponseBody
	@RequestMapping(value = "/admin/center/templateConfig/batch/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult deleteByIdArr(@RequestParam("idArr[]") Integer[] idArr) {
		JsonResult result = new JsonResult();
		// 验证参数
		if (null == idArr || idArr.length < 1) {
			result.failure("请选项要删除的数据");
			return result;
		}
		// 删除
		Integer delete = templateConfigService.deleteByBatch(idArr);
		if (null != delete && delete > 0) {
			result.success();
		} else {
			result.failure();
		}

		return result;
	}

	/**
	 * @Title: update
	 * @Description: 修改
	 * @author zhuzq
	 * @date 2021年12月23日 17:27:04
	 * @param templateConfigVo
	 * @return
	 */
	@AdminControllerLog(description="模板配置修改")
	@ResponseBody
	@RequestMapping(value = "/admin/center/templateConfig/update", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult update(TemplateConfigVo templateConfigVo) {
		JsonResult result = new JsonResult();

		// 验证参数
		Integer id = templateConfigVo.getId();
		if (null == id) {
			result.failure("主键不能为空");
			return result;
		}
		String errMsg = templateConfigService.checkParam(templateConfigVo);
		if (StringUtils.isNotBlank(errMsg)) {
			result.failure(errMsg);
			return result;
		}

		// 唯一性验证
		//errMsg = templateConfigService.checkUnique(templateConfigVo);
		//if (StringUtils.isNotBlank(errMsg)) {
		//	result.failure(errMsg);
		//	return result;
		//}
		// 修改
		boolean update = templateConfigService.updateTemplateConfig(templateConfigVo);
		if (update) {
			result.success();
		} else {
			result.failure();
		}

		return result;
	}

	/**
	 * @Title: list
	 * @Description: 分页查找
	 * @author zhuzq
	 * @date 2021年12月23日 17:27:04
	 * @param templateConfigVo
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/center/templateConfig/list", method = { RequestMethod.POST })
	public AdminResultByPage list(TemplateConfigVo templateConfigVo, HttpServletRequest request) {

		Integer page = Integer.valueOf(request.getParameter("page"));
		Integer limit = Integer.valueOf(request.getParameter("limit"));

		AdminResultByPage jsonResult = new AdminResultByPage(page, limit);

		
		jsonResult = templateConfigService.findByPage(templateConfigVo, jsonResult);

		return jsonResult;
	}

	/**
	 * @Title: toList
	 * @Description: 列表UI
	 * @author zhuzq
	 * @date 2021年12月23日 17:27:04
	 * @return
	 */
	@RequestMapping(value = "/admin/center/templateConfig/list/ui", method = { RequestMethod.GET })
	public String toList() {
		return "/admin/center/templateConfig/templateConfig_list";
	}

	/**
	 * @Title: edit
	 * @Description: 编辑
	 * @author zhuzq
	 * @date 2021年12月23日 17:27:04
	 * @param templateConfigId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/admin/center/templateConfig/edit", method = { RequestMethod.GET, RequestMethod.POST })
	public String edit(Integer id, HttpServletRequest request) {
		// 编辑,为空新增
		if (null != id) {
			TemplateConfigDto entity = templateConfigService.getTemplateConfig(id);
			request.setAttribute("entity", entity);
		}
		
		
		Map<String,Object> paramMap = new HashMap<String, Object>();
		List<ProjectConfig> projectConfigList = projectConfigService.select(paramMap);
		request.setAttribute("projectConfigList", projectConfigList);
		
		return "/admin/center/templateConfig/templateConfig_edit";
	}
	
	/**
	 * @Title: get
	 * @Description: 查找
	 * @author zhuzq
	 * @date 2021年12月23日 17:27:04
	 * @param templateConfigId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/center/templateConfig/get", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult get(Integer id) {

		JsonResult result = new JsonResult();
		// 验证参数
		if (null == id || id < 1) {
			result.failure("用户ID不能为空");
			return result;
		}
		TemplateConfigDto entity = templateConfigService.getTemplateConfig(id);
		result.success("entity", entity);
		return result;
	}

}
