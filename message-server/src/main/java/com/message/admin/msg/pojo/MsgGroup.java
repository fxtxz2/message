package com.message.admin.msg.pojo;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.system.comm.model.BaseEntity;

/**
 * msg_group实体
 * @author autoCode
 * @date 2017-12-04 17:13:15
 * @version V1.0.0
 */
@Alias("msgGroup")
@SuppressWarnings("serial")
@JsonInclude(Include.NON_NULL)
public class MsgGroup extends BaseEntity implements Serializable {
	//编号
	private String id;
	//来源系统
	private String sysNo;
	//类型[10系统、20个人、30其它]
	private Integer type;
	//名称
	private String name;
	//创建时间
	private Date createTime;
	
	public MsgGroup() {
		super();
	}
	public MsgGroup(String id, String sysNo) {
		super();
		this.id = id;
		this.sysNo = sysNo;
	}
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
	
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
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