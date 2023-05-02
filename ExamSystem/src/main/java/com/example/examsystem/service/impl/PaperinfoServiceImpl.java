package com.example.examsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.examsystem.dto.Response;
import com.example.examsystem.dto.ResponseEnum;
import com.example.examsystem.entity.Paperinfo;
import com.example.examsystem.exception.DaoException;
import com.example.examsystem.mapper.PaperinfoMapper;
import com.example.examsystem.service.PaperinfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Slf4j
@Service    //bean
//ServiceImpl是mybatis-plus的类，默认实现IService
//PaperinfoServiceImpl类是服务实现类的一个实例，提供PaperinfoService接口中定义的业务逻辑的实际实现，使用PaperinfoMapper类与数据库交互并对Paperinfo记录执行CRUD操作。
public class PaperinfoServiceImpl extends ServiceImpl<PaperinfoMapper, Paperinfo> implements PaperinfoService {

    @Resource
    private PaperinfoMapper paperinfoMapper;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response insertPaperinfo(Paperinfo paperinfo){

        paperinfoMapper.insert(paperinfo);
        return new Response(ResponseEnum.Add_Paperinfo_Success);
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response updatePaperinfo(Paperinfo paperinfo){
        Paperinfo paperinfotest = this.getOne(
                new QueryWrapper<Paperinfo>().eq("id",paperinfo.getId())
        );
        if (paperinfotest == null) return new Response(ResponseEnum.Update_Paperinfo_Failure);
        if(!this.updateById(paperinfo)){
            throw new DaoException(ResponseEnum.Update_Paperinfo_Failure);
        }
        return new Response(ResponseEnum.Update_Paperinfo_Success);
    }

    @Override
    public Response deletePaperinfo(Integer id) {
        Paperinfo paperinfotest = this.getOne(
                new QueryWrapper<Paperinfo>().eq("id",id)
        );
        if (paperinfotest == null) return new Response(ResponseEnum.Delete_Paperinfo_Failure);
        if(!this.removeById(paperinfotest)){
            throw new DaoException(ResponseEnum.Delete_Paperinfo_Failure);
        }
        return new Response(ResponseEnum.Delete_Paperinfo_Success);
    }
}
