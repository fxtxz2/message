package com.message.admin.msg.pojo;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.system.comm.model.BaseEntity;

/**
 * msg_rece实体
 * @author autoCode
 * @date 2017-12-04 17:13:15
 * @version V1.0.0
 */
@Alias("msgRece")
@SuppressWarnings("serial")
@JsonInclude(Include.NON_NULL)
public class MsgRece extends BaseEntity implements Serializable {
	//编号
	private String id;
	//消息编号
	private String msgId;
	//接收人来源系统编码
	private String receSysNo;
	//接收人
	private String receUserId;
	//接收时间
	private Date receTime;
	//是否阅读
	private Integer isRead;
	//阅读时间
	private Date readTime;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getMsgId() {
		return msgId;
	}
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	
	public String getReceUserId() {
		return receUserId;
	}
	public void setReceUserId(String receUserId) {
		this.receUserId = receUserId;
	}
	
	public Date getReceTime() {
		return receTime;
	}
	public void setReceTime(Date receTime) {
		this.receTime = receTime;
	}
	
	public Integer getIsRead() {
		return isRead;
	}
	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}
	
	public Date getReadTime() {
		return readTime;
	}
	public void setReadTime(Date readTime) {
		this.readTime = readTime;
	}
	public String getReceSysNo() {
		return receSysNo;
	}
	public void setReceSysNo(String receSysNo) {
		this.receSysNo = receSysNo;
	}
}