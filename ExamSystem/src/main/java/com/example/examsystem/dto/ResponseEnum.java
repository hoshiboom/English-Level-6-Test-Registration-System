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
    public static final ResponseEnum List_Admin_Success = new ResponseEnum(2002011,"获得管理员列表成功");
    public static final ResponseEnum List_Admin_Failure = new ResponseEnum(2002010,"获得管理员列表失败");
    public static final ResponseEnum Get_Admin_Success = new ResponseEnum(2002021,"查询管理员成功");
    public static final ResponseEnum Get_Admin_Failure = new ResponseEnum(2002020,"查询管理员失败");
    public static final ResponseEnum Delete_Admin_Success = new ResponseEnum(2002031,"删除管理员成功");
    public static final ResponseEnum Delete_Admin_Failure = new ResponseEnum(2002030,"删除管理员失败");
    public static final ResponseEnum Update_Admin_Success = new ResponseEnum(2002041,"更新管理员成功");
    public static final ResponseEnum Update_Admin_Failure = new ResponseEnum(2002040,"更新管理员失败");
    public static final ResponseEnum Add_Admin_Success = new ResponseEnum(2002051,"添加管理员成功");
    public static final ResponseEnum Add_Admin_Failure = new ResponseEnum(2002050,"添加管理员失败");

    // ============================================= StudentController ============================================= //
    public static final ResponseEnum List_Student_Success = new ResponseEnum(2003011,"获得学生列表成功");
    public static final ResponseEnum List_Student_Failure = new ResponseEnum(2003010,"获得学生列表失败");
    public static final ResponseEnum Get_Student_Success = new ResponseEnum(2003021,"查询学生成功");
    public static final ResponseEnum Get_Student_Failure = new ResponseEnum(2003020,"查询学生失败");
    public static final ResponseEnum Delete_Student_Success = new ResponseEnum(2003031,"删除学生成功");
    public static final ResponseEnum Delete_Student_Failure = new ResponseEnum(2003030,"删除学生失败");
    public static final ResponseEnum Update_Student_Success = new ResponseEnum(2003041,"更新学生成功");
    public static final ResponseEnum Update_Student_Failure = new ResponseEnum(2003040,"更新学生失败");
    public static final ResponseEnum Add_Student_Success = new ResponseEnum(2003051,"添加学生成功");
    public static final ResponseEnum Add_Student_Failure = new ResponseEnum(2003050,"添加学生失败");

    // ============================================= TeacherController ============================================= //
    public static final ResponseEnum List_Teacher_Success = new ResponseEnum(2004011,"获得教师列表成功");
    public static final ResponseEnum List_Teacher_Failure = new ResponseEnum(2004010,"获得教师列表失败");
    public static final ResponseEnum Get_Teacher_Success = new ResponseEnum(2004021,"查询教师成功");
    public static final ResponseEnum Get_Teacher_Failure = new ResponseEnum(2004020,"查询教师失败");
    public static final ResponseEnum Delete_Teacher_Success = new ResponseEnum(2004031,"删除教师成功");
    public static final ResponseEnum Delete_Teacher_Failure = new ResponseEnum(2004030,"删除教师失败");
    public static final ResponseEnum Update_Teacher_Success = new ResponseEnum(2004041,"更新教师成功");
    public static final ResponseEnum Update_Teacher_Failure = new ResponseEnum(2004040,"更新教师失败");
    public static final ResponseEnum Add_Teacher_Success = new ResponseEnum(2004051,"添加教师成功");
    public static final ResponseEnum Add_Teacher_Failure = new ResponseEnum(2004050,"添加教师失败");

    // ============================================= QuestionController ============================================= //
    public static final ResponseEnum List_Question_Success = new ResponseEnum(2005011,"获得问题列表成功");
    public static final ResponseEnum List_Question_Failure = new ResponseEnum(2005010,"获得问题列表失败");
    public static final ResponseEnum Get_Question_Success = new ResponseEnum(2005021,"查询问题成功");
    public static final ResponseEnum Get_Question_Failure = new ResponseEnum(2005020,"查询问题失败");
    public static final ResponseEnum Delete_Question_Success = new ResponseEnum(2005031,"删除问题成功");
    public static final ResponseEnum Delete_Question_Failure = new ResponseEnum(2005030,"删除问题失败");
    public static final ResponseEnum Update_Question_Success = new ResponseEnum(2005041,"更新问题成功");
    public static final ResponseEnum Update_Question_Failure = new ResponseEnum(2005040,"更新问题失败");
    public static final ResponseEnum Add_Question_Success = new ResponseEnum(2005051,"添加问题成功");
    public static final ResponseEnum Add_Question_Failure = new ResponseEnum(2005050,"添加问题失败");


    // ============================================= PaperinfoController ============================================= //
    public static final ResponseEnum List_Paperinfo_Success = new ResponseEnum(2006011,"获得试卷列表成功");
    public static final ResponseEnum List_Paperinfo_Failure = new ResponseEnum(2006010,"获得试卷列表失败");
    public static final ResponseEnum Get_Paperinfo_Success = new ResponseEnum(2006021,"查询试卷成功");
    public static final ResponseEnum Get_Paperinfo_Failure = new ResponseEnum(2006020,"查询试卷失败");
    public static final ResponseEnum Delete_Paperinfo_Success = new ResponseEnum(2006031,"删除试卷成功");
    public static final ResponseEnum Delete_Paperinfo_Failure = new ResponseEnum(2006030,"删除试卷失败");
    public static final ResponseEnum Update_Paperinfo_Success = new ResponseEnum(2006041,"更新试卷成功");
    public static final ResponseEnum Update_Paperinfo_Failure = new ResponseEnum(2006040,"更新试卷失败");
    public static final ResponseEnum Add_Paperinfo_Success = new ResponseEnum(2006051,"添加试卷成功");
    public static final ResponseEnum Add_Paperinfo_Failure = new ResponseEnum(2006050,"添加试卷失败");

    // ============================================= PaperorgController ============================================= //
    public static final ResponseEnum List_Paperorg_Success = new ResponseEnum(2007011,"获得试卷题目列表成功");
    public static final ResponseEnum List_Paperorg_Failure = new ResponseEnum(2007010,"获得试卷题目列表失败");
    public static final ResponseEnum Get_Paperorg_Success = new ResponseEnum(2007021,"查询试卷题目成功");
    public static final ResponseEnum Get_Paperorg_Failure = new ResponseEnum(2007020,"查询试卷题目失败");
    public static final ResponseEnum Delete_Paperorg_Success = new ResponseEnum(2007031,"删除试卷题目成功");
    public static final ResponseEnum Delete_Paperorg_Failure = new ResponseEnum(2007030,"删除试卷题目失败");
    public static final ResponseEnum Update_Paperorg_Success = new ResponseEnum(2007041,"更新试卷题目成功");
    public static final ResponseEnum Update_Paperorg_Failure = new ResponseEnum(2007040,"更新试卷题目失败");
    public static final ResponseEnum Add_Paperorg_Success = new ResponseEnum(2007051,"添加试卷题目成功");
    public static final ResponseEnum Add_Paperorg_Failure = new ResponseEnum(2007050,"添加试卷题目失败");
    public static final ResponseEnum Get_Paperorg_Details_Success =new ResponseEnum(2007061,"获得试卷详细题目成功");
    public static final ResponseEnum Get_Paperorg_Details_Failure = new ResponseEnum(2007060,"获得试卷详细题目失败");


    // ============================================= SignupController ============================================= //
    public static final ResponseEnum List_Signup_Success = new ResponseEnum(2007011,"获得报名考试列表成功");
    public static final ResponseEnum List_Signup_Failure = new ResponseEnum(2007010,"获得报名考试列表失败");
    public static final ResponseEnum Get_Signup_Success = new ResponseEnum(2007021,"查询报名考试成功");
    public static final ResponseEnum Get_Signup_Failure = new ResponseEnum(2007020,"查询报名考试失败");
    public static final ResponseEnum Delete_Signup_Success = new ResponseEnum(2007031,"删除报名考试成功");
    public static final ResponseEnum Delete_Signup_Failure = new ResponseEnum(2007030,"删除报名考试失败");
    public static final ResponseEnum Update_Signup_Success = new ResponseEnum(2007041,"更新报名考试成功");
    public static final ResponseEnum Update_Signup_Failure = new ResponseEnum(2007040,"更新报名考试失败");
    public static final ResponseEnum Add_Signup_Success = new ResponseEnum(2007051,"添加报名考试成功");
    public static final ResponseEnum Add_Signup_Failure = new ResponseEnum(2007050,"添加报名考试失败");

    // ============================================= DoandcheckController ============================================= //
    public static final ResponseEnum List_Doandcheck_Success = new ResponseEnum(2008011,"获得作答情况列表成功");
    public static final ResponseEnum List_Doandcheck_Failure = new ResponseEnum(2008010,"获得作答情况列表失败");
    public static final ResponseEnum Get_Doandcheck_Success = new ResponseEnum(2008021,"查询作答成功");
    public static final ResponseEnum Get_Doandcheck_Failure = new ResponseEnum(2008020,"查询作答失败");
    public static final ResponseEnum Delete_Doandcheck_Success = new ResponseEnum(2008031,"删除作答成功");
    public static final ResponseEnum Delete_Doandcheck_Failure = new ResponseEnum(2008030,"删除作答失败");
    public static final ResponseEnum Update_Doandcheck_Success = new ResponseEnum(2008041,"更新作答成功");
    public static final ResponseEnum Update_Doandcheck_Failure = new ResponseEnum(2008040,"更新作答失败");
    public static final ResponseEnum Add_Doandcheck_Success = new ResponseEnum(2008041,"新增作答成功");
    public static final ResponseEnum Add_Doandcheck_Failure = new ResponseEnum(2008040,"新增作答失败");


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
