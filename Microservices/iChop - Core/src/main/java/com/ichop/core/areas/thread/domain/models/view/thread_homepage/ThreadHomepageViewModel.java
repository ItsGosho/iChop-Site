package com.ichop.core.areas.thread.domain.models.view.thread_homepage;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ThreadHomepageViewModel {

    private String id;
    private String title;
    private LocalDateTime createdOn;
    private String creatorUsername;
    private Integer totalViews;
    private Integer totalReactions;
    private Integer totalComments;
    private String content;
    private Integer rowsForNewsPage;

}
