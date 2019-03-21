package com.ichop.core.domain.models.binding.comment;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CommentCreateBindingModel {

    @NotNull
    @NotEmpty
    private String content;
}
