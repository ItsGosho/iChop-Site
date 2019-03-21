package com.ichop.core.domain.entities.reaction;

import com.ichop.core.domain.entities.threads.Thread;
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

    @ManyToOne(optional = false,targetEntity = Thread.class)
    private Thread thread;

}
