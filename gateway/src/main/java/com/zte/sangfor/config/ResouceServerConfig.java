package com.zte.sangfor.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * 资源配置
 */
@Configuration
public class ResouceServerConfig {

    //资源ID和授权服务的ID要一致
    private static final String RESOURCE_ID = "res1";


    //auth服务的资源配置
    @Configuration
    @EnableResourceServer
    public class AuthServerConfig extends ResourceServerConfigurerAdapter {

        @Autowired
        private TokenStore tokenStore;

        @Override
        public void configure(ResourceServerSecurityConfigurer resource) {
            resource.tokenStore(tokenStore).resourceId(RESOURCE_ID).stateless(true);
        }

        @Override
        public void configure(HttpSecurity httpSecurity) throws Exception {
            //auth 服务鉴权全部放行
            httpSecurity.authorizeRequests()
                        .antMatchers("/auth/**").permitAll();
        }
     }

     //订单服务
     @Configuration
     @EnableResourceServer
     public class OrderServerConfig extends ResourceServerConfigurerAdapter {

         @Autowired
         private TokenStore tokenStore;

         @Override
         public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
                resources.resourceId(RESOURCE_ID)
                        .tokenStore(tokenStore)
                        .stateless(true);
         }

         @Override
         public void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                .antMatchers("/order/**")
                .access("#oauth2.hasScope('all')")//scope和授权服务的ID要一致
                .and().csrf().disable();
        }
    }
    //其他服务的配置
}
