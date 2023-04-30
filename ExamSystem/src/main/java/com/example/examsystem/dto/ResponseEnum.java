package com.example.examsystem.dto;

import lombok.Getter;

@Getter
public class ResponseEnum {
    /**
     * 解释响应码
     * 200 * 01 0
     * 200  表示 成功
     * *    对应 不同的controller
     * 01   对应 不同的业务
     * 0/1  表示 业务执行成功与否
     * 400 客户端异常
     * 500 服务端异常
     */

    // ============================================= UserController ============================================= //
    public static final ResponseEnum Has_Email = new ResponseEnum(2001010, "邮箱已被注册");
    public static final ResponseEnum Not_Email = new ResponseEnum(2001011, "邮箱未被注册");
    public static final ResponseEnum VerifyCode_Send_Failure = new ResponseEnum(2001020, "验证码发送失败");
    public static final ResponseEnum VerifyCode_Send_Success = new ResponseEnum(2001021, "验证码发送成功");
    public static final ResponseEnum Register_Failure = new ResponseEnum(2001030, "注册失败, 验证码错误");
    public static final ResponseEnum Register_Success = new ResponseEnum(2001031, "注册成功");
    public static final ResponseEnum Login_Failure = new ResponseEnum(2001040, "登录失败");
    public static final ResponseEnum Login_Success = new ResponseEnum(2001041, "登录成功");
    public static final ResponseEnum Not_Login = new ResponseEnum(2001050, "没有登录");
    public static final ResponseEnum Has_Login = new ResponseEnum(2001051, "已经登录");
    public static final ResponseEnum Logout_Success = new ResponseEnum(2001061, "退出登录成功");

    // ============================================= AdminController ============================================= //


    // ============================================= 通用 ============================================= //
    public static final ResponseEnum Hello_World = new ResponseEnum(100000, "你好, 世界! 你好, 朋友!");
    public static final ResponseEnum TEST_TEST = new ResponseEnum(100001, "测试返回值");

    // 客户端异常
    public static final ResponseEnum Not_ThisFile = new ResponseEnum(400050, "没有这样的文件");
    public static final ResponseEnum Param_Check = new ResponseEnum(400000, "参数有误, 请检查");
    public static final ResponseEnum Param_Missing = new ResponseEnum(400010, "参数缺少, 请检查");
    public static final ResponseEnum Method_Not_Supported = new ResponseEnum(400020, "请求方法不支持");
    public static final ResponseEnum Not_IsMultipart = new ResponseEnum(400030, "不是一个Multipart 请求");
    public static final ResponseEnum Request_Part_Missing = new ResponseEnum(400040, "请求体参数缺少, 请检查");
    public static final ResponseEnum Res_Not_Found = new ResponseEnum(404010, "资源没有发现");

    public static final ResponseEnum File_Type_Error = new ResponseEnum(400010, "文件类型不支持");
    public static final ResponseEnum File_Is_Null = new ResponseEnum(400020, "文件为NULL");
    public static final ResponseEnum FileName_Is_Null = new ResponseEnum(400030, "文件名为NULL");

    // 服务器异常
    public static final ResponseEnum SYSTEM_UNKNOWN = new ResponseEnum(500000, "系统繁忙");
    public static final ResponseEnum Write_File_Failure = new ResponseEnum(500010, "写文件失败");
    public static final ResponseEnum Create_Dir_Failure = new ResponseEnum(500020, "创建目录失败");
    public static final ResponseEnum Delete_File_Failure = new ResponseEnum(500030, "删除文件失败");


    final String msg;
    final Integer code;

    ResponseEnum(Integer code, String msg) {
        this.msg = msg;
        this.code = code;
    }
}
