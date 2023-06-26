package com.example.examsystem.controler;


import com.example.examsystem.dto.Response;
import com.example.examsystem.dto.ResponseEnum;
import com.example.examsystem.service.ScoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@Validated
@CrossOrigin
@RestController //Restful风格
public class ScoreController {
    @Resource
    private ScoreService scoreService;

    @RequestMapping(value ="updateScore", method = RequestMethod.GET)
    public Response updateScore(@RequestParam(value = "studentId",required = true) Integer studentId,
                                @RequestParam(value = "paperinfoId",required = true)Integer paperInfoId){
        return scoreService.updateScore(studentId,paperInfoId);
    }
    @RequestMapping(value ="/score", method = RequestMethod.GET)
    public Response getScore(@RequestParam(value = "studentId",required = true) Integer studentId,
                             @RequestParam(value = "paperinfoId",required = true)Integer paperInfoId){


        return scoreService.getScore(studentId,paperInfoId);
    }
}
