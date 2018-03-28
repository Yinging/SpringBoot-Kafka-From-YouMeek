package com.gitnavi.springboot.producer.service;

import com.baomidou.mybatisplus.service.IService;
import com.gitnavi.springboot.producer.pojo.entity.SysUser;


public interface SysUserService extends IService<SysUser> {


	void sendMessage(String topicName, SysUser sysUser);


}
