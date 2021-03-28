package com.nandu.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nandu.exception.NotFoundException;
import com.nandu.mapper.TypeMapper;
import com.nandu.pojo.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeMapper typeMapper;

    @Override
    public List<Type> findallType() {
        return typeMapper.findallType();
    }

    @Override
    public Type findTypebyId(Long id) {
        if (id == null || id < 0){
            throw new NotFoundException("请选择一条记录进行操作...");
        }
        return typeMapper.findTypebyId(id);
    }

    @Override
    public Type findTypebyName(String name) {
        return typeMapper.findTypebyName(name);
    }



    @Override
    public List<Type> findTopType() {
        return typeMapper.findTopType();
    }


    @Override
    public int addType(Type type) {
        return typeMapper.addType(type);
    }

    @Override
    public int updateType(Type type) {
        Type typebyId = typeMapper.findTypebyId(type.getId());
        if(typebyId == null){
            throw new NotFoundException("不存在该类型！");
        }
        return typeMapper.updateType(type);
    }

    @Override
    public int delType(Long id) {
        return typeMapper.delType(id);
    }

    @Override
    public List<Type> findBlogType() {
        return typeMapper.findBlogType();
    }

    @Override
    public PageInfo<Type> findForPage(Integer page, Integer size) {
        if (page == null) {
            page = 1;
        }
        if (size == null) {
            size = 5;
        }

        PageHelper.startPage(page, size);

        List<Type> types = findallType();

        PageInfo<Type> typePageInfo = new PageInfo<>(types);

        return typePageInfo;
    }
}
