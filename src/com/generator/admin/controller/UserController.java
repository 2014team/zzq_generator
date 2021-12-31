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
import com.generator.constant.ValidFlagEnum;
import com.generator.domain.dto.RoleDto;
import com.generator.domain.dto.UserDto;
import com.generator.domain.vo.UserVo;
import com.generator.service.RoleService;
import com.generator.service.UserService;
import com.generator.util.SessionUtil;

/**
 * @ClassName: UserController
 * @Description: 用户
 * @author zhuzq
 * @date 2020年4月23日 下午1:38:44
 */
@Controller
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;

	/**
	 * @Title: save
	 * @Description: 保存
	 * @author zhuzq
	 * @date 2020年4月23日 下午1:38:54
	 * @param userVo
	 * @return
	 */
	@AdminControllerLog(description="用户保存")
	@ResponseBody
	@RequestMapping(value = "/admin/center/user/save", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult save(UserVo userVo) {
		JsonResult result = new JsonResult();

		// 参数验证
		String errMsg = userService.checkParam(userVo);
		if (StringUtils.isNotBlank(errMsg)) {
			result.failure(errMsg);
			return result;
		}

		// 唯一性验证
		errMsg = userService.checkUnique(userVo);
		if (StringUtils.isNotBlank(errMsg)) {
			result.failure(errMsg);
			return result;
		}
		// 保存
		boolean save = userService.saveUser(userVo);
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
	 * @date 2020年4月23日 下午1:39:03
	 * @param userId
	 * @return
	 */
	@AdminControllerLog(description="用户删除")
	@ResponseBody
	@RequestMapping(value = "/admin/center/user/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult delete(Integer userId) {
		JsonResult result = new JsonResult();

		// 验证参数
		if (null == userId) {
			result.failure("用户ID不能为空");
			return result;
		}
		// 删除
		boolean delete = userService.deleteUser(userId);
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
	 * @date 2020年4月23日 下午1:39:19
	 * @param userIdArr
	 * @return
	 */
	@AdminControllerLog(description="用户批量删除")
	@ResponseBody
	@RequestMapping(value = "/admin/center/user/batch/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult deleteByIdArr(@RequestParam("userIdArr[]") Integer[] userIdArr) {
		JsonResult result = new JsonResult();
		// 验证参数
		if (null == userIdArr || userIdArr.length < 1) {
			result.failure("请选项要删除的数据");
			return result;
		}
		// 删除
		Integer delete = userService.deleteByBatch(userIdArr);
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
	 * @date 2020年4月23日 下午1:39:28
	 * @param userVo
	 * @return
	 */
	@AdminControllerLog(description="用户修改")
	@ResponseBody
	@RequestMapping(value = "/admin/center/user/update", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult update(UserVo userVo,HttpServletRequest request) {
		JsonResult result = new JsonResult();

		// 验证参数
		Integer userId = userVo.getUserId();
		if (null == userId) {
			result.failure("用户ID不能为空");
			return result;
		}
		String errMsg = userService.checkParam(userVo);
		if (StringUtils.isNotBlank(errMsg)) {
			result.failure(errMsg);
			return result;
		}
		
		UserDto userDto = SessionUtil.getSessionUser(request);
		if(!userDto.getUserId() .equals(userId)  && !"admin".equals(userDto.getUserName())){
			result.failure("不能修改他人用户");
			return result;
		}

		// 唯一性验证
		errMsg = userService.checkUnique(userVo);
		if (StringUtils.isNotBlank(errMsg)) {
			result.failure(errMsg);
			return result;
		}
		// 修改
		boolean update = userService.updateUser(userVo);
		if (update) {
			result.success();
		} else {
			result.failure();
		}

		return result;
	}

	/**
	 * @Title: validFlag
	 * @Description: 更新状态
	 * @author zhuzq
	 * @date 2020年4月23日 下午1:39:36
	 * @param userVo
	 * @return
	 */
	@AdminControllerLog(description="用户更新状态")
	@ResponseBody
	@RequestMapping(value = "/admin/center/user/validFlag", method = { RequestMethod.POST })
	public JsonResult validFlag(UserVo userVo) {
		JsonResult jsonResult = new JsonResult();
		Integer userId = userVo.getUserId();
		if (null == userId) {
			jsonResult.failure("用户ID参数错误");
			return jsonResult;
		}

		Integer validFlag = userVo.getValidFlag();
		if (null == validFlag) {
			jsonResult.failure("状态参数错误");
			return jsonResult;
		}

		// 更新状态
		boolean result = userService.updateValidFlag(userVo);
		if (result) {
			jsonResult.success();
		} else {
			jsonResult.failure();
		}
		return jsonResult;
	}

	/**
	 * @Title: list
	 * @Description: 分页查找
	 * @author zhuzq
	 * @date 2020年4月23日 下午1:40:00
	 * @param userVo
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/center/user/list", method = { RequestMethod.POST })
	public AdminResultByPage list(UserVo userVo, HttpServletRequest request) {

		Integer page = Integer.valueOf(request.getParameter("page"));
		Integer limit = Integer.valueOf(request.getParameter("limit"));

		AdminResultByPage jsonResult = new AdminResultByPage(page, limit);
		
		
		jsonResult = userService.findByPage(userVo, jsonResult,request);

		return jsonResult;
	}

	/**
	 * @Title: toList
	 * @Description: 列表UI
	 * @author zhuzq
	 * @date 2020年4月23日 下午1:41:16
	 * @return
	 */
	@RequestMapping(value = "/admin/center/user/list/ui", method = { RequestMethod.GET })
	public String toList(HttpServletRequest request) {
		Map<String, Object> paramMap = null;
		List<RoleDto> roleDtoList = roleService.selectList(paramMap);
		request.setAttribute("roleDtoList", roleDtoList);
		return "/admin/center/user/user_list";
	}

	/**
	 * @Title: edit
	 * @Description: 编辑
	 * @author zhuzq
	 * @date 2020年4月23日 下午1:41:25
	 * @param userId
	 * @param request
	 * @return
	 */
	@AdminControllerLog(description="用户编辑")
	@RequestMapping(value = "/admin/center/user/edit", method = { RequestMethod.GET, RequestMethod.POST })
	public String edit(Integer userId, HttpServletRequest request) {
		// 编辑,为空新增
		UserDto sessionUser  = SessionUtil.getSessionUser(request);
		if (null != userId) {
			UserDto userDTO = userService.getUser(userId);
			if(!sessionUser.getUserId() .equals(userId)  && !"admin".equals(sessionUser.getUserName())){
				userDTO.setPassword("******");
			}
			
			request.setAttribute("userDTO", userDTO);
		}

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("validFlag", ValidFlagEnum.ON.ordinal());
		List<RoleDto> roleatoList = roleService.selectList(paramMap);
		request.setAttribute("roleatoList", roleatoList);
		
		
		request.setAttribute("sessionUser", sessionUser);

		return "/admin/center/user/user_edit";
	}

	/**
	 * @Title: get
	 * @Description: 查找
	 * @author zhuzq
	 * @date 2020年5月3日 下午3:18:05
	 * @param userId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/center/user/get", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult get(Integer userId) {

		JsonResult result = new JsonResult();
		// 验证参数
		if (null == userId || userId < 1) {
			result.failure("用户ID不能为空");
			return result;
		}
		UserDto userDto = userService.getUser(userId);
		result.success("userDto", userDto);
		return result;
	}

}
