package com.message.admin.msg.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.message.admin.msg.dao.MsgGroupDao;
import com.message.admin.msg.enums.MsgGroupType;
import com.message.admin.msg.pojo.MsgGroup;
import com.message.admin.msg.service.MsgGroupService;
import com.system.comm.model.Page;
import com.system.comm.utils.FrameStringUtil;
import com.system.comm.utils.FrameTimeUtil;
import com.system.handle.model.ResponseFrame;
import com.system.handle.model.ResponseCode;

/**
 * msg_group的Service
 * @author autoCode
 * @date 2017-12-04 17:13:15
 * @version V1.0.0
 */
@Component
public class MsgGroupServiceImpl implements MsgGroupService {

	@Autowired
	private MsgGroupDao msgGroupDao;
	
	@Override
	public void save(MsgGroup msgGroup) {
		if(FrameStringUtil.isEmpty(msgGroup.getName())) {
			msgGroup.setName(msgGroup.getId());
		}
		if(msgGroup.getType() == null) {
			msgGroup.setType(MsgGroupType.SYS.getCode());
		}
		msgGroup.setCreateTime(FrameTimeUtil.getTime());
		msgGroupDao.save(msgGroup);
	}
	
	@Override
	public ResponseFrame saveOrUpdate(MsgGroup msgGroup) {
		ResponseFrame frame = new ResponseFrame();
		MsgGroup org = get(msgGroup.getId());
		if(org == null) {
			save(msgGroup);
		} else {
			msgGroupDao.update(msgGroup);
		}
		frame.setCode(ResponseCode.SUCC.getCode());
		return frame;
	}

	@Override
	public MsgGroup get(String id) {
		return msgGroupDao.get(id);
	}

	@Override
	public ResponseFrame pageQuery(MsgGroup msgGroup) {
		msgGroup.setDefPageSize();
		ResponseFrame frame = new ResponseFrame();
		int total = msgGroupDao.findMsgGroupCount(msgGroup);
		List<MsgGroup> data = null;
		if(total > 0) {
			data = msgGroupDao.findMsgGroup(msgGroup);
		}
		Page<MsgGroup> page = new Page<MsgGroup>(msgGroup.getPage(), msgGroup.getSize(), total, data);
		frame.setBody(page);
		frame.setCode(ResponseCode.SUCC.getCode());
		return frame;
	}
	
	@Override
	public ResponseFrame delete(String id) {
		ResponseFrame frame = new ResponseFrame();
		msgGroupDao.delete(id);
		frame.setCode(ResponseCode.SUCC.getCode());
		return frame;
	}

	@Override
	public List<MsgGroup> findUnread(String sysNo, String userId) {
		List<MsgGroup> data = new ArrayList<MsgGroup>();
		List<MsgGroup> list = msgGroupDao.findUnread(sysNo, userId);
		for (MsgGroup mg : list) {
			MsgGroup group = get(mg.getId());
			group.setUnreadNum(mg.getUnreadNum());
			data.add(group);
		}
		return data;
	}
}