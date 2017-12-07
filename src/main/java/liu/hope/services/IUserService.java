package liu.hope.services;

import java.util.List;

import org.springframework.stereotype.Service;

import liu.hope.bean.User;
import liu.hope.bean.UserCondition;

@Service
public interface IUserService {

	/**
	 * �û���¼���
	 * @param user
	 * @return
	 */
	User login(User user);
	
	User findOneByUsername(String username);
	
	/**
	 * ��ҳ��ѯ
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	List<User> limit(int pageNo,int pageSize);
	
	/**
	 * ����������
	 * @return
	 */
	Integer getCount();
	
	/**
	 * ����û�
	 * @param userCondition
	 * @return
	 */
	Boolean appendUser(UserCondition userCondition);
	
	/**
	 * ɾ���û�
	 * @param userid
	 * @return
	 */
	Boolean removeUser(List<Integer> userids);
	
	
}
