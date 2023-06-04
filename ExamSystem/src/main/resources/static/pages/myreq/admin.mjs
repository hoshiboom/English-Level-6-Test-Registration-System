import {requests}  from "./http.mjs";
const admin_token = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlSWQiOjEsIm5hbWUiOiJoaSIsImlkIjoyLCJleHAiOjIxNTg1MDI0ODh9.ZHjqCg-bjcDjfwRQemntQ2UjNu87XVl32EjsbsASV10";
var token;

document.getElementById("login-button").addEventListener("click", function() {
    event.preventDefault()
    var form = document.getElementById("login-form");
    var number = form.elements["number"].value;
    var password = form.elements["password"].value;

    var data = {
    number: number,
    password: password
    };
    let obj1={
        path:"/login/admin",
        method:"POST",
        token:null,
        data:data
    }
    requests(obj1).then(function(data){
        console.log(data);
        console.log(data.code);
        if (data.code === 2001041) {
            // 将 token 声明在全局作用域中
            token = data.token;
            // 跳转到另一个界面
            window.location.href = "./admin.html";
        } else {
            // 不是2001041则弹出提示框
            alert("操作失败，请重试或联系管理员。");
        }
    });
});
  
// document.getElementById("regist-botton").addEventListener("click", function() {
//     event.preventDefault()
//     var form = document.getElementById("registrationForm");
//     var username = form.elements["username"].value;
//     var password = form.elements["password"].value;
//     var number = form.elements["number"].value;
//     var phone = form.elements["phone"].value;
//     var email = form.elements["email"].value;
//     var idnumber = form.elements["idnumber"].value;

//     var data = {
//       username: username,
//       password: password,
//       number: number,
//       phone: phone,
//       email: email,
//       idnumber: idnumber
//     };

//     let obj1={
//         path:"/admin",
//         method:"POST",
//         token:token,
//         data:data
//     }
//     requests(obj1).then(function(data){
//         console.log(data);
//         console.log(data.code);
//         if (data.code === 2001041) {
//             alert("注册成功！");
//             // 跳转到另一个界面
//             window.location.href = "./admin.html";
//         } else {
//             // 不是2001041则弹出提示框
//             alert("操作失败，请重试或联系管理员。");
//         }
//     });
// });