package com.message.admin.sys.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.message.admin.sys.pojo.SysInfo;
import com.system.handle.model.ResponseFrame;

/**
 * sys_info的Service
 * @author autoCode
 * @date 2017-12-04 16:59:38
 * @version V1.0.0
 */
@Component
public interface SysInfoService {
	
	/**
	 * 保存或修改
	 * @param sysInfo
	 * @return
	 */
	public ResponseFrame saveOrUpdate(SysInfo sysInfo);
	
	/**
	 * 根据sysNo获取对象
	 * @param sysNo
	 * @return
	 */
	public SysInfo get(String sysNo);

	/**
	 * 分页获取对象
	 * @param sysInfo
	 * @return
	 */
	public ResponseFrame pageQuery(SysInfo sysInfo);
	
	/**
	 * 根据sysNo删除对象
	 * @param sysNo
	 * @return
	 */
	public ResponseFrame delete(String sysNo);
}