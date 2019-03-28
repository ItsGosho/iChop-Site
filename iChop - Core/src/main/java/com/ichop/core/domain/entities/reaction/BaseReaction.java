package com.ichop.core.domain.entities.reaction;

import com.ichop.core.domain.entities.BaseEntity;
import com.ichop.core.domain.entities.users.User;
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
