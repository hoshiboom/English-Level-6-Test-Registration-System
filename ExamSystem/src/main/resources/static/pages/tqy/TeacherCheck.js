// 请求前缀url
const baseUrl = "http:\/\/hoshiboom.space";

/*
* 传参：object
* obj.path: 请求路径
* obj.method: 请求方法 默认get
* obj.data: 请求参数 默认为空
* obj.token: 根据用户实际token设置
* */
function requests(obj) {
    let method;
    if(obj.method){
        obj.method = obj.method.toUpperCase();
        method=obj.method;
    }
    else{
        method="GET";
    }

    var urlencoded = new URLSearchParams();
    // get请求的data参数拼接到url后面，post等其他请求的data参数放到body
    if (obj.method === "GET") {
        if(obj.data){
            let ec = encodeURIComponent;                       // URL编码
            let queryParams = Object.keys(obj.data)         // 获取data键名
                .map(k => `${ec(k)}=${ec(obj.data[k])}`)    // 将键名和值连接起来
                .join('&');
            if(queryParams) obj.path += `?${queryParams}`;
            console.log(obj.path)
        }
    } else {
        for(var key in obj.data) {
            urlencoded.append(key,obj.data[key]);
        }
    }

    var myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/x-www-form-urlencoded");
    myHeaders.append("token", obj.token);
    //myHeaders.append("Content-Type","application/json")

    var requestOptions = {
        method: method,
        headers: myHeaders,
        redirect: 'follow'
    };

    if(method!=="GET" && method!=="HEAD"){
        requestOptions.body=urlencoded
    }

    console.log(requestOptions);
    // 发送请求并返回 promise 对象，注意 fetch不会拦截400/500等异常请求️，只有网络不通时才会失败
    return fetch(`${baseUrl}${obj.path}`, requestOptions).then(function(response){
        return response.json();
    }).then(function(data) {
        return new Promise(function(resolve, reject){
            //console.log(data);
            const global_code=parseInt(data.code.toString().substring(0,3));
            if (global_code!==400&&global_code!==500) {
                //响应码不是400，500，返回数据
                resolve(data)
            } else {
                reject("请求失败：" + data.code.toString()+data.msg);
            }

        })
    }).catch(function(error) {
        console.log('error', error);
    })
}

function requests1111(obj,studentId,state) {
    let method;
    if(obj.method){
        obj.method = obj.method.toUpperCase();
        method=obj.method;
    }
    else{
        method="GET";
    }

    var urlencoded = new URLSearchParams();
    // get请求的data参数拼接到url后面，post等其他请求的data参数放到body
    if (obj.method === "GET") {
        if(obj.data){
            let ec = encodeURIComponent;                       // URL编码
            let queryParams = Object.keys(obj.data)         // 获取data键名
                .map(k => `${ec(k)}=${ec(obj.data[k])}`)    // 将键名和值连接起来
                .join('&');
            if(queryParams) obj.path += `?${queryParams}`;
            console.log(obj.path)
        }
    } else {
        for(var key in obj.data) {
            urlencoded.append(key,obj.data[key]);
        }
    }

    var myHeaders = new Headers();
    myHeaders.append("token", obj.token);
    myHeaders.append("Content-Type","application/json")

    var requestOptions = {
        method: method,
        headers: myHeaders,
        redirect: 'follow'
    };
    //修改
    var raw = JSON.stringify({
        "studentId": studentId,
        "state": state
    });

    if(method!=="GET" && method!=="HEAD"){
        requestOptions.body=raw
    }

    console.log(requestOptions);
    // 发送请求并返回 promise 对象，注意 fetch不会拦截400/500等异常请求️，只有网络不通时才会失败
    return fetch(`${baseUrl}${obj.path}`, requestOptions).then(function(response){
        return response.json();
    }).then(function(data) {
        return new Promise(function(resolve, reject){
            //console.log(data);
            const global_code=parseInt(data.code.toString().substring(0,3));
            if (global_code!==400&&global_code!==500) {
                //响应码不是400，500，返回数据
                resolve(data)
            } else {
                reject("请求失败：" + data.code.toString()+data.msg);
            }

        })
    }).catch(function(error) {
        console.log('error', error);
    })
}

function ChooseInsert()//确认按钮实现
{
    //const teacher_token = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlSWQiOjIsIm5hbWUiOiIyIiwiaWQiOjEsImV4cCI6MTY4Nzc0NTE2OX0.ZbfnYXjYw3gPvfIG6N85_4j3_B-4m3vY_eNnXzeT72E";
    var TempTranslationScore=document.getElementById("TranslationScore");
    var TranslationScore=TempTranslationScore.value;//老师翻译题评分
    var TempArticleScore=document.getElementById("ArticleScore");
    var ArticleScore=TempArticleScore.value;//老师作文题评分
    //以教师身份登录
    let obj0=
        {
            path:"/login/teacher",
            method:"POST",
            token : null ,
            data:{
                number : 2,
                password : 2
            }
        }
    var cur_teacher_token;
    requests(obj0).then(function(data)//首先以教师身份登录
    {
        console.log(data);
        if(data.code == 2001041)//登录成功，获取当前token
        {
            cur_teacher_token=data.token;
            //console.log(cur_teacher_token);
            var raw = JSON.stringify({
                "studentId": 1,
                "state": 2
            });
            let obj1=
                {
                    path:"/doandcheckByCondition",
                    method:"POST",
                    mode : "cors",
                    token:cur_teacher_token,//用老师账户获取
                    body:raw
                }
            let ResponseData1;
            //按行获取doandcheck表中state为3（翻译）/4（作文）的student_answer字段，
            requests1111(obj1,1,3).then(function(data)
            {
                ResponseData1=data;
                console.log(data);
                if(data.code==2008021)//查询作答成功
                {


                }
                else
                {
                    alert("查询作答失败");
                }

            });
        }
        else
        {
            alert("查询权限错误");
        }
    });


}