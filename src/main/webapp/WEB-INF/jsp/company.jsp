<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<script src="jquery-3.2.1/jquery-3.2.1.min.js"></script>
<link href="css/bootstrap.min.css" rel="stylesheet">
<script src="js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>公司信息</title>
<style>
body {
	margin-left: auto;
	margin-right: auto;
	margin-TOP: 100PX;
	width: 20em;
}
</style>
</head>
<body>
<form action="">
<table align='center' border='1' cellspacing='0'>
    <tr>
        <td>id</td>
        <td>åä½åç§°</td>
        <td>åå­¦ç±»å</td>
        <td>æé«ç®¡ççº§å«</td>
        <td>æ¨æ¬¾å½¢å¼</td>
        <td>å¨èåå·¥äººæ°</td>
        <td>åä½å°å</td>
        <td>æç«æ¶é´</td>
    </tr>
    <c:forEach items="${cs}" var="c" varStatus="st">
        <tr>
            <td>${c.id}</td>
            <td>${c.name}</td>
            <td>${c.type}</td>
            <td>${c.rand}</td>
            <td>${c.btype}</td>
            <td>${c.count}</td>
            <td>${c.address}</td>
            <td>${c.time}</td> 
        </tr>
    </c:forEach>
</table>
</form>
</body>
</html>