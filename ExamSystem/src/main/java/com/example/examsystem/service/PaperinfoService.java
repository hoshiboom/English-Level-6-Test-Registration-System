package com.example.examsystem.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.examsystem.dto.Response;
import com.example.examsystem.entity.Paperinfo;


//IService是Mybatis-Plus提供的接口，定义了基本的CRUD操作，PaperinfoService继承了这些操作并且可以在Paperinfo表上进行增删改查
public interface PaperinfoService extends IService<Paperinfo> {

    Response insertPaperinfo(Paperinfo Paperinfo);
    Response updatePaperinfo(Paperinfo Paperinfo);

    Response deletePaperinfo(Integer id);
}
