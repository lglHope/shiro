package liu.hope.services.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import liu.hope.bean.Privilege;
import liu.hope.bean.User;
import liu.hope.dao.IPrivilegeDao;
import liu.hope.services.IPrivilegeService;

@Service("privilegeService")
public class PrivilegeServiceImpl implements IPrivilegeService {
	
	@Resource
	private IPrivilegeDao privilegeDao;

	@Override
	public List<String> findAllPrivilegeUri() {
		return privilegeDao.findAllPrivilegeUri();
	}

	@Override
	public List<Privilege> findByUsername(String username) {
		if (username == null) {
			return null;
		}
		//������û��ǳ�������Ա�ͻ������Ȩ��
		Subject subject = SecurityUtils.getSubject();
		if (subject.hasRole("��������Ա")) {
			return privilegeDao.findAll();
		}
		return privilegeDao.findByUsername(username);
	}

}
