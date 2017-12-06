package com.message.admin.msg.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.message.admin.msg.pojo.MsgGroup;
import com.message.admin.msg.pojo.MsgInfo;
import com.message.admin.msg.service.MsgGroupService;
import com.message.admin.msg.service.MsgInfoService;
import com.message.admin.msg.service.MsgReceService;
import com.monitor.api.ApiInfo;
import com.monitor.api.ApiParam;
import com.monitor.api.ApiRes;
import com.system.comm.enums.Boolean;
import com.system.comm.model.Orderby;
import com.system.comm.utils.FrameJsonUtil;
import com.system.comm.utils.FrameStringUtil;
import com.system.handle.model.ResponseCode;
import com.system.handle.model.ResponseFrame;

/**
 * msg_info的Controller
 * @author autoCode
 * @date 2017-12-04 17:13:15
 * @version V1.0.0
 */
@RestController
public class MsgInfoController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MsgInfoController.class);

	@Autowired
	private MsgInfoService msgInfoService;
	@Autowired
	private MsgReceService msgReceService;
	@Autowired
	private MsgGroupService msgGroupService;

	@RequestMapping(name = "消息-新增", value = "/msgInfo/save")
	@ApiInfo(params = {
			@ApiParam(name="消息分组编号[可以传入sys代表系统的消息分组]", code="groupId", value=""),
			@ApiParam(name="标题", code="title", value=""),
			@ApiParam(name="内容", code="content", value="", required=false),
			@ApiParam(name="发送人编码", code="sendUserId", value=""),
			@ApiParam(name="接收人编码集合[多个用;分隔]", code="receUserIds", value=""),
			@ApiParam(name="系统编码", code="sysNo", value=""),
	}, response = {
			@ApiRes(name="响应码[0成功、-1失败]", code="code", clazz=String.class, value="0"),
			@ApiRes(name="响应消息", code="message", clazz=String.class, value="success"),
			@ApiRes(name="主体内容", code="body", clazz=Object.class, value=""),
	})
	public ResponseFrame save(MsgInfo msgInfo) {
		try {
			ResponseFrame frame = msgInfoService.save(msgInfo);
			return frame;
		} catch (Exception e) {
			LOGGER.error("处理业务异常: " + e.getMessage(), e);
			return new ResponseFrame(ResponseCode.SERVER_ERROR);
		}
	}
	
	@RequestMapping(name = "消息-获取我的未读消息数", value = "/msgInfo/getCountUnread")
	@ApiInfo(params = {
			@ApiParam(name="系统编码", code="sysNo", value=""),
			@ApiParam(name="用户编号", code="userId", value=""),
	}, response = {
			@ApiRes(name="响应码[0成功、-1失败]", code="code", clazz=String.class, value="0"),
			@ApiRes(name="响应消息", code="message", clazz=String.class, value="success"),
			@ApiRes(name="主体内容", code="body", clazz=Object.class, value=""),
	})
	public ResponseFrame getCountUnread(String sysNo, String userId) {
		try {
			ResponseFrame frame = new ResponseFrame();
			frame.setBody(msgReceService.getCountUnread(sysNo, userId));
			frame.setCode(ResponseCode.SUCC.getCode());
			return frame;
		} catch (Exception e) {
			LOGGER.error("处理业务异常: " + e.getMessage(), e);
			return new ResponseFrame(ResponseCode.SERVER_ERROR);
		}
	}
	
	@RequestMapping(name = "消息-分页根据来源系统编码和用户编号查询未读的消息列表", value = "/msgInfo/pageQueryUnread")
	@ApiInfo(params = {
			@ApiParam(name="页面", code="page", value="1"),
			@ApiParam(name="每页大小", code="size", value="10"),
			@ApiParam(name="排序[{\"property\": \"createTime\", \"type\":\"desc\", \"order\":1}]", code="orderby", value="", required=false),
			@ApiParam(name="系统编码", code="sysNo", value=""),
			@ApiParam(name="用户编号", code="userId", value=""),
			@ApiParam(name="分组编号[多个用;分隔]", code="groupIds", value=""),
	}, response = {
			@ApiRes(name="响应码[0成功、-1失败]", code="code", clazz=String.class, value="0"),
			@ApiRes(name="响应消息", code="message", clazz=String.class, value="success"),
			@ApiRes(name="主体内容", code="body", clazz=Object.class, value=""),
			@ApiRes(name="当前页码", code="page", pCode="body", clazz=Integer.class, value="1"),
			@ApiRes(name="每页大小", code="size", pCode="body", clazz=Integer.class, value="10"),
			@ApiRes(name="总页数", code="totalPage", pCode="body", clazz=Integer.class, value="5"),
			@ApiRes(name="总记录数", code="total", pCode="body", clazz=Integer.class, value="36"),
			@ApiRes(name="数据集合", code="rows", pCode="body", clazz=List.class, value=""),
			@ApiRes(name="消息编码", code="id", pCode="rows", value=""),
			@ApiRes(name="标题", code="title", pCode="rows", value=""),
			@ApiRes(name="发送人", code="sendUserId", pCode="rows", value=""),
			@ApiRes(name="发送时间", code="sendTime", pCode="rows", value=""),
	})
	public ResponseFrame pageQueryUnread(MsgInfo msgInfo, String orderby) {
		try {
			if(FrameStringUtil.isEmpty(msgInfo.getSysNo())) {
				return new ResponseFrame(-2, "系统编码不能为空");
			}
			if(FrameStringUtil.isEmpty(msgInfo.getUserId())) {
				return new ResponseFrame(-2, "查询用户不能为空");
			}
			if(FrameStringUtil.isNotEmpty(orderby)) {
				List<Orderby> orderbys = FrameJsonUtil.toList(orderby, Orderby.class);
				msgInfo.setOrderbys(orderbys);
			}
			msgInfo.setIsRead(Boolean.FALSE.getCode());
			ResponseFrame frame = msgInfoService.pageQuery(msgInfo);
			return frame;
		} catch (Exception e) {
			LOGGER.error("处理业务异常: " + e.getMessage(), e);
			return new ResponseFrame(ResponseCode.SERVER_ERROR);
		}
	}

	
	@RequestMapping(name = "消息-根据分组获取未读记录的列表", value = "/msgInfo/findGroupUnread")
	@ApiInfo(params = {
			@ApiParam(name="系统编码", code="sysNo", value=""),
			@ApiParam(name="用户编号", code="userId", value=""),
	}, response = {
			@ApiRes(name="响应码[0成功、-1失败]", code="code", clazz=String.class, value="0"),
			@ApiRes(name="响应消息", code="message", clazz=String.class, value="success"),
			@ApiRes(name="主体内容", code="body", clazz=Object.class, value=""),
			@ApiRes(name="id", code="id", pCode="body", value=""),
	})
	public ResponseFrame findGroupUnread(String sysNo, String userId) {
		try {
			ResponseFrame frame = new ResponseFrame();
			List<MsgGroup> data = msgGroupService.findUnread(sysNo, userId);
			frame.setBody(data);
			return frame;
		} catch (Exception e) {
			LOGGER.error("处理业务异常: " + e.getMessage(), e);
			return new ResponseFrame(ResponseCode.SERVER_ERROR);
		}
	}
	
	@RequestMapping(name = "分页查询信息", value = "/msgInfo/pageQuery")
	@ApiInfo(params = {
			@ApiParam(name="页面", code="page", value="1"),
			@ApiParam(name="每页大小", code="size", value="10"),
			@ApiParam(name="排序[{\"property\": \"createTime\", \"type\":\"desc\", \"order\":1}]", code="orderby", value="", required=false),
			@ApiParam(name="系统编码", code="sysNo", value=""),
			@ApiParam(name="用户编号", code="userId", value=""),
			@ApiParam(name="是否阅读[0否、1是]", code="isRead", value=""),
			@ApiParam(name="分组编号[多个用;分隔]", code="groupIds", value=""),
	}, response = {
			@ApiRes(name="响应码[0成功、-1失败]", code="code", clazz=String.class, value="0"),
			@ApiRes(name="响应消息", code="message", clazz=String.class, value="success"),
			@ApiRes(name="主体内容", code="body", clazz=Object.class, value=""),
			@ApiRes(name="当前页码", code="page", pCode="body", clazz=Integer.class, value="1"),
			@ApiRes(name="每页大小", code="size", pCode="body", clazz=Integer.class, value="10"),
			@ApiRes(name="总页数", code="totalPage", pCode="body", clazz=Integer.class, value="5"),
			@ApiRes(name="总记录数", code="total", pCode="body", clazz=Integer.class, value="36"),
			@ApiRes(name="数据集合", code="rows", pCode="body", clazz=List.class, value=""),
			@ApiRes(name="消息编码", code="id", pCode="rows", value=""),
			@ApiRes(name="标题", code="title", pCode="rows", value=""),
			@ApiRes(name="发送人", code="sendUserId", pCode="rows", value=""),
			@ApiRes(name="发送时间", code="sendTime", pCode="rows", value=""),
			@ApiRes(name="是否阅读[0否、1是]", code="isRead", pCode="rows", value=""),
			@ApiRes(name="阅读时间", code="readTime", pCode="rows", value=""),
	})
	public ResponseFrame pageQuery(MsgInfo msgInfo, String orderby) {
		try {
			if(FrameStringUtil.isEmpty(msgInfo.getSysNo())) {
				return new ResponseFrame(-2, "系统编码不能为空");
			}
			if(FrameStringUtil.isEmpty(msgInfo.getUserId())) {
				return new ResponseFrame(-2, "查询用户不能为空");
			}
			if(FrameStringUtil.isNotEmpty(orderby)) {
				List<Orderby> orderbys = FrameJsonUtil.toList(orderby, Orderby.class);
				msgInfo.setOrderbys(orderbys);
			}
			ResponseFrame frame = msgInfoService.pageQuery(msgInfo);
			return frame;
		} catch (Exception e) {
			LOGGER.error("处理业务异常: " + e.getMessage(), e);
			return new ResponseFrame(ResponseCode.SERVER_ERROR);
		}
	}
	
	@RequestMapping(name = "消息-获取详细信息", value = "/msgInfo/getDtl")
	@ApiInfo(params = {
			@ApiParam(name="消息编号", code="id", value=""),
			@ApiParam(name="系统编码", code="sysNo", value=""),
			@ApiParam(name="用户编号", code="userId", value=""),
	}, response = {
			@ApiRes(name="响应码[0成功、-1失败]", code="code", clazz=String.class, value="0"),
			@ApiRes(name="响应消息", code="message", clazz=String.class, value="success"),
			@ApiRes(name="主体内容", code="body", clazz=Object.class, value=""),@ApiRes(name="消息编码", code="id", pCode="rows", value=""),
			@ApiRes(name="标题", code="title", pCode="body", value=""),
			@ApiRes(name="发送人", code="sendUserId", pCode="body", value=""),
			@ApiRes(name="发送时间", code="sendTime", pCode="body", value=""),
	})
	public ResponseFrame get(String id, String sysNo, String userId) {
		try {
			ResponseFrame frame = msgInfoService.getDtl(id, sysNo, userId);
			return frame;
		} catch (Exception e) {
			LOGGER.error("处理业务异常: " + e.getMessage(), e);
			return new ResponseFrame(ResponseCode.SERVER_ERROR);
		}
	}

	@RequestMapping(name = "消息-根据主键删除", value = "/msgInfo/delete")
	@ApiInfo(params = {
			@ApiParam(name="消息编号", code="id", value=""),
			@ApiParam(name="系统编码", code="sysNo", value=""),
			@ApiParam(name="用户编号", code="userId", value=""),
	}, response = {
			@ApiRes(name="响应码[0成功、-1失败]", code="code", clazz=String.class, value="0"),
			@ApiRes(name="响应消息", code="message", clazz=String.class, value="success"),
			@ApiRes(name="主体内容", code="body", clazz=Object.class, value=""),
	})
	public ResponseFrame delete(String id, String sysNo, String userId) {
		try {
			ResponseFrame frame = msgInfoService.delete(id, sysNo, userId);
			return frame;
		} catch (Exception e) {
			LOGGER.error("处理业务异常: " + e.getMessage(), e);
			return new ResponseFrame(ResponseCode.SERVER_ERROR);
		}
	}
}