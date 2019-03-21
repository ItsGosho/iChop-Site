package com.ichop.core.domain.models.service.comment;

import com.ichop.core.domain.models.service.BaseServiceModel;
import com.ichop.core.domain.models.service.reaction.CommentReactionServiceModel;
import com.ichop.core.domain.models.service.report.CommentReportServiceModel;
import com.ichop.core.domain.models.service.thread.ThreadServiceModel;
import com.ichop.core.domain.models.service.user.UserServiceModel;
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
