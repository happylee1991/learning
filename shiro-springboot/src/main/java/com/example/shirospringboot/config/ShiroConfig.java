package com.example.shirospringboot.config;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    @Value("${realm-path}")
    String realmPath;
//    @Value("${server.servlet.context-path}")
//    String preFix;

    @Bean
    public IniRealm iniRealm() {
        IniRealm iniRealm = new IniRealm(realmPath);
        return iniRealm;
    }

    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(IniRealm iniRealm) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager(iniRealm);

        return defaultWebSecurityManager;
    }



        @Bean
    public ShiroFilterFactoryBean shiroFilter (DefaultWebSecurityManager defaultWebSecurityManager) {
            ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
            shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
            Map<String, String> filterMap = new HashMap<>();
            filterMap.put("/", "anon");
            filterMap.put("/login", "anon");
            filterMap.put("/login.html", "anon");
            filterMap.put("/register", "anon");
            filterMap.put("/register.html", "anon");
            filterMap.put("/**", "authc");
            shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
            return shiroFilterFactoryBean;
    }

        @Bean
    public Object object(DefaultWebSecurityManager defaultWebSecurityManager) {
            SecurityUtils.setSecurityManager(defaultWebSecurityManager);
            Subject subject = SecurityUtils.getSubject();
            return subject;
    }


}
