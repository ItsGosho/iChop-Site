package com.ichop.core.domain.models.view.thread_read;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentCreatorThreadReadViewModel {

    private String username;
    private String minecraftAccountName;
    private Integer totalComments;

}
