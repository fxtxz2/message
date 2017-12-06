# message
消息服务平台，给其他系统提供统一的消息接入，可以处理未读、已读、消息列表等

# 接入系统
1. 需要先创建系统的信息
调用接口：/sysInfo/saveOrUpdate
参数：
	sysNo：系统编码
	name：系统名称
参考测试类：AccessUserTest.java

2. 导入系统用户信息
调用接口：/userInfo/saveOrUpdate
参数：
	sysNo：系统编码
	userId：用户编号
参考测试类：AccessUserTest.java

3. 新增修改消息分组
调用接口：/msgGroup/saveOrUpdate
参数：
	id：分组编码
	sysNo：系统编码
	name：分组名称
	type：类型[10系统、20个人、30其它]
参考测试类：MsgGroupTest.java

4. 发送消息
调用接口：/msgInfo/save
参数：
	sysNo：系统编码
	groupId：消息分组编号[可以传入sys代表系统的消息分组]
	title：标题
	content：内容
	sendUserId：发送人编码
	receUserIds：接收人编码集合[多个用;分隔]
参考测试类：MsgSendTest.java

5. 获取用户的未读消息数
调用接口：/msgInfo/getCountUnread
参数：
	sysNo：系统编码
	userId：用户编号
参考测试类：MsgQueryTest.java

6. 获取用户的未读消息列表
调用接口：/msgInfo/pageQueryUnread
参数：
	page：页码
	size：每页大小
	sysNo：系统编码
	userId：用户编号
	groupId：分组编号，多个;隔开
参考测试类：MsgQueryTest.java

7. 获取用户的消息列表
调用接口：/msgInfo/pageQuery
参数：
	page：页码
	size：每页大小
	sysNo：系统编码
	userId：用户编号
	isRead：是否阅读[0否、1是]不传入获取所有状态的消息
	groupId：分组编号，多个;隔开
参考测试类：MsgQueryTest.java

8. 根据分组获取未读记录的列表
调用接口：/msgGroup/findUnread
参数：
	sysNo：系统编码
	userId：用户编号
参考测试类：MsgQueryTest.java 

9. 获取消息详情
调用接口：/msgInfo/getDtl
参数：
	id：消息编号
	sysNo：系统编码
	userId：用户编号
参考测试类：MsgQueryTest.java 
