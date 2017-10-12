<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<script src="../resources/jquery-easyui-1.5.3/jquery.min.js"></script>
<script src="../resources/highchart/highcharts.js"></script>
<script src="../resources/jquery-easyui-1.5.3/jquery.easyui.min.js"></script>
<script
	src="../resources/jquery-easyui-1.5.3/locale/easyui-lang-zh_CN.js"></script>
<link href="../resources/jquery-easyui-1.5.3/themes/icon.css"
	rel="stylesheet">
<link href="../resources/jquery-easyui-1.5.3/themes/default/easyui.css"
	rel="stylesheet">
<head>
<title>DataGrid 数据表格</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
</head>
<script type="text/javascript">
var realpath="<%=path%>";

	/**
	 * 前端点击上传触发js事件
	 * @date: 2017-10-12
	 * @author:肖宇
	 * @return
	 * @教程：https://www.2cto.com/kf/201611/563034.html
	 * @要点：注意url路径
	 */
	//上传按钮触发事件
	function uploadBtn() {
		var uploadEventFile = $("#uploadEventFile").val();
		if (uploadEventFile == '') {
			alert("请选择excel,再上传");
		} else if (uploadEventFile.lastIndexOf(".xls") < 0) {//可判断以.xls和.xlsx结尾的excel
			alert("只能上传Excel文件");
		} else {
			var url = '/demo/person/upload';
			var formData = new FormData($('form')[0]);
			user.sendAjaxRequest(url, 'POST', formData);
		}
	}

	//上传文件
	var User = function() {
		this.init = function() {
			//模拟上传excel
			$("#uploadEventBtn").unbind("click").bind("click", function() {
				$("#uploadEventFile").click();
			});
			$("#uploadEventFile").bind(
					"change",
					function() {
						$("#uploadEventPath").attr("value",
								$("#uploadEventFile").val());
					});

		};

		this.sendAjaxRequest = function(url, type, data) {
			$.ajax({
				url : url,
				type : type,
				data : data,
				success : function(result) {
					alert(result);
				},
				error : function() {
					alert("excel上传失败");
				},
				cache : false,
				contentType : false,
				processData : false
			});
		};
	}

	var user;
	$(function() {
		user = new User();
		user.init();
	});

	/*以下为无效内容
	//点击上传按钮
	this.uploadBtn = function() {
		var uploadEventFile = $("#uploadEventFile").val();
		if (uploadEventFile == '') {
			alert("请选择excel,再上传");
		} else if (uploadEventFile.lastIndexOf(".xls") < 0) {//可判断以.xls和.xlsx结尾的excel
			alert("只能上传Excel文件");
		} else {
			var url = '/demo/person/upload/';
			var formData = new FormData($('form')[0]);
			user.sendAjaxRequest(url, 'POST', formData);
		}
	};
	 */

	/**
	 * 前端esayui中datagrid控件
	 * @date: 2017-10-12
	 * @author:肖宇
	 * @return
	 * @教程：http://www.jeasyui.net/tutorial/147.html
	 * @throws IOException
	 */
	//设置分页控件 
	var p = $('#list_data').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,//每页显示的记录条数，默认为10 
		pageList : [ 5, 10, 15 ],//可以设置每页记录条数的列表 
		beforePageText : '第',//页数文本框前显示的汉字 
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录',
	/*onBeforeRefresh:function(){
	    $(this).pagination('loading');
	    alert('before refresh');
	    $(this).pagination('loaded');
	}*/
	});

	//新增人员
	function newUser() {
		$('#dlg').dialog('open').dialog('setTitle', '增加');
		$('#fm').form('clear');
		url = '/demo/person/addperson';
	}

	//编辑人员
	function editUser() {
		var row = $('#dg').datagrid('getSelected');
		if (row) {
			$('#dlg').dialog('open').dialog('setTitle', 'Edit User');
			$('#fm').form('load', row);
			url = '/demo/person/update?pId=' + row.id;
		}
	}

	//保存人员信息
	function saveUser() {
		$('#fm').form('submit', {
			url : url,
			onSubmit : function() {
				return $(this).form('validate');
			},
			success : function(result) {
				var result = eval('(' + result + ')');
				//if (result.errorMsg) {
				//$.messager.show({
				//title : 'Error',
				//msg : result.errorMsg
				//});
				//} else {
				$('#dlg').dialog('close'); // close the dialog
				$('#dg').datagrid('reload'); // reload the user data
				//}
			}
		});
	}

	//删除人员信息
	function destroyUser() {
		var row = $('#dg').datagrid('getSelected');
		if (row) {
			$.messager.confirm('Confirm',
					'Are you sure you want to destroy this user?', function(r) {
						if (r) {
							$.post('destroy_user.php', {
								id : row.id
							}, function(result) {
								if (result.success) {
									$('#dg').datagrid('reload'); // reload the user data
								} else {
									$.messager.show({ // show error message
										title : 'Error',
										msg : result.errorMsg
									});
								}
							}, 'json');
						}
					});
		}
	}
</script>
<body>
	<!-- esayui的表格控件 -->
	<!-- 教程：http://www.jeasyui.net/tutorial/147.html -->
	<!-- 要点：url路径的配置生成表单信息 -->
	<table id="dg" title="人员信息" class="easyui-datagrid"
		style="width: 550px; height: 250px" url="/demo/person/showAllTrainee"
		toolbar="#toolbar" rownumbers="true" fitColumns="true"
		singleSelect="true" pagination="true" rownumbers="true"
		iconCls="icon-edit" collapsible="false">
		<thead>
			<tr>
				<th field="p_name" width="50">姓名</th>
				<th field="p_sex" width="50">性别</th>
				<th field="p_idcard" width="50">身份证</th>
				<th field="p_age" width="50">年龄</th>
				<th field="p_birth" width="50">出生日期</th>
				<th field="p_address" width="50">籍贯</th>
				<th field="p_grade" width="50">最高学历</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true"
			onclick="newUser()">增加</a> <a href="#" class="easyui-linkbutton"
			iconCls="icon-edit" plain="true" onclick="editUser()">编辑</a> <a
			href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true"
			onclick="destroyUser()">删除</a> <a href="/demo/person/OutputExcel"
			class="easyui-linkbutton" iconCls="icon-remove" plain="true"
			onclick="">导出数据到excel</a> <a href="#" class="easyui-linkbutton"
			iconCls="icon-remove" plain="true" onclick="" id="uploadEventBtn">从excel导入数据</a>
	</div>

	<!-- 上传excel数据 -->
	<form enctype="multipart/form-data" id="batchUpload"
		action="/demo/person/upload" method="post" class="form-horizontal">
		<input type="file" name="file" id="uploadEventFile">
	</form>
	<button type="button" class="btn btn-success btn-sm"
		onclick="uploadBtn()">上传</button>

	<!-- esayui的增加窗口div -->
	<div id="dlg" class="easyui-dialog"
		style="width: 400px; height: 280px; padding: 10px 20px" closed="true"
		buttons="#dlg-buttons">
		<div class="ftitle">User Information</div>
		<form id="fm" method="post">
			<div class="fitem">
				<label>姓名:</label> <input name="pName" class="easyui-validatebox"
					required="true">
			</div>
			<div class="fitem">
				<label>性别:</label> <input name="pSex" class="easyui-validatebox"
					required="true">
			</div>
			<div class="fitem">
				<label>身份证:</label> <input name="pIdcard" required="true">
			</div>
			<div class="fitem">
				<label>籍贯:</label> <input name="pAddress" class="easyui-validatebox">
			</div>
			<div class="fitem">
				<label>最高学历:</label> <input name="pGrade" class="easyui-validatebox">
			</div>
		</form>
	</div>
	<div id="dlg-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
			onclick="saveUser()">Save</a> <a href="#" class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">Cancel</a>
	</div>



	<!-- 以下内容为饼图控件highchart -->
	<!-- 教程地址：http://www.runoob.com/highcharts/highcharts-pie-legends.html -->
	<!-- 要点：将series从controller中以json形式shuchu -->
	<div id="container"
		style="width: 550px; height: 400px; margin: 0 auto; right: 0px">
		<script language="JavaScript">
			$(document)
					.ready(
							function() {
								var series = "";
								$.ajax({
									url : realpath + "/person/chart",
									type : "post",
									data : "",//要传递的数据
									async : false,//异步或同步
									dataType : "json",
									contentType : "application/json",
									success : function(d) {
										series = [ {
											type : d.type,
											name : d.name,
											data : d.data
										} ]; //c为map的键值
									}
								});

								/*
								  var series= [{
								     type: 'pie',
								     name: 'Browser share',
								     data: [
								        ['Firefox',   1],
								        ['IE',       1],
								        {
								           name: 'Chrome',
								           y: 1,
								           sliced: true,
								           selected: true
								        },
								        ['Safari',    1],
								        ['Opera',    1],
								        ['Others',  1]
								     ]
								  }];  
								 */

								var chart = {
									plotBackgroundColor : null,
									plotBorderWidth : null,
									plotShadow : false
								};
								var title = {
									text : '学历的分布情况'
								};
								var tooltip = {
									pointFormat : '{series.name}: <b>{point.percentage:.1f}%</b>'
								};
								var plotOptions = {
									pie : {
										allowPointSelect : true,
										cursor : 'pointer',
										dataLabels : {
											enabled : false
										},
										showInLegend : true
									}
								};

								var json = {};
								json.chart = chart;
								json.title = title;
								json.tooltip = tooltip;
								json.series = series;
								json.plotOptions = plotOptions;
								$('#container').highcharts(json);
							});
		</script>
	</div>
</body>
</html>