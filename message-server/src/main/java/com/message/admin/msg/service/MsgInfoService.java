package com.message.admin.msg.service;

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
	 * 保存
	 * @param msgInfo
	 * @return
	 */
	public ResponseFrame save(MsgInfo msgInfo);
	
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
	 * 根据id删除我接收的消息
	 * @param id
	 * @return
	 */
	public ResponseFrame deleteRece(String id, String sysNo, String userId);

	public ResponseFrame getDtl(String id, String sysNo, String userId);

	public ResponseFrame updateIsRead(String id, String sysNo, String userId,
			Integer isRead);

}