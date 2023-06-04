import {requests}  from "./http.mjs";
const admin_token = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlSWQiOjEsIm5hbWUiOiJoaSIsImlkIjoyLCJleHAiOjIxNTg1MDI0ODh9.ZHjqCg-bjcDjfwRQemntQ2UjNu87XVl32EjsbsASV10";
//请求示例
let obj1={
    path:"/login/admin",
    method:"POST",
    token:null,
    data:{
        number:2012618,
        password:123456
    }
}

requests(obj1).then(function(data){
    console.log(data);
    console.log(data.date);
});



let obj2={
    path:"/admin/2",
    method:"GET",
    token:admin_token,
}
requests(obj2).then(function(data){
    console.log(data);
    console.log(data.date);
});