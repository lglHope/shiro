package liu.hope.dao;

import java.util.List;

import liu.hope.bean.Privilege;

public interface IPrivilegeDao {

	List<Privilege> findAll();
	
	List<String> findAllPrivilegeUri();
	
	List<Privilege> findByUsername(String username);
}
