package com.message.admin.msg.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.message.admin.msg.pojo.MsgGroup;
import com.message.admin.msg.service.MsgGroupService;
import com.monitor.api.ApiInfo;
import com.monitor.api.ApiParam;
import com.monitor.api.ApiRes;
import com.system.handle.model.ResponseCode;
import com.system.handle.model.ResponseFrame;

/**
 * msg_group的Controller
 * @author autoCode
 * @date 2017-12-04 17:13:15
 * @version V1.0.0
 */
@RestController
public class MsgGroupController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MsgGroupController.class);

	@Autowired
	private MsgGroupService msgGroupService;
	
	@RequestMapping(name = "消息分组-新增或修改", value = "/msgGroup/saveOrUpdate")
	@ApiInfo(params = {
			@ApiParam(name="编码", code="id", value=""),
			@ApiParam(name="系统编码", code="sysNo", value=""),
			@ApiParam(name="类型[10系统、20个人、30其它]", code="type", value=""),
			@ApiParam(name="分组名称", code="name", value=""),
	}, response = {
			@ApiRes(name="响应码[0成功、-1失败]", code="code", clazz=String.class, value="0"),
			@ApiRes(name="响应消息", code="message", clazz=String.class, value="success"),
			@ApiRes(name="主体内容", code="body", clazz=Object.class, value=""),
	})
	public ResponseFrame saveOrUpdate(MsgGroup msgGroup) {
		try {
			ResponseFrame frame = msgGroupService.saveOrUpdate(msgGroup);
			return frame;
		} catch (Exception e) {
			LOGGER.error("处理业务异常: " + e.getMessage(), e);
			return new ResponseFrame(ResponseCode.SERVER_ERROR);
		}
	}
	
	@RequestMapping(name = "获取详细信息", value = "/msgGroup/get")
	@ApiInfo(params = {
			@ApiParam(name="id", code="id", value=""),
	}, response = {
			@ApiRes(name="响应码[0成功、-1失败]", code="code", clazz=String.class, value="0"),
			@ApiRes(name="响应消息", code="message", clazz=String.class, value="success"),
			@ApiRes(name="主体内容", code="body", clazz=Object.class, value=""),
	})
	public ResponseFrame get(String id) {
		try {
			ResponseFrame frame = new ResponseFrame();
			frame.setBody(msgGroupService.get(id));
			frame.setCode(ResponseCode.SUCC.getCode());
			return frame;
		} catch (Exception e) {
			LOGGER.error("处理业务异常: " + e.getMessage(), e);
			return new ResponseFrame(ResponseCode.SERVER_ERROR);
		}
	}

	@RequestMapping(name = "根据主键删除", value = "/msgGroup/delete")
	@ApiInfo(params = {
			@ApiParam(name="id", code="id", value=""),
	}, response = {
			@ApiRes(name="响应码[0成功、-1失败]", code="code", clazz=String.class, value="0"),
			@ApiRes(name="响应消息", code="message", clazz=String.class, value="success"),
			@ApiRes(name="主体内容", code="body", clazz=Object.class, value=""),
	})
	public ResponseFrame delete(String id) {
		try {
			ResponseFrame frame = msgGroupService.delete(id);
			return frame;
		} catch (Exception e) {
			LOGGER.error("处理业务异常: " + e.getMessage(), e);
			return new ResponseFrame(ResponseCode.SERVER_ERROR);
		}
	}
}