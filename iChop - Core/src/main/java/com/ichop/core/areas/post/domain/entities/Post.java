package com.ichop.core.areas.post.domain.entities;

import com.ichop.core.base.BaseEntity;
import com.ichop.core.areas.user.domain.entities.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity(name = "Post")
@Table(name = "posts")
public class Post extends BaseEntity {

    @ManyToOne(targetEntity = User.class)
    private User user;

    @ManyToOne(targetEntity = User.class)
    private User creator;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "created_on", nullable = false,updatable = false)
    private LocalDateTime createdOn;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true,targetEntity = PostReport.class)
    private List<PostReport> reports;

}
