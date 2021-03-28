package com.nandu.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.awt.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "t_comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nickname; //昵称
    private String email;  //联系方式
    private String content;  //内容
    private String avatar;   //头像地址
//    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp createTime;
    private Boolean adminComment;  //是否为管理员评论


    @Transient
    private Long BlogId;
    @ManyToOne
    private Blog blog;

    @OneToMany(mappedBy = "parentComment")
    private List<Comment> replyComments;

    @Transient
    private Long parentCommentId;
    private String parentNickname;
    @ManyToOne
    private Comment parentComment;

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", content='" + content + '\'' +
                ", avatar='" + avatar + '\'' +
                ", createTime=" + createTime +
                ", adminComment=" + adminComment +
                ", BlogId=" + BlogId +
                ", replyComments=" + replyComments +
                ", parentCommentId=" + parentCommentId +
                '}';
    }
}
