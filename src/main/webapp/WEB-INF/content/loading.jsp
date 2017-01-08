<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>loading</title>
  <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
  <script src="//code.jquery.com/jquery-1.9.1.js"></script>
  <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
  <link rel="stylesheet" href="http://jqueryui.com/resources/demos/style.css">
    	<link rel="stylesheet" type="text/css" href="assets/css/animsition.min.css">
  	<link rel="stylesheet" type="text/css" href="assets/css/style.css">
  	<link rel="stylesheet" type="text/css" href="assets/css/index.css"> 
  <style>
	  .ui-progressbar {
	    position: relative;
	    height: 1.8em;
	  }
	  .progress-label {
	    position: absolute;
	    left: 50%;
	    top: 4px;
	    font-weight: bold;
	    text-shadow: 1px 1px 0 #fff;
	  }
	  #progressbar {
	  	width: 80%;
	  	position: absolute;
	  	left: 50%;
	  	top: 30%;
	  	transform: translate(-50%,-30%);
	  }
  </style>
  <script>
  $(function() {
    var progressbar = $( "#progressbar" ),
      progressLabel = $( ".progress-label" );
 
    progressbar.progressbar({
      value: false,
      change: function() {
        progressLabel.text( progressbar.progressbar( "value" ) + "%" );
      },
      complete: function() {
        progressLabel.text( "完成！" );
      }
    });
    var value = 0;
 	var name = "${jobName}";
 	var id = "${jobId}";
    function progress() {
        $.ajax({  
            type:"post",//请求方式  
            url:"getProgressValueByJson",//发送请求地址 
            data:{progressValue:value},
            timeout:30000,//超时时间：30秒  
            dataType:"json",//设置返回数据的格式  
            //请求成功后的回调函数 data为json格式  
            success:function(data){  
            	value = data.progressValue
               if(data.progressValue>=100){  
            	   window.location.href='http://192.168.1.45/play.html';
               }
               progressbar.progressbar( "value", data.progressValue );
               if ( data.progressValue <= 99 ) {
                   setTimeout( progress, 1400 );
                 }
           }, 
    	});
    };
 
    setTimeout( progress, 1 );
  });
  </script>
</head>
<body>
 
<div id="progressbar"><div class="progress-label">加载...</div></div>
 
</body>
</html>