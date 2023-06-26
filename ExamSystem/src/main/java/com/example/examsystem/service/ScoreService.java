package com.example.examsystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.examsystem.dto.Response;
import com.example.examsystem.entity.Score;

public interface ScoreService extends IService<Score> {


    Response getScore(Integer studentId, Integer paperInfoId);

    Response updateScore(Integer studentId, Integer paperInfoId);
}
