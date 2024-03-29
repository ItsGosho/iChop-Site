package com.ichop.core.areas.user.domain.models.view.user_profile;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostsUserProfileViewModel {

    private String id;
    private String content;
    private String creatorUsername;
    private LocalDateTime createdOn;

}
