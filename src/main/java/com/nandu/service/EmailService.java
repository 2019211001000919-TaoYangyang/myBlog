package com.nandu.service;

import com.nandu.mapper.BlogMapper;
import com.nandu.pojo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class EmailService {

    @Autowired
    private JavaMailSenderImpl mailSender;

    @Autowired
    private BlogMapper blogMapper;

    @Async
    public void email(Comment comment){
        //邮件设置1：一个简单的邮件
        SimpleMailMessage send = new SimpleMailMessage();
        send.setSubject("博客消息通知");
        String content = "<h3>收到，收到！感谢您在博客留言~~</h3><br><h2 style='color:red'>您评论的内容为：" + comment.getContent() + "</h2>";
        send.setText(content);
        send.setTo(comment.getEmail());
        send.setFrom("1732190917@qq.com");
        mailSender.send(send);
        //邮件设置1：一个简单的邮件
        SimpleMailMessage in = new SimpleMailMessage();
        in.setSubject("博客消息通知");
        String text = "收到一条新的博客评论:" +
                "\n博客标题："+ blogMapper.findbyId(comment.getBlogId()).getTitle() + "    " +
                "\n评论昵称：" + comment.getNickname() + "      "  +
                "\n评论内容" + comment.getContent();
        in.setText(text);
        in.setFrom(comment.getEmail());
        in.setTo("1732190917@qq.com");
        mailSender.send(in);
    }
}
