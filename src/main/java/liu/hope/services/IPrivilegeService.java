package liu.hope.services;

import java.util.List;

import liu.hope.bean.Privilege;

public interface IPrivilegeService {

	List<String> findAllPrivilegeUri();
	
	List<Privilege> findByUsername(String username);
}
