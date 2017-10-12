<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<script src="../resources/js/jquery/2.0.0/jquery.min.js"></script>
<link href="../resources/css/bootstrap/3.3.6/bootstrap.min.css"
	rel="stylesheet">
<script src="../resources/js/bootstrap/3.3.6/bootstrap.min.js"></script>
<script type="text/javascript"
	src="../resources/js/jquery/ztree/jquery.ztree.core.js"></script>
<script type="text/javascript"
	src="../resources/js/jquery/ztree/jquery.ztree.excheck.js"></script>
<script type="text/javascript"
	src="../resources/js/jquery/ztree/jquery.ztree.exedit.js"></script>
<head>
<script type="text/javascript">
function myFunction(){
	var r=confirm("你确定要删除这条数据吗!");
	if (r==true){
		return true;
	}
	else{
		return false;
	}
}

</script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div
		style="position: absolute; top: 0px; left: 0px; width: 100%; height: 84%; border: 1px solid #000">


		<form action="/demo/person/OutputExcel/?page=${page.pageNum}"
			method="post">
			<table class="table table-hover" align="center">
				<thead>
					<tr>
						<th>序号</th>
						<th>姓名</th>
						<th>性别</th>
						<th>身份证</th>
						<th>年龄</th>
						<th>出生日期</th>
						<th>籍贯</th>
						<th>最高学历</th>
						<th>操作</th>
					</tr>
				</thead>
				<c:forEach items="${cs}" var="c" varStatus="st">
					<tbody>
						<tr>
							<!-- 序号列不变 -->
							<td>${st.index+1}</td>
							<td>${c.pName}</td>
							<td>${c.pSex}</td>
							<td>${c.pIdcard}</td>
							<td>${c.pAge}</td>
							<td>${c.pBirth}</td>
							<td>${c.pAddress}</td>
							<td>${c.pGrade}</td>
							<td><div class="dropdown">
									<button type="button" class="btn dropdown-toggle"
										id="dropdownMenu1" data-toggle="dropdown">
										操作 <span class="caret"></span>
									</button>
									<ul class="dropdown-menu" role="menu"
										aria-labelledby="dropdownMenu1">
										<li><a href="/demo/person/add">增加</a></li>
										<li role="presentation"><a href="/demo/person/deleteperson?pId=${c.pId}" onclick="javascript:return myFunction()">删除</a></li>
										<li role="presentation"><a href="/demo/person/update?pId=${c.pId}">修改</a></li>
									</ul>
								</div></td>
						</tr>
					</tbody>
				</c:forEach>
			</table>
			<div
				style="position: absolute; bottom: 0px; text-align: center; left: 320px"
				align="center">
				<a href="/demo/person/${list}?page=1">首 页</a> <a
					href="/demo/person/${list}?page=${page.prePage}">上一页</a> <a
					href="/demo/person/${list}?page=${page.nextPage}">下一页</a> <a
					href="/demo/person/${list}?page=${page.pages}">末 页</a>
				<p>${page.pageNum}/${page.pages}</p>
				<input type="submit" value="导出到excel" class="btn btn-success"
					style="bottom: 0px">
			</div>
		</form>
	</div>
</body>
</html>