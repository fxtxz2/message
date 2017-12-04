package com.message.admin.sys.pojo;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.system.comm.model.BaseEntity;

/**
 * sys_info实体
 * @author autoCode
 * @date 2017-12-04 16:59:38
 * @version V1.0.0
 */
@Alias("sysInfo")
@SuppressWarnings("serial")
@JsonInclude(Include.NON_NULL)
public class SysInfo extends BaseEntity implements Serializable {
	//系统编码
	private String sysNo;
	//系统名称
	private String name;
	//创建时间
	private Date createTime;
	
	public String getSysNo() {
		return sysNo;
	}
	public void setSysNo(String sysNo) {
		this.sysNo = sysNo;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}