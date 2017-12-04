package com.message.admin.msg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.message.admin.msg.dao.MsgInfoDao;
import com.message.admin.msg.pojo.MsgInfo;
import com.message.admin.msg.service.MsgInfoService;
import com.system.comm.model.Page;
import com.system.handle.model.ResponseFrame;
import com.system.handle.model.ResponseCode;

/**
 * msg_info的Service
 * @author autoCode
 * @date 2017-12-04 17:13:15
 * @version V1.0.0
 */
@Component
public class MsgInfoServiceImpl implements MsgInfoService {

	@Autowired
	private MsgInfoDao msgInfoDao;
	
	@Override
	public ResponseFrame saveOrUpdate(MsgInfo msgInfo) {
		ResponseFrame frame = new ResponseFrame();
		if(msgInfo.getId() == null) {
			msgInfoDao.save(msgInfo);
		} else {
			msgInfoDao.update(msgInfo);
		}
		frame.setCode(ResponseCode.SUCC.getCode());
		return frame;
	}

	@Override
	public MsgInfo get(String id) {
		return msgInfoDao.get(id);
	}

	@Override
	public ResponseFrame pageQuery(MsgInfo msgInfo) {
		msgInfo.setDefPageSize();
		ResponseFrame frame = new ResponseFrame();
		int total = msgInfoDao.findMsgInfoCount(msgInfo);
		List<MsgInfo> data = null;
		if(total > 0) {
			data = msgInfoDao.findMsgInfo(msgInfo);
		}
		Page<MsgInfo> page = new Page<MsgInfo>(msgInfo.getPage(), msgInfo.getSize(), total, data);
		frame.setBody(page);
		frame.setCode(ResponseCode.SUCC.getCode());
		return frame;
	}
	
	@Override
	public ResponseFrame delete(String id) {
		ResponseFrame frame = new ResponseFrame();
		msgInfoDao.delete(id);
		frame.setCode(ResponseCode.SUCC.getCode());
		return frame;
	}
}