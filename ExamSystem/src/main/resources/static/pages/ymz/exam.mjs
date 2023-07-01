import {requests} from "./http.mjs";

var token;

if (window.location.href.includes("exam.html")) {
    let loginObject = {
        path: "/login/student",
        method: "POST",
        data: {
            number: 6523,
            password: "13256"
        }
    }
    requests(loginObject).then(function (response) {
        console.log(response);
        token=response.token;
        let obj = {
            path: "/paperorgDetail?paperinfoId=1",

            token: token
        }
        requests(obj).then(function (response) {
            console.log("试卷详细信息响应：");
            console.log(response);
            var questions = response.data.records;

            var q1 = questions[0];
            var q1str = `<div className="question-title">${q1.orderId}. ${q1.questionName}</div>
            <div className="options">
                <label><input type="radio" name="listening-q1" value="A"> A. ${q1.optionA}</label>
                <label><input type="radio" name="listening-q1" value="B"> B. ${q1.optionB}</label>  
                <label><input type="radio" name="listening-q1" value="C"> C. ${q1.optionC}</label>
            </div>`
            document.getElementById("part1-listen").innerHTML = q1str;

            var q2 = questions[1];
            var q2str = `<div class="reading-text">
                ${q2.questionDescription}
                </div>
                <br />
                <div class="question-title">1. ${q2.questionName}</div>
                <div class="options">
                  <label><input type="radio" name="reading-q1" value="A"> A. ${q2.optionA}</label>
                  <label><input type="radio" name="reading-q1" value="B"> B. ${q2.optionB}</label>
                  <label><input type="radio" name="reading-q1" value="C"> C. ${q2.optionC}</label>
                </div>`
            document.getElementById("part2-readA").innerHTML = q2str;

            var q3 = questions[2];
            var q3str = `<div class="reading-text">
                ${q3.questionDescription}
                </div>
                <br />
                <div class="question-title">1. ${q3.questionName}</div>
                <div class="options">
                  <label><input type="radio" name="reading-q1" value="A"> A. ${q3.optionA}</label>
                  <label><input type="radio" name="reading-q1" value="B"> B. ${q3.optionB}</label>
                  <label><input type="radio" name="reading-q1" value="C"> C. ${q3.optionC}</label>
                </div>`
            document.getElementById("part2-readB").innerHTML = q3str;

            var q4 = questions[3];
            var q4str = `<div class="reading-text">
                ${q4.questionDescription}
                </div>
                <br />
                <div class="question-title">1. ${q4.questionName}</div>
                <div class="options">
                  <label><input type="radio" name="reading-q1" value="A"> A. ${q4.optionA}</label>
                  <label><input type="radio" name="reading-q1" value="B"> B. ${q4.optionB}</label>
                  <label><input type="radio" name="reading-q1" value="C"> C. ${q4.optionC}</label>
                </div>`
            document.getElementById("part2-readC").innerHTML = q4str;


            var q5 = questions[4];
            var q5str =`<div class="translate-text">${q5.questionName}
                </div>
                <br />
                <textarea id="translation-answer" rows="4" cols="50" placeholder="请输入翻译答案"></textarea>`;
            document.getElementById("part3-translation").innerHTML = q5str;

            var q6 = questions[5];
            var q6str =`<div class="translate-text">${q6.questionName}
                </div>
                <br />
                <textarea id="essay-answer" rows="6" cols="50" placeholder="请输入作文答案"></textarea>`;
            document.getElementById("part4-essay").innerHTML = q6str;



        })


    })

}
