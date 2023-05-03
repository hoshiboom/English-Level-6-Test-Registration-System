package com.example.examsystem.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.examsystem.dto.Response;
import com.example.examsystem.entity.Paperorg;


//IService是Mybatis-Plus提供的接口，定义了基本的CRUD操作，PaperorgService继承了这些操作并且可以在Paperorg表上进行增删改查
public interface PaperorgService extends IService<Paperorg> {

    Response insertPaperorg(Paperorg Paperorg);
    Response updatePaperorg(Paperorg Paperorg);

    Response deletePaperorg(Integer id);

    Response getPaperorgDetails(Integer id, Integer curpage, Integer size);
}
