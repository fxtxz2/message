package com.message.admin.msg.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.message.admin.msg.pojo.MsgGroup;

/**
 * msg_group的Dao
 * @author autoCode
 * @date 2017-12-04 17:13:15
 * @version V1.0.0
 */
public interface MsgGroupDao {

	public abstract void save(MsgGroup msgGroup);

	public abstract void update(MsgGroup msgGroup);

	public abstract void delete(@Param("id")String id);

	public abstract MsgGroup get(@Param("id")String id);

	public abstract List<MsgGroup> findMsgGroup(MsgGroup msgGroup);
	
	public abstract int findMsgGroupCount(MsgGroup msgGroup);
}