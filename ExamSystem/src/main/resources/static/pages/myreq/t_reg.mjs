import {requests}  from "./http.mjs";
//const admin_token = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlSWQiOjEsIm5hbWUiOiJoaSIsImlkIjoyLCJleHAiOjIxNTcxNzY4MjJ9.VHiy2uE0V19JbdYhb6DgWJbYXp-2O5HcJ2LP6QO5QHs";
var token;

// 密码强度判断函数
function checkPasswordStrength(password) {
    // 密码长度至少为8位
    if (password.length < 6) {
        return "密码长度至少为6位";
    }

    // 密码包含至少一个大写字母、一个小写字母和一个数字
    if (!/[A-Z]/.test(password) || !/[a-z]/.test(password) || !/[0-9]/.test(password)) {
        return "密码必须包含至少一个大写字母、一个小写字母和一个数字";
    }

    // 密码强度合格
    return "strong";
}

document.getElementById("regist-botton").addEventListener("click", function() {
    event.preventDefault()
    var form = document.getElementById("registrationForm");
    var username = form.elements["username"].value;
    var password = form.elements["password"].value;
    var password2 = form.elements["password2"].value;
    var number = form.elements["number"].value;
    var phone = form.elements["phone"].value;
    var email = form.elements["email"].value;
    var idnumber = form.elements["idnumber"].value;

    var passwordStrength = checkPasswordStrength(password);
    if (passwordStrength !== "strong") {
        alert("密码强度不够：" + passwordStrength);
        return;
    }
    if(password!=password2)
    {
        alert("两次输入密码不相等！请重新输入");
        return;
    }
    else if(username=="")
    {
        alert("用户名不能为空！");
        return;
    }
    else if(password==""||password2=="")
    {
        alert("密码不能为空！");
        return;
    }
    else if(number==""||phone==""||email==""||idnumber=="")
    {
        alert("请填写所有信息。");
        return;
    }
    else{
    var data = {
      name: username,
      password: password,
      number: number,
      phone: phone,
      email: email,
      idnumber: idnumber
    };

    let obj1={
        path:"/teacher",
        method:"POST",
        token:null,
        data:data
    }
    requests(obj1).then(function(data){
        console.log(data);
        console.log(data.code);
        if (data.code === 2003051) {
            alert("注册成功！");
            window.location.href = "./teacher_login.html";
        } 
        else {
            alert("操作失败，请重试或联系管理员。");
        }
    });}
});