package com.ichop.core.areas.thread.domain.models.view.thread_read;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentThreadReadViewModel {

    private String id;
    private String content;
    private CommentCreatorThreadReadViewModel commentCreator;
    private LocalDateTime createdOn;
    private Long totalLikes;
    private Long totalDislikes;
}
