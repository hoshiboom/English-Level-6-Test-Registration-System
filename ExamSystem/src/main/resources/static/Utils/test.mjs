import {requests}  from "./http.mjs";
const admin_token = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlSWQiOjEsIm5hbWUiOiJoaSIsImlkIjoyLCJleHAiOjIxNTg1MDI0ODh9.ZHjqCg-bjcDjfwRQemntQ2UjNu87XVl32EjsbsASV10";
//请求示例
let obj={
    path:"/login/admin",
    method:"POST",
    token:admin_token,
    data:{
        number:2012618,
        password:123456
    }
}
requests(obj).then(function(data){
    console.log(data);
    console.log(data.date);
});
