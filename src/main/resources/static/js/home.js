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
	$("#myInfo").on('click',function(){//个人资料点击监听
		_layer.open({
			type: 2,
			area: ['36%' , '40%'],
			resize: false,
			content: '/userInfo/myInfoPage',
		});
	});
	$("#changePass").on('click',function(){//修改密码点击监听
		_layer.open({
			type: 2,
			area: ['34%' , '40%'],
			resize: false,
			content: '/userInfo/changePassPage',
		});
	});
    function getDate(){//获取当前日期
    	var date = new Date();
    	var str = "" + date.getFullYear() + "年";
    	str += (date.getMonth()+1) + "月";
    	str += date.getDate() + "日";
    	$("#date").html("当前日期："+str);
   };
   function getLoginName(){//获取用户信息
	   $.get("/userInfo/getLoginUser",{},function(res){
		   let head = '/images/head.gif';
		   if (res.data != null){
			   if (res.data.avatar != null && res.data.avatar != ''){
				   head = res.data.avatar;
			   }
			   $("#name").html("<img src="+head+" class='layui-nav-img'>"+res.data.userName);
		   }else {
			   $("#mine").hide();
		   }
	   })
   };
   $(function(){
	   	init();
	});
}) 
