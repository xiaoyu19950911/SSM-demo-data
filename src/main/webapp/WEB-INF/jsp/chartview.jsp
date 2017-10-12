<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<script src="../resources/js/jquery/2.0.0/jquery.min.js"></script>
<script src="../resources/highchart/highcharts.js"></script>
<link href="resources/css/bootstrap/3.3.6/bootstrap.min.css"
	rel="stylesheet">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>饼图</title>
</head>
<body>
<div id="container" style="width: 550px; height: 400px; margin: 0 auto"></div>
<script language="JavaScript">
$(document).ready(function() {  
   var chart = {
       plotBackgroundColor: null,
       plotBorderWidth: null,
       plotShadow: false
   };
   var title = {
      text: 'Browser market shares at a specific website, 2014'   
   };      
   var tooltip = {
      pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
   };
   var plotOptions = {
      pie: {
         allowPointSelect: true,
         cursor: 'pointer',
         dataLabels: {
            enabled: false           
         },
         showInLegend: true
      }
   };
   var series= [{
      type: 'pie',
      name: 'Browser share',
      data: [
         ['Firefox',   45.0],
         ['IE',       26.8],
         {
            name: 'Chrome',
            y: 12.8,
            sliced: true,
            selected: true
         },
         ['Safari',    8.5],
         ['Opera',     6.2],
         ['Others',   0.7]
      ]
   }];     
      
   var json = {};   
   json.chart = chart; 
   json.title = title;     
   json.tooltip = tooltip;  
   json.series = series;
   json.plotOptions = plotOptions;
   $('#container').highcharts(json);  
});
</script>
</body>
</html>