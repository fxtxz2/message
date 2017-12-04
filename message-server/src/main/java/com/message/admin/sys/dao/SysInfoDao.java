package com.message.admin.sys.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.message.admin.sys.pojo.SysInfo;

/**
 * sys_info的Dao
 * @author autoCode
 * @date 2017-12-04 16:59:38
 * @version V1.0.0
 */
public interface SysInfoDao {

	public abstract void save(SysInfo sysInfo);

	public abstract void update(SysInfo sysInfo);

	public abstract void delete(@Param("sysNo")String sysNo);

	public abstract SysInfo get(@Param("sysNo")String sysNo);

	public abstract List<SysInfo> findSysInfo(SysInfo sysInfo);
	
	public abstract int findSysInfoCount(SysInfo sysInfo);
}