package com.ichop.core.domain.models.view.thread_read;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentThreadReadViewModel {

    private String id;
    private String content;
    private String creatorUsername;
    private String minecraftAccountName;
    private Integer creatorTotalComments;
    private LocalDateTime createdOn;
    private Integer totalLikes;
    private Integer totalDislikes;
}
