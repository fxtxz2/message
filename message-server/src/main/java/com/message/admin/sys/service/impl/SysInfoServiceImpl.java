package com.message.admin.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.message.admin.sys.dao.SysInfoDao;
import com.message.admin.sys.pojo.SysInfo;
import com.message.admin.sys.service.SysInfoService;
import com.system.comm.model.Page;
import com.system.handle.model.ResponseFrame;
import com.system.handle.model.ResponseCode;

/**
 * sys_info的Service
 * @author autoCode
 * @date 2017-12-04 16:59:38
 * @version V1.0.0
 */
@Component
public class SysInfoServiceImpl implements SysInfoService {

	@Autowired
	private SysInfoDao sysInfoDao;
	
	@Override
	public ResponseFrame saveOrUpdate(SysInfo sysInfo) {
		ResponseFrame frame = new ResponseFrame();
		if(sysInfo.getSysNo() == null) {
			sysInfoDao.save(sysInfo);
		} else {
			sysInfoDao.update(sysInfo);
		}
		frame.setCode(ResponseCode.SUCC.getCode());
		return frame;
	}

	@Override
	public SysInfo get(String sysNo) {
		return sysInfoDao.get(sysNo);
	}

	@Override
	public ResponseFrame pageQuery(SysInfo sysInfo) {
		sysInfo.setDefPageSize();
		ResponseFrame frame = new ResponseFrame();
		int total = sysInfoDao.findSysInfoCount(sysInfo);
		List<SysInfo> data = null;
		if(total > 0) {
			data = sysInfoDao.findSysInfo(sysInfo);
		}
		Page<SysInfo> page = new Page<SysInfo>(sysInfo.getPage(), sysInfo.getSize(), total, data);
		frame.setBody(page);
		frame.setCode(ResponseCode.SUCC.getCode());
		return frame;
	}
	
	@Override
	public ResponseFrame delete(String sysNo) {
		ResponseFrame frame = new ResponseFrame();
		sysInfoDao.delete(sysNo);
		frame.setCode(ResponseCode.SUCC.getCode());
		return frame;
	}
}