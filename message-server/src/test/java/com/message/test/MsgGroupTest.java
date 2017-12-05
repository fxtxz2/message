package com.message.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.message.MessageConfig;
import com.message.admin.msg.enums.MsgGroupType;
import com.system.auth.AuthUtil;
import com.system.comm.utils.FrameHttpUtil;

/**
 * 消息分组
 * @author yuejing
 * @date 2017年12月5日 上午9:33:24
 */
public class MsgGroupTest {

	public static void main(String[] args) throws IOException {
		
		System.out.println("===================== 维护分组 begin =======================");
		//分组的编码，后续查询时，有参数可以根据该编码合并消息
		String id = "sys";
		String result = saveOrUpdate(id, "test", MsgGroupType.SYS.getCode(), "系统分组");
		System.out.println(result);
		
		id = "notice";
		result = saveOrUpdate(id, "test", MsgGroupType.SYS.getCode(), "公告");
		System.out.println(result);
		System.out.println("===================== 维护分组 end =======================");
		
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
	private static String saveOrUpdate(String id, String sysNo, Integer type,
			String name) throws IOException {
		String clientId = MessageConfig.clientId;
		String time = String.valueOf(System.currentTimeMillis());
		String sercret = MessageConfig.sercret;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("clientId", clientId);
		params.put("time", time);
		params.put("sign", AuthUtil.auth(clientId, time, sercret));

		params.put("sysNo", sysNo);
		params.put("id", id);
		params.put("type", type);
		params.put("name", name);
		return FrameHttpUtil.post(MessageConfig.address + "/msgGroup/saveOrUpdate", params);
	}
}