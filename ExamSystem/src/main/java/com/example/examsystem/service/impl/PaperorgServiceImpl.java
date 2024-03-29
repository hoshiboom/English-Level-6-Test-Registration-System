package com.example.examsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.examsystem.dto.Response;
import com.example.examsystem.dto.ResponseEnum;
import com.example.examsystem.entity.PaperView;
import com.example.examsystem.entity.Paperinfo;
import com.example.examsystem.entity.Paperorg;
import com.example.examsystem.entity.Question;
import com.example.examsystem.exception.DaoException;
import com.example.examsystem.mapper.PaperorgMapper;
import com.example.examsystem.service.PaperorgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service    //bean
//ServiceImpl是mybatis-plus的类，默认实现IService
//PaperorgServiceImpl类是服务实现类的一个实例，提供PaperorgService接口中定义的业务逻辑的实际实现，使用PaperorgMapper类与数据库交互并对Paperorg记录执行CRUD操作。
public class PaperorgServiceImpl extends ServiceImpl<PaperorgMapper, Paperorg> implements PaperorgService {

    @Resource
    private PaperorgMapper paperorgMapper;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response insertPaperorg(Paperorg paperorg){

        paperorgMapper.insert(paperorg);
        return new Response(ResponseEnum.Add_Paperorg_Success);
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response updatePaperorg(Paperorg paperorg){
        Paperorg paperorgtest = this.getOne(
                new QueryWrapper<Paperorg>().eq("id",paperorg.getId())
        );
        if (paperorgtest == null) return new Response(ResponseEnum.Update_Paperorg_Failure);
        if(!this.updateById(paperorg)){
            throw new DaoException(ResponseEnum.Update_Paperorg_Failure);
        }
        return new Response(ResponseEnum.Update_Paperorg_Success);
    }

    @Override
    public Response deletePaperorg(Integer id) {
        Paperorg paperorgtest = this.getOne(
                new QueryWrapper<Paperorg>().eq("id",id)
        );
        if (paperorgtest == null) return new Response(ResponseEnum.Delete_Paperorg_Failure);
        if(!this.removeById(paperorgtest)){
            throw new DaoException(ResponseEnum.Delete_Paperorg_Failure);
        }
        return new Response(ResponseEnum.Delete_Paperorg_Success);
    }

    @Override
    public Response getPaperorgById(Integer paperinfoId, Page<PaperView> ipage) {
        Page<PaperView> paperorgtest= paperorgMapper.SelectPaperorgById(paperinfoId, ipage);
        if(paperorgtest == null) return new Response(ResponseEnum.List_Paperorg_Failure);
        else return new Response(ResponseEnum.List_Paperorg_Success,paperorgtest);
    }

    @Override
    public Response insertPaperinfo(Paperorg paperorg) {
        paperorgMapper.insert(paperorg);
        return new Response(ResponseEnum.Add_Paperorg_Success,paperorg);
    }

    @Override
    public Response deletePaperinfo(Integer id) {
        Paperorg paperorgtest = this.getOne(
                new QueryWrapper<Paperorg>().eq("id",id)
        );
        if (paperorgtest == null) return new Response(ResponseEnum.Delete_Paperorg_Failure);
        if(!this.removeById(paperorgtest)){
            throw new DaoException(ResponseEnum.Delete_Paperorg_Failure);
        }
        return new Response(ResponseEnum.Delete_Paperorg_Success);
    }


}
