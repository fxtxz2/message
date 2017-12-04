package com.message.admin.sys.pojo;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.system.comm.model.BaseEntity;

/**
 * user_info实体
 * @author autoCode
 * @date 2017-12-04 16:59:38
 * @version V1.0.0
 */
@Alias("userInfo")
@SuppressWarnings("serial")
@JsonInclude(Include.NON_NULL)
public class UserInfo extends BaseEntity implements Serializable {
	//编号
	private String id;
	//来源系统
	private String sysNo;
	//用户编号
	private String userId;
	//创建时间
	private Date createTime;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getSysNo() {
		return sysNo;
	}
	public void setSysNo(String sysNo) {
		this.sysNo = sysNo;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}