package liu.hope.realm;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Component;

import liu.hope.bean.Privilege;
import liu.hope.bean.User;
import liu.hope.services.IUserService;

@Component("userRealm")
public class UserRealm extends AuthorizingRealm {
	
	@Resource
	private IUserService userService;

	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
		//这里要求用户账号是唯一的
		String username = (String) principal.getPrimaryPrincipal();
		User user = userService.findOneByUsername(username);
		//查询出来的角色和权限都放在这个info里面
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addRole(user.getRole().getRoleCn());
		List<Privilege> privileges = user.getRole().getPrivileges();
		List<String> urls = new ArrayList<>();
		for (Privilege privilege : privileges) {
			if (privilege.getPriUrl()!=null) {
				urls.add(privilege.getPriUrl());
			}
		}
		info.addStringPermissions(urls);
		return info;
	}

	/**
	 * 登录验证 保存正确的用户信息
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal();
		User user = userService.findOneByUsername(username);
		if (user == null) {
			throw new UnknownAccountException("账号不存在");
		}
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, user.getPassword(),this.getClass().getName());
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		session.setAttribute("user", user);
		return info;
	}

}
