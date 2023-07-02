import {requests}  from "./http.mjs";
const stu_token = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlSWQiOjMsIm5hbWUiOiIxIiwiaWQiOjEsImV4cCI6MTY4ODMxNzk2Nn0.ZkhpRT5PydBfUFGmdRcEyUCW0UiGkdNiUyHVmnxY6X0";
var token;

document.getElementById("sign-button").addEventListener("click", function()
{
    event.preventDefault();
    var form = document.getElementById("registration-form");
    var studentID = form.elements["studentID"].value;
    var paperinfoID = 1;

    var data = {
        studentID: studentID,
        paperinfoID: paperinfoID
    };
    let obj1 = {
        path: "/signup",
        method: "POST",
        token: stu_token,
        data: data
    }
    requests(obj1).then(function(data) {
        console.log(data);
        console.log(data.code);
        if (data.code === 2007051) {
            // 将 token 声明在全局作用域中
            token = data.token;

            //获取表单数据
            var name = document.getElementById('name').value;
            var studentID = document.getElementById('studentID').value;

            // 创建URL参数
            var params = '?name=' + encodeURIComponent(name) +
                '&studentID=' + encodeURIComponent(studentID) ;

            // 清空页面内容
            document.body.innerHTML = '';

            // 创建报名成功提示元素
            var successMessage = document.createElement('h2');
            successMessage.textContent = '报名成功！现在跳转至缴费页面...';
            document.body.appendChild(successMessage);

            // 创建倒计时元素
            var countdown = document.createElement('h2');
            countdown.textContent = '3';
            countdown.style.textAlign = 'center';
            document.body.appendChild(countdown);

            // 倒计时函数
            function startCountdown() {
                var count = 3;
                var timer = setInterval(function ()
                {
                    countdown.textContent = count;
                    count--;
                    if (count < 0) {
                        clearInterval(timer);
                        window.location.href = 'pay.html'+ params; // 跳转到缴费页面
                    }
                }, 1000);
            }

            // 启动倒计时
            startCountdown();

        } else {
            // 不是2001041则弹出提示框
            alert("操作失败，请重试或联系管理员。");
        }
    });
});
