package com.ms.server.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

//@Configuration
public class TaskConfig {
	
    @Autowired
    private Environment env;

	/**
	 * 配置jdbc模板
	 * @param dataSource
	 * @return
	 */
	/*@Bean
	public OrderDealTask orderDealTask() {
		String isOpen = env.getProperty("task.is.open");
		if("1".equals(isOpen)) {
			//订单相关 完成服务状态   meet 的时间  默认计算 120分钟后 结束
			return new OrderDealTask().run(10, 3 * 60);
		}
		return null;
	}*/
}
