// 请求前缀url
const baseUrl = "http:\/\/hoshiboom.space";
var cur_token;//登陆后token
var translation_nums=0;//DefaultCall后得到的当前要批改的翻译题个数
var article_nums=0;//DefaultCall后得到的当前要批改的作文题个数
var translation_array=[];//记录获取的结果
var article_array=[];//记录获取的结果
var TranslationToCheck=0;//批改下标
var ArticleToCheck=0;
var NoSubmitTranslation=false;
var NosubmitArticle=false;//true代表不该提交
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
            //console.log(obj.path)
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

    //console.log(requestOptions);
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

//该函数在打开此页面时默认调用
function DefaultCall()
{
    //step1：以管理员身份登录
    let obj0=
        {
            path:"/login/admin",
            method:"POST",
            token : null ,
            mode : "cors",
            data:{
                number : 2012618,
                password : 123456
            }
        }
    requests(obj0).then(function(data)//首先以管理员身份登录
    {
        //console.log(data);
        if(data.code == 2001041)//登录成功，获取当前token
        {
            console.log("登录成功");
            //step2：向后台发送一次请求，获取返回的要批改的翻译题和作文题数目
            cur_token=data.token;
            //console.log(cur_token);
            let objtranslation=
            {
                path:"/check",
                method:"GET",
                token : cur_token ,
                mode : "cors",
                data:{
                    type : 3, //翻译题
                    state : 1 //未批改
                }
            }
            //console.log(objtranslation.token);
            requests(objtranslation).then(function (data)
            {
                //记录当前要批改的翻译题数目和学生答案
                console.log(data);
                translation_nums=data.data.records.length;
                for(var i=0;i<translation_nums;i++)
                {
                    translation_array.push(data.data.records[i]);
                }
                showTranslation(TranslationToCheck);
                console.log("translation_nums : "+translation_nums);
                console.log("TranslationToCheck : "+TranslationToCheck);
            });
            let objArticle=
                {
                    path:"/check",
                    method:"GET",
                    token : cur_token ,
                    mode : "cors",
                    data:{
                        type : 4, //作文题
                        state : 1 //未批改
                    }
                }
            requests(objArticle).then(function (data)
            {
                //记录当前要批改的作文题数目和学生答案
                console.log(data);
                article_nums=data.data.records.length;
                //console.log(article_nums);
                for (var i=0;i<article_nums;i++)
                {
                    article_array.push(data.data.records[i]);
                }
                showArticle(ArticleToCheck);
                console.log("article_nums : "+article_nums);
                console.log("ArticleToCheck : "+ArticleToCheck);
            });
        }
        else
        {
            alert("查询权限错误");
        }
    });

}

//用于具体展示
function showTranslation(TranslationToShow)
{
    //TranslationToShow,ArticleToShow为要展示的题目下标
    //判断一下是否还有没改的数据
    if(TranslationToShow>=translation_nums)
    {
        var translationData = "您已批改完成所有翻译题";
        var textres=document.getElementById("TranslationResult");
        textres.value=translationData;
        NoSubmitTranslation=true;
        var tempscore=document.getElementById("TranslationScore");
        tempscore.readOnly=true;
    }
    else//还有要批改的数据
    {
        //step1：
        var translationData=translation_array[TranslationToShow].studentAnswer;
        var textres=document.getElementById("TranslationResult");
        textres.value=translationData;
        TranslationToCheck+=1;
        console.log("After showTranslation,TranslationToCheck is "+TranslationToCheck);
    }
}

function showArticle(ArticleToShow)
{
    if(ArticleToShow>=article_nums)
    {
        var ArticleData = "您已批改完成所有作文题";
        var textres=document.getElementById("ArticleResult");
        textres.value=ArticleData;
        NosubmitArticle=true;
        var tempscore=document.getElementById("ArticleScore");
        tempscore.readOnly=true;
    }
    else
    {
        var ArticleData=article_array[ArticleToShow].studentAnswer;
        var textres=document.getElementById("ArticleResult");
        textres.value=ArticleData;
        ArticleToCheck+=1;
        console.log("After showArticle,ArticleToCheck is "+ArticleToCheck);
    }
}

//确认按钮实现
function ChooseInsert()
{
    // console.log("开始调用一次");
    if(TranslationToCheck<= translation_nums || ArticleToCheck <= article_nums)
    {
        var TempTranslationScore=document.getElementById("TranslationScore");
        var TranslationScore=TempTranslationScore.value;//老师翻译题评分
        var TempArticleScore=document.getElementById("ArticleScore");
        var ArticleScore=TempArticleScore.value;//老师作文题评分
        if(TranslationScore==''||ArticleScore=='')
        {
            if(TranslationScore=='')
            {
                if(TranslationToCheck<translation_nums)
                {
                    alert("未批改翻译题");
                    return;
                }
                else
                {
                    NoSubmitTranslation=true;
                }
            }
            if(ArticleScore=='')
            {
                if(ArticleToCheck < article_nums)
                {
                    alert("未批改作文题");
                    return;
                }
                else
                {
                    NosubmitArticle=true;
                }
            }
        }
        if(TranslationScore<0 || TranslationScore >=106.5)
        {
            alert("翻译题目分数应当在0~106.5");
            return;
        }
        if(ArticleScore<0 || ArticleScore >=106.5)
        {
            alert("作文题目分数应当在0~106.5");
            return;
        }
        if(NoSubmitTranslation==false)
        {
            //将结果更新
            var translationupdatepath="/doandcheck/"+translation_array[TranslationToCheck-1].id.toString();
            //console.log("index is"+TranslationToCheck-1);
            //console.log("id is"+translation_array[TranslationToCheck-1].id.toString());
            let translationobj=
                {
                    path:translationupdatepath,
                    method:"PUT",
                    token : cur_token ,
                    mode : "cors",
                    data:{
                        studentId : translation_array[TranslationToCheck-1].studentId,
                        paperinfoId : translation_array[TranslationToCheck-1].paperinfoId,
                        questionId : translation_array[TranslationToCheck-1].questionId,
                        studentAnswer : translation_array[TranslationToCheck-1].studentAnswer,
                        actualScore : parseFloat(TranslationScore),
                        state : 2 //已经批改了，就应该修改为2了
                    }
                }
            requests(translationobj).then(function(data)
            {
                console.log(data);
                //console.log("index is"+TranslationToCheck-1);
                //console.log("id is"+translation_array[TranslationToCheck-1].id.toString());
                if(data.code==2008041)
                {
                    console.log("翻译题批改成功");
                    //每次调用都需要再展示一下下一道需要批改的题目
                    showTranslation(TranslationToCheck);
                    var TempTranslationScore=document.getElementById("TranslationScore");
                    TempTranslationScore.value='';
                }
                else
                {
                    //未提交成功
                    alert("翻译题批改数据提交失败，请重新尝试");
                }
            });
        }
        //同样对待作文题
        if(NosubmitArticle==false)
        {
            var articleupdatepath="/doandcheck/"+article_array[ArticleToCheck-1].id.toString();
            let articleobj=
                {
                    path:articleupdatepath,
                    method:"PUT",
                    token : cur_token ,
                    mode : "cors",
                    data:{
                        studentId : article_array[ArticleToCheck-1].studentId,
                        paperinfoId : article_array[ArticleToCheck-1].paperinfoId,
                        questionId : article_array[ArticleToCheck-1].questionId,
                        studentAnswer : article_array[ArticleToCheck-1].studentAnswer,
                        actualScore : parseFloat(ArticleScore),
                        state : 2 //已经批改了，就应该修改为2了
                    }
                }
            requests(articleobj).then(function(data)
            {
                console.log(data);
                if(data.code==2008041)
                {
                    console.log("作文题批改成功");
                    //每次调用都需要再展示一下下一道需要批改的题目
                    showArticle(ArticleToCheck);
                    var TempArticleScore=document.getElementById("ArticleScore");
                    //清空输入数据
                    TempArticleScore.value='';
                }
                else
                {
                    //未提交成功
                    alert("作文题批改数据提交失败，请重新尝试");
                }
            });
        }
    }
    // console.log("结束调用一次");
}
