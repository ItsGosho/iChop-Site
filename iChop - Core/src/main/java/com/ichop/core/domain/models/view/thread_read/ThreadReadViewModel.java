package com.ichop.core.domain.models.view.thread_read;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ThreadReadViewModel {

    private String id;
    private String title;
    private LocalDateTime createdOn;
    private String creatorUsername;
    private Integer creatorTotalComments;
    private Integer totalViews;
    private Integer totalReactions;
    private Integer totalComments;
    private String content;
    private Integer rowsForNewsPage;
    private List<CommentThreadReadViewModel> comments;

}
