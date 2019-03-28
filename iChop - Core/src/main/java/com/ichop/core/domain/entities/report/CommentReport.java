package com.ichop.core.domain.entities.report;

import com.ichop.core.domain.entities.comment.Comment;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity(name = "CommentReport")
@Table(name = "comments_reports")
public class CommentReport extends BaseReport {

    @ManyToOne(targetEntity = Comment.class)
    private Comment comment;

}
