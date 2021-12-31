
var admin_url_map = {
	'admin/LOGIN/SUBMIT': {url: '/admin/login/submit', isUsed: true, desc: '首页'},
	'admin/INDEX': {url: '/admin/index', isUsed: true, desc: '跳转登录'},
	
	//用户
	'admin/CENTER/USER/LIST': {url: '/admin/center/user/list', isUsed: true, desc: '管理员列表'},
	'admin/CENTER/USER/VALIDFLAG': {url: '/admin/center/user/validFlag', isUsed: true, desc: '更新状态'},
	'admin/CENTER/USER/DELETE': {url: '/admin/center/user/delete', isUsed: true, desc: '删除用户'},
	'admin/CENTER/USER/UPDATE': {url: '/admin/center/user/update', isUsed: true, desc: '修改用户'},
	'admin/CENTER/USER/EDIT': {url: '/admin/center/user/edit', isUsed: true, desc: '编辑用户'},
	'admin/CENTER/USER/SAVE': {url: '/admin/center/user/save', isUsed: true, desc: '保存用户'},
	'admin/CENTER/USER/BATCH/DELETE': {url: '/admin/center/user/batch/delete', isUsed: true, desc: '用户批量删除'},
	'admin/CENTER/USER/GET': {url: '/admin/center/user/get', isUsed: true, desc: '查找单个用户'},
	
	//角色
	'admin/CENTER/ROLE/GET': {url: '/admin/center/role/get', isUsed: true, desc: '角色查找'},
	'admin/CENTER/ROLE/SAVE': {url: '/admin/center/role/save', isUsed: true, desc: '角色保存'},
	'admin/CENTER/ROLE/DELETE': {url: '/admin/center/role/delete', isUsed: true, desc: '角色删除'},
	'admin/CENTER/ROLE/UPDATE': {url: '/admin/center/role/update', isUsed: true, desc: '角色修改'},
	'admin/CENTER/ROLE/EDIT': {url: '/admin/center/role/edit', isUsed: true, desc: '角色编辑'},
	'admin/CENTER/ROLE/LIST': {url: '/admin/center/role/list', isUsed: true, desc: '角色列表'},
	'admin/CENTER/ROLE/BATCH/DELETE': {url: '/admin/center/role/batch/delete', isUsed: true, desc: '角色批量删除'},
	'admin/CENTER/ROLE/VALIDFLAG': {url: '/admin/center/role/validFlag', isUsed: true, desc: '角色状态修改'},
	'admin/CENTER/ROLE/RIGHT': {url: '/admin/center/role/right', isUsed: true, desc: '角色分配权限'},
	'admin/CENTER/ROLE/RIGHT/SAVE': {url: '/admin/center/role/right/save', isUsed: true, desc: '角色分配权限保存'},
	
	//菜单
	'admin/CENTER/MENU/GET': {url: '/admin/center/menu/get', isUsed: true, desc: '菜单查找'},
	'admin/CENTER/MENU/SAVE': {url: '/admin/center/menu/save', isUsed: true, desc: '菜单保存'},
	'admin/CENTER/MENU/DELETE': {url: '/admin/center/menu/delete', isUsed: true, desc: '菜单删除'},
	'admin/CENTER/MENU/UPDATE': {url: '/admin/center/menu/update', isUsed: true, desc: '菜单修改'},
	'admin/CENTER/MENU/EDIT': {url: '/admin/center/menu/edit', isUsed: true, desc: '菜单编辑'},
	'admin/CENTER/MENU/LIST': {url: '/admin/center/menu/list', isUsed: true, desc: '菜单列表'},
	'admin/CENTER/MENU/BATCH/DELETE': {url: '/admin/center/menu/batch/delete', isUsed: true, desc: '菜单批量删除'},
	'admin/CENTER/MENU/VALIDFLAG': {url: '/admin/center/menu/validFlag', isUsed: true, desc: '菜单状态修改'},
	
	//日志
	'admin/CENTER/LOG/DETAIL': {url: '/admin/center/log/detail', isUsed: true, desc: '日志详情'},
	'admin/CENTER/LOG/LIST': {url: '/admin/center/log/list', isUsed: true, desc: '日志列表'},
	'admin/CENTER/LOG/BATCH/DELETE': {url: '/admin/center/log/batch/delete', isUsed: true, desc: '日志批量删除'},
	
	
	// 数据源配配置
	'admin/CENTER/DATABASECONFIG/LIST': {url: '/admin/center/databaseConfig/list', isUsed: true, desc: '列表'},
	'admin/CENTER/DATABASECONFIG/DELETE': {url: '/admin/center/databaseConfig/delete', isUsed: true, desc: '删除'},
	'admin/CENTER/DATABASECONFIG/UPDATE': {url: '/admin/center/databaseConfig/update', isUsed: true, desc: '更新'},
	'admin/CENTER/DATABASECONFIG/EDIT': {url: '/admin/center/databaseConfig/edit', isUsed: true, desc: '编辑'},
	'admin/CENTER/DATABASECONFIG/DETAIL': {url: '/admin/center/databaseConfig/detail', isUsed: true, desc: '详情信息'},
	'admin/CENTER/DATABASECONFIG/SAVE': {url: '/admin/center/databaseConfig/save', isUsed: true, desc: '保存信息'},
	'admin/CENTER/DATABASECONFIG/BATCH/DELETE': {url: '/admin/center/databaseConfig/batch/delete', isUsed: true, desc: '批量删除'},
	'admin/CENTER/DATABASECONFIG/GET': {url: '/admin/center/databaseConfig/get', isUsed: true, desc: '查找单个信息'},
	
	// 参数配置
	'admin/CENTER/PARAMETERCONFIG/LIST': {url: '/admin/center/parameterConfig/list', isUsed: true, desc: '列表'},
	'admin/CENTER/PARAMETERCONFIG/DELETE': {url: '/admin/center/parameterConfig/delete', isUsed: true, desc: '删除'},
	'admin/CENTER/PARAMETERCONFIG/UPDATE': {url: '/admin/center/parameterConfig/update', isUsed: true, desc: '更新'},
	'admin/CENTER/PARAMETERCONFIG/EDIT': {url: '/admin/center/parameterConfig/edit', isUsed: true, desc: '编辑'},
	'admin/CENTER/PARAMETERCONFIG/DETAIL': {url: '/admin/center/parameterConfig/detail', isUsed: true, desc: '详情信息'},
	'admin/CENTER/PARAMETERCONFIG/SAVE': {url: '/admin/center/parameterConfig/save', isUsed: true, desc: '保存信息'},
	'admin/CENTER/PARAMETERCONFIG/BATCH/DELETE': {url: '/admin/center/parameterConfig/batch/delete', isUsed: true, desc: '批量删除'},
	'admin/CENTER/PARAMETERCONFIG/GET': {url: '/admin/center/parameterConfig/get', isUsed: true, desc: '查找单个信息'},
	
	
	// 模板配置
	'admin/CENTER/TEMPLATECONFIG/LIST': {url: '/admin/center/templateConfig/list', isUsed: true, desc: '列表'},
	'admin/CENTER/TEMPLATECONFIG/DELETE': {url: '/admin/center/templateConfig/delete', isUsed: true, desc: '删除'},
	'admin/CENTER/TEMPLATECONFIG/UPDATE': {url: '/admin/center/templateConfig/update', isUsed: true, desc: '更新'},
	'admin/CENTER/TEMPLATECONFIG/EDIT': {url: '/admin/center/templateConfig/edit', isUsed: true, desc: '编辑'},
	'admin/CENTER/TEMPLATECONFIG/DETAIL': {url: '/admin/center/templateConfig/detail', isUsed: true, desc: '详情信息'},
	'admin/CENTER/TEMPLATECONFIG/SAVE': {url: '/admin/center/templateConfig/save', isUsed: true, desc: '保存信息'},
	'admin/CENTER/TEMPLATECONFIG/BATCH/DELETE': {url: '/admin/center/templateConfig/batch/delete', isUsed: true, desc: '批量删除'},
	'admin/CENTER/TEMPLATECONFIG/GET': {url: '/admin/center/templateConfig/get', isUsed: true, desc: '查找单个信息'},
	
	// 组合配置
	'admin/CENTER/UNITECONFIG/LIST': {url: '/admin/center/uniteConfig/list', isUsed: true, desc: '列表'},
	'admin/CENTER/UNITECONFIG/DELETE': {url: '/admin/center/uniteConfig/delete', isUsed: true, desc: '删除'},
	'admin/CENTER/UNITECONFIG/UPDATE': {url: '/admin/center/uniteConfig/update', isUsed: true, desc: '更新'},
	'admin/CENTER/UNITECONFIG/EDIT': {url: '/admin/center/uniteConfig/edit', isUsed: true, desc: '编辑'},
	'admin/CENTER/UNITECONFIG/DETAIL': {url: '/admin/center/uniteConfig/detail', isUsed: true, desc: '详情信息'},
	'admin/CENTER/UNITECONFIG/SAVE': {url: '/admin/center/uniteConfig/save', isUsed: true, desc: '保存信息'},
	'admin/CENTER/UNITECONFIG/BATCH/DELETE': {url: '/admin/center/uniteConfig/batch/delete', isUsed: true, desc: '批量删除'},
	'admin/CENTER/UNITECONFIG/GET': {url: '/admin/center/uniteConfig/get', isUsed: true, desc: '查找单个信息'},
	
	'admin/CENTER/DATABASETYPECONFIG/LIST': {url: '/admin/center/databaseTypeConfig/list', isUsed: true, desc: '列表'},
	'admin/CENTER/DATABASETYPECONFIG/DELETE': {url: '/admin/center/databaseTypeConfig/delete', isUsed: true, desc: '删除'},
	'admin/CENTER/DATABASETYPECONFIG/UPDATE': {url: '/admin/center/databaseTypeConfig/update', isUsed: true, desc: '更新'},
	'admin/CENTER/DATABASETYPECONFIG/EDIT': {url: '/admin/center/databaseTypeConfig/edit', isUsed: true, desc: '编辑'},
	'admin/CENTER/DATABASETYPECONFIG/DETAIL': {url: '/admin/center/databaseTypeConfig/detail', isUsed: true, desc: '详情信息'},
	'admin/CENTER/DATABASETYPECONFIG/SAVE': {url: '/admin/center/databaseTypeConfig/save', isUsed: true, desc: '保存信息'},
	'admin/CENTER/DATABASETYPECONFIG/BATCH/DELETE': {url: '/admin/center/databaseTypeConfig/batch/delete', isUsed: true, desc: '批量删除'},
	'admin/CENTER/DATABASETYPECONFIG/GET': {url: '/admin/center/databaseTypeConfig/get', isUsed: true, desc: '查找单个信息'},
	
	'admin/CENTER/GENERATETEMPLATES/LIST': {url: '/admin/center/generateTemplates/list', isUsed: true, desc: '列表'},
	'admin/CENTER/GENERATETEMPLATES/DELETE': {url: '/admin/center/generateTemplates/delete', isUsed: true, desc: '删除'},
	'admin/CENTER/GENERATETEMPLATES/UPDATE': {url: '/admin/center/generateTemplates/update', isUsed: true, desc: '更新'},
	'admin/CENTER/GENERATETEMPLATES/EDIT': {url: '/admin/center/generateTemplates/edit', isUsed: true, desc: '编辑'},
	'admin/CENTER/GENERATETEMPLATES/DETAIL': {url: '/admin/center/generateTemplates/detail', isUsed: true, desc: '详情信息'},
	'admin/CENTER/GENERATETEMPLATES/SAVE': {url: '/admin/center/generateTemplates/save', isUsed: true, desc: '保存信息'},
	'admin/CENTER/GENERATETEMPLATES/BATCH/DELETE': {url: '/admin/center/generateTemplates/batch/delete', isUsed: true, desc: '批量删除'},
	'admin/CENTER/GENERATETEMPLATES/GET': {url: '/admin/center/generateTemplates/get', isUsed: true, desc: '查找单个信息'},
	'admin/CENTER/GENERATETEMPLATES/EXECUTE': {url: '/admin/center/generateTemplates/execute', isUsed: true, desc: '生成代码'},
	
	
	'admin/CENTER/PROJECTCONFIG/LIST': {url: '/admin/center/projectConfig/list', isUsed: true, desc: '列表'},
	'admin/CENTER/PROJECTCONFIG/DELETE': {url: '/admin/center/projectConfig/delete', isUsed: true, desc: '删除'},
	'admin/CENTER/PROJECTCONFIG/UPDATE': {url: '/admin/center/projectConfig/update', isUsed: true, desc: '更新'},
	'admin/CENTER/PROJECTCONFIG/EDIT': {url: '/admin/center/projectConfig/edit', isUsed: true, desc: '编辑'},
	'admin/CENTER/PROJECTCONFIG/DETAIL': {url: '/admin/center/projectConfig/detail', isUsed: true, desc: '详情信息'},
	'admin/CENTER/PROJECTCONFIG/SAVE': {url: '/admin/center/projectConfig/save', isUsed: true, desc: '保存信息'},
	'admin/CENTER/PROJECTCONFIG/BATCH/DELETE': {url: '/admin/center/projectConfig/batch/delete', isUsed: true, desc: '批量删除'},
	'admin/CENTER/PROJECTCONFIG/GET': {url: '/admin/center/projectConfig/get', isUsed: true, desc: '查找单个信息'},
	'admin/CENTER/PROJECTCONFIG/EXECUTE': {url: '/admin/center/projectConfig/execute', isUsed: true, desc: '生成代码'},
	
	
	
}

var web_url_map = {

}



function getAminUrl(key) {
	if(admin_url_map[key] && admin_url_map[key].isUsed == true){
		return admin_url_map[key].url;
	}else{
		return '';
	}
}

function getWebUrl(key) {
	if(web_url_map[key] && web_url_map[key].isUsed == true){
		return web_url_map[key].url;
	}else{
		return '';
	}
}
