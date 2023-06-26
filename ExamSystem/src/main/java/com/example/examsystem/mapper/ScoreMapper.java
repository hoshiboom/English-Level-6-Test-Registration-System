package com.example.examsystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.examsystem.dto.Response;
import com.example.examsystem.entity.Score;
import com.example.examsystem.entity.ScoreSum;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper //MyBatis注解，Mapper指示这是一个mapper接口，映射到sql语句，BaseMapper接口是包含CRUD的通用接口
@Repository
public interface ScoreMapper extends BaseMapper<Score> {

    @Select("select score from score where student_id = #{studentId} and paperinfo_id=${paperInfoId} and state = 1")
    Score getScore(Integer studentId, Integer paperInfoId);

    @Select("select SUM(actual_score) as score from doandcheck where student_id=${studentId} and paperinfo_id=${paperInfoId} and state=2")
    Score getScoreFromDo(Integer studentId, Integer paperInfoId);
}
