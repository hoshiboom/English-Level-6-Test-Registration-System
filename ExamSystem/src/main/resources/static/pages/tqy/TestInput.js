// 请求前缀url
const baseUrl = "http:\/\/hoshiboom.space";
var cur_token;
var cur_type=0;//当前录入试题类型
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
        //console.log(data);
        if(data.code == 2001041)//登录成功，获取当前token
        {
            cur_token=data.token;
            console.log("试题录入权限获取成功");
        }
        else
        {
            alert("管理员账户登录失败，无法进行试题录入");
        }
    });
}

// export {requests};
function ChooseInsert()
{
    var typeselect=document.getElementById("types");
    var choosename=document.getElementById("testname");
    var choosedescription=document.getElementById("testdescription");
    var choiceA=document.getElementById("testdescriptionA");
    var choiceB=document.getElementById("testdescriptionB");
    var choiceC=document.getElementById("testdescriptionC");
    var choiceD=document.getElementById("testdescriptionD");
    var answers=document.getElementById("testanswer");
    var scoresinput=document.getElementById("testscore");

    var ValueTestType=typeselect.selectedIndex;//type
    var ValueTestName=choosename.value;//name
    var ValueTestDescription=choosedescription.value;//description
    var ValueTestChoiceA=choiceA.value;
    var ValueTestChoiceB=choiceB.value;
    var ValueTestChoiceC=choiceC.value;
    var ValueTestChoiceD=choiceD.value;
    var ValueTestAnswer=answers.value;//answer
    var ValueTestScore=scoresinput.value;//score

    if(SubmitCheck(ValueTestType))//合乎规范，允许提交
    {
        let obj1={
            path:"/question",
            method:"POST",
            token:cur_token,//用管理员账户添加
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
        console.log(obj1);
        var idreturned;
        requests(obj1).then(function(data)
        {
            console.log(data);
            console.log(data.date);
            idreturned=data.id;
        });
        //console.log("request submit");
        //字段清空操作
        typeselect.selectedIndex=0;
        choosename.value='';
        choosedescription.value='';
        choiceA.value='';
        choiceB.value='';
        choiceC.value='';
        choiceD.value='';
        answers.value='';
        scoresinput.value='';
    }

}

//用于判断不同题目类型时哪些必填、哪些选填
function InputCheck()
{
    var typeselect=document.getElementById("types");
    var ValueTestType=typeselect.selectedIndex;//type
    if(ValueTestType==1)//选择题，所有字段必填
    {
        cur_type=1;
        var choiceA=document.getElementById("testdescriptionA");
        choiceA.disabled=false;
        var choiceB=document.getElementById("testdescriptionB");
        choiceB.disabled=false;
        var choiceC=document.getElementById("testdescriptionC");
        choiceC.disabled=false;
        var choiceD=document.getElementById("testdescriptionD");
        choiceD.disabled=false;
        var answers=document.getElementById("testanswer");
        answers.disabled=false;
        var scoresinput=document.getElementById("testscore");
        choiceA.value='';
        choiceB.value='';
        choiceC.value='';
        choiceD.value='';
        scoresinput.value='';
        scoresinput.disabled=false;
    }
    else if(ValueTestType==2)//填空题，设置选项不可填，分值设定为3.55
    {
        cur_type=2;
        var choiceA=document.getElementById("testdescriptionA");
        choiceA.disabled=true;
        var choiceB=document.getElementById("testdescriptionB");
        choiceB.disabled=true;
        var choiceC=document.getElementById("testdescriptionC");
        choiceC.disabled=true;
        var choiceD=document.getElementById("testdescriptionD");
        choiceD.disabled=true;
        var scoresinput=document.getElementById("testscore");
        scoresinput.value=3;
        scoresinput.disabled=true;

        //给选项设定一下值，免得交不上
        choiceA.value="No value";
        choiceB.value="No value";
        choiceC.value="No value";
        choiceD.value="No value";
    }
    else if(ValueTestType==3)//翻译题，设置选项不可填、分值106.5
    {
        cur_type=3;
        var choiceA=document.getElementById("testdescriptionA");
        choiceA.disabled=true;
        var choiceB=document.getElementById("testdescriptionB");
        choiceB.disabled=true;
        var choiceC=document.getElementById("testdescriptionC");
        choiceC.disabled=true;
        var choiceD=document.getElementById("testdescriptionD");
        choiceD.disabled=true;
        var scoresinput=document.getElementById("testscore");
        scoresinput.value=106;
        scoresinput.disabled=true;
        //给选项设定一下值，免得交不上
        choiceA.value="No value";
        choiceB.value="No value";
        choiceC.value="No value";
        choiceD.value="No value";
    }
    else if(ValueTestType==4)//作文题，设置选项、标答不可填、分值106.5
    {
        cur_type=4;
        var choiceA=document.getElementById("testdescriptionA");
        choiceA.disabled=true;
        var choiceB=document.getElementById("testdescriptionB");
        choiceB.disabled=true;
        var choiceC=document.getElementById("testdescriptionC");
        choiceC.disabled=true;
        var choiceD=document.getElementById("testdescriptionD");
        choiceD.disabled=true;
        var answers=document.getElementById("testanswer");
        answers.disabled=true;
        var scoresinput=document.getElementById("testscore");
        scoresinput.value=106;
        scoresinput.disabled=true;
        //给选项设定一下值，免得交不上
        choiceA.value="No value";
        choiceB.value="No value";
        choiceC.value="No value";
        choiceD.value="No value";
    }
}

//用于提交前进行字段判断
function SubmitCheck(curtype)
{
    console.log("规范性检查");
    var typeselect=document.getElementById("types");
    var choosename=document.getElementById("testname");
    var choosedescription=document.getElementById("testdescription");
    var choiceA=document.getElementById("testdescriptionA");
    var choiceB=document.getElementById("testdescriptionB");
    var choiceC=document.getElementById("testdescriptionC");
    var choiceD=document.getElementById("testdescriptionD");
    var answers=document.getElementById("testanswer");
    var scoresinput=document.getElementById("testscore");

    var ValueTestType=typeselect.selectedIndex;//type
    var ValueTestName=choosename.value;//name
    var ValueTestDescription=choosedescription.value;//description
    var ValueTestChoiceA=choiceA.value;
    var ValueTestChoiceB=choiceB.value;
    var ValueTestChoiceC=choiceC.value;
    var ValueTestChoiceD=choiceD.value;
    var ValueTestAnswer=answers.value;//answer
    var ValueTestScore=scoresinput.value;//score

    var showstring="您未输入";
    var normative=false;//false不规范，不允许提交
    if(curtype==1)
    {
        //选择题，判断所有字段必填
        if(ValueTestType=='')
        {
            showstring+="题目类型";
        }
        if(ValueTestName=='')
        {
            if(showstring!="您未输入")
            {
                showstring+="、";
            }
            showstring+="题目名称";
        }
        if(ValueTestDescription=='')
        {
            if(showstring!="您未输入")
            {
                showstring+="、";
            }
            showstring+="题目具体描述";
        }
        if(ValueTestChoiceA=='')
        {
            if(showstring!="您未输入")
            {
                showstring+="、";
            }
            showstring+="A选项";
        }
        if(ValueTestChoiceB=='')
        {
            if(showstring!="您未输入")
            {
                showstring+="、";
            }
            showstring+="B选项";
        }
        if(ValueTestChoiceC=='')
        {
            if(showstring!="您未输入")
            {
                showstring+="、";
            }
            showstring+="C选项";
        }
        if(ValueTestChoiceD=='')
        {
            if(showstring!="您未输入")
            {
                showstring+="、";
            }
            showstring+="D选项";
        }
        if(ValueTestAnswer=='')
        {
            if(showstring!="您未输入")
            {
                showstring+="、";
            }
            showstring+="该题目正确答案";
        }
        else//答案只能为A/B/C/D
        {
            if(ValueTestAnswer=='A'||ValueTestAnswer=='a')
            {

            }
            else if(ValueTestAnswer=='B'||ValueTestAnswer=='b')
            {

            }
            else if(ValueTestAnswer=='C'||ValueTestAnswer=='c')
            {

            }
            else if(ValueTestAnswer=='D'||ValueTestAnswer=='d')
            {

            }
            else
            {
                showstring="answer type error";
                alert("选择题答案只能为选项之一");
            }
        }
        if(ValueTestScore=='')
        {
            if(showstring!="您未输入")
            {
                showstring+="、";
            }
            showstring+="该题目分值";
        }
        else//选择题判断分值！0~14
        {
            if(parseFloat(ValueTestScore)<=0||parseFloat(ValueTestScore)>14.2)
            {
                alert("选择题分值输入错误，分值区间应当在0~14.2之间");;
            }
        }
    }
    else if(curtype==2)
    {
        //填空题
        if(ValueTestType=='')
        {
            showstring+="题目类型";
        }
        if(ValueTestName=='')
        {
            if(showstring!="您未输入")
            {
                showstring+="、";
            }
            showstring+="题目名称";
        }
        if(ValueTestDescription=='')
        {
            if(showstring!="您未输入")
            {
                showstring+="、";
            }
            showstring+="题目具体描述";
        }
        if(ValueTestAnswer=='')
        {
            if(showstring!="您未输入")
            {
                showstring+="、";
            }
            showstring+="该题目正确答案";
        }

    }
    else if(curtype==3)
    {
        //翻译题
        if(ValueTestType=='')
        {
            showstring+="题目类型";
        }
        if(ValueTestName=='')
        {
            if(showstring!="您未输入")
            {
                showstring+="、";
            }
            showstring+="题目名称";
        }
        if(ValueTestDescription=='')
        {
            if(showstring!="您未输入")
            {
                showstring+="、";
            }
            showstring+="题目具体描述";
        }
        if(ValueTestAnswer=='')
        {
            if(showstring!="您未输入")
            {
                showstring+="、";
            }
            showstring+="该题目正确答案";
        }
        if(ValueTestScore=='')
        {
            if(showstring!="您未输入")
            {
                showstring+="、";
            }
            showstring+="该题目分值";
        }
    }
    else if(curtype==4)
    {
        //作文题
        if(ValueTestType=='')
        {
            showstring+="题目类型";
        }
        if(ValueTestName=='')
        {
            if(showstring!="您未输入")
            {
                showstring+="、";
            }
            showstring+="题目名称";
        }
        if(ValueTestDescription=='')
        {
            if(showstring!="您未输入")
            {
                showstring+="、";
            }
            showstring+="题目具体描述";
        }
    }
    else
    {
        showstring+="题目类型";
    }

    if(showstring=="您未输入"&&curtype!=0)//说明都很规范
    {
        normative=true;
    }
    else
    {
        normative=false;
        alert(showstring);
    }
    //console.log("normative"+normative);
    return normative;
}