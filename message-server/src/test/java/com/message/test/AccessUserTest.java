package com.message.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.message.MessageConfig;
import com.system.auth.AuthUtil;
import com.system.comm.utils.FrameHttpUtil;

/**
 * 接入用户信息
 * @author yuejing
 * @date 2017年12月5日 上午9:33:24
 */
public class AccessUserTest {

	public static void main(String[] args) throws IOException {
		
		System.out.println("===================== 新增系统信息 begin =======================");
		String result = addSysInfo("test", "测试系统");
		System.out.println(result);
		System.out.println("===================== 新增系统信息 end =======================");
		
		System.out.println("===================== 新增用户信息 begin =======================");
		result = addUserInfo("test", "111111", "13876548675", "test@qq.com");
		System.out.println(result);
		result = addUserInfo("test", "222222", "13876548675", "test@qq.com");
		System.out.println(result);
		result = addUserInfo("test", "123456", "13876548675", "test@qq.com");
		System.out.println(result);
		System.out.println("===================== 新增用户信息 end =======================");
	}
	
	/**
	 * 添加系统信息
	 * @param sysNo	系统编码
	 * @param name	系统名称
	 * @return
	 * @throws IOException
	 */
	private static String addSysInfo(String sysNo, String name) throws IOException {
		String clientId = MessageConfig.clientId;
		String time = String.valueOf(System.currentTimeMillis());
		String sercret = MessageConfig.sercret;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("clientId", clientId);
		params.put("time", time);
		params.put("sign", AuthUtil.auth(clientId, time, sercret));
		
		params.put("sysNo", sysNo);
		params.put("name", name);
		return FrameHttpUtil.post(MessageConfig.address + "/sysInfo/saveOrUpdate", params);
	}
	
	/**
	 * 添加用户信息
	 * @param sysNo		系统编码
	 * @param userId	用户编号
	 * @return
	 * @throws IOException
	 */
	private static String addUserInfo(String sysNo, String userId,
			String phone, String email) throws IOException {
		String clientId = MessageConfig.clientId;
		String time = String.valueOf(System.currentTimeMillis());
		String sercret = MessageConfig.sercret;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("clientId", clientId);
		params.put("time", time);
		params.put("sign", AuthUtil.auth(clientId, time, sercret));
		
		params.put("sysNo", sysNo);
		params.put("userId", userId);
		params.put("phone", phone);
		params.put("email", email);
		return FrameHttpUtil.post(MessageConfig.address + "/userInfo/saveOrUpdate", params);
	}
}