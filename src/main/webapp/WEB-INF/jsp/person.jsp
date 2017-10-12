<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path=request.getContextPath();
%>
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

<script type="text/javascript" src="../resources/js/WdatePicker.js"></script>
<meta charset="UTF-8">
  <link rel="stylesheet" href="../resources/css/ztree/zTreeStyle/zTreeStyle.css" type="text/css">
<title>人员信息</title>
<style>
body {
	margin-left: auto;
	margin-right: auto;
	margin-TOP: 100PX;
	width: 20em;
}

p {
	margin-left: 10px;
	margin-top: 10px;
}
</style>
<SCRIPT type="text/javascript" >

	var zTree;
	var demoIframe;
	var realpath="<%=path%>";

	var setting = {
		view: {
			dblClickExpand: false,
			showLine: true,
			selectedMulti: false
		},
		data: {
			simpleData: {
				enable:true,
				idKey: "id",
				pIdKey: "pid",
				rootPId: ""
			}
		},
		callback: {
			beforeClick: function(treeId, treeNode) {
				var zTree = $.fn.zTree.getZTreeObj("tree");
				if (treeNode.isParent) {
					zTree.expandNode(treeNode);
					return false;
				} else {
					demoIframe.attr("src",treeNode.file + ".html");
					return true;
				}
			}
		}
	};
/*
	var zNodes =[
		{id:1, pId:0, name:"上海心源计算机技术有限公司", open:true},
		{id:11, pId:1, name:"在编人员", file:"onlist"},
		{id:12, pId:1, name:"减少人员", file:"relist"},
		{id:13, pId:1, name:"离退人员", file:"outlist"},
		{id:14, pId:1, name:"所有人员", file:"list"},

		{id:2, pId:0, name:"上海心源信息技术有限公司", open:false},
		{id:21, pId:2, name:"在编人员", file:"excheck/checkbox"},
		{id:22, pId:2, name:"减少人员", file:"excheck/checkbox_nocheck"},
		{id:23, pId:2, name:"离退人员", file:"excheck/checkbox_chkDisabled"},

		{id:3, pId:0, name:"上海宏瑞技术有限公司", open:false},
		{id:31, pId:3, name:"在编人员", file:"exedit/drag"},
		{id:32, pId:3, name:"减少人员", file:"exedit/drag_super"},
		{id:33, pId:3, name:"离退人员", file:"exedit/drag_fun"},
		
		{id:4, pId:0, name:"上海稿付信息技术有限公司", open:false},
		{id:41, pId:4, name:"在编人员", file:"bigdata/common"},
		{id:42, pId:4, name:"减少人员", file:"bigdata/diy_async"},
		{id:43, pId:4, name:"离退人员", file:"bigdata/page"},

		{id:5, pId:0, name:"上海大地有限责任公司", open:false},
		{id:51, pId:5, name:"在编人员", file:"super/oneroot"},
		{id:52, pId:5, name:"减少人员", file:"super/oneclick"},
		{id:53, pId:5, name:"离退人员", file:"super/singlepath"},
		
		{id:6, pId:0, name:"上海华革有限责任公司", open:false},
		{id:61, pId:6, name:"在编人员", file:"exhide/common"},
		{id:62, pId:6, name:"减少人员", file:"exhide/checkbox"},
		{id:63, pId:6, name:"离退人员", file:"exhide/radio"}
	];
*/

	$(document).ready(function(){
		$.ajax({
			url:realpath+"/ztree/list",
			type:"post",
			data:"",//要传递的数据
			dataType:"json",
			contentType: "application/json",
			success:function(d){
			var zNodes=d.c;//c为map的键值
			var t = $("#tree");
			t = $.fn.zTree.init(t, setting, zNodes);
			demoIframe = $("#testIframe");
			demoIframe.bind("load", loadReady);
			var zTree = $.fn.zTree.getZTreeObj("tree");
			zTree.selectNode(zTree.getNodeByParam("id", 101));
			}
		});
		
	});

	function loadReady() {
		var bodyH = demoIframe.contents().find("body").get(0).scrollHeight,
		htmlH = demoIframe.contents().find("html").get(0).scrollHeight,
		maxH = Math.max(bodyH, htmlH), minH = Math.min(bodyH, htmlH),
		h = demoIframe.height() >= maxH ? minH:maxH ;
		if (h < 530) h = 530;
		demoIframe.height(h);
	}
  </SCRIPT>
</head>

<body>
	<div
		style="position: absolute; top: 0px; left: 0px; width: 100%; height: 100px; border: 1px solid #000">
		<ul class="nav nav-tabs">
			<li role="presentation" class="active"><a href="/demo/person/listall">人员信息</a></li>
			<li role="presentation"><a href="/demo/person/test">单位信息</a></li>
		</ul>
	</div>
	<div
		style="position: absolute; top: 100px; left: 0px; width: 25%; height: 600px; border: 1px solid #000">
		<ul id="tree" class="ztree" style="width:260px; overflow:auto;"></ul>
	</div>
	<%request.setCharacterEncoding("UTF-8");%> 
	<form action="/demo/person/serchlist"  id="form2" target="testIframe" method="post">
		<div
			style="position: absolute; top: 100px; left: 25%; width: 60%; height: 150px; border: 1px solid #000">

			<div class="input-group input-group">
				<span class="input-group-addon" id="basic-addon1">姓名搜索：</span> <input
					type="text" class="form-control" placeholder="Serch for..."
					aria-describedby="basic-addon1" name="pName" id="pName">
			</div>
			<div class="input-group input-group">
				<span class="input-group-addon" id="basic-addon1">出生日期：</span> 
				<input
					type="text" class="form-control" placeholder="出生日期"
					aria-describedby="basic-addon1" name="pBirth" id="pBirth" onClick="WdatePicker({lang:'zh-cn',dateFmt:'yyyyMMdd'})">
			</div>

		</div>
		<div
			style="position: absolute; top: 100px; right: 0px; width: 15%; height: 150px; border: 1px solid #000; text-align: center">
			<!-- <button type="button" class="btn btn-primary" onclick="ss()">查询</button> -->
		<button type="submit" class="btn btn-primary">查询</button> 
			<button type="reset" class="btn btn-warning">清空</button>
		</div>
	</form>
	<div
		style="position: absolute; top: 250px; right: 0px; width: 75%; height: 450px; border: 1px solid #000">
		
		<IFRAME ID="testIframe" Name="testIframe" FRAMEBORDER=0 SCROLLING=AUTO width=100% height=600px   SRC="test"></IFRAME><!-- 以前是list -->
	</div>



</body>
</html>