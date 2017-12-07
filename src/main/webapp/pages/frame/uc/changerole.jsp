<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
	String path = request.getContextPath();
	String basepath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	//System.out.println("basepath:"+basepath);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basepath %>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>变更用户角色</title>
<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="js/lhgdialog/lhgdialog.js"></script>
<script type="text/javascript">
var DG = frameElement.lhgDG;
DG.addBtn("makesure","确定",makesure);

$(function(){
	$.post("dept/findAllDept",{},function(data){
		$("#userid").attr("value",data[0].userid);
		$("#myName").text(data[0].username);
		for(var i = 1;i<data.length;i++){
			$("#myDept").append("<option value='"+data[i].deptno+"'>"+data[i].dname+"</option>")
		}
	},"json")
})

function makesure(){
	//alert("确定");
	
	//ajax提交请求到后台，完成角色变更的工作
	//后台处理完毕后，在ajax的回调函数中，关闭子窗口：
	//DG.curWin.location.reload();//关闭子窗口并刷新父页面
	DG.cancel();//直接退出子窗口
	
	
}

</script>
</head>
<body >
<form>
	<input type="hidden" value="" name="userid" id="userid" />
	用户名：<span id="myName"></span><br/>
	岗位：
	<select id="myDept" name="deptno">
		
	</select>
	
	
</form>

</body>
</html>