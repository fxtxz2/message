package com.message.admin.msg.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.message.admin.msg.pojo.MsgInfo;

/**
 * msg_info的Dao
 * @author autoCode
 * @date 2017-12-04 17:13:15
 * @version V1.0.0
 */
public interface MsgInfoDao {

	public abstract void save(MsgInfo msgInfo);

	public abstract void update(MsgInfo msgInfo);

	public abstract void delete(@Param("id")String id);

	public abstract MsgInfo get(@Param("id")String id);

	public abstract List<MsgInfo> findMsgInfo(MsgInfo msgInfo);
	public abstract Integer findMsgInfoCount(MsgInfo msgInfo);

}