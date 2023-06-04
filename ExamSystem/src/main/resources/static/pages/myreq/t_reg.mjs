import {requests}  from "./http.mjs";
const admin_token = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlSWQiOjEsIm5hbWUiOiJoaSIsImlkIjoyLCJleHAiOjIxNTcxNzY4MjJ9.VHiy2uE0V19JbdYhb6DgWJbYXp-2O5HcJ2LP6QO5QHs";
var token;

document.getElementById("regist-botton").addEventListener("click", function() {
    event.preventDefault()
    var form = document.getElementById("registrationForm");
    var username = form.elements["username"].value;
    var password = form.elements["password"].value;
    var number = form.elements["number"].value;
    var phone = form.elements["phone"].value;
    var email = form.elements["email"].value;
    var idnumber = form.elements["idnumber"].value;

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
        token:admin_token,
        data:data
    }
    requests(obj1).then(function(data){
        console.log(data);
        console.log(data.code);
        if (data.code === 2003051) {
            alert("注册成功！");
            // 跳转到另一个界面
            window.location.href = "./teacher.html";
        } 
        else {
            alert("操作失败，请重试或联系管理员。");
        }
    });
});