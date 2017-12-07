<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basepath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basepath %>" />
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<title>项目管理系统</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
-->
</style>
<link href="css/css.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
    $(function () {
    	
    });
    
    function submitForm() {
    	$.post("code/check",{codeValue:$("#codeValue").val()},function(data){
    		$("#form").submit();
    		/* if(data=="1"){
    			$("#hint").text("验证码正确");
    			$("#form").submit();
    		}else if(data=="-1"){
    			$("#hint").text("验证码错误");
    		} */
    	},"json")
    }

    function changeImg() {
        var imgSrc = $("#imgObj");
        var src = imgSrc.attr("src");
        imgSrc.attr("src", chgUrl());
    }

    // 时间戳
    // 为了使每次生成图片不一致，即不让浏览器读缓存，所以需要加上时间戳
    function chgUrl() {
        var timestamp = (new Date()).valueOf();
        var url = "code/code?timestamp=" + timestamp;
        return url;
    }

</script>
</head>

<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="147" background="images/top02.gif"><img src="images/top03.gif" width="776" height="147" /></td>
  </tr>
</table>
<form action="user/login" method="post" id="form">
<table width="562" border="0" align="center" cellpadding="0" cellspacing="0" class="right-table03">
  <tr>
    <td width="221"><table width="95%" border="0" cellpadding="0" cellspacing="0" class="login-text01">
      
      <tr>
        <td><table width="100%" border="0" cellpadding="0" cellspacing="0" class="login-text01">
          <tr>
            <td align="center"><img src="images/ico13.gif" width="107" height="97" /></td>
          </tr>
          <tr>
            <td height="40" align="center">&nbsp;</td>
          </tr>
          
        </table></td>
        <td><img src="images/line01.gif" width="5" height="292" /></td>
      </tr>
    </table></td>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="31%" height="35" class="login-text02">用户名称：<br /></td>
        <td width="69%"><input name="username" value="admin" type="text" size="30" /></td>
      </tr>
      <tr>
        <td height="35" class="login-text02">密　码：<br /></td>
        <td><input name="password" type="password" value="admin" size="33" /></td>
      </tr>
      <tr>
        <td height="35" class="login-text02">验证图片：<br /></td>
        <td>
        <img src="${pageContext.request.contextPath}/code/code " width="109" height="40" id="imgObj" alt="验证码"/>
        <a href="javascript:void(0)" onclick="changeImg()">看不清楚，换张图片</a></td>
      </tr>
      <tr>
        <td height="35" class="login-text02">请输入验证码：</td>
        <td><input name="codeValue" type="text" size="30" id="codeValue"/>
        <span id="hint"></span>
        </td>
      </tr>
      <tr>
        <td height="35">&nbsp;</td>
        <td><input name="submit2" type="button" class="right-button01" value="确认登陆" onclick="submitForm()"  />
          <input name="submit232" type="reset" class="right-button02" value="重 置" /></td>
      </tr>
    </table></td>
  </tr>
</table>
</form>
</body>
</html>