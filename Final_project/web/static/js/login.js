layui.use(['form', 'layer', 'util'], function () {

    let $ = layui.jquery;
    let layer = layui.layer;
    let form = layui.form;
    var util = layui.util;

    // 提前隐藏注册窗口
    $("#img2").hide();
    $("#form2").hide();

    // 展示注册窗口
    $("#goregist").click(function () {
        // $("#loginpage").fadeOut();
        // 隐藏
        $("#img1").hide();
        $("#form1").hide();

        // 出现
        $("#img2").show();
        $("#form2").show();

        // 修改页面大小
        $("#loginpage").css({height: "400px"});
        //更换验证码
        $("#regist_img").attr('src', '/Final_project/kaptcha.jpg?d=' + new Date());
    })
    //展示登录页面
    $("#gologin").click(function () {
        // $("#loginpage").fadeOut();
        // 隐藏
        $("#img1").show();
        $("#form1").show();

        // 出现
        $("#img2").hide();
        $("#form2").hide();

        // 修改页面大小
        $("#loginpage").css({height: "350px"});
        //更换验证码
        $("#login_img").attr('src', '/Final_project/kaptcha.jpg?d=' + new Date());
    })

    // 更换验证码图片
    $("#login_img").click(function () {
        this.src = "/Final_project/kaptcha.jpg?d=" + new Date();
    });
    $("#regist_img").click(function () {
        this.src = "/Final_project/kaptcha.jpg?d=" + new Date();
    });

    //检查注册信息
    form.on('submit(regist)', function (data) {
        var formData = data.field;
        $.ajax({
            type: 'post', url: '/Final_project/UserServlet', data: {
                'username': formData.username,
                'userpass': formData.userpass,
                'phone': formData.phone,
                'imgcode': formData.imgcode,
                'action': 'adduser'
            }, success: function (result) {
                if (result === '添加成功') {
                    layer.alert("注册成功\n请登录", {icon: 1, title: '注册成功'}, function () {
                        window.parent.location.reload();
                    });
                } else {
                    layer.alert(result, {icon: 2, title: '注册失败'});
                    if (result === '用户名不可用') {
                        $("#regist_username").val("");
                    }
                    $("#regist_imgcode").val("");
                    $("#regist_img").attr('src', '/Final_project/kaptcha.jpg?d=' + new Date());
                }
            }
        })
        return false
    })
    form.on('submit(login)', function (data) {
        var formData = data.field;
        $.ajax({
            type: 'post', url: '/Final_project/UserServlet', data: {
                'username': formData.username,
                'userpass': formData.userpass,
                'imgcode': formData.imgcode,
                'action': 'login'
            }, success: function (result) {
                $("#login_img").attr('src', '/Final_project/kaptcha.jpg?d=' + new Date());
                $("#login_imgcode").val("");
                if (result === '登录成功') {
                    layer.alert("登录成功", {icon: 1, title: '登录成功'}, function () {

                        // window.parent.location.reload();
                        window.location.href = "/Final_project/index.jsp";
                    })
                }else {
                    layer.alert(result, {icon: 2, title: '登录失败'});
                    if(result==='用户不存在'){
                        $("#login_username").val("");
                        $("#login_userpass").val("");
                    }else if(result==='密码错误'){
                        $("#login_userpass").val("");
                    }
                }
            }
        })
        return false
    })
})
