package ichop.domain.entities.reaction;

import ichop.domain.entities.threads.Thread;
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

    @ManyToOne(optional = false)
    private Thread thread;

}
