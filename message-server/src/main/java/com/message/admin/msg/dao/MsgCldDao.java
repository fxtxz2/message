package com.message.admin.msg.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.message.admin.msg.pojo.MsgCld;

/**
 * msg_cld的Dao
 * @author autoCode
 * @date 2017-12-04 17:13:15
 * @version V1.0.0
 */
public interface MsgCldDao {

	public abstract void save(MsgCld msgCld);

	public abstract void update(MsgCld msgCld);

	public abstract void delete(@Param("id")String id);

	public abstract MsgCld get(@Param("id")String id);

	public abstract List<MsgCld> findMsgCld(MsgCld msgCld);
	
	public abstract int findMsgCldCount(MsgCld msgCld);
}