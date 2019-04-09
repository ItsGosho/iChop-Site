package com.ichop.core;

import com.ichop.core.areas.comment.domain.entities.Comment;
import com.ichop.core.areas.log.domain.entities.UserLog;
import com.ichop.core.areas.log.domain.entities.UserLogType;
import com.ichop.core.areas.post.domain.entities.Post;
import com.ichop.core.areas.reaction.domain.entities.CommentReaction;
import com.ichop.core.areas.reaction.domain.entities.ReactionType;
import com.ichop.core.areas.reaction.domain.entities.ThreadReaction;
import com.ichop.core.areas.role.domain.entities.UserRole;
import com.ichop.core.areas.role.domain.entities.UserRoles;
import com.ichop.core.areas.thread.domain.entities.Thread;
import com.ichop.core.areas.user.domain.entities.User;
import com.ichop.core.areas.user.domain.entities.UserFollow;
import org.apache.commons.lang3.RandomStringUtils;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.LinkedList;

public class EntityFactory {

    public User createUser(){
        User user = new User();
        user.setUsername("username");
        user.setPassword("password");
        user.setEmail("email@domain.bg");
        user.setLocation("location");
        user.setRegistrationDate(LocalDateTime.now());
        user.setAuthorities(new HashSet<>());
        user.setLogs(new LinkedList<>());
        user.setEnabled(true);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);

        return user;
    }

    public UserRole createUserRole(UserRoles userRoles){
        UserRole userRole = new UserRole();
        userRole.setAuthority(userRoles.name());

        return userRole;
    }

    public User createUserRANDOM(){
        User user = new User();
        user.setUsername(RandomStringUtils.randomAlphabetic(5));
        user.setPassword(RandomStringUtils.randomAlphabetic(5));
        user.setEmail(RandomStringUtils.randomAlphabetic(5) +
                "@" +
                RandomStringUtils.randomAlphabetic(3) +
                "." +
                RandomStringUtils.randomAlphabetic(2));
        user.setLocation(RandomStringUtils.randomAlphabetic(5));
        user.setRegistrationDate(LocalDateTime.now());
        user.setAuthorities(new HashSet<>());
        user.setLogs(new LinkedList<>());
        user.setEnabled(true);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);

        return user;
    }

    public Comment createComment(){
        Comment comment = new Comment();
        comment.setContent("content");
        comment.setCreatedOn(LocalDateTime.now());
        comment.setReports(new LinkedList<>());
        comment.setReactions(new LinkedList<>());

        return comment;
    }

    public Thread createThread(){
        Thread thread = new Thread();
        thread.setTitle("title");
        thread.setContent("content");
        thread.setCreatedOn(LocalDateTime.now());
        thread.setViews(0);
        thread.setReports(new LinkedList<>());
        thread.setComments(new LinkedList<>());
        thread.setReactions(new LinkedList<>());

        return thread;
    }

    public UserLog createUserLog(UserLogType logType){
        UserLog userLog = new UserLog();
        userLog.setMessage("message");
        userLog.setHappenedOn(LocalDateTime.now());
        userLog.setLogType(logType);

        return userLog;
    }

    public Post createPost(){
        Post post = new Post();
        post.setContent("content");
        post.setCreatedOn(LocalDateTime.now());
        post.setReports(new LinkedList<>());

        return post;
    }

    public CommentReaction createCommentReaction(){
        CommentReaction commentReaction = new CommentReaction();
        commentReaction.setReactionDate(LocalDateTime.now());
        commentReaction.setReactionType(ReactionType.LIKE);

        return commentReaction;
    }

    public CommentReaction createCommentReaction(User user,Comment comment){
        CommentReaction commentReaction = new CommentReaction();
        commentReaction.setReactionDate(LocalDateTime.now());
        commentReaction.setReactionType(ReactionType.LIKE);
        commentReaction.setUser(user);
        commentReaction.setComment(comment);

        return commentReaction;
    }

    public CommentReaction createCommentReaction(User user,Comment comment,ReactionType reactionType){
        CommentReaction commentReaction = new CommentReaction();
        commentReaction.setReactionDate(LocalDateTime.now());
        commentReaction.setReactionType(reactionType);
        commentReaction.setUser(user);
        commentReaction.setComment(comment);

        return commentReaction;
    }

    public ThreadReaction createThreadReaction(){
        ThreadReaction threadReaction = new ThreadReaction();
        threadReaction.setReactionDate(LocalDateTime.now());
        threadReaction.setReactionType(ReactionType.LIKE);

        return threadReaction;
    }

    public ThreadReaction createThreadReaction(User user,Thread thread){
        ThreadReaction threadReaction = new ThreadReaction();
        threadReaction.setReactionDate(LocalDateTime.now());
        threadReaction.setReactionType(ReactionType.LIKE);
        threadReaction.setThread(thread);
        threadReaction.setUser(user);

        return threadReaction;
    }

    public ThreadReaction createThreadReaction(User user,Thread thread,ReactionType reactionType){
        ThreadReaction threadReaction = new ThreadReaction();
        threadReaction.setReactionDate(LocalDateTime.now());
        threadReaction.setReactionType(reactionType);
        threadReaction.setThread(thread);
        threadReaction.setUser(user);

        return threadReaction;
    }

    public UserFollow createUserFollow(User user,User follower){
        UserFollow userFollow = new UserFollow();
        userFollow.setUser(user);
        userFollow.setFollower(follower);
        userFollow.setSince(LocalDateTime.now());

        return userFollow;
    }

}
