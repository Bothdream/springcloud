package com.zte.sangfor.order.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResouceServerConfig extends ResourceServerConfigurerAdapter {
    //资源ID和授权服务的ID要一致
    private static final String RESOURCE_ID = "res1";

    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(RESOURCE_ID)
                .tokenServices(tokenServices()).stateless(true);
    }

    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/**")
                .access("#oauth2.hasScope('all')")//scope和授权服务的ID要一致
                .and().csrf().disable();
    }
    //资源服务令牌解析服务
    @Bean
    public ResourceServerTokenServices tokenServices() {
        RemoteTokenServices services = new RemoteTokenServices();
        //token鉴权
        services.setCheckTokenEndpointUrl("http://localhost:6003/oauth/check_token");
        //鉴权客户端ID
        services.setClientId("c1");
        //客户端密码
        services.setClientSecret("secret");
        return services;
    }
}
