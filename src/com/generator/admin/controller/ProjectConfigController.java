package com.generator.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.generator.domain.dto.ProjectConfigDto;
import com.generator.domain.vo.ProjectConfigVo;
import com.generator.service.ProjectConfigService;
import com.generator.common.entity.JsonResult;
import com.generator.common.entity.AdminResultByPage;
import com.generator.annotation.AdminControllerLog;

/**
 * @ClassName: ProjectConfigController
 * @Description: 项目配置管理
 * @author zhuzq
 * @date 2021年12月28日 16:37:20
 */
@Controller
public class ProjectConfigController {

	@Autowired
	private ProjectConfigService projectConfigService;

	/**
	 * @Title: save
	 * @Description: 保存
	 * @author zhuzq
	 * @date 2021年12月28日 16:37:20
	 * @param projectConfigVo
	 * @return
	 */
	@AdminControllerLog(description="项目配置管理保存")
	@ResponseBody
	@RequestMapping(value = "/admin/center/projectConfig/save", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult save(ProjectConfigVo projectConfigVo) {
		JsonResult result = new JsonResult();

		// 参数验证
		String errMsg = projectConfigService.checkParam(projectConfigVo);
		if (StringUtils.isNotBlank(errMsg)) {
			result.failure(errMsg);
			return result;
		}

		// 唯一性验证
		//errMsg = projectConfigService.checkUnique(projectConfigVo);
		//if (StringUtils.isNotBlank(errMsg)) {
		//	result.failure(errMsg);
		//	return result;
		//}
		// 保存
		boolean save = projectConfigService.saveProjectConfig(projectConfigVo);
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
	 * @date 2021年12月28日 16:37:20
	 * @param projectConfigId
	 * @return
	 */
	@AdminControllerLog(description="项目配置管理删除")
	@ResponseBody
	@RequestMapping(value = "/admin/center/projectConfig/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult delete(Integer id) {
		JsonResult result = new JsonResult();

		// 验证参数
		if (null == id) {
			result.failure("主键不能为空");
			return result;
		}
		// 删除
		boolean delete = projectConfigService.deleteProjectConfig(id);
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
	 * @date 2021年12月28日 16:37:20
	 * @param projectConfigIdArr
	 * @return
	 */
	@AdminControllerLog(description="项目配置管理批量删除")
	@ResponseBody
	@RequestMapping(value = "/admin/center/projectConfig/batch/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult deleteByIdArr(@RequestParam("idArr[]") Integer[] idArr) {
		JsonResult result = new JsonResult();
		// 验证参数
		if (null == idArr || idArr.length < 1) {
			result.failure("请选项要删除的数据");
			return result;
		}
		// 删除
		Integer delete = projectConfigService.deleteByBatch(idArr);
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
	 * @date 2021年12月28日 16:37:20
	 * @param projectConfigVo
	 * @return
	 */
	@AdminControllerLog(description="项目配置管理修改")
	@ResponseBody
	@RequestMapping(value = "/admin/center/projectConfig/update", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult update(ProjectConfigVo projectConfigVo) {
		JsonResult result = new JsonResult();

		// 验证参数
		Integer id = projectConfigVo.getId();
		if (null == id) {
			result.failure("主键不能为空");
			return result;
		}
		String errMsg = projectConfigService.checkParam(projectConfigVo);
		if (StringUtils.isNotBlank(errMsg)) {
			result.failure(errMsg);
			return result;
		}

		// 唯一性验证
		//errMsg = projectConfigService.checkUnique(projectConfigVo);
		//if (StringUtils.isNotBlank(errMsg)) {
		//	result.failure(errMsg);
		//	return result;
		//}
		// 修改
		boolean update = projectConfigService.updateProjectConfig(projectConfigVo);
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
	 * @date 2021年12月28日 16:37:20
	 * @param projectConfigVo
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/center/projectConfig/list", method = { RequestMethod.POST })
	public AdminResultByPage list(ProjectConfigVo projectConfigVo, HttpServletRequest request) {

		Integer page = Integer.valueOf(request.getParameter("page"));
		Integer limit = Integer.valueOf(request.getParameter("limit"));

		AdminResultByPage jsonResult = new AdminResultByPage(page, limit);

		jsonResult = projectConfigService.findByPage(projectConfigVo, jsonResult);

		return jsonResult;
	}

	/**
	 * @Title: toList
	 * @Description: 列表UI
	 * @author zhuzq
	 * @date 2021年12月28日 16:37:20
	 * @return
	 */
	@RequestMapping(value = "/admin/center/projectConfig/list/ui", method = { RequestMethod.GET })
	public String toList() {
		return "/admin/center/projectConfig/projectConfig_list";
	}

	/**
	 * @Title: edit
	 * @Description: 编辑
	 * @author zhuzq
	 * @date 2021年12月28日 16:37:20
	 * @param projectConfigId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/admin/center/projectConfig/edit", method = { RequestMethod.GET, RequestMethod.POST })
	public String edit(Integer id, HttpServletRequest request) {
		// 编辑,为空新增
		if (null != id) {
			ProjectConfigDto entity = projectConfigService.getProjectConfig(id);
			request.setAttribute("entity", entity);
		}
		return "/admin/center/projectConfig/projectConfig_edit";
	}
	
	/**
	 * @Title: get
	 * @Description: 查找
	 * @author zhuzq
	 * @date 2021年12月28日 16:37:20
	 * @param projectConfigId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/center/projectConfig/get", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult get(Integer id) {

		JsonResult result = new JsonResult();
		// 验证参数
		if (null == id || id < 1) {
			result.failure("用户ID不能为空");
			return result;
		}
		ProjectConfigDto entity = projectConfigService.getProjectConfig(id);
		result.success("entity", entity);
		return result;
	}

}
