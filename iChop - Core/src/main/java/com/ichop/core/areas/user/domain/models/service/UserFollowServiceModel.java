package com.ichop.core.areas.user.domain.models.service;

import com.ichop.core.base.BaseServiceModel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserFollowServiceModel extends BaseServiceModel {

    private UserServiceModel user;
    private UserServiceModel follower;
    private LocalDateTime since;

}
