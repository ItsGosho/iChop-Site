package com.ichop.core.areas.user.domain.models.view.user_profile;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class UserProfileViewModel {

    private String username;
    private String minecraftAccountName;
    private UserInformationProfileViewModel information;
    private String role;
    private String location;
    private LocalDateTime lastOnline;
    private LocalDateTime registrationDate;
    private Integer totalMessages;
    private Integer totalLikes;
    private Integer totalDislikes;
    private List<PostsUserProfileViewModel> posts;

    private Integer totalFollowers;
    private Integer totalFollowing;
    private List<UserProfileViewModel> followers;
    private List<UserProfileViewModel> followings;

    //TODO:

}
