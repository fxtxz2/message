package com.message.admin.msg.pojo;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.system.comm.model.BaseEntity;

/**
 * msg_info实体
 * @author autoCode
 * @date 2017-12-04 17:13:15
 * @version V1.0.0
 */
@Alias("msgInfo")
@SuppressWarnings("serial")
@JsonInclude(Include.NON_NULL)
public class MsgInfo extends BaseEntity implements Serializable {
	//编号
	private String id;
	//消息分组编号
	private String groupId;
	//标题
	private String title;
	//内容
	private String content;
	//创建时间
	private Date createTime;
	//发送人
	private String sendUserId;
	//状态[10待发送、20已发送]
	private Integer status;
	//发送时间
	private Date sendTime;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public String getSendUserId() {
		return sendUserId;
	}
	public void setSendUserId(String sendUserId) {
		this.sendUserId = sendUserId;
	}
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
}