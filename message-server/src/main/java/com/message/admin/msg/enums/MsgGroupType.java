package com.message.admin.msg.enums;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.system.comm.model.KvEntity;

/**
 * 类型[10系统、20个人、30其它]
 * @author yuejing
 * @date 2016年5月16日 下午5:52:03
 * @version V1.0.0
 */
public enum MsgGroupType {
	SYS		(10, "系统"),
	PERSONAL(20, "个人"),
	OTHER	(30, "其他"),
	;
	
	private int code;
	private String name;
	private static List<KvEntity> list = new ArrayList<KvEntity>();
	private static Map<Integer, String> map = new HashMap<Integer, String>();

	private MsgGroupType(int code, String name) {
		this.code = code;
		this.name = name;
	}
	
	static {
		EnumSet<MsgGroupType> set = EnumSet.allOf(MsgGroupType.class);
		for(MsgGroupType e : set){
			map.put(e.getCode(), e.getName());
			list.add(new KvEntity(String.valueOf(e.getCode()), e.getName()));
		}
	}

	/**
	 * 根据Code获取对应的汉字
	 * @param code
	 * @return
	 */
	public static String getText(Integer code) {
		return map.get(code);
	}
	
	/**
	 * 获取集合
	 * @return
	 */
	public static List<KvEntity> getList() {
		return list;
	}

	public int getCode() {
		return code;
	}
	public String getName() {
		return name;
	}
}
