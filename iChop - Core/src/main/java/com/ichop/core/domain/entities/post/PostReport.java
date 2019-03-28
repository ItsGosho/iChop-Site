package com.ichop.core.domain.entities.post;

import com.ichop.core.domain.entities.report.BaseReport;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity(name = "PostReport")
@Table(name = "posts_reports")
public class PostReport extends BaseReport {

    @ManyToOne(targetEntity = Post.class)
    private Post post;

}
