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
public class MsgSendTest {

	public static void main(String[] args) throws IOException {
		
		System.out.println("===================== 发送信息 begin =======================");
		//分组的编码，后续查询时，有参数可以根据该编码合并消息
		String groupId = "sys";
		String sendUserId = "111111";
		String receUserIds = "222222;123456";
		String result = sendMsg("test", groupId, "这是一条测试标题1", "这是正文1", sendUserId, receUserIds);
		System.out.println(result);
		
		groupId = "test";
		result = sendMsg("test", groupId, "这是一条测试标题2", "这是正文2", sendUserId, receUserIds);
		System.out.println(result);
		System.out.println("===================== 发送信息 end =======================");
		
	}
	
	/**
	 * 发送信息
	 * @param title 
	 * @param content 
	 * @param sendUserId 
	 * @param receUserIds 
	 * @return
	 * @throws IOException
	 */
	private static String sendMsg(String sysNo, String groupId,
			String title, String content,
			String sendUserId, String receUserIds) throws IOException {
		String clientId = MessageConfig.clientId;
		String time = String.valueOf(System.currentTimeMillis());
		String sercret = MessageConfig.sercret;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("clientId", clientId);
		params.put("time", time);
		params.put("sign", AuthUtil.auth(clientId, time, sercret));

		params.put("sysNo", sysNo);
		params.put("groupId", groupId);
		params.put("title", title);
		params.put("content", content);
		params.put("sendUserId", sendUserId);
		params.put("receUserIds", receUserIds);
		return FrameHttpUtil.post(MessageConfig.address + "/msgInfo/save", params);
	}
}