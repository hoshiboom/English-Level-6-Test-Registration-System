import {requests}  from "./http.mjs";
const stu_token = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlSWQiOjMsIm5hbWUiOiIxIiwiaWQiOjEsImV4cCI6MTY4ODI1MzEwOX0.8sVKfnMl4ZGqy22USnVwf2GlJSvrk4BTz5A2bVWRy2E";
var token;
//var amount;

let obj1 = {
    path: "/paperinfo/1",
    method: "GET",
    token: stu_token,
    data: null
}
requests(obj1).then(function(data) {
    console.log(data);
    console.log(data.code);
    console.log(data.data.money);
    if (data.code === 2006021) {
        // 将 token 声明在全局作用域中
        token = data.token;
        //amount= data.data.money;
        document.getElementById('amount').textContent = data.data.money ;
    } else {
        // 不是2001041则弹出提示框
        alert("操作失败，请重试或联系管理员。");
    }
});

// 获取URL参数
function getUrlParameter(name) {
    name = name.replace(/[[]/, '\\[').replace(/[\]]/, '\\]');
    var regex = new RegExp('[\\?&]' + name + '=([^&#]*)');
    var results = regex.exec(location.search);
    return results === null ? '' : decodeURIComponent(results[1].replace(/\+/g, ' '));
}
// 获取参数值并设置到页面元素中
document.getElementById('name').textContent = getUrlParameter('name');
document.getElementById('studentID').textContent = getUrlParameter('studentID');


document.getElementById("pay-button").addEventListener("click", function()
{
    event.preventDefault()
    alert('缴费成功！');
    // 跳转到另一个界面
    window.location.href = "./exam.html";
});