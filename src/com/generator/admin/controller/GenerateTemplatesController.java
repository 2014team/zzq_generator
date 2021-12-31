package com.generator.admin.controller;

import java.util.HashMap;
import java.util.List;

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
import com.generator.domain.dto.GenerateTemplatesDto;
import com.generator.domain.entity.ProjectConfig;
import com.generator.domain.vo.GenerateTemplatesVo;
import com.generator.service.GenerateTemplatesService;
import com.generator.service.ProjectConfigService;

/**
 * @ClassName: GenerateTemplatesController
 * @Description: 模本生成管理
 * @author zhuzq
 * @date 2021年12月27日 09:37:25
 */
@Controller
public class GenerateTemplatesController {

	@Autowired
	private GenerateTemplatesService generateTemplatesService;
	@Autowired
	private ProjectConfigService projectConfigService;
	/**
	 * @Title: save
	 * @Description: 保存
	 * @author zhuzq
	 * @date 2021年12月27日 09:37:25
	 * @param generateTemplatesVo
	 * @return
	 */
	@AdminControllerLog(description="模本生成管理保存")
	@ResponseBody
	@RequestMapping(value = "/admin/center/generateTemplates/save", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult save(GenerateTemplatesVo generateTemplatesVo) {
		JsonResult result = new JsonResult();

		// 参数验证
		String errMsg = generateTemplatesService.checkParam(generateTemplatesVo);
		if (StringUtils.isNotBlank(errMsg)) {
			result.failure(errMsg);
			return result;
		}

		// 唯一性验证
		//errMsg = generateTemplatesService.checkUnique(generateTemplatesVo);
		//if (StringUtils.isNotBlank(errMsg)) {
		//	result.failure(errMsg);
		//	return result;
		//}
		// 保存
		boolean save = generateTemplatesService.saveGenerateTemplates(generateTemplatesVo);
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
	 * @date 2021年12月27日 09:37:25
	 * @param generateTemplatesId
	 * @return
	 */
	@AdminControllerLog(description="模本生成管理删除")
	@ResponseBody
	@RequestMapping(value = "/admin/center/generateTemplates/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult delete(Integer id) {
		JsonResult result = new JsonResult();

		// 验证参数
		if (null == id) {
			result.failure("主键不能为空");
			return result;
		}
		// 删除
		boolean delete = generateTemplatesService.deleteGenerateTemplates(id);
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
	 * @date 2021年12月27日 09:37:25
	 * @param generateTemplatesIdArr
	 * @return
	 */
	@AdminControllerLog(description="模本生成管理批量删除")
	@ResponseBody
	@RequestMapping(value = "/admin/center/generateTemplates/batch/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult deleteByIdArr(@RequestParam("idArr[]") Integer[] idArr) {
		JsonResult result = new JsonResult();
		// 验证参数
		if (null == idArr || idArr.length < 1) {
			result.failure("请选项要删除的数据");
			return result;
		}
		// 删除
		Integer delete = generateTemplatesService.deleteByBatch(idArr);
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
	 * @date 2021年12月27日 09:37:25
	 * @param generateTemplatesVo
	 * @return
	 */
	@AdminControllerLog(description="模本生成管理修改")
	@ResponseBody
	@RequestMapping(value = "/admin/center/generateTemplates/update", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult update(GenerateTemplatesVo generateTemplatesVo) {
		JsonResult result = new JsonResult();

		// 验证参数
		Integer id = generateTemplatesVo.getId();
		if (null == id) {
			result.failure("主键不能为空");
			return result;
		}
		String errMsg = generateTemplatesService.checkParam(generateTemplatesVo);
		if (StringUtils.isNotBlank(errMsg)) {
			result.failure(errMsg);
			return result;
		}

		// 唯一性验证
		//errMsg = generateTemplatesService.checkUnique(generateTemplatesVo);
		//if (StringUtils.isNotBlank(errMsg)) {
		//	result.failure(errMsg);
		//	return result;
		//}
		// 修改
		boolean update = generateTemplatesService.updateGenerateTemplates(generateTemplatesVo);
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
	 * @date 2021年12月27日 09:37:25
	 * @param generateTemplatesVo
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/center/generateTemplates/list", method = { RequestMethod.POST })
	public AdminResultByPage list(GenerateTemplatesVo generateTemplatesVo, HttpServletRequest request) {

		Integer page = Integer.valueOf(request.getParameter("page"));
		Integer limit = Integer.valueOf(request.getParameter("limit"));

		AdminResultByPage jsonResult = new AdminResultByPage(page, limit);

		jsonResult = generateTemplatesService.findByPage(generateTemplatesVo, jsonResult);

		return jsonResult;
	}

	/**
	 * @Title: toList
	 * @Description: 列表UI
	 * @author zhuzq
	 * @date 2021年12月27日 09:37:25
	 * @return
	 */
	@RequestMapping(value = "/admin/center/generateTemplates/list/ui", method = { RequestMethod.GET })
	public String toList() {
		return "/admin/center/generateTemplates/generateTemplates_list";
	}

	/**
	 * @Title: edit
	 * @Description: 编辑
	 * @author zhuzq
	 * @date 2021年12月27日 09:37:25
	 * @param generateTemplatesId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/admin/center/generateTemplates/edit", method = { RequestMethod.GET, RequestMethod.POST })
	public String edit(Integer id, HttpServletRequest request) {
		// 编辑,为空新增
		if (null != id) {
			GenerateTemplatesDto entity = generateTemplatesService.getGenerateTemplates(id);
			request.setAttribute("entity", entity);
		}
		

		List<ProjectConfig> projectConfigList = projectConfigService.select(new HashMap<String, Object>());
		request.setAttribute("projectConfigList", projectConfigList);
		return "/admin/center/generateTemplates/generateTemplates_edit";
	}
	
	/**
	 * @Title: get
	 * @Description: 查找
	 * @author zhuzq
	 * @date 2021年12月27日 09:37:25
	 * @param generateTemplatesId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/center/generateTemplates/get", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult get(Integer id) {

		JsonResult result = new JsonResult();
		// 验证参数
		if (null == id || id < 1) {
			result.failure("用户ID不能为空");
			return result;
		}
		GenerateTemplatesDto entity = generateTemplatesService.getGenerateTemplates(id);
		result.success("entity", entity);
		return result;
	}
	
	
	/**
	* @Title: execute
	* @Description: 执行生成模板
	* @author zhuzq
	* @date  2021年12月27日 上午10:50:15
	* @param generateTemplatesVo
	* @return
	*/
	@ResponseBody
	@RequestMapping(value = "/admin/center/generateTemplates/execute", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult execute(GenerateTemplatesVo generateTemplatesVo,@RequestParam("idArr[]") Integer[] idArr) {
		JsonResult result = new JsonResult();
		
		// 验证参数
		if (null == idArr || idArr.length < 1) {
			result.failure("用户ID不能为空");
			return result;
		}
		
		String execute = null;
		for (Integer id : idArr) {
			execute = generateTemplatesService.execute(id);
			if(StringUtils.isNotEmpty(execute)){
				result.failure(execute);
				return result;
			}
		}
		
		result.success();
		return result;
	}

}
