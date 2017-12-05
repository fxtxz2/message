package com.message.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.message.MessageConfig;
import com.system.auth.AuthUtil;
import com.system.comm.utils.FrameHttpUtil;

/**
 * 发送消息
 * @author yuejing
 * @date 2017年12月5日 上午9:33:24
 */
public class MsgQueryTest {

	public static void main(String[] args) throws IOException {
		
		System.out.println("===================== 根据来源系统编码和用户编号查询未读记录总数 begin =======================");
		String result = getCountUnread("test", "222222");
		System.out.println(result);
		System.out.println("===================== 根据来源系统编码和用户编号查询未读记录总数 end =======================");
		
		System.out.println("===================== 根据来源系统编码和用户编号查询未读记录列表 begin =======================");
		result = pageQueryUnread(1, 5, "test", "222222");
		System.out.println(result);
		
		result = pageQueryUnread(2, 5, "test", "222222");
		System.out.println(result);
		System.out.println("===================== 根据来源系统编码和用户编号查询未读记录列表 end =======================");
		
		System.out.println("===================== 根据来源系统编码和用户编号查询记录列表 begin =======================");
		result = pageQuery(1, 5, "test", "222222", null);
		System.out.println(result);
		
		result = pageQuery(2, 5, "test", "222222", null);
		System.out.println(result);
		System.out.println("===================== 根据来源系统编码和用户编号查询记录列表 end =======================");
		
		System.out.println("===================== 获取消息详情 begin =======================");
		result = getDtl("b51027ae970d49e5956ebdb46783f75a", "test", "222222");
		System.out.println(result);
		System.out.println("===================== 获取消息详情 end =======================");
		
	}
	
	private static String getDtl(String id, String sysNo,
			String userId) throws IOException {
		String clientId = MessageConfig.clientId;
		String time = String.valueOf(System.currentTimeMillis());
		String sercret = MessageConfig.sercret;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("clientId", clientId);
		params.put("time", time);
		params.put("sign", AuthUtil.auth(clientId, time, sercret));

		params.put("id", id);
		params.put("sysNo", sysNo);
		params.put("userId", userId);
		return FrameHttpUtil.post(MessageConfig.address + "/msgInfo/getDtl", params);
	}
	
	private static String pageQuery(Integer page, Integer size, String sysNo, String userId,
			Integer isRead) throws IOException {
		String clientId = MessageConfig.clientId;
		String time = String.valueOf(System.currentTimeMillis());
		String sercret = MessageConfig.sercret;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("clientId", clientId);
		params.put("time", time);
		params.put("sign", AuthUtil.auth(clientId, time, sercret));

		params.put("page", page);
		params.put("size", size);
		params.put("sysNo", sysNo);
		params.put("userId", userId);
		params.put("isRead", isRead);
		return FrameHttpUtil.post(MessageConfig.address + "/msgInfo/pageQuery", params);
	}
	
	private static String pageQueryUnread(Integer page, Integer size, String sysNo, String userId) throws IOException {
		String clientId = MessageConfig.clientId;
		String time = String.valueOf(System.currentTimeMillis());
		String sercret = MessageConfig.sercret;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("clientId", clientId);
		params.put("time", time);
		params.put("sign", AuthUtil.auth(clientId, time, sercret));

		params.put("page", page);
		params.put("size", size);
		params.put("sysNo", sysNo);
		params.put("userId", userId);
		return FrameHttpUtil.post(MessageConfig.address + "/msgInfo/pageQueryUnread", params);
	}

	private static String getCountUnread(String sysNo, String userId) throws IOException {
		String clientId = MessageConfig.clientId;
		String time = String.valueOf(System.currentTimeMillis());
		String sercret = MessageConfig.sercret;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("clientId", clientId);
		params.put("time", time);
		params.put("sign", AuthUtil.auth(clientId, time, sercret));

		params.put("sysNo", sysNo);
		params.put("userId", userId);
		return FrameHttpUtil.post(MessageConfig.address + "/msgInfo/getCountUnread", params);
	}
}