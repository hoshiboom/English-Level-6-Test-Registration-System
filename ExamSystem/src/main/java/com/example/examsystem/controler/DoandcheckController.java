package com.example.examsystem.controler;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.examsystem.dto.Response;
import com.example.examsystem.dto.ResponseEnum;
import com.example.examsystem.entity.Doandcheck;
import com.example.examsystem.mapper.DoandcheckMapper;
import com.example.examsystem.service.DoandcheckService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.List;
import java.util.function.Function;



@Slf4j
@Validated
@CrossOrigin
@RestController //Restful风格
public class DoandcheckController {

    @Resource
    private DoandcheckService doandcheckService;

    @Resource
    private DoandcheckMapper doandcheckMapper;

    @Resource
    @Qualifier("globalVariable")
    private Function<String, String> parameterValue;


    //分页获得列表
    @RequestMapping(value = "/doandcheck", method = RequestMethod.GET)
    public Response DoandcheckPage(@RequestParam(required = false) Integer curpage,
                                   @RequestParam(required = false) Integer size) {
        if (curpage == null || curpage <= 0) curpage = 1;
        Integer pagesize = Integer.parseInt(parameterValue.apply("Page_Size"));
        if (size == null || size <= 0 || size > pagesize) size = pagesize;

        Page<Doandcheck> page = doandcheckService.page(new Page<>(curpage, size),
                new QueryWrapper<Doandcheck>());
        return new Response(ResponseEnum.List_Doandcheck_Success, page);

    }

    @RequestMapping(value = "/doandcheckByCondition", method = RequestMethod.GET)
    public Response DoandcheckPage(@RequestBody(required = false)Doandcheck doandcheckCondition) {

        QueryWrapper<Doandcheck> queryWrapper = new QueryWrapper<Doandcheck>();
        // Java 泛型表达式，用于表示一个未知类，该类是 Doandcheck 或 Doandcheck 的子类。
        // 可以获取 Doandcheck 对象的类信息，进而对其进行反射操作，如获取字段和字段值。
        Class<? extends Doandcheck> doclass = doandcheckCondition.getClass();
        Field[] fields = doclass.getDeclaredFields();//获取 doClass 对象的所有字段
        for (Field field : fields) {
            //将字段设置为可访问，即使字段是私有的也可以进行访问
            // 通过反射访问私有字段需要先设置其可访问性。
            field.setAccessible(true);
            try {
                //使用反射获取 doAndCheckCondition 对象的当前字段的值
                Object value = field.get(doandcheckCondition);
                if (value != null && field.getName() != "serialVersionUID") {
                    String ColumnName = convertFieldNameToColumnName(field.getName());
                    queryWrapper.eq(ColumnName, value);
                }
            }catch (IllegalAccessException e) {//用于处理访问字段时可能抛出的 IllegalAccessException 异常
                e.printStackTrace();
            }
        }
        List<Doandcheck> list = doandcheckMapper.selectList(queryWrapper);
        return new Response(ResponseEnum.Get_Doandcheck_Success, list);

    }

    private String convertFieldNameToColumnName(String fieldName) {
        StringBuilder columnName = new StringBuilder();
        for (char c : fieldName.toCharArray()) {
            if (Character.isUpperCase(c)) {
                columnName.append("_").append(Character.toLowerCase(c));
            } else {
                columnName.append(c);
            }
        }
        return columnName.toString();
    }


    //根据id获得信息
    @RequestMapping(value = "/doandcheck/{id}", method = RequestMethod.GET)
    public Response getDoandcheckById(@PathVariable("id") Integer id) {

        Doandcheck Doandcheck = doandcheckService.getOne(
                new QueryWrapper<Doandcheck>().eq("id", id)
        );
        if (Doandcheck == null) return new Response(ResponseEnum.Get_Doandcheck_Failure);
        return new Response(ResponseEnum.Get_Doandcheck_Success, Doandcheck);
    }

    //添加信息
    @RequestMapping(value = "/doandcheck", method = RequestMethod.POST)
    public Response addDoandcheck(@RequestParam("studentId") Integer studentId,
                                  @RequestParam("paperinfoId") Integer paperinfoId,
                                  @RequestParam("questionId") Integer questionId,
                                  @RequestParam("studentAnswer") String studentAnswer,
                                  @RequestParam("actualScore") Integer actualScore,
                                  @RequestParam("state") Integer state
    ) {

        Doandcheck doandcheck = new Doandcheck().setStudentId(studentId).setPaperinfoId(paperinfoId).setQuestionId(questionId)
                .setStudentAnswer(studentAnswer).setActualScore(actualScore).setState(state);
        return doandcheckService.insertDoandcheck(doandcheck);
    }

    //根据id更新信息
    @RequestMapping(value = "/doandcheck/{id}", method = RequestMethod.PUT)
    public Response updateDoandcheck(@RequestParam("studentId") Integer studentId,
                                     @RequestParam("paperinfoId") Integer paperinfoId,
                                     @RequestParam("questionId") Integer questionId,
                                     @RequestParam("studentAnswer") String studentAnswer,
                                     @RequestParam("actualScore") Integer actualScore,
                                     @RequestParam("state") Integer state,
                                     @PathVariable("id") Integer id
    ) {

        Doandcheck doandcheck = new Doandcheck().setId(id).setStudentId(studentId).setPaperinfoId(paperinfoId).setQuestionId(questionId)
                .setStudentAnswer(studentAnswer).setActualScore(actualScore).setState(state);
        return doandcheckService.updateDoandcheck(doandcheck);
    }

    //根据id删除信息
    @RequestMapping(value = "/doandcheck/{id}", method = RequestMethod.DELETE)
    public Response deleteDoandcheck(@PathVariable("id") Integer id) {
        return doandcheckService.deleteDoandcheck(id);
    }
}
