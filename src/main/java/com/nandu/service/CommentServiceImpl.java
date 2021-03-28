package com.nandu.service;

import com.nandu.mapper.BlogMapper;
import com.nandu.mapper.CommentMapper;
import com.nandu.pojo.Comment;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private EmailService emailService;


    @Override
    public List<Comment> getCommentByBlogId(Long blogId) {  //查询父评论
        //没有父节点的默认为-1
        List<Comment> comments = commentMapper.findByBlogIdAndParentCommentNull(blogId, (long) -1);
//        System.out.println(comments);
        return eachComment(comments);
    }

    @Override
    //接收回复的表单
    public int saveComment(Comment comment) {
        //获得父id
        Long parentCommentId = comment.getParentCommentId();
        //没有父级评论默认是-1
        if (parentCommentId != -1) {
            //有父级评论
            comment.setParentCommentId(parentCommentId);
            Comment byParentCommentId = commentMapper.findParentbyParentId(parentCommentId);
            comment.setParentNickname(byParentCommentId.getNickname());
        } else {
            //没有父级评论
            comment.setParentCommentId(parentCommentId);
            comment.setParentComment(null);
        }
        comment.setCreateTime(new Timestamp(new Date().getTime()));
        int i = commentMapper.saveComment(comment);
        emailService.email(comment);
        return i;
    }


    //临时数组
    private  List<Comment> tempReply = new ArrayList<>();

    public  List<Comment> eachComment(List<Comment> commentList){
        ArrayList<Comment> new_CommentList = new ArrayList<>();
        for (Comment comment : commentList) {
            Comment c = new Comment();
            BeanUtils.copyProperties(comment,c);
            new_CommentList.add(c);
        }
//        System.out.println(new_CommentList);
        //合并顶级级评论的所有回复到一个集合中（并查集压缩路径）
        combineChildren(new_CommentList);
        return new_CommentList;
    }

    public void combineChildren(List<Comment> comments){
        //对每一条顶级评论遍历
        for (Comment comment : comments) {
            //得到所有子评论
            List<Comment> replyComments = comment.getReplyComments();
            //对每一条子评论循环迭代，放入临时数组中
            for (Comment reply : replyComments) {
                recursively(reply);
            }
            //修改一级评论的reply集合为迭代后的集合（线性）
            comment.setReplyComments(tempReply);
            //清理临时数组
            tempReply = new ArrayList<>();
        }
    }


    public void recursively(Comment reply){
        tempReply.add(reply);  //将顶结点添加到临时数组
        //得到所有子评论
        List<Comment> allSonComment = commentMapper.findAllSonComment(reply.getId());
        if (allSonComment != null){
//            reply.setReplyComments(allSonComment);
            List<Comment> nextReply = allSonComment;
            for (Comment r : nextReply) {
                List<Comment> allSonComment1 = commentMapper.findAllSonComment(r.getId());
                if (allSonComment1 != null){
                   recursively(r);
                }
            }
        }
    }

}
