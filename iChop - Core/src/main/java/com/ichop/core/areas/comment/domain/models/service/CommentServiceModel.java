package com.ichop.core.areas.comment.domain.models.service;

import com.ichop.core.base.BaseServiceModel;
import com.ichop.core.areas.reaction.domain.models.service.CommentReactionServiceModel;
import com.ichop.core.areas.report.domain.models.service.CommentReportServiceModel;
import com.ichop.core.areas.thread.domain.models.service.ThreadServiceModel;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class CommentServiceModel extends BaseServiceModel {

    public ThreadServiceModel thread;
    public String content;
    public UserServiceModel creator;
    public LocalDateTime createdOn;
    private List<CommentReactionServiceModel> reactions;
    private List<CommentReportServiceModel> reports;

}
