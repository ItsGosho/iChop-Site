package com.ichop.core.areas.thread.domain.models.binding;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ThreadCreateBindingModel {

    @NotNull
    @NotEmpty
    @Length(min = 3,max = 50)
    private String title;

    @NotNull
    @NotEmpty
    private String content;

}
