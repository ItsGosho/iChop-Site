package ichop.domain.models.service;

import ichop.domain.entities.threads.ReactionType;

public class ReactServiceModel {

    private String id;
    private UserServiceModel user;
    private ReactionType reactionType;

    public UserServiceModel getUser() {
        return user;
    }

    public void setUser(UserServiceModel user) {
        this.user = user;
    }

    public ReactionType getReactionType() {
        return reactionType;
    }

    public void setReactionType(ReactionType reactionType) {
        this.reactionType = reactionType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
