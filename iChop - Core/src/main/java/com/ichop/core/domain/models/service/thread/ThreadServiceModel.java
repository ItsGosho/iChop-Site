package com.ichop.core.domain.models.service.thread;

import com.ichop.core.domain.models.service.comment.CommentServiceModel;
import com.ichop.core.domain.entities.report.ThreadReport;
import com.ichop.core.domain.models.service.BaseServiceModel;
import com.ichop.core.domain.models.service.reaction.ThreadReactionServiceModel;
import com.ichop.core.domain.models.service.user.UserServiceModel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ThreadServiceModel extends BaseServiceModel {

    private String title;
    private String content;
    private UserServiceModel creator;
    private LocalDateTime createdOn;
    private List<CommentServiceModel> comments;
    private Integer views;
    private List<ThreadReactionServiceModel> reactions;
    private List<ThreadReport> reports;

}
