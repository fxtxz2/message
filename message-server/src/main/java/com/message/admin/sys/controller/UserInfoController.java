package com.message.admin.sys.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.message.admin.sys.pojo.UserInfo;
import com.message.admin.sys.service.UserInfoService;
import com.monitor.api.ApiInfo;
import com.monitor.api.ApiParam;
import com.monitor.api.ApiRes;
import com.system.comm.model.Orderby;
import com.system.comm.utils.FrameJsonUtil;
import com.system.comm.utils.FrameStringUtil;
import com.system.handle.model.ResponseCode;
import com.system.handle.model.ResponseFrame;

/**
 * user_info的Controller
 * @author autoCode
 * @date 2017-12-04 16:59:38
 * @version V1.0.0
 */
@RestController
public class UserInfoController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserInfoController.class);

	@Autowired
	private UserInfoService userInfoService;
	
	@RequestMapping(name = "用户-获取详细信息", value = "/userInfo/get")
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
			frame.setBody(userInfoService.get(id));
			frame.setCode(ResponseCode.SUCC.getCode());
			return frame;
		} catch (Exception e) {
			LOGGER.error("处理业务异常: " + e.getMessage(), e);
			return new ResponseFrame(ResponseCode.SERVER_ERROR);
		}
	}

	@RequestMapping(name = "用户-新增或修改", value = "/userInfo/saveOrUpdate")
	@ApiInfo(params = {
			@ApiParam(name="系统编码", code="sysNo", value=""),
			@ApiParam(name="用户编号", code="userId", value=""),
	}, response = {
			@ApiRes(name="响应码[0成功、-1失败]", code="code", clazz=String.class, value="0"),
			@ApiRes(name="响应消息", code="message", clazz=String.class, value="success"),
			@ApiRes(name="主体内容", code="body", clazz=Object.class, value=""),
	})
	public ResponseFrame saveOrUpdate(UserInfo userInfo) {
		try {
			ResponseFrame frame = userInfoService.saveOrUpdate(userInfo);
			return frame;
		} catch (Exception e) {
			LOGGER.error("处理业务异常: " + e.getMessage(), e);
			return new ResponseFrame(ResponseCode.SERVER_ERROR);
		}
	}

	@RequestMapping(name = "用户-分页查询信息", value = "/userInfo/pageQuery")
	@ApiInfo(params = {
			@ApiParam(name="页面", code="page", value="1"),
			@ApiParam(name="每页大小", code="size", value="10"),
			@ApiParam(name="排序[{\"property\": \"createTime\", \"type\":\"desc\", \"order\":1}]", code="orderby", value="", required=false),
	}, response = {
			@ApiRes(name="响应码[0成功、-1失败]", code="code", clazz=String.class, value="0"),
			@ApiRes(name="响应消息", code="message", clazz=String.class, value="success"),
			@ApiRes(name="主体内容", code="body", clazz=Object.class, value=""),
			@ApiRes(name="当前页码", code="page", pCode="body", clazz=Integer.class, value="1"),
			@ApiRes(name="每页大小", code="size", pCode="body", clazz=Integer.class, value="10"),
			@ApiRes(name="总页数", code="totalPage", pCode="body", clazz=Integer.class, value="5"),
			@ApiRes(name="总记录数", code="total", pCode="body", clazz=Integer.class, value="36"),
			@ApiRes(name="数据集合", code="rows", pCode="body", clazz=List.class, value=""),
			@ApiRes(name="id", code="id", pCode="rows", value=""),
	})
	public ResponseFrame pageQuery(UserInfo userInfo, String orderby) {
		try {
			if(FrameStringUtil.isNotEmpty(orderby)) {
				List<Orderby> orderbys = FrameJsonUtil.toList(orderby, Orderby.class);
				userInfo.setOrderbys(orderbys);
			}
			ResponseFrame frame = userInfoService.pageQuery(userInfo);
			return frame;
		} catch (Exception e) {
			LOGGER.error("处理业务异常: " + e.getMessage(), e);
			return new ResponseFrame(ResponseCode.SERVER_ERROR);
		}
	}

	@RequestMapping(name = "用户-根据主键删除", value = "/userInfo/delete")
	@ApiInfo(params = {
			@ApiParam(name="id", code="id", value=""),
	}, response = {
			@ApiRes(name="响应码[0成功、-1失败]", code="code", clazz=String.class, value="0"),
			@ApiRes(name="响应消息", code="message", clazz=String.class, value="success"),
			@ApiRes(name="主体内容", code="body", clazz=Object.class, value=""),
	})
	public ResponseFrame delete(String id) {
		try {
			ResponseFrame frame = userInfoService.delete(id);
			return frame;
		} catch (Exception e) {
			LOGGER.error("处理业务异常: " + e.getMessage(), e);
			return new ResponseFrame(ResponseCode.SERVER_ERROR);
		}
	}
}