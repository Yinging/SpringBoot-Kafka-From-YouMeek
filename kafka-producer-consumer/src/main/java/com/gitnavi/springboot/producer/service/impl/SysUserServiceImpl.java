package com.gitnavi.springboot.producer.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.gitnavi.springboot.producer.kafka.KafkaProducerSender;
import com.gitnavi.springboot.producer.mapper.SysUserMapper;
import com.gitnavi.springboot.producer.pojo.entity.SysUser;
import com.gitnavi.springboot.producer.service.SysUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

	@Resource
	private KafkaProducerSender kafkaProducerSender;

	//=====================================业务处理 start=====================================

	@Override
	public void sendMessage(String topicName, SysUser sysUser) {
		String message = "这是 producer 从 Swagger 接受到的数据：" + JSON.toJSONString(sysUser);
		kafkaProducerSender.send(topicName, message);
	}
	//=====================================业务处理  end=====================================

	//=====================================私有方法 start=====================================

	//=====================================私有方法  end=====================================

}
