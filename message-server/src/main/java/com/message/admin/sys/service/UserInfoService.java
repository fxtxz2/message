package com.message.admin.sys.service;

import org.springframework.stereotype.Component;

import com.message.admin.sys.pojo.UserInfo;
import com.system.handle.model.ResponseFrame;

/**
 * user_info的Service
 * @author autoCode
 * @date 2017-12-04 16:59:38
 * @version V1.0.0
 */
@Component
public interface UserInfoService {
	
	/**
	 * 保存或修改
	 * @param userInfo
	 * @return
	 */
	public ResponseFrame saveOrUpdate(UserInfo userInfo);
	
	/**
	 * 根据id获取对象
	 * @param id
	 * @return
	 */
	public UserInfo get(String id);

	public UserInfo getBySysNoUserId(String sysNo, String userId);
	
	/**
	 * 分页获取对象
	 * @param userInfo
	 * @return
	 */
	public ResponseFrame pageQuery(UserInfo userInfo);
	
	/**
	 * 根据id删除对象
	 * @param id
	 * @return
	 */
	public ResponseFrame delete(String id);
}