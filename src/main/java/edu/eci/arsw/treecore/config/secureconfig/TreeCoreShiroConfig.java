package edu.eci.arsw.treecore.config.secureconfig;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class TreeCoreShiroConfig {

	/**
	 * Metodo que crea el atributo autorizador de shiro
	 * 
	 * @param securityManager Objeto Security Manager
	 * @return Atributo autorizador
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
			org.apache.shiro.mgt.SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}

	/**
	 * Metodo que crea el filtro del bean
	 * 
	 * @param securityManager Objeto Security Manager
	 * @return Filtro de shiro
	 */
	@Bean
	public ShiroFilterFactoryBean getShiroFilterFactoryBean(
			@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
		
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		/**
		 * Shiro The built-in filter can realize the authority related amount
		 * interceptor Common filters: anon:Access without authentication (login)
		 * authc:Must be authenticated to access user:If you use the function of
		 * rememberMe, you can access it directly perms:The resource must have resource
		 * permission to aanonccess role:The resource must have role permission to access
		 */
		Map<String, String> filterMap = new LinkedHashMap<>();
		// filterMap.put("/tree.html", "authc");
		// filterMap.put("/addProject.html", "authc");
		// filterMap.put("/profile.html", "authc");

		// filterMap.put("/login", "perms[user:add]");
		// shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

		filterMap.put("/signup.html","anon");
		filterMap.put("/index.html","anon");
		filterMap.put("/","anon");
		filterMap.put("/login.html","anon");	
		
		// Set unauthorized pages

		filterMap.put("/profile.html","authc");
		filterMap.put("/addProject.html","authc");
		filterMap.put("/project_description.html","authc");
		filterMap.put("/tree.html","authc");

		// Modify the page of the blocked request jump

		//shiroFilterFactoryBean.setLoginUrl("/index.html");
		// // Set unauthorized pages

        filterMap.put("/login","anon");
        
        //shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        
		return shiroFilterFactoryBean;
	}

	/**
	 * Metodo que crea el controlador de seguridad
	 * 
	 * @param userRealm Rol del usuario
	 * @return Controlador de segurida
	 */
	@Bean(name = "securityManager")
	public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		// Correlation realm
		securityManager.setRealm(userRealm);
		return securityManager;
	}

	/**
	 * Metodo para crear el objeto-rol del usuario
	 * 
	 * @return Rol del usuario
	 */
	@Bean(name = "userRealm")
	public UserRealm getRealm() {
		return new UserRealm();
	}

}
