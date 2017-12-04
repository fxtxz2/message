package com.message.admin.sys.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.message.admin.sys.pojo.UserInfo;

/**
 * user_info的Dao
 * @author autoCode
 * @date 2017-12-04 16:59:38
 * @version V1.0.0
 */
public interface UserInfoDao {

	public abstract void save(UserInfo userInfo);

	public abstract void update(UserInfo userInfo);

	public abstract void delete(@Param("id")String id);

	public abstract UserInfo get(@Param("id")String id);

	public abstract List<UserInfo> findUserInfo(UserInfo userInfo);
	
	public abstract int findUserInfoCount(UserInfo userInfo);
}