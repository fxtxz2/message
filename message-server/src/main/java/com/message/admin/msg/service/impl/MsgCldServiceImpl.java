package com.message.admin.msg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.message.admin.msg.dao.MsgCldDao;
import com.message.admin.msg.pojo.MsgCld;
import com.message.admin.msg.service.MsgCldService;
import com.system.comm.model.Page;
import com.system.handle.model.ResponseFrame;
import com.system.handle.model.ResponseCode;

/**
 * msg_cld的Service
 * @author autoCode
 * @date 2017-12-04 17:13:15
 * @version V1.0.0
 */
@Component
public class MsgCldServiceImpl implements MsgCldService {

	@Autowired
	private MsgCldDao msgCldDao;
	
	@Override
	public ResponseFrame saveOrUpdate(MsgCld msgCld) {
		ResponseFrame frame = new ResponseFrame();
		if(msgCld.getId() == null) {
			msgCldDao.save(msgCld);
		} else {
			msgCldDao.update(msgCld);
		}
		frame.setCode(ResponseCode.SUCC.getCode());
		return frame;
	}

	@Override
	public MsgCld get(String id) {
		return msgCldDao.get(id);
	}

	@Override
	public ResponseFrame pageQuery(MsgCld msgCld) {
		msgCld.setDefPageSize();
		ResponseFrame frame = new ResponseFrame();
		int total = msgCldDao.findMsgCldCount(msgCld);
		List<MsgCld> data = null;
		if(total > 0) {
			data = msgCldDao.findMsgCld(msgCld);
		}
		Page<MsgCld> page = new Page<MsgCld>(msgCld.getPage(), msgCld.getSize(), total, data);
		frame.setBody(page);
		frame.setCode(ResponseCode.SUCC.getCode());
		return frame;
	}
	
	@Override
	public ResponseFrame delete(String id) {
		ResponseFrame frame = new ResponseFrame();
		msgCldDao.delete(id);
		frame.setCode(ResponseCode.SUCC.getCode());
		return frame;
	}
}