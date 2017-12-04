package com.message.admin.msg.pojo;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.system.comm.model.BaseEntity;

/**
 * msg_cld实体
 * @author autoCode
 * @date 2017-12-04 17:13:15
 * @version V1.0.0
 */
@Alias("msgCld")
@SuppressWarnings("serial")
@JsonInclude(Include.NON_NULL)
public class MsgCld extends BaseEntity implements Serializable {
	//编号
	private String id;
	//消息编号
	private String msgId;
	//标题
	private String title;
	//内容
	private String content;
	//创建时间
	private Date createTime;
	
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
}