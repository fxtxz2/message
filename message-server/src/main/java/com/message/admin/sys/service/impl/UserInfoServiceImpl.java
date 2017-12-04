package com.message.admin.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.message.admin.sys.dao.UserInfoDao;
import com.message.admin.sys.pojo.UserInfo;
import com.message.admin.sys.service.UserInfoService;
import com.system.comm.model.Page;
import com.system.handle.model.ResponseFrame;
import com.system.handle.model.ResponseCode;

/**
 * user_info的Service
 * @author autoCode
 * @date 2017-12-04 16:59:38
 * @version V1.0.0
 */
@Component
public class UserInfoServiceImpl implements UserInfoService {

	@Autowired
	private UserInfoDao userInfoDao;
	
	@Override
	public ResponseFrame saveOrUpdate(UserInfo userInfo) {
		ResponseFrame frame = new ResponseFrame();
		if(userInfo.getId() == null) {
			userInfoDao.save(userInfo);
		} else {
			userInfoDao.update(userInfo);
		}
		frame.setCode(ResponseCode.SUCC.getCode());
		return frame;
	}

	@Override
	public UserInfo get(String id) {
		return userInfoDao.get(id);
	}

	@Override
	public ResponseFrame pageQuery(UserInfo userInfo) {
		userInfo.setDefPageSize();
		ResponseFrame frame = new ResponseFrame();
		int total = userInfoDao.findUserInfoCount(userInfo);
		List<UserInfo> data = null;
		if(total > 0) {
			data = userInfoDao.findUserInfo(userInfo);
		}
		Page<UserInfo> page = new Page<UserInfo>(userInfo.getPage(), userInfo.getSize(), total, data);
		frame.setBody(page);
		frame.setCode(ResponseCode.SUCC.getCode());
		return frame;
	}
	
	@Override
	public ResponseFrame delete(String id) {
		ResponseFrame frame = new ResponseFrame();
		userInfoDao.delete(id);
		frame.setCode(ResponseCode.SUCC.getCode());
		return frame;
	}
}