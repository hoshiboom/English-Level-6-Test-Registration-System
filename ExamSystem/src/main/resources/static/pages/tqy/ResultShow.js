// 请求前缀url
const baseUrl = "http:\/\/hoshiboom.space";
var ListenScore = 0;
var totalscore=0;
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

function GetResults()//展示所有的题目
{
    //alert("begin");
    const params = new URLSearchParams(window.location.search);
    let responseId=params.get('responseId');//QueryResult.html传来的学生id
    let responsePassword=params.get('responsePassword');
    if(responseId==null)
    {
        alert("wrong");
    }
    let obj0=
        {
            path:"/login/admin",
            method:"POST",
            token : null ,
            mode : "cors",
            data:{
                'number' : 2012618,
                password : 123456
            }
        }
    var cur_token;
    requests(obj0).then(function(data)
    {
        console.log(data);
        if(data.code == 2001041)//登录成功，获取当前token
        {
            console.log("登录成功");
            cur_token=data.token;
            // var obj1path="/doandcheck/"+responseId.toString();
            // let obj1=
            //     {
            //         path:obj1path,
            //         method:"GET",
            //         token:cur_token,//用管理员账户获取question_id和paperinfo_id
            //         data:{
            //             studentId : responseId,
            //             state:3
            //         }
            //     }
            // let ResponseData1;
            // requests(obj1).then(function(data)
            // {
            //     ResponseData1=data;
            //     console.log(data);
            //     ListenScore=data.data.actualScore;
            //     const resultDiv = document.getElementById("result");
            //     resultDiv.textContent = ListenScore; // 将数字展示在页面中
            // });
            //获取总成绩
            let objTotalScore={
                path:"/score",
                method:"GET",
                mode : "cors",
                token:cur_token,
                data:{
                    studentId:responseId,
                    paperinfoId:2
                }
            }
            requests(objTotalScore).then(function(data)
            {
                console.log(data);
                if(data.code==2009011)//查询总分成功
                {
                    totalscore=data.data.score;
                    const totalDiv=document.getElementById("totalscore");
                    totalDiv.textContent=totalscore;
                }
                else
                {
                    alert("此学生无此试卷成绩");
                }
            })
        }
        else
        {
            alert("查询权限错误");
        }
    });
    //根据student表的id在doandcheck表中获取question_id和paperinfo_id

}
