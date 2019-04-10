package com.ichop.core.areas.post.domain.models.binding;

import com.ichop.core.areas.user.domain.models.service.UserServiceModel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PostCreateBindingModel {

    @NotNull
    @NotEmpty
    @Length(min = 3,max = 150)
    private String content;

    @NotNull
    private UserServiceModel user;

    @NotNull
    private UserServiceModel creator;
}
