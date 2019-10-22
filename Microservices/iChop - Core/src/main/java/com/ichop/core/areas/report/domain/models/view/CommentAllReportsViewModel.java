package com.ichop.core.areas.report.domain.models.view;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentAllReportsViewModel {

    private String commentId;
    private String commentContent;
    private String reason;
    private String creatorUsername;
    private LocalDateTime reportDate;
    private String reportId;

}
