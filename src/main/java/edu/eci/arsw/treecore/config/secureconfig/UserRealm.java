package edu.eci.arsw.treecore.config.secureconfig;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import edu.eci.arsw.treecore.exceptions.ServiciosTreeCoreException;
import edu.eci.arsw.treecore.model.impl.Usuario;
import edu.eci.arsw.treecore.services.TreeCoreUserServices;

public class UserRealm extends AuthorizingRealm {

	@Autowired
	private TreeCoreUserServices userService;

	/**
	 * Execute authorization logic
	 * 
	 * @param principalCollection
	 * @return
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		// Empower resources
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		// Add the authorization string of the resource, which should be the same as the
		// authorization intercepting string
		info.addStringPermission("user:add");
		return info;
	}

	/**
	 * Execute authentication logic
	 * 
	 * @param authenticationToken
	 * @return
	 * @throws AuthenticationException
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
			throws AuthenticationException {
		// Write shiro judgment logic to judge user name and password
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
		Usuario user = null;
		try {
			user = userService.getUsuario(token.getUsername());
			// Judge user name
			if (user == null) {
				// The user name does not exist, and shiro will throw unknown accountexception
				// for us
				System.out.println(user);
				return null;
			}
		
		} catch (ServiciosTreeCoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// To determine the password, shiro will help us determine whether the password
		// is the same as the password in the database
		return new SimpleAuthenticationInfo("", user.getPasswd(), "");
	}
}