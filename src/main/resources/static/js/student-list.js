/**
 * 学生列表
 */
layui.use(['form' ,'table' ,'layer', 'laydate'], function() {
    var $ = layui.$;
    var _table = layui.table,_form = layui.form,_layer= layui.layer,_laydate = layui.laydate;

    function init() {
        //年选择器
        _laydate.render({
            elem: '#year'
            ,type: 'year'
            ,trigger: 'click'
        });
        // 单行监听
        _table.on('tool(student)', function(obj){
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
        _table.on('toolbar(student)',function(obj){
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
            _table.reload('student', {
                page: {
                    layout: ['prev','page', 'next','count',],
                },where: {
                    name : data.field.name,
                    collegeName : data.field.collegeName,
                    majorName : data.field.majorName,
                    year : data.field.year,
                    schoolNumber : data.field.schoolNumber,
                    page : '1'
                }
            });
            return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
        });
    };
    function add() { //新增
        _layer.open({
            title : '新增学生',
            type : 2,
            area: ['800px', '500px'],
            end: function(){
                reload();
            },
            content : '/studentInfo/addOrUpdateStudentPage'
        })
    };
    function edit(id) { //编辑
        _layer.open({
            title : "编辑学生",
            type : 2,
            area: ['800px', '500px'],
            end: function(){
                reload();
            },
            content : '/studentInfo/addOrUpdateStudentPage?id='+ id
        });
    };
    function remove(ids) { //删除
        if(!$.isArray(ids)) {
            ids = [ids];
        }
        _layer.confirm('确认是否删除？', {
            btn: ['确认','取消'] //按钮
        }, function(){
            $.post("/majorInfo/removeMajor", {ids:ids}, function(res){
                if(res.code == "SUCCESS"){
                    _layer.msg(res.message,{icon: 1});
                }else{
                    _layer.msg(res.message,{icon: 2});
                }
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
        _table.reload('student',{
            page: {
                layout: ['prev','page', 'next','count',],
            },where: {
                condition
            }
        });
    };

    $(function() {
        var flag = false;
        _table.init('student', {
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
//时间转换函数
function showTime(tempDate)
{
    if(tempDate == null){
        return '';
    }
    var d = new Date(tempDate);
    var year = d.getFullYear();
    var month = d.getMonth();
    month++;
    var day = d.getDate();
    var hours = d.getHours();
    var minutes = d.getMinutes();
    var seconds = d.getSeconds();
    month = month<10 ? "0"+month:month;
    day = day<10 ? "0"+day:day;
    hours = hours<10 ? "0"+hours:hours;
    minutes = minutes<10 ? "0"+minutes:minutes;
    seconds = seconds<10 ? "0"+seconds:seconds;
    var time = year+"-"+month+"-"+day+" "+hours+":"+minutes+":"+seconds;
    return time;
};