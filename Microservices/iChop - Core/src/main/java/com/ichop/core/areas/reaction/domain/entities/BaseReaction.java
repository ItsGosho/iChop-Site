package com.ichop.core.areas.reaction.domain.entities;

import com.ichop.core.base.BaseEntity;
import com.ichop.core.areas.user.domain.entities.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseReaction extends BaseEntity {

    @ManyToOne(targetEntity = User.class)
    private User user;

    @Column(name = "reaction_type",nullable = false,updatable = false)
    @Enumerated(value = EnumType.STRING)
    private ReactionType reactionType;

    @Column(name = "reaction_date",nullable = false,updatable = false)
    private LocalDateTime reactionDate;

}
