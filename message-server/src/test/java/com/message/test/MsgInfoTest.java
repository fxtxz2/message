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
public class MsgInfoTest {

	public static void main(String[] args) throws IOException {
		
		System.out.println("===================== 删除我接收的消息 begin =======================");
		String result = deleteRece("989c151875454f9eaa11478e93a6bc48", "test", "222222");
		System.out.println(result);
		System.out.println("===================== 删除我接收的消息 end =======================");
		
		System.out.println("===================== 标记消息阅读状态 begin =======================");
		result = updateIsRead("09029db86403475683efd6670b6a9593", "test", "222222", 1);
		System.out.println(result);
		System.out.println("===================== 标记消息阅读状态 end =======================");
		
	}
	
	private static String updateIsRead(String id, String sysNo,
			String userId, Integer isRead) throws IOException {
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
		params.put("isRead", isRead);
		return FrameHttpUtil.post(MessageConfig.address + "/msgInfo/updateIsRead", params);
	}
	
	private static String deleteRece(String id, String sysNo,
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
		return FrameHttpUtil.post(MessageConfig.address + "/msgInfo/deleteRece", params);
	}
	
}