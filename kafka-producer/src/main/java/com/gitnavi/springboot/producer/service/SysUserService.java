package com.gitnavi.springboot.producer.service;

import com.baomidou.mybatisplus.service.IService;
import com.gitnavi.springboot.producer.pojo.entity.SysUser;

import java.util.List;


public interface SysUserService extends IService<SysUser> {


	List<SysUser> findAll();

}
