<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="resources/js/jquery/2.0.0/jquery.min.js"></script>
<link href="resources/css/bootstrap/3.3.6/bootstrap.min.css"
	rel="stylesheet">
<script src="resources/js/bootstrap/3.3.6/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function validateForm()
{
  var y=document.forms["myForm"]["pIdcard"].value;
  y = y.toUpperCase();
  //身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X。   
  if (!(/(^\d{15}$)|(^\d{17}([0-9]|X)$)/.test(y))) {
      alert('输入的身份证号长度不对，或者号码不符合规定！\n15位号码应全为数字，18位号码末位可以为数字或X。');
      return false;
  }
  //校验位按照ISO 7064:1983.MOD 11-2的规定生成，X可以认为是数字10。 
  //下面分别分析出生日期和校验位 
  var len, re;
  len = y.length;
  if (len == 15) {
      re = new RegExp(/^(\d{6})(\d{2})(\d{2})(\d{2})(\d{3})$/);
      var arrSplit = y.match(re);

      //检查生日日期是否正确 
      var dtmBirth = new Date('19' + arrSplit[2] + '/' + arrSplit[3] + '/' + arrSplit[4]);
      var bGoodDay;
      bGoodDay = (dtmBirth.getYear() == Number(arrSplit[2])) && ((dtmBirth.getMonth() + 1) == Number(arrSplit[3])) && (dtmBirth.getDate() == Number(arrSplit[4]));
      if (!bGoodDay) {
          alert('输入的身份证号里出生日期不对！');
          return false;
      }
      else {
          //将15位身份证转成18位 
          //校验位按照ISO 7064:1983.MOD 11-2的规定生成，X可以认为是数字10。 
          var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
          var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2');
          var nTemp = 0, i;
          y = y.substr(0, 6) + '19' + y.substr(6, y.length - 6);
          for (i = 0; i < 17; i++) {
              nTemp += y.substr(i, 1) * arrInt[i];
          }
          y += arrCh[nTemp % 11];
          return true;
      }
  }
  if (len == 18) {
      re = new RegExp(/^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X)$/);
      var arrSplit = y.match(re);

      //检查生日日期是否正确 
      var dtmBirth = new Date(arrSplit[2] + "/" + arrSplit[3] + "/" + arrSplit[4]);
      var bGoodDay;
      bGoodDay = (dtmBirth.getFullYear() == Number(arrSplit[2])) && ((dtmBirth.getMonth() + 1) == Number(arrSplit[3])) && (dtmBirth.getDate() == Number(arrSplit[4]));
      if (!bGoodDay) {
          alert(dtmBirth.getYear());
          alert(arrSplit[2]);
          alert('输入的身份证号里出生日期不对！');
          return false;
      }
      else {
          //检验18位身份证的校验码是否正确。 
          //校验位按照ISO 7064:1983.MOD 11-2的规定生成，X可以认为是数字10。 
          var valnum;
          var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
          var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2');
          var nTemp = 0, i;
          for (i = 0; i < 17; i++) {
              nTemp += num.substr(i, 1) * arrInt[i];
          }
          valnum = arrCh[nTemp % 11];
          if (valnum != y.substr(17, 1)) {
              alert('18位身份证的校验码不正确！'); //应该为： + valnum
              return false;
          }
          return true;
      }
  }
  return false;
}
</script>
</head>
<body>
	<div
		style="position: absolute; top: 0px; left: 0px; width: 100%; height: 87%; border: 1px solid #000">
		<datalist id="itemlist">
			<option>item1</option>
			<option>item2</option>
		</datalist>
		<form action="/demo/person/${re}" name="myForm" onsubmit="return validateForm()">
			<table class="table table-hover" align="center">
				<tr>
					<td>姓名：</td>
					<td><input type="text" name="pName" value="${p.pName}" required="required"></td>
				</tr>
				<tr>
					<td>性别：</td>
					<td><input type="radio" name="pSex" value="男" checked>男
						<input type="radio" name="pSex" value="女">女</td>
				</tr>
				<tr>
					<td>身份证：</td>
					<td><input type="text" name="pIdcard" value="${p.pIdcard}" required="required"></td>
				</tr>
				<!-- <tr>
					<td>年龄：</td>
					<td><input type="text" name="pAge" value="${p.pAge}"></td>
				</tr>
				<tr>
					<td>出生日期：</td>
					<td><input type="text" readonly="readonly" name="pBirth"
						value="${p.pBirth}"></td>
				</tr>
				 -->
				<tr>
					<td>地址：</td>
					<td><input type="text" name="pAddress" value="${p.pAddress}"></td>
				</tr>
				<tr>
					<td>学历：</td>
					<td>
					<select name="pGrade">
					<option value="专科（高职）">专科（高职）</option>
					<option value="本科">本科</option>
					<option value="硕士">硕士</option>
					<option value="博士">博士</option>
					</select>
					<!--  <input type="text" name="pGrade" value="${p.pGrade}">--></td>
				</tr>
				<tr>
					<td>类型：</td>
					<td>
					<select name="pType">
					<option value="0">在编人员</option>
					<option value="1">减少人员</option>
					<option value="2">离退人员</option>
					</select>
					<!--  <input type="text" name="pType" value="${p.pType}" list="itemlist">--></td>
					
				</tr>
				<tr>
					<td><input type="submit" value="提交"></td>
					<td><input type="reset" value="重置"></td>
				</tr>
				<tr>
					<td colspan="2" style="display: none"><input type="text"
						name="pId" value="${p.pId}"></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>