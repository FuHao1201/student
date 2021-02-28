/** 
  * 首页 
  */ 
layui.use(['form' ,'layer' ,'element'], function() {
	var _form = layui.form,_layer = layui.layer,_element = layui.element; 
    var $ = layui.$;
    function init(){
    	getDate();
    	getLoginName();
    };
    function getDate(){//获取当前日期
    	var date = new Date();
    	var str = "" + date.getFullYear() + "年";
    	str += (date.getMonth()+1) + "月";
    	str += date.getDate() + "日";
    	$("#date").html("当前日期："+str);
   };
   function getLoginName(){//获取用户信息
	   $.get("/userInfo/getLoginUser",{},function(res){
		   console.log(res)
		   let head = '/images/head.gif';
		   if (res.data.avatar != null && res.data.avatar != ''){
		   		head = res.data.avatar;
		   }
		   $("#name").html("<img src="+head+" class='layui-nav-img'>"+res.data.userName);
	   })
   };
   $(function(){
	   	init();
	});
}) 
