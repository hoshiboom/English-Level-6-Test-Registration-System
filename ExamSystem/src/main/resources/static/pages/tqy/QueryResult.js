// 请求前缀url
const baseUrl = "http:\/\/hoshiboom.space";
var cur_token;
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
    myHeaders.append("Access-Control-Allow-Origin", '*');

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

//获取管理员权限先
function DefaultCall()
{
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
    requests(obj0).then(function(data)
    {
        console.log(data);
        if(data.code == 2001041)//登录成功，获取当前token
        {
            cur_token=data.token;
        }
        else
        {
            console.log("管理员权限获取错误");
            alert("网页加载错误");
        }
    });
}

function querySubmit() {
    var tempname = document.getElementById("xm");
    var name = tempname.value;
    var tempid = document.getElementById("sfz");
    var sfzid = tempid.value;
    if(name==''||sfzid=='')
    {
        if(name=='')
        {
            alert("请输入名称");
            return;
        }
        alert("请输入身份证号");
        return;
    }
    //判断姓名合法性
    const pattern = /^[\u4e00-\u9fa5]+$/;
    //判断一下身份证输入规格正确性
    var regex = /^\d+[X\d]?$/;
    if (!pattern.test(name))
    {
        alert("姓名必须只包含中文！");
        tempname.value='';
        tempid.value='';
        return;
    }
    if (!regex.test(sfzid))
    {
        alert("身份证号码必须为任意多个数字组成且最后一位可能为X或数字的字符串！");
        tempname.value='';
        tempid.value='';
        return;
    }
    let obj1=
        {
            path:"/studentByIDNumber",
            method:"GET",
            mode : "cors",
            token:cur_token,//用管理员账户查询学生身份证号
            data:{
                idNumber : sfzid
            }
        }
    let responseData;
    requests(obj1).then(function(data)
    {
        responseData=data;
        console.log(data);
        if(data.code != 2003021)
        {
            alert("查询失败，请重新确认输入");
            tempname.value='';
            tempid.value='';
        }
        else//查询成功
        {
            if(data.data.name!=tempname.value)
            {
                alert("输入的姓名与身份证段不匹配，请重新输入");
                return;
            }
            console.log("查询成功");
            const params = new URLSearchParams();
            params.append('responseId', responseData.data.id);
            const url = './ResultShow.html?' + params.toString();
            window.open(url,'_self');
        }
    });





}