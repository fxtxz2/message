package com.message.admin.msg.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.message.admin.msg.pojo.MsgInfo;
import com.system.handle.model.ResponseFrame;

/**
 * msg_info的Service
 * @author autoCode
 * @date 2017-12-04 17:13:15
 * @version V1.0.0
 */
@Component
public interface MsgInfoService {
	
	/**
	 * 保存或修改
	 * @param msgInfo
	 * @return
	 */
	public ResponseFrame saveOrUpdate(MsgInfo msgInfo);
	
	/**
	 * 根据id获取对象
	 * @param id
	 * @return
	 */
	public MsgInfo get(String id);

	/**
	 * 分页获取对象
	 * @param msgInfo
	 * @return
	 */
	public ResponseFrame pageQuery(MsgInfo msgInfo);
	
	/**
	 * 根据id删除对象
	 * @param id
	 * @return
	 */
	public ResponseFrame delete(String id);
}