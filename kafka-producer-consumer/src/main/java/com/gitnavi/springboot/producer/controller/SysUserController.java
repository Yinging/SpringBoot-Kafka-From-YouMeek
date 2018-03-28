package com.gitnavi.springboot.producer.controller;


import com.gitnavi.springboot.producer.pojo.entity.SysUser;
import com.gitnavi.springboot.producer.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;


@Api(value = "SysUserRestController RESTful", description = "用户模块管理 REST API")
@RestController
@RequestMapping("/api/sysUser")
public class SysUserController {

	@Resource
	private SysUserService sysUserService;

	//=====================================业务处理 start=====================================


	@ApiOperation(value = "发送用户信息到 kafka", notes = "发送用户信息到 kafka")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "sysUser", value = "用户信息", paramType = "body"),
	})
	@RequestMapping(value = "/send", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> post(@RequestBody SysUser sysUser) {
		Map<String, Object> map = new HashMap<>();
		if (StringUtils.isBlank(sysUser.getLoginName()) && StringUtils.isBlank(sysUser.getEmail())) {
			sysUser.setEmail("GitNavi.com@qq.com");
			sysUser.setLoginName("YouMeek.com");
		}
		sysUserService.sendMessage("my-topic-test",sysUser);
		map.put("发送结果", "发送成功");
		return ResponseEntity.ok(map);
	}

	//=====================================业务处理  end=====================================

	//=====================================私有方法 start=====================================

	//=====================================私有方法  end=====================================

}
