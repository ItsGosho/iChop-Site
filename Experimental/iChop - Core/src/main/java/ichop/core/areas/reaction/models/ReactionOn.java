package ichop.core.areas.reaction.models;

public enum ReactionOn {

    THREAD, THREAD_COMMENT;

    public static boolean isValid(String val){
        return val.equals(THREAD.name()) || !val.equals(THREAD_COMMENT.name());
    }
}
