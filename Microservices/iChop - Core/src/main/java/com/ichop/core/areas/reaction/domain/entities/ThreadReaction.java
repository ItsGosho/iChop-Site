package com.ichop.core.areas.reaction.domain.entities;

import com.ichop.core.areas.thread.domain.entities.Thread;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity(name = "ThreadReaction")
@Table(name = "threads_reactions")
public class ThreadReaction extends BaseReaction {

    @ManyToOne(targetEntity = Thread.class)
    private Thread thread;

}
