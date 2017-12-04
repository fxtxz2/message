package com.message.admin.msg.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.message.admin.msg.pojo.MsgRece;

/**
 * msg_rece的Dao
 * @author autoCode
 * @date 2017-12-04 17:13:15
 * @version V1.0.0
 */
public interface MsgReceDao {

	public abstract void save(MsgRece msgRece);

	public abstract void update(MsgRece msgRece);

	public abstract void delete(@Param("id")String id);

	public abstract MsgRece get(@Param("id")String id);

	public abstract List<MsgRece> findMsgRece(MsgRece msgRece);
	
	public abstract int findMsgReceCount(MsgRece msgRece);
}