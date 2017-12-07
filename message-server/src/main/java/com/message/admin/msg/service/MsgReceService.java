package com.message.admin.msg.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.message.admin.msg.pojo.MsgInfo;
import com.message.admin.msg.pojo.MsgRece;
import com.system.handle.model.ResponseFrame;

/**
 * msg_rece的Service
 * @author autoCode
 * @date 2017-12-04 17:13:15
 * @version V1.0.0
 */
@Component
public interface MsgReceService {
	
	/**
	 * 根据id获取对象
	 * @param id
	 * @return
	 */
	public MsgRece get(String id);

	/**
	 * 分页获取对象
	 * @param msgRece
	 * @return
	 */
	public ResponseFrame pageQuery(MsgRece msgRece);
	
	/**
	 * 根据id删除对象
	 * @param id
	 * @return
	 */
	public ResponseFrame delete(String id);

	public ResponseFrame saveList(MsgInfo msgInfo, List<String> receUserIds);

	/**
	 * 根据用户编号获取未读消息的总数
	 * @param receSysNo
	 * @param receUserId 
	 * @return
	 */
	public Integer getCountUnread(String receSysNo, String receUserId);

	public ResponseFrame updateIsRead(String msgId, String sysNo, String userId, Integer isRead);

	public MsgRece getByMsgIdReceUserId(String msgId, String receUserId);
}