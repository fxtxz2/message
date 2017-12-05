package com.ms.server;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.ms.env.Env;
import com.ms.env.EnvUtil;
import com.ms.server.interceptor.AuthSecurityInterceptor;
import com.system.auth.AuthUtil;
import com.system.auth.model.AuthClient;
import com.system.comm.utils.FrameStringUtil;

@ComponentScan("com.*")
@EnableAutoConfiguration(exclude={
		DataSourceAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class,
		//HibernateJpaAutoConfiguration.class, //（如果使用Hibernate时，需要加）
})
@ServletComponentScan("com.*")
@SpringBootApplication
public class MessageServerApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(MessageServerApplication.class, args);
		
		initAuthClient();
	}

	private static void initAuthClient() {
		String authString = EnvUtil.get(Env.CLIENT_AUTH_INFO);
		if(FrameStringUtil.isNotEmpty(authString)) {
			List<String> auths = FrameStringUtil.toArray(authString, ";");
			for (String string : auths) {
				List<String> auth = FrameStringUtil.toArray(string, "_");
				AuthClient client = new AuthClient(auth.get(0), auth.get(1), "http://127.0.0.1", auth.get(2), "http://127.0.0.1/callback.htm");
				AuthUtil.addAuthClient(client);
			}
		}
		/*//初始化config的配置
		SysConfigService sysConfigService = FrameSpringBeanUtil.getBean(SysConfigService.class);
		String clientId = sysConfigService.getValue(SysConfigCode.CONFIG_CLIENT_ID);
		String token = sysConfigService.getValue(SysConfigCode.CONFIG_CLIENT_TOKEN);
		AuthUtil.addAuthClient(new AuthClient(clientId, SysConfigCode.CONFIG_CLIENT_ID.getName(),
				"http://xxxx:", token, ""));*/
	}
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(MessageServerApplication.class);
    }

	@Bean
	public WebMvcConfigurerAdapter webMvcConfigurerAdapter() {
		WebMvcConfigurerAdapter wmca = new WebMvcConfigurerAdapter() {

			/**
			 * 添加拦截器
			 */
			@Override
			public void addInterceptors(InterceptorRegistry registry) {
				registry.addInterceptor(new AuthSecurityInterceptor())
				.addPathPatterns("/**");
			}

			/*@Override
			public void addResourceHandlers(ResourceHandlerRegistry registry) {
				registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
				registry.addResourceHandler("/view/**").addResourceLocations("/view/");
				super.addResourceHandlers(registry);
			}*/
		};
		return wmca;
	}
}