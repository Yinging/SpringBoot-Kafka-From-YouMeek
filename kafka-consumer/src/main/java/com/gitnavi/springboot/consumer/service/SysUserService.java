package com.gitnavi.springboot.consumer.service;

import com.baomidou.mybatisplus.service.IService;
import com.gitnavi.springboot.consumer.pojo.entity.SysUser;

import java.util.List;


public interface SysUserService extends IService<SysUser> {


	List<SysUser> findAll();

}
