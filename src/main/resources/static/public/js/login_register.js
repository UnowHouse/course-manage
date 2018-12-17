
// 登录按钮的实现
$("#login_btn").click(function () {
    if ( $("#username").val() == ""||
        $("#login_password").val() =="")
    {
        alert("请将信息填写完整");
        return false;
    }
    $.ajax({
        type:"POST",
        url:"manage/login",
        data:{
            username:$("#username").val(),
            password:$("#login_password").val()
        },
        success:function(data){
            window.location.href='manage.html'
        },
        error:function(){
            alert("账号密码错误")
        }
    })
})

//注册按钮的实现
$("#reg_btn").click(function () {
    if ($("#register_username").val() == "" ||
        $("#register_password").val() == "" ||
        $("#register_password_agian").val() == "")
    {
        alert("请将信息填写完整");
        return false;
    }else if ( $("#register_password").val() != $("#register_password_agian").val() ){
        alert("两次密码不一致！")
        return false;
    }
    else{
        $.ajax({
            type:"POST",
            url:"manage/register",
            data:{
                username:$("#register_username").val(),
                password:$("#register_password").val(),
            },
            success:function(data){
                alert("注册成功！")
            },
            error:function(){
                alert("账号已存在！")
            }
        })
    }
})

// 设置高度自适应浏览器视窗
document.getElementsByClassName('box')[0].style.height = document.documentElement.clientHeight + 'px'

/**
 * 页面效果
 */
let input_list = document.getElementsByClassName('input')   // 获取所有input标签
function input_focus(e) {
    e.previousElementSibling.style.animationPlayState = 'running'
    e.nextElementSibling.style.width = '100%'
}
function input_blur(e, value) {
    if (value != '') {
        return
    }
    e.nextElementSibling.style.width = '0'
}
// 绑定获取焦点和失去焦点事件
for (let i of input_list) {
    // animationPlayState控制动画的状态 paused(停止) 和 running(开始)
    i.previousElementSibling.style.animationPlayState = 'paused'
    i.setAttribute('onfocus', 'input_focus(this)')
    i.setAttribute('onblur', 'input_blur(this, this.value)')
}
// 页面切换
let reg_link = document.getElementById('to_reg')
let login_link = document.getElementById('to_login')
reg_link.addEventListener('click', function() {
    login_link.parentElement.parentElement.classList.remove('fadeOut')
    login_link.parentElement.parentElement.classList.add('fadeIn')
    this.parentElement.parentElement.classList.add('fadeOut')
    this.parentElement.parentElement.classList.remove('fadeIn')
    // 重新绑定焦点事件
    for (let i of input_list) {
        i.value = ''
        // animationPlayState控制动画的状态 paused(停止) 和 running(开始)
        i.previousElementSibling.style.animationPlayState = 'paused'
        i.setAttribute('onfocus', 'input_focus(this)')
        i.setAttribute('onblur', 'input_blur(this, this.value)')
    }
})
login_link.addEventListener('click', function() {
    reg_link.parentElement.parentElement.classList.remove('fadeOut')
    reg_link.parentElement.parentElement.classList.add('fadeIn')
    this.parentElement.parentElement.classList.add('fadeOut')
    this.parentElement.parentElement.classList.remove('fadeIn')
    // 重新绑定焦点事件
    for (let i of input_list) {
        i.value = ''
        // animationPlayState控制动画的状态 paused(停止) 和 running(开始)
        i.previousElementSibling.style.animationPlayState = 'paused'
        i.setAttribute('onfocus', 'input_focus(this)')
        i.setAttribute('onblur', 'input_blur(this, this.value)')
    }
})
