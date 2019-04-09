package com.ichop.core.areas.report.domain.entities;

import com.ichop.core.areas.post.domain.entities.Post;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity(name = "PostReport")
@Table(name = "posts_reports")
public class PostReport extends BaseReport {

    @ManyToOne(targetEntity = Post.class,cascade = CascadeType.REMOVE)
    private Post post;

}
