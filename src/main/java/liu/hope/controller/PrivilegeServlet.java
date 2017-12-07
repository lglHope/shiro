package liu.hope.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import liu.hope.services.IPrivilegeService;

@Controller
@RequestMapping("privilege")
public class PrivilegeServlet {
	
	@Resource
	private IPrivilegeService privilegeService;
	

}
