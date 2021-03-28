package com.nandu.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nandu.exception.NotFoundException;
import com.nandu.mapper.TagMapper;
import com.nandu.mapper.TypeMapper;
import com.nandu.pojo.Tag;
import com.nandu.pojo.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagMapper tagMapper;

    @Override
    public List<Tag> findallTag() {
        return tagMapper.findallTag();
    }

    @Override
    public Tag findTagbyId(Long id) {
        if (id == null || id < 0){
            throw new NotFoundException("请选择一条记录进行操作...");
        }
        return tagMapper.findTagbyId(id);
    }

    @Override
    public Tag findTagbyName(String name) {
        return tagMapper.findTagbyName(name);
    }


    @Override
    public List<Tag> findTagByString(String tagIds) {
        List<Tag> tags = new ArrayList<>();
        List<Long> longs = convertToList(tagIds);
        for (Long long1 : longs) {
            tags.add(tagMapper.findTagbyId(long1));
        }
        return tags;
    }

    @Override
    public List<Tag> findBlogTag() {
        return tagMapper.findBlogTag();
    }

    @Override
    public List<Tag> findTopTag() {
        return tagMapper.findTopTag();
    }

    @Override
    public int addTag(Tag tag) {
        return tagMapper.addTag(tag);
    }

    @Override
    public int updateTag(Tag tag) {
        Tag tagbyId = tagMapper.findTagbyId(tag.getId());
        if(tagbyId == null){
            throw new NotFoundException("不存在该类型！");
        }
        return tagMapper.updateTag(tag);
    }

    @Override
    public int delTag(Long id) {
        return tagMapper.delTag(id);
    }

    @Override
    public PageInfo<Tag> findForPage(Integer page, Integer size) {
        if (page == null) {
            page = 1;
        }
        if (size == null) {
            size = 5;
        }

        PageHelper.startPage(page, size);

        List<Tag> tags = findallTag();

        PageInfo<Tag> tagPageInfo = new PageInfo<>(tags);

        return tagPageInfo;
    }


    private List<Long> convertToList(String ids) {  //把前端的tagIds字符串转换为list集合
        List<Long> list = new ArrayList<>();
        if (!"".equals(ids) && ids != null) {
            String[] idarray = ids.split(",");
            for (int i=0; i < idarray.length;i++) {
                list.add(new Long(idarray[i]));
            }
        }
        return list;
    }
}
