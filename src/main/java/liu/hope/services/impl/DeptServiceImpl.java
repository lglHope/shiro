package liu.hope.services.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import liu.hope.bean.Dept;
import liu.hope.dao.IDeptDao;
import liu.hope.services.IDeptService;

@Service("deptService")
public class DeptServiceImpl implements IDeptService {

	@Resource
	private IDeptDao deptDao;
	
	@Override
	public List<Dept> findAllDept() {
		return deptDao.findAllDept();
	}

}
