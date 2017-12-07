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
	 * ��Ȩ
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
		//����Ҫ���û��˺���Ψһ��
		String username = (String) principal.getPrimaryPrincipal();
		User user = userService.findOneByUsername(username);
		//��ѯ�����Ľ�ɫ��Ȩ�޶��������info����
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
	 * ��¼��֤ ������ȷ���û���Ϣ
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal();
		User user = userService.findOneByUsername(username);
		if (user == null) {
			throw new UnknownAccountException("�˺Ų�����");
		}
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, user.getPassword(),this.getClass().getName());
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		session.setAttribute("user", user);
		return info;
	}

}
