package com.nandu.mapper;

import com.nandu.pojo.Comment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentMapper {

    //根据创建时间倒序来排
    List<Comment> findByBlogIdAndParentCommentNull(@Param("blogId") Long blogId, @Param("blogParentId") Long blogParentId);

    //查询父级对象
    Comment findParentbyParentId(@Param("parentCommentId")Long parentcommentid);

    //查询所有子级对象
    List<Comment> findAllSonComment(@Param("id") Long id);

    //添加一个评论
    int saveComment(Comment comment);

}
