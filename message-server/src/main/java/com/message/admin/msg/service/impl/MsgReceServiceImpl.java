package com.message.admin.msg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.message.admin.msg.dao.MsgReceDao;
import com.message.admin.msg.pojo.MsgRece;
import com.message.admin.msg.service.MsgReceService;
import com.system.comm.model.Page;
import com.system.handle.model.ResponseFrame;
import com.system.handle.model.ResponseCode;

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
	public ResponseFrame saveOrUpdate(MsgRece msgRece) {
		ResponseFrame frame = new ResponseFrame();
		if(msgRece.getId() == null) {
			msgReceDao.save(msgRece);
		} else {
			msgReceDao.update(msgRece);
		}
		frame.setCode(ResponseCode.SUCC.getCode());
		return frame;
	}

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
}