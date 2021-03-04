package com.project.student.config;

import com.project.student.common.UserRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);//设置安全管理器
        shiroFilterFactoryBean.setLoginUrl("/");//没有认证后跳到的页面
        shiroFilterFactoryBean.setSuccessUrl("/userInfo/home");
        //添加shiro的内置过滤器  设置要拦截的url
        Map<String,String>  filterChainDefinitionMap=new LinkedHashMap<>();//拦截
        filterChainDefinitionMap.put("/","anon");//登录不需要进行认证
        filterChainDefinitionMap.put("/userInfo/login","anon");//登录不需要进行认证
        filterChainDefinitionMap.put("/toLogin","anon");//登录不需要进行认证
        filterChainDefinitionMap.put("/layui/**","anon");//layui文件不需要进行认证
        filterChainDefinitionMap.put("/js/**","anon");//js文件不需要认证
        filterChainDefinitionMap.put("/images/**","anon");//images文件不需要认证
        filterChainDefinitionMap.put("/css/**","anon");//css文件不需要认证
        filterChainDefinitionMap.put("/favicon.ico","anon");//图标不需要认证
        filterChainDefinitionMap.put("/**","authc");
        //设置注销的url
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);//把设置好的过滤设置到ShiroFilterFactoryBean
        return shiroFilterFactoryBean;
    }

    //2. DefaultWebSecurityManager
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联realm对象  userRealm
        securityManager.setRealm(userRealm);
        return  securityManager;
    }
    //1. 创建realm对象 自定义的·类
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }

}
