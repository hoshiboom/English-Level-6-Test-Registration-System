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

// export {requests};
function ChooseInsert() {
    const admin_token = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlSWQiOjEsIm5hbWUiOiJoaSIsImlkIjoyLCJleHAiOjIxNTg1MDI0ODh9.ZHjqCg-bjcDjfwRQemntQ2UjNu87XVl32EjsbsASV10";
    var typeselect=document.getElementById("types");
    var ValueTestType=typeselect.selectedIndex;//type
    var choosename=document.getElementById("testname");
    var ValueTestName=choosename.value;//name
    var choosedescription=document.getElementById("testdescription");
    var ValueTestDescription=choosedescription.value;//description
    var choiceA=document.getElementById("testdescriptionA");
    var ValueTestChoiceA=choiceA.value;
    var choiceB=document.getElementById("testdescriptionB");
    var ValueTestChoiceB=choiceB.value;
    var choiceC=document.getElementById("testdescriptionC");
    var ValueTestChoiceC=choiceC.value;
    var choiceD=document.getElementById("testdescriptionD");
    var ValueTestChoiceD=choiceD.value;
    var answers=document.getElementById("testanswer");
    var ValueTestAnswer=answers.value;//answer
    var scoresinput=document.getElementById("testscore");
    var ValueTestScore=scoresinput.value;//score
    //some bugs here
    //alert("111111");
    let obj1={
        path:"/question",
        method:"POST",
        token:admin_token,//用管理员账户添加
        data:{
            questionName:ValueTestName,
            questionDescription:ValueTestDescription,
            optionA:ValueTestChoiceA,
            optionB:ValueTestChoiceB,
            optionC:ValueTestChoiceC,
            optionD:ValueTestChoiceD,
            answer:ValueTestAnswer,
            typeId:ValueTestType,
            score:ValueTestScore
        }
    }

    requests(obj1).then(function(data)
    {
        console.log(data);
        console.log(data.date);
    });
    console.log("request submit");
}