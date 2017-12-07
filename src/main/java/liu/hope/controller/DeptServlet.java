package liu.hope.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import liu.hope.bean.Dept;
import liu.hope.bean.User;
import liu.hope.services.IDeptService;

@Controller
@RequestMapping("dept")
public class DeptServlet {

	@Resource
	private IDeptService deptService;
	
	//@Scheduled(cron="秒 分 时 天 月 星期")没有年份
	@Scheduled(cron="30/5 * * * * ?")
	@ResponseBody
	@RequestMapping(value="findAllDept")
	public List<Object> findAllDept(){
//		int i = 1;
//		System.out.println(i++);
		List<Dept> depts = deptService.findAllDept();
		List<Object> all = new ArrayList<>();
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getSession().getAttribute("user");
		all.add(user);
		for (Dept dept : depts) {
			all.add(dept);
		}
		return all;
	}
	
}
