package com.nandu.service;

import com.nandu.pojo.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getCommentByBlogId(Long blogId);

    int saveComment(Comment comment);
}
