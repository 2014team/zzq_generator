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

import com.generator.annotation.AdminControllerLog;
import com.generator.common.entity.AdminResultByPage;
import com.generator.common.entity.JsonResult;
import com.generator.constant.StatusEnum;
import com.generator.domain.dto.ParameterConfigDto;
import com.generator.domain.entity.ProjectConfig;
import com.generator.domain.vo.ParameterConfigVo;
import com.generator.service.ParameterConfigService;
import com.generator.service.ProjectConfigService;

/**
 * @ClassName: ParameterConfigController
 * @Description: 参数配置
 * @author zhuzq
 * @date 2021年12月09日 16:15:36
 */
@Controller
public class ParameterConfigController {

	@Autowired
	private ParameterConfigService parameterConfigService;
	
	@Autowired
	private ProjectConfigService projectConfigService;

	/**
	 * @Title: save
	 * @Description: 保存
	 * @author zhuzq
	 * @date 2021年12月09日 16:15:36
	 * @param parameterConfigVo
	 * @return
	 */
	@AdminControllerLog(description="参数配置保存")
	@ResponseBody
	@RequestMapping(value = "/admin/center/parameterConfig/save", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult save(ParameterConfigVo parameterConfigVo) {
		JsonResult result = new JsonResult();

		// 参数验证
		String errMsg = parameterConfigService.checkParam(parameterConfigVo);
		if (StringUtils.isNotBlank(errMsg)) {
			result.failure(errMsg);
			return result;
		}

		// 唯一性验证
		//errMsg = parameterConfigService.checkUnique(parameterConfigVo);
		//if (StringUtils.isNotBlank(errMsg)) {
		//	result.failure(errMsg);
		//	return result;
		//}
		// 保存
		boolean save = parameterConfigService.saveParameterConfig(parameterConfigVo);
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
	 * @date 2021年12月09日 16:15:36
	 * @param parameterConfigId
	 * @return
	 */
	@AdminControllerLog(description="参数配置删除")
	@ResponseBody
	@RequestMapping(value = "/admin/center/parameterConfig/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult delete(Integer id) {
		JsonResult result = new JsonResult();

		// 验证参数
		if (null == id) {
			result.failure("主键不能为空");
			return result;
		}
		// 删除
		boolean delete = parameterConfigService.deleteParameterConfig(id);
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
	 * @date 2021年12月09日 16:15:36
	 * @param parameterConfigIdArr
	 * @return
	 */
	@AdminControllerLog(description="参数配置批量删除")
	@ResponseBody
	@RequestMapping(value = "/admin/center/parameterConfig/batch/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult deleteByIdArr(@RequestParam("idArr[]") Integer[] idArr) {
		JsonResult result = new JsonResult();
		// 验证参数
		if (null == idArr || idArr.length < 1) {
			result.failure("请选项要删除的数据");
			return result;
		}
		// 删除
		Integer delete = parameterConfigService.deleteByBatch(idArr);
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
	 * @date 2021年12月09日 16:15:36
	 * @param parameterConfigVo
	 * @return
	 */
	@AdminControllerLog(description="参数配置修改")
	@ResponseBody
	@RequestMapping(value = "/admin/center/parameterConfig/update", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult update(ParameterConfigVo parameterConfigVo) {
		JsonResult result = new JsonResult();

		// 验证参数
		Integer id = parameterConfigVo.getId();
		if (null == id) {
			result.failure("主键不能为空");
			return result;
		}
		String errMsg = parameterConfigService.checkParam(parameterConfigVo);
		if (StringUtils.isNotBlank(errMsg)) {
			result.failure(errMsg);
			return result;
		}

		// 唯一性验证
		//errMsg = parameterConfigService.checkUnique(parameterConfigVo);
		//if (StringUtils.isNotBlank(errMsg)) {
		//	result.failure(errMsg);
		//	return result;
		//}
		// 修改
		boolean update = parameterConfigService.updateParameterConfig(parameterConfigVo);
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
	 * @date 2021年12月09日 16:15:36
	 * @param parameterConfigVo
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/center/parameterConfig/list", method = { RequestMethod.POST })
	public AdminResultByPage list(ParameterConfigVo parameterConfigVo, HttpServletRequest request) {

		Integer page = Integer.valueOf(request.getParameter("page"));
		Integer limit = Integer.valueOf(request.getParameter("limit"));

		AdminResultByPage jsonResult = new AdminResultByPage(page, limit);

		
		jsonResult = parameterConfigService.findByPage(parameterConfigVo, jsonResult);

		return jsonResult;
	}

	/**
	 * @Title: toList
	 * @Description: 列表UI
	 * @author zhuzq
	 * @date 2021年12月09日 16:15:36
	 * @return
	 */
	@RequestMapping(value = "/admin/center/parameterConfig/list/ui", method = { RequestMethod.GET })
	public String toList() {
		return "/admin/center/parameterConfig/parameterConfig_list";
	}

	/**
	 * @Title: edit
	 * @Description: 编辑
	 * @author zhuzq
	 * @date 2021年12月09日 16:15:36
	 * @param parameterConfigId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/admin/center/parameterConfig/edit", method = { RequestMethod.GET, RequestMethod.POST })
	public String edit(Integer id, HttpServletRequest request) {
		// 编辑,为空新增
		if (null != id) {
			ParameterConfigDto entity = parameterConfigService.getParameterConfig(id);
			request.setAttribute("entity", entity);
		}
		
		Map<String,Object> paramMap = new HashMap<String, Object>();
		List<ProjectConfig> projectConfigList = projectConfigService.select(paramMap);
		request.setAttribute("projectConfigList", projectConfigList);
		return "/admin/center/parameterConfig/parameterConfig_edit";
	}
	
	/**
	 * @Title: get
	 * @Description: 查找
	 * @author zhuzq
	 * @date 2021年12月09日 16:15:36
	 * @param parameterConfigId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/center/parameterConfig/get", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult get(Integer id) {

		JsonResult result = new JsonResult();
		// 验证参数
		if (null == id || id < 1) {
			result.failure("用户ID不能为空");
			return result;
		}
		ParameterConfigDto entity = parameterConfigService.getParameterConfig(id);
		result.success("entity", entity);
		return result;
	}

}
