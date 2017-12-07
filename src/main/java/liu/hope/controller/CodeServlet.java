package liu.hope.controller;

import java.awt.image.RenderedImage;
import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import liu.hope.utils.CodeUtil;

@Controller
@RequestMapping("code")
public class CodeServlet {

	@RequestMapping(value="code")
	public String code(HttpServletRequest request,HttpServletResponse response){
		// 调用工具类生成的验证码和验证码图片
		Map<String, Object> codeAndPic = CodeUtil.generateCodeAndPic();
		HttpSession session = request.getSession();
		// 将四位数字的验证码保存到Session中。
		session.setAttribute("code", codeAndPic.get("code").toString());
		// 禁止图像缓存。
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", -1);
		response.setContentType("image/jpeg");
		ServletOutputStream servletOutputStream = null;
		try {
            servletOutputStream = response.getOutputStream();
            ImageIO.write((RenderedImage) codeAndPic.get("codePic"), "jpeg", servletOutputStream);
            servletOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value="check")
	public String check(HttpServletRequest request){
		HttpSession session = request.getSession();
		String code = (String) session.getAttribute("code");
		String string = request.getParameter("codeValue");
		if (code!=null&&!code.isEmpty()&&string!=null&&!string.isEmpty()) {
			if (code.equalsIgnoreCase(string)) {
				return "1";
			}
		}
		return "-1";
	}
	
}
