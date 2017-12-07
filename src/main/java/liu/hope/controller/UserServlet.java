package liu.hope.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import liu.hope.bean.User;
import liu.hope.bean.UserCondition;
import liu.hope.services.IUserService;

@Controller
@RequestMapping("user")
public class UserServlet {
	
	@Resource
	private IUserService userService;

	@RequestMapping(value = "login")
	public String login(Model model, User user) {
		Subject subject = SecurityUtils.getSubject();
		User user2 = (User) subject.getSession().getAttribute("user");
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),user.getPassword());
		try {
			subject.login(token);
		} catch (UnknownAccountException e) {
			return "index";
		}catch (IncorrectCredentialsException e) {
			return "index";
		}
		model.addAttribute("user", user2);
		return "pages/main";
	}

	@RequestMapping(value = "logOut")
	public String logOut(HttpSession session) {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return "index";
	}

	@RequestMapping(value = "limit")
	public String limit(Model model, Integer pageNo, Integer pageSize) {
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 2 : pageSize;
		List<User> users = userService.limit(pageNo, pageSize);
		Integer count = userService.getCount();
		model.addAttribute("users", users);
		model.addAttribute("allpage", (count - 1) / pageSize + 1);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pageSize", pageSize);
		return "pages/frame/uc/user_list";
	}
	
	@ResponseBody
	@RequestMapping(value = "append",method=RequestMethod.POST)
	public String append(MultipartFile file, HttpServletRequest request,UserCondition userCondition,Model model) {
		String resetName = null;
		if (file != null&&!file.isEmpty()) {
			String filename = file.getOriginalFilename();
			String suffix = filename.substring(filename.lastIndexOf("."), filename.length());
			String name = UUID.randomUUID().toString().replace("-", "");
			resetName = name + suffix;
			String realPath = request.getServletContext().getRealPath("/userPic");
			File file2 = new File(realPath);
			if (!file2.exists()) {
				file2.mkdirs();
			}
			try {
				file.transferTo(new File(realPath + File.separator + resetName));
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		userCondition.setPicpath(resetName);
		Boolean boo = userService.appendUser(userCondition);
		if(boo){
			return "1";
		}
		return "-1";
	}
	
	@RequiresPermissions(value={"user/append"})
	@RequestMapping(value = "append", method = RequestMethod.GET)
	public String append(){
		return "pages/frame/uc/user_add";
	}
	
	@RequestMapping(value="removeUser")
	public String removeUser(String[] delid,Model model){
		List<Integer> ids = new ArrayList<>();
		for (String string : delid) {
			int i = Integer.parseInt(string.isEmpty()?"0":string);
			ids.add(i);
		}
		Boolean boo = userService.removeUser(ids);
		if(boo){
			model.addAttribute("msg", 1);
			return null;
		}
		model.addAttribute("msg", -1);
		return null;
	}

}
