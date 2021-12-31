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
import com.generator.domain.dto.DatabaseConfigDto;
import com.generator.domain.entity.DatabaseTypeConfig;
import com.generator.domain.entity.ProjectConfig;
import com.generator.domain.vo.DatabaseConfigVo;
import com.generator.service.DatabaseConfigService;
import com.generator.service.DatabaseTypeConfigService;
import com.generator.service.ProjectConfigService;

/**
 * @ClassName: DatabaseConfigController
 * @Description: 数据库配置
 * @author zhuzq
 * @date 2021年12月09日 14:37:29
 */
@Controller
public class DatabaseConfigController {

	@Autowired
	private DatabaseConfigService databaseConfigService;
	@Autowired
	private DatabaseTypeConfigService databaseTypeConfigService;
	@Autowired
	private ProjectConfigService projectConfigService;
	/**
	 * @Title: save
	 * @Description: 保存
	 * @author zhuzq
	 * @date 2021年12月09日 14:37:29
	 * @param databaseConfigVo
	 * @return
	 */
	@AdminControllerLog(description="数据库配置保存")
	@ResponseBody
	@RequestMapping(value = "/admin/center/databaseConfig/save", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult save(DatabaseConfigVo databaseConfigVo) {
		JsonResult result = new JsonResult();

		// 参数验证
		String errMsg = databaseConfigService.checkParam(databaseConfigVo);
		if (StringUtils.isNotBlank(errMsg)) {
			result.failure(errMsg);
			return result;
		}

		// 唯一性验证
		//errMsg = databaseConfigService.checkUnique(databaseConfigVo);
		//if (StringUtils.isNotBlank(errMsg)) {
		//	result.failure(errMsg);
		//	return result;
		//}
		// 保存
		boolean save = databaseConfigService.saveDatabaseConfig(databaseConfigVo);
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
	 * @date 2021年12月09日 14:37:29
	 * @param databaseConfigId
	 * @return
	 */
	@AdminControllerLog(description="数据库配置删除")
	@ResponseBody
	@RequestMapping(value = "/admin/center/databaseConfig/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult delete(Integer id) {
		JsonResult result = new JsonResult();

		// 验证参数
		if (null == id) {
			result.failure("主键不能为空");
			return result;
		}
		// 删除
		boolean delete = databaseConfigService.deleteDatabaseConfig(id);
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
	 * @date 2021年12月09日 14:37:29
	 * @param databaseConfigIdArr
	 * @return
	 */
	@AdminControllerLog(description="数据库配置批量删除")
	@ResponseBody
	@RequestMapping(value = "/admin/center/databaseConfig/batch/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult deleteByIdArr(@RequestParam("idArr[]") Integer[] idArr) {
		JsonResult result = new JsonResult();
		// 验证参数
		if (null == idArr || idArr.length < 1) {
			result.failure("请选项要删除的数据");
			return result;
		}
		// 删除
		Integer delete = databaseConfigService.deleteByBatch(idArr);
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
	 * @date 2021年12月09日 14:37:29
	 * @param databaseConfigVo
	 * @return
	 */
	@AdminControllerLog(description="数据库配置修改")
	@ResponseBody
	@RequestMapping(value = "/admin/center/databaseConfig/update", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult update(DatabaseConfigVo databaseConfigVo) {
		JsonResult result = new JsonResult();

		// 验证参数
		Integer id = databaseConfigVo.getId();
		if (null == id) {
			result.failure("主键不能为空");
			return result;
		}
		String errMsg = databaseConfigService.checkParam(databaseConfigVo);
		if (StringUtils.isNotBlank(errMsg)) {
			result.failure(errMsg);
			return result;
		}

		// 唯一性验证
		//errMsg = databaseConfigService.checkUnique(databaseConfigVo);
		//if (StringUtils.isNotBlank(errMsg)) {
		//	result.failure(errMsg);
		//	return result;
		//}
		// 修改
		boolean update = databaseConfigService.updateDatabaseConfig(databaseConfigVo);
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
	 * @date 2021年12月09日 14:37:29
	 * @param databaseConfigVo
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/center/databaseConfig/list", method = { RequestMethod.POST })
	public AdminResultByPage list(DatabaseConfigVo databaseConfigVo, HttpServletRequest request) {

		Integer page = Integer.valueOf(request.getParameter("page"));
		Integer limit = Integer.valueOf(request.getParameter("limit"));

		AdminResultByPage jsonResult = new AdminResultByPage(page, limit);

		jsonResult = databaseConfigService.findByPage(databaseConfigVo, jsonResult);

		return jsonResult;
	}

	/**
	 * @Title: toList
	 * @Description: 列表UI
	 * @author zhuzq
	 * @date 2021年12月09日 14:37:29
	 * @return
	 */
	@RequestMapping(value = "/admin/center/databaseConfig/list/ui", method = { RequestMethod.GET })
	public String toList() {
		return "/admin/center/databaseConfig/databaseConfig_list";
	}

	/**
	 * @Title: edit
	 * @Description: 编辑
	 * @author zhuzq
	 * @date 2021年12月09日 14:37:29
	 * @param databaseConfigId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/admin/center/databaseConfig/edit", method = { RequestMethod.GET, RequestMethod.POST })
	public String edit(Integer id, HttpServletRequest request) {
		// 编辑,为空新增
		if (null != id) {
			DatabaseConfigDto entity = databaseConfigService.getDatabaseConfig(id);
			request.setAttribute("entity", entity);
		}
		
		List<DatabaseTypeConfig> databaseTypeConfigList =  databaseTypeConfigService.select(new HashMap<String, Object>());
		request.setAttribute("databaseTypeConfigList", databaseTypeConfigList);
		
		Map<String,Object> paramMap = new HashMap<String, Object>();
		List<ProjectConfig> projectConfigList = projectConfigService.select(paramMap);
		request.setAttribute("projectConfigList", projectConfigList);
		
		return "/admin/center/databaseConfig/databaseConfig_edit";
	}
	
	/**
	 * @Title: get
	 * @Description: 查找
	 * @author zhuzq
	 * @date 2021年12月09日 14:37:29
	 * @param databaseConfigId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/center/databaseConfig/get", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult get(Integer id) {

		JsonResult result = new JsonResult();
		// 验证参数
		if (null == id || id < 1) {
			result.failure("用户ID不能为空");
			return result;
		}
		DatabaseConfigDto entity = databaseConfigService.getDatabaseConfig(id);
		result.success("entity", entity);
		return result;
	}

}
