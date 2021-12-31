package com.generator.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.generator.domain.dto.DatabaseTypeConfigDto;
import com.generator.domain.vo.DatabaseTypeConfigVo;
import com.generator.service.DatabaseTypeConfigService;
import com.generator.common.entity.JsonResult;
import com.generator.common.entity.AdminResultByPage;
import com.generator.annotation.AdminControllerLog;

/**
 * @ClassName: DatabaseTypeConfigController
 * @Description: 数据库类型配置
 * @author zhuzq
 * @date 2021年12月25日 17:37:05
 */
@Controller
public class DatabaseTypeConfigController {

	@Autowired
	private DatabaseTypeConfigService databaseTypeConfigService;

	/**
	 * @Title: save
	 * @Description: 保存
	 * @author zhuzq
	 * @date 2021年12月25日 17:37:05
	 * @param databaseTypeConfigVo
	 * @return
	 */
	@AdminControllerLog(description="数据库类型配置保存")
	@ResponseBody
	@RequestMapping(value = "/admin/center/databaseTypeConfig/save", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult save(DatabaseTypeConfigVo databaseTypeConfigVo) {
		JsonResult result = new JsonResult();

		// 参数验证
		String errMsg = databaseTypeConfigService.checkParam(databaseTypeConfigVo);
		if (StringUtils.isNotBlank(errMsg)) {
			result.failure(errMsg);
			return result;
		}

		// 唯一性验证
		//errMsg = databaseTypeConfigService.checkUnique(databaseTypeConfigVo);
		//if (StringUtils.isNotBlank(errMsg)) {
		//	result.failure(errMsg);
		//	return result;
		//}
		// 保存
		boolean save = databaseTypeConfigService.saveDatabaseTypeConfig(databaseTypeConfigVo);
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
	 * @date 2021年12月25日 17:37:05
	 * @param databaseTypeConfigId
	 * @return
	 */
	@AdminControllerLog(description="数据库类型配置删除")
	@ResponseBody
	@RequestMapping(value = "/admin/center/databaseTypeConfig/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult delete(Integer id) {
		JsonResult result = new JsonResult();

		// 验证参数
		if (null == id) {
			result.failure("主键不能为空");
			return result;
		}
		// 删除
		boolean delete = databaseTypeConfigService.deleteDatabaseTypeConfig(id);
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
	 * @date 2021年12月25日 17:37:05
	 * @param databaseTypeConfigIdArr
	 * @return
	 */
	@AdminControllerLog(description="数据库类型配置批量删除")
	@ResponseBody
	@RequestMapping(value = "/admin/center/databaseTypeConfig/batch/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult deleteByIdArr(@RequestParam("idArr[]") Integer[] idArr) {
		JsonResult result = new JsonResult();
		// 验证参数
		if (null == idArr || idArr.length < 1) {
			result.failure("请选项要删除的数据");
			return result;
		}
		// 删除
		Integer delete = databaseTypeConfigService.deleteByBatch(idArr);
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
	 * @date 2021年12月25日 17:37:05
	 * @param databaseTypeConfigVo
	 * @return
	 */
	@AdminControllerLog(description="数据库类型配置修改")
	@ResponseBody
	@RequestMapping(value = "/admin/center/databaseTypeConfig/update", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult update(DatabaseTypeConfigVo databaseTypeConfigVo) {
		JsonResult result = new JsonResult();

		// 验证参数
		Integer id = databaseTypeConfigVo.getId();
		if (null == id) {
			result.failure("主键不能为空");
			return result;
		}
		String errMsg = databaseTypeConfigService.checkParam(databaseTypeConfigVo);
		if (StringUtils.isNotBlank(errMsg)) {
			result.failure(errMsg);
			return result;
		}

		// 唯一性验证
		//errMsg = databaseTypeConfigService.checkUnique(databaseTypeConfigVo);
		//if (StringUtils.isNotBlank(errMsg)) {
		//	result.failure(errMsg);
		//	return result;
		//}
		// 修改
		boolean update = databaseTypeConfigService.updateDatabaseTypeConfig(databaseTypeConfigVo);
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
	 * @date 2021年12月25日 17:37:05
	 * @param databaseTypeConfigVo
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/center/databaseTypeConfig/list", method = { RequestMethod.POST })
	public AdminResultByPage list(DatabaseTypeConfigVo databaseTypeConfigVo, HttpServletRequest request) {

		Integer page = Integer.valueOf(request.getParameter("page"));
		Integer limit = Integer.valueOf(request.getParameter("limit"));

		AdminResultByPage jsonResult = new AdminResultByPage(page, limit);

		jsonResult = databaseTypeConfigService.findByPage(databaseTypeConfigVo, jsonResult);

		return jsonResult;
	}

	/**
	 * @Title: toList
	 * @Description: 列表UI
	 * @author zhuzq
	 * @date 2021年12月25日 17:37:05
	 * @return
	 */
	@RequestMapping(value = "/admin/center/databaseTypeConfig/list/ui", method = { RequestMethod.GET })
	public String toList() {
		return "/admin/center/databaseTypeConfig/databaseTypeConfig_list";
	}

	/**
	 * @Title: edit
	 * @Description: 编辑
	 * @author zhuzq
	 * @date 2021年12月25日 17:37:05
	 * @param databaseTypeConfigId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/admin/center/databaseTypeConfig/edit", method = { RequestMethod.GET, RequestMethod.POST })
	public String edit(Integer id, HttpServletRequest request) {
		// 编辑,为空新增
		if (null != id) {
			DatabaseTypeConfigDto entity = databaseTypeConfigService.getDatabaseTypeConfig(id);
			request.setAttribute("entity", entity);
		}
		return "/admin/center/databaseTypeConfig/databaseTypeConfig_edit";
	}
	
	/**
	 * @Title: get
	 * @Description: 查找
	 * @author zhuzq
	 * @date 2021年12月25日 17:37:05
	 * @param databaseTypeConfigId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/center/databaseTypeConfig/get", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult get(Integer id) {

		JsonResult result = new JsonResult();
		// 验证参数
		if (null == id || id < 1) {
			result.failure("用户ID不能为空");
			return result;
		}
		DatabaseTypeConfigDto entity = databaseTypeConfigService.getDatabaseTypeConfig(id);
		result.success("entity", entity);
		return result;
	}

}
