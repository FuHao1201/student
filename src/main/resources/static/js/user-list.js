/**
 * 用户列表
 */
layui.use(['form' ,'table' ,'layer', 'laytpl'], function() {
    var $ = layui.$;
    var _table = layui.table,_form = layui.form,_layer= layui.layer;

    function init() {
        // 单行监听
        _table.on('tool(user)', function(obj){
            var event = obj.event;
            var data = obj.data;
            if(event == 'reload') {
                obj = {page: 1}; //查询从首页开始
                reload(obj);
            }
            if(event == 'update'){
                edit(data.id);
            }
            if(event == 'remove'){
                remove(data.id);
            }
        });
        //工具栏监听
        _table.on('toolbar(user)',function(obj){
            console.log(obj)
            var event = obj.event;
            var data = obj.data;
            if(event == 'add'){
                add();
            }
            if(event == 'removeBatch'){
                removeBatch(obj);
            }
        });
        //查询点击监听
        _form.on('submit(SearchForm)', function(data){
            _table.reload('user', {
                page: {
                    layout: ['prev','page', 'next','count',],
                },where: {
                    userName : data.field.userName,
                    loginName : data.field.loginName,
                    tel : data.field.tel,
                    page : '1'
                }
            });
            return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
        });
        //监听启用禁用开关操作
        _form.on('switch(enableFlag)', function(obj){
            let enableFlag = obj.value;
            let id = obj.elem.id;
            let data = {
                id : id,
                enableFlag :enableFlag
            }
            $.post("/userInfo/updateEnableFlag",data,function (res) {
                if(res.code == "SUCCESS"){
                    _layer.msg(res.message,{icon: 1});
                    $("#"+id).val(res.data);
                }else{
                    _layer.msg(res.message,{icon: 2});
                    reload();
                }
            })
        });
    };
    function add() { //新增
        _layer.open({
            title : '新增用户',
            type : 2,
            area: ['600px', '320px'],
            end: function(){
                reload();
            },
            content : '/userInfo/addOrUpdateUserPage'
        })
    };
    function edit(id) { //编辑
        _layer.open({
            title : "编辑用户",
            type : 2,
            area: ['600px', '320px'],
            end: function(){
                reload();
            },
            content : '/userInfo/addOrUpdateUserPage?id='+ id
        });
    };
    function remove(ids) { //删除
        if(!$.isArray(ids)) {
            ids = [ids];
        }
        _layer.confirm('确认是否删除？', {
            btn: ['确认','取消'] //按钮
        }, function(){
            $.post("/userInfo/removeUser", {ids:ids}, function(res){
                var msg = res.message;
                _layer.msg(msg, {icon: 1});
                reload();
            })
        }, function(){
            _layer.close();
        });
    };
    function removeBatch(obj) { // 批量删除
        var checkStatus = _table.checkStatus(obj.config.id)
            , data = checkStatus.data //获取选中的数据
            , ids = [];
        $.each(data, function(i,val){
            ids.push(val.id);
        });
        if(ids.length == 0) {
            _layer.msg('请选择至少一条数据！', {icon: 0});
            return;
        }
        remove(ids);
    };
    function reload(condition) { // 重载列表
        _table.reload('user',{
            page: {
                layout: ['prev','page', 'next','count',],
            },where: {
                condition
            }
        });
    };

    $(function() {
        var flag = false;
        _table.init('user', {
            parseData: function(res){ //res 即为原始返回的数据
                console.log(res)
                if(res.data == null){
                    //显示无数据提示内容
                    return {
                        "code": 201, //解析接口状态
                        "msg": '未查到数据' //解析提示文本
                    };
                }
                return {
                    "code": res.code, //解析接口状态
                    "msg": res.message, //解析提示文本
                    "count": res.data.total, //解析数据长度
                    "data": res.data.records, //解析数据列表
                };
            }
        });
        init();
    });
})
