package com.nandu.utils;

import com.nandu.pojo.Comment;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class CommentUtil {

    //临时数组
    private  List<Comment> tempReply = new ArrayList<>();

    public  List<Comment> eachComment(List<Comment> commentList){
        ArrayList<Comment> new_CommentList = new ArrayList<>();
        for (Comment comment : commentList) {
            Comment c = new Comment();
            BeanUtils.copyProperties(comment,c);
            new_CommentList.add(c);
        }
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
        if (reply.getReplyComments() != null){
            List<Comment> nextReply = reply.getReplyComments();
            for (Comment r : nextReply) {
                tempReply.add(r);
                if (reply.getReplyComments() != null){
                    recursively(r);
                }
            }
        }
    }




}
