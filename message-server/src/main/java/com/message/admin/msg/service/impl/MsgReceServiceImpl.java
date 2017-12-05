package com.message.admin.msg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.message.admin.msg.dao.MsgReceDao;
import com.message.admin.msg.pojo.MsgInfo;
import com.message.admin.msg.pojo.MsgRece;
import com.message.admin.msg.service.MsgReceService;
import com.system.comm.enums.Boolean;
import com.system.comm.model.Page;
import com.system.comm.utils.FrameNoUtil;
import com.system.comm.utils.FrameTimeUtil;
import com.system.handle.model.ResponseCode;
import com.system.handle.model.ResponseFrame;

/**
 * msg_rece的Service
 * @author autoCode
 * @date 2017-12-04 17:13:15
 * @version V1.0.0
 */
@Component
public class MsgReceServiceImpl implements MsgReceService {

	@Autowired
	private MsgReceDao msgReceDao;
	
	@Override
	public MsgRece get(String id) {
		return msgReceDao.get(id);
	}

	@Override
	public ResponseFrame pageQuery(MsgRece msgRece) {
		msgRece.setDefPageSize();
		ResponseFrame frame = new ResponseFrame();
		int total = msgReceDao.findMsgReceCount(msgRece);
		List<MsgRece> data = null;
		if(total > 0) {
			data = msgReceDao.findMsgRece(msgRece);
		}
		Page<MsgRece> page = new Page<MsgRece>(msgRece.getPage(), msgRece.getSize(), total, data);
		frame.setBody(page);
		frame.setCode(ResponseCode.SUCC.getCode());
		return frame;
	}
	
	@Override
	public ResponseFrame delete(String id) {
		ResponseFrame frame = new ResponseFrame();
		msgReceDao.delete(id);
		frame.setCode(ResponseCode.SUCC.getCode());
		return frame;
	}

	@Override
	public ResponseFrame saveList(MsgInfo msgInfo, List<String> receUserIds) {
		ResponseFrame frame = new ResponseFrame();
		for (String receUserId : receUserIds) {
			MsgRece msgRece = new MsgRece();
			msgRece.setId(FrameNoUtil.uuid());
			msgRece.setMsgId(msgInfo.getId());
			msgRece.setReceSysNo(msgInfo.getSysNo());
			msgRece.setReceUserId(receUserId);
			msgRece.setReceTime(FrameTimeUtil.getTime());
			msgRece.setIsRead(Boolean.FALSE.getCode());
			msgReceDao.save(msgRece);
		}
		frame.setCode(ResponseCode.SUCC.getCode());
		return frame;
	}

	@Override
	public Integer getCountUnread(String receSysNo, String receUserId) {
		return msgReceDao.getCountUnread(receSysNo, receUserId);
	}

	@Override
	public ResponseFrame updateIsRead(String msgId, String sysNo, String userId) {
		ResponseFrame frame = new ResponseFrame();
		MsgRece msgRece = getByMsgIdReceUserId(msgId, userId);
		if(msgRece == null) {
			frame.setCode(-2);
			frame.setMessage("不存在该消息");
			return frame;
		}
		msgReceDao.updateIsRead(msgRece.getId(), Boolean.TRUE.getCode());
		frame.setSucc();
		return frame;
	}

	@Override
	public MsgRece getByMsgIdReceUserId(String msgId, String receUserId) {
		return msgReceDao.getByMsgIdReceUserId(msgId, receUserId);
	}
}