var yggl_chartColors = [
    '#ff5f86',
	'#7fc0ff',
    '#40a05c',
    '#61bc76',
    '#ffd664',
    '#ffbf6b'
];

/**
 * 各类图表
 */
function yggl_loadygglChart(){
	var yg_code=yggl_SelectygNode.code;
	var singleDate ="";
	var startDate ="";
	var endDate ="";
	var radioType = $("#ygkh").find("input[type='radio']:checked").val();
	radioType=2;
	if(radioType == "1"){
					 $("#ygkh").find("#choiceTime").validatebox({validType:'date'}); 
		singleDate = $("#ygkh").find("#choiceTime").datebox("getValue"); 
	}else{
		startDate =  $("#ygkh").find("#choiceStartTime").datebox("getValue");
		endDate =  $("#ygkh").find("#choiceEndTime").datebox("getValue"); 
	}
	yggl_loadgzrzChart(yg_code,singleDate,startDate,endDate);
	yggl_loadrwqkChart(yg_code,singleDate,startDate,endDate);
	//yggl_loadrwqkChart2(yg_code,singleDate,startDate,endDate);
}

/**
 * 工作日志情况
 */
function yggl_loadgzrzChart(yg_code,singleDate,startDate,endDate){
	
   $.ajax2({
	    type:"POST",
	    dataType:"json",
	    url:"index.php?r=gzrz/get-gzrzchart",
	    data:{"yg_code":yg_code,"singleDate":singleDate,"startDate":startDate,"endDate":endDate},
		success:function(json){
			var data = json.data;
			var ctype = json.ctype;
			var serieDatas = [];
			var tmpSer = {};
			var tmpObj = {};
			for(var i=0; i<ctype.length; i++){
				tmpObj = ctype[i];
				tmpSer = {};
				tmpSer['name'] = data['N'+tmpObj['ctype']]['name'];
				//过滤多余字符串
				tmpSer['y'] = parseFloat(data['N'+tmpObj['ctype']]['count']);
				tmpSer['description'] = parseFloat(data['N'+tmpObj['ctype']]['count']);
				serieDatas.push(tmpSer);
			}
			//alert(JSON.stringify(serieDatas));
			
			$('#gzrz_chart').highcharts({
		        chart: {plotBackgroundColor: null,plotBorderWidth: null,plotShadow: false},
		        //colors:chartColor1,
		        title: {text: '日志情况'},
		        tooltip: { headerFormat: '',pointFormat: '{point.name}: <b>{point.percentage:.2f}%（{point.description}）</b>'},//（{point.description}）
		        plotOptions: {
		            pie: {
		                allowPointSelect: true,
		                cursor: 'pointer',
		                dataLabels: {
		                   enabled: true,
		                    format: '<b>{point.name}</b>:{point.percentage:.2f}%（{point.description}）',//（{point.description}）
//		                    connectorWidth: 0,
                        	distance: 10,
		                    style: {
		                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
		                    }
		                },
		                colors:yggl_chartColors,
		                events: {
				        	click: function(e) {
		                    	var title=e.point.name;
				        	}
				        }
		            }
		        },
		        series: [{ type: 'pie',name: '',data: serieDatas}]
		    });
		}
	});
}

/**
 * 任务完成情况
 */
function yggl_loadrwqkChart(yg_code,singleDate,startDate,endDate){
	
   $.ajax2({
	    type:"POST",
	    dataType:"json",
	    url:"index.php?r=rw/get-rwchart",
	    data:{"yg_code":yg_code,"singleDate":singleDate,"startDate":startDate,"endDate":endDate},
		success:function(json){
			var data = json.data;
			var ctype = json.ctype;
			var serieDatas = [];
			var tmpSer = {};
			var tmpObj = {};
			for(var i=0; i<ctype.length; i++){
				tmpObj = ctype[i];
				tmpSer = {};
				tmpSer['name'] = data['N'+tmpObj['ctype']]['name'];
				//过滤多余字符串
				tmpSer['y'] = parseFloat(data['N'+tmpObj['ctype']]['count']);
				tmpSer['description'] = parseFloat(data['N'+tmpObj['ctype']]['count']);
				serieDatas.push(tmpSer);
			}
			$('#gzrz_chart2').highcharts({
		        chart: {plotBackgroundColor: null,plotBorderWidth: null,plotShadow: false},
		        //colors:chartColor1,
		        title: {text: '任务完成情况'},
		        tooltip: { headerFormat: '',pointFormat: '{point.name}: <b>{point.percentage:.2f}%（{point.description}）</b>'},//（{point.description}）
		        plotOptions: {
		            pie: {
		                allowPointSelect: true,
		                cursor: 'pointer',
		                dataLabels: {
		                   enabled: true,
		                    format: '<b>{point.name}</b>:{point.percentage:.2f}%（{point.description}）',//（{point.description}）
//		                    connectorWidth: 0,
                        	distance: 10,
		                    style: {
		                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
		                    }
		                },
		                colors:yggl_chartColors,
		                events: {
				        	click: function(e) {
		                    	var title=e.point.name;
				        	}
				        }
		            }
		        },
		        series: [{ type: 'pie',name: '',data: serieDatas}]
		    });
		}
	});
}

/**
 * 任务完成情况
 */
function yggl_loadrwqkChart2(yg_code,singleDate,startDate,endDate){
	
   $.ajax2({
	    type:"POST",
	    dataType:"json",
	    url:"index.php?r=gzrz/get-gzrzchart",
	    data:{"yg_code":yg_code,"singleDate":singleDate,"startDate":startDate,"endDate":endDate},
		success:function(json){
			var data = json.data;
			var ctype = json.ctype;
			var serieDatas = [];
			var tmpSer = {};
			var tmpObj = {};
			for(var i=0; i<ctype.length; i++){
				tmpObj = ctype[i];
				tmpSer = {};
				tmpSer['name'] = data['N'+tmpObj['ctype']]['name'];
				//过滤多余字符串
				tmpSer['y'] = parseFloat(data['N'+tmpObj['ctype']]['count']);
				serieDatas.push(tmpSer);
			}
			$('#gzrz_chart3').highcharts({
		        chart: {plotBackgroundColor: null,plotBorderWidth: null,plotShadow: false},
		        //colors:chartColor1,
		        title: {text: '日志分析'},
		        tooltip: { headerFormat: '',pointFormat: '{point.name}: <b>{point.percentage:.2f}%</b>'},//（{point.description}）
		        plotOptions: {
		            pie: {
		                allowPointSelect: true,
		                cursor: 'pointer',
		                dataLabels: {
		                   enabled: true,
		                    format: '<b>{point.name}</b>:{point.percentage:.2f}%',//（{point.description}）
//		                    connectorWidth: 0,
                        	distance: 10,
		                    style: {
		                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
		                    }
		                },
		                colors:yggl_chartColors,
		                events: {
				        	click: function(e) {
		                    	var title=e.point.name;
				        	}
				        }
		            }
		        },
		        series: [{ type: 'pie',name: '',data: serieDatas}]
		    });
		}
	});
}



/**
 * 导出岗位Excel
 */
function hgper_exportPostInfo(){
	$("input[name='exportInfo']").val(JSON.stringify(selCondition['exportInfo']));
  	$("input[name='exportTitle']").val(selCondition['exportTitle']);
  	$("#hgper_exportExcel").submit();
}

/**
 * 各区县事业单位分布情况
 */
function hgper_cmpDistribution(){
	$.ajax2({
	    type:"POST",
	    dataType:"json",
	    url:"index.php?r=hgperson/cmp-distribution",
	    data:{"selCondition":JSON.stringify(selCondition)},
		success:function(data){
			var datainfo = [];
			for(var key in data){
				datainfo.push(parseInt(data[key]));
			}
		    $('#hgperson_cmpdis_chart').highcharts({
		        chart: {type: 'column'},
		        title: {text: '各区人员分布情况'},
		        xAxis: {
		            categories: ['市直管','静安区','徐汇区','黄浦区','普陀区','长宁区','杨浦区','浦东新区','虹口区','闵行区','宝山区','嘉定区','松江区','青浦区','奉贤区','金山区','崇明县','自贸区'],
		            crosshair: true
		        },
		        //colors:hgper_chartColors,
		        yAxis: {min: 0,title: {text: '人数'}},
		        plotOptions: {
		            series: {
		                borderWidth: 0,
		                dataLabels: {
		                    enabled: true,
		                    format: '{point.y}'
		                }
		            },
		            column: {
			      		cursor: 'pointer',
				      	events: {
				        	click: function(e) {
				        		selCondition['areaName'] = e.point.category;
				            	openGqkpage();
				        	}
				        }
			      	}
		        },
		        tooltip: {pointFormat: '人数：{point.y}'},
		        series: [{ name: '各区人员分布情况',data: datainfo}]
		    });
    	}
	});
}


