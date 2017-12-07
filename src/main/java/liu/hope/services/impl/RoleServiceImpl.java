package liu.hope.services.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import liu.hope.bean.Role;
import liu.hope.dao.IRoleDao;
import liu.hope.services.IRoleService;

@Service("roleService")
public class RoleServiceImpl implements IRoleService {
	
	@Resource
	private IRoleDao roleDao;

	@Override
	public List<Role> findAllRole() {
		return roleDao.findAllRole();
	}

}
