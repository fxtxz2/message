package com.message.admin.msg.service;

import org.springframework.stereotype.Component;

import com.message.admin.msg.pojo.MsgGroup;
import com.system.handle.model.ResponseFrame;

/**
 * msg_group的Service
 * @author autoCode
 * @date 2017-12-04 17:13:15
 * @version V1.0.0
 */
@Component
public interface MsgGroupService {


	public void save(MsgGroup msgGroup);
	
	/**
	 * 保存或修改
	 * @param msgGroup
	 * @return
	 */
	public ResponseFrame saveOrUpdate(MsgGroup msgGroup);
	
	/**
	 * 根据id获取对象
	 * @param id
	 * @return
	 */
	public MsgGroup get(String id);

	/**
	 * 分页获取对象
	 * @param msgGroup
	 * @return
	 */
	public ResponseFrame pageQuery(MsgGroup msgGroup);
	
	/**
	 * 根据id删除对象
	 * @param id
	 * @return
	 */
	public ResponseFrame delete(String id);
}