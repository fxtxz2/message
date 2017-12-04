package com.message.admin.msg.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.message.admin.msg.pojo.MsgCld;
import com.system.handle.model.ResponseFrame;

/**
 * msg_cld的Service
 * @author autoCode
 * @date 2017-12-04 17:13:15
 * @version V1.0.0
 */
@Component
public interface MsgCldService {
	
	/**
	 * 保存或修改
	 * @param msgCld
	 * @return
	 */
	public ResponseFrame saveOrUpdate(MsgCld msgCld);
	
	/**
	 * 根据id获取对象
	 * @param id
	 * @return
	 */
	public MsgCld get(String id);

	/**
	 * 分页获取对象
	 * @param msgCld
	 * @return
	 */
	public ResponseFrame pageQuery(MsgCld msgCld);
	
	/**
	 * 根据id删除对象
	 * @param id
	 * @return
	 */
	public ResponseFrame delete(String id);
}