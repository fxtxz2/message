package com.message.admin.msg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.message.admin.msg.dao.MsgInfoDao;
import com.message.admin.msg.enums.MsgInfoStatus;
import com.message.admin.msg.pojo.MsgGroup;
import com.message.admin.msg.pojo.MsgInfo;
import com.message.admin.msg.service.MsgGroupService;
import com.message.admin.msg.service.MsgInfoService;
import com.message.admin.msg.service.MsgReceService;
import com.system.comm.model.Page;
import com.system.comm.utils.FrameNoUtil;
import com.system.comm.utils.FrameStringUtil;
import com.system.comm.utils.FrameTimeUtil;
import com.system.handle.model.ResponseCode;
import com.system.handle.model.ResponseFrame;

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
	@Autowired
	private MsgGroupService msgGroupService;
	@Autowired
	private MsgReceService msgReceService;

	@Override
	public ResponseFrame save(MsgInfo msgInfo) {
		ResponseFrame frame = new ResponseFrame();
		if(FrameStringUtil.isEmpty(msgInfo.getReceUserIds())) {
			frame.setCode(-2);
			frame.setMessage("接收人信息不能为空");
			return frame;
		}
		List<String> receUserIds = FrameStringUtil.toArray(msgInfo.getReceUserIds(), ";");
		if(receUserIds.size() == 0) {
			frame.setCode(-2);
			frame.setMessage("接收人信息不能为空");
			return frame;
		}
		MsgGroup group = msgGroupService.get(msgInfo.getGroupId());
		if(group == null) {
			//创建分组
			group = new MsgGroup(msgInfo.getGroupId(), msgInfo.getSysNo());
			msgGroupService.save(group);
		}
		if(msgInfo.getStatus() == null) {
			msgInfo.setStatus(MsgInfoStatus.SUCC.getCode());
			msgInfo.setSendTime(FrameTimeUtil.getTime());
		}
		msgInfo.setId(FrameNoUtil.uuid());
		msgInfo.setCreateTime(FrameTimeUtil.getTime());
		msgInfoDao.save(msgInfo);
		
		//保存接收人信息
		msgReceService.saveList(msgInfo, receUserIds);
		frame.setCode(ResponseCode.SUCC.getCode());
		return frame;
	}

	@Override
	public MsgInfo get(String id) {
		return msgInfoDao.get(id);
	}

	@Override
	public ResponseFrame delete(String id, String sysNo, String userId) {
		ResponseFrame frame = new ResponseFrame();
		msgInfoDao.delete(id);
		frame.setCode(ResponseCode.SUCC.getCode());
		return frame;
	}

	@Override
	public ResponseFrame pageQuery(MsgInfo msgInfo) {
		msgInfo.setDefPageSize();
		ResponseFrame frame = new ResponseFrame();
		if(FrameStringUtil.isEmpty(msgInfo.getGroupIds())) {
			List<String> groupIdList = FrameStringUtil.toArray(msgInfo.getGroupIds(), ";");
			if(groupIdList.size() > 0) {
				msgInfo.setGroupIdList(groupIdList);
			}
		}
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
	public ResponseFrame getDtl(String id, String sysNo, String userId) {
		ResponseFrame frame = new ResponseFrame();
		MsgInfo msgInfo = get(id);
		if(msgInfo == null) {
			frame.setCode(-2);
			frame.setMessage("不存在该消息记录");
			return frame;
		}
		//修改状态为已读
		msgReceService.updateIsRead(id, sysNo, userId);
		frame.setBody(msgInfo);
		frame.setSucc();
		return frame;
	}
}