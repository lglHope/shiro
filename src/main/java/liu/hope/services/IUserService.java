package liu.hope.services;

import java.util.List;

import org.springframework.stereotype.Service;

import liu.hope.bean.User;
import liu.hope.bean.UserCondition;

@Service
public interface IUserService {

	/**
	 * 用户登录检测
	 * @param user
	 * @return
	 */
	User login(User user);
	
	User findOneByUsername(String username);
	
	/**
	 * 分页查询
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	List<User> limit(int pageNo,int pageSize);
	
	/**
	 * 数据总条数
	 * @return
	 */
	Integer getCount();
	
	/**
	 * 添加用户
	 * @param userCondition
	 * @return
	 */
	Boolean appendUser(UserCondition userCondition);
	
	/**
	 * 删除用户
	 * @param userid
	 * @return
	 */
	Boolean removeUser(List<Integer> userids);
	
	
}
