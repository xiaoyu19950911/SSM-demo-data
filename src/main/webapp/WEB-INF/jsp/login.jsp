<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="resources/js/jquery/2.0.0/jquery.min.js"></script>
<link href="resources/css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
<script src="resources/js/bootstrap/3.3.6/bootstrap.min.js"></script>
<meta charset="UTF-8">
<title>登录页面</title>
<style>
body {
	margin-left: auto;
	margin-right: auto;
	margin-TOP: 100PX;
	width: 20em;
}
</style>
<script type="text/javascript">
function validateForm()
{
  var x=document.forms["myForm"]["uUsername"].value;
  var y=document.forms["myForm"]["uPassword"].value;
  if (x==null || x==""||y==null||y=="")
  {
    alert("用户名和密码不能为空！");
    return false;
  }
}
</script>
</head>
<body>
	<form action="/demo/user/login" method="post" onsubmit="return validateForm()" name="myForm">
		<table
			class="table table-striped table-bordered table-hover  table-condensed">
			<tr>
				<td align="right">用户名：</td>
				<td align="left"><input type="text" class="form-control"
					name="uUsername"></td>
			</tr>
			<tr>
				<td align="right">密码：</td>
				<td align="left"><input type="password" class="form-control"
					name="uPassword"></td>
			</tr>
			<tr>
				<td><input type="submit" value="登录" class="btn btn-primary"></td>
				<td><input type="button" value="退出" class="btn btn-danger"></td>
			</tr>
		</table>

	</form>
</body>
</html>