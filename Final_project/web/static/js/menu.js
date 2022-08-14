layui.use(['jquery', 'form', 'table', 'element','layer'], function() {
    var form = layui.form;
    let layer = layui.layer;
    var table = layui.table;
    var element = layui.element;
    let $ = layui.jquery;
    $(document).on('click', 'h1', function(data) {
        var fname=$(this).attr("data-value");
        layer.open({
            offset: 'lt',
            type: 2,
            title: '菜品介绍',
            shadeClose: true,
            shade: false,
            maxmin: true, //开启最大化最小化按钮
            area: ['893px', '600px'],
            content: '/Final_project/foodInfoServlet?action=findFood&fname='+fname
        });
    });
    $(document).on('click', 'img', function(data) {
        var fname=$(this).attr("data-value");
        // alert(fname);
        $.ajax({
            type: 'post', url: '/Final_project/OrderServlet', data: {
                'fname': fname,
                'action': 'addorder'
            }, success: function (result) {
                if(result === 'succ'){
                    layer.msg('添加成功，请前往 订单管理--我的订单 中查看');
                }else {
                    layer.msg('添加失败，请稍后再试');
                }
            }
        })
    });
});