package liu.hope.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;

import liu.hope.bean.Role;
import liu.hope.bean.User;
import liu.hope.bean.UserCondition;
import liu.hope.dao.IPrivilegeDao;
import liu.hope.dao.IUserDao;
import liu.hope.services.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService {

	@Resource
	private IUserDao userDao;
	
	@Resource
	private IPrivilegeDao privilegeDao;

	public User login(User user) {
		if (user == null) {
			return null;
		}
		User user2 = userDao.findOneByNameAndPwd(user);
		if (user2 != null) {
			Role role = user2.getRole();
			if (role != null && role.getRoleid() == 1) {
				role.setPrivileges(privilegeDao.findAll());
			}
		}

		return user2;
	}

	public List<User> limit(int pageNo, int pageSize) {
		Map<String, Object> map = new HashMap<>();
		map.put("start", (pageNo-1)*pageSize);
		map.put("pageSize", pageSize);
		return userDao.findForLimit(map);
	}

	@Override
	public Integer getCount() {
		return userDao.getCount();
	}

	@Override
	public Boolean appendUser(UserCondition userCondition) {
		if (userCondition==null) {
			return false;
		}
		return userDao.appendUser(userCondition)==1;
	}

	@Override
	public Boolean removeUser(List<Integer> userids) {
		return userDao.removeUser(userids)==userids.size();
	}

	@Override
	public User findOneByUsername(String username) {
		if (username == null) {
			return null;
		}
		User user = userDao.findOneByUsername(username);
		if (user != null) {
			Role role = user.getRole();
			if (role != null && role.getRoleid() == 1) {
				role.setPrivileges(privilegeDao.findAll());
			}
		}
		return user;
	}
	
	
}
