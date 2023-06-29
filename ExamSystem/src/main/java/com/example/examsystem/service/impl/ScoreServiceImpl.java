package com.example.examsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.examsystem.dto.Response;
import com.example.examsystem.dto.ResponseEnum;
import com.example.examsystem.entity.Score;
import com.example.examsystem.entity.ScoreSum;
import com.example.examsystem.mapper.ScoreMapper;
import com.example.examsystem.service.ScoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service    //bean
public class ScoreServiceImpl extends ServiceImpl<ScoreMapper,Score> implements ScoreService {

    @Resource
    private ScoreMapper scoreMapper;


    @Override
    public Response getScore(Integer studentId, Integer paperInfoId) {
        Score tmp =  scoreMapper.getScore(studentId, paperInfoId);
        if(tmp == null){//学生成绩可能分散在doandcheck中还没有被统计
            tmp=scoreMapper.getScoreFromDo(studentId, paperInfoId);
            if(tmp == null){
                return new Response(ResponseEnum.Get_Score_Failure);
            }
            tmp.setState(1).setStudentId(studentId).setPaperinfoId(paperInfoId);
            this.save(tmp);
        }

        return new Response(ResponseEnum.Get_Score_Success,tmp);
    }

    @Override
    public Response updateScore(Integer studentId, Integer paperInfoId) {
        Score tmp = scoreMapper.getScoreFromDo(studentId, paperInfoId);
        tmp.setState(1).setStudentId(studentId).setPaperinfoId(paperInfoId);
        this.save(tmp);
        return new Response(ResponseEnum.Get_Score_Success,tmp);
    }

    @Override
    public Response getAllscore(Integer studentId) {
        List<Score> listscore=scoreMapper.selectList(new QueryWrapper<Score>().eq("student_id", studentId));
        if(listscore!=null) {
            return new Response(ResponseEnum.Get_Score_Success,listscore);
        }
        return new Response(ResponseEnum.Get_Score_Failure);
    }
}
