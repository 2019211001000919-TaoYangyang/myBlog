package com.nandu.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.catalina.LifecycleState;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "t_blog")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //主键自增
    private Long id;  //主键
    private String title; //博客标题
    @Basic(fetch = FetchType.LAZY)
    @Lob    //@Lob注解声明大字段类型 第一次初始化时才有效，一般和@Basic懒加载一起使用，只有需要获取的时候才去查询；也可以直接去数据库内将该字段改为longtext类型
    private String content; //博客内容
    private String firstPicture; //博客首图
    private String flag; //原创or转载
    private Integer views;//查看次数
    private Boolean appreciation; //赞赏开启
    private Boolean shareStatement; //转载声明
    private Boolean commentabled;//是否评论
    private Boolean published;//是否发布(状态)
    private Boolean recommend; //是否推荐
    private String description;  //描述
//    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp createTime; //创建时间
//    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp updateTime; //更新时间
    @Transient     //指定字段不在数据库
    private Long typeId;
    @Transient
    private Long userId;
    private String tagIds;


    @ManyToOne //指定对应关系为多对一
    private Type type; //多个博客可从属于一个分类

    @ManyToMany(cascade = {CascadeType.PERSIST})    //cascade = {CascadeType.PERSIST}级联新增，blog增加一个标签时，tag表加一行
    private List<Tag> tags = new ArrayList<>();

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "blog")
    private List<Comment> comments = new ArrayList<>();

    public void init() {
        this.tagIds = tagsToIds(this.getTags());
    }

    //1,2,3
    private String tagsToIds(List<Tag> tags) {
        if (!tags.isEmpty()) {
            StringBuffer ids = new StringBuffer();
            boolean flag = false;
            for (Tag tag : tags) {
                if (flag) {
                    ids.append(",");
                } else {
                    flag = true;
                }
                ids.append(tag.getId());
            }
            return ids.toString();
        } else {
            return tagIds;
        }
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", firstPicture='" + firstPicture + '\'' +
                ", flag='" + flag + '\'' +
                ", views=" + views +
                ", appreciation=" + appreciation +
                ", shareStatement=" + shareStatement +
                ", commentabled=" + commentabled +
                ", published=" + published +
                ", recommend=" + recommend +
                ", description='" + description + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", typeId=" + typeId +
                ", tagIds='" + tagIds + '\'' +
                ", userId=" + userId +
                '}';
    }
}
