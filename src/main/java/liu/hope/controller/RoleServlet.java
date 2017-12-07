package liu.hope.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import liu.hope.bean.Role;
import liu.hope.services.IRoleService;

@Controller
@RequestMapping("role")
public class RoleServlet {

	@Resource
	private IRoleService roleService;
	
	@ResponseBody
	@RequestMapping(value="findAllRole",method=RequestMethod.POST)
	public List<Role> findAllRole(){
		List<Role> roles = roleService.findAllRole();
		return roles;
	}
	
}
