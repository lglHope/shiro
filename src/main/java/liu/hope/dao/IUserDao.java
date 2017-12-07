package liu.hope.dao;

import java.util.List;
import java.util.Map;

import liu.hope.bean.User;
import liu.hope.bean.UserCondition;

public interface IUserDao {

	User findOneByNameAndPwd(User user);
	
	List<User> findForLimit(Map<String, Object> map);
	
	Integer getCount();
	
	Integer appendUser(UserCondition userCondition);
	
	Integer removeUser(List<Integer> userids);
	
	User findOneByUsername(String username);
	
}
