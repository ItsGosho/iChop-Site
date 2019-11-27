package ichop.comments.services;

import ichop.comments.annotations.CommentType;
import ichop.comments.domain.entities.Comment;
import ichop.comments.domain.entities.ThreadComment;
import ichop.comments.domain.entities.UserProfileComment;
import ichop.comments.domain.enums.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@SuppressWarnings("all")
public class GenericCommentServices {

    private final ApplicationContext applicationContext;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public GenericCommentServices(ApplicationContext applicationContext, MongoTemplate mongoTemplate) {
        this.applicationContext = applicationContext;
        this.mongoTemplate = mongoTemplate;
    }

    public <C extends Comment> void deleteById(String id, Type type) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));

        this.mongoTemplate.remove(query, this.getEntity(type));
    }

    public <C extends Comment> boolean existsById(String id, Type type) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));

        return this.mongoTemplate.findOne(query, this.getEntity(type)) != null;
    }

    public <C extends Comment> boolean isCreator(String id, String creatorUsername, Type type) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        C comment = (C) this.mongoTemplate.findOne(query, this.getEntity(type));

        return comment.getCreatorUsername().equals(creatorUsername);
    }

    /*TODO: dont hardcore iterate them ,bat jorko! 2019*/
    public Long findCreatorTotal(String username) {
        Query query = new Query();
        query.addCriteria(Criteria.where("creatorUsername").is(username));

        Long totalThreadComments = this.mongoTemplate.count(query, ThreadComment.class);
        Long totalUserProfileComments = this.mongoTemplate.count(query, UserProfileComment.class);

        return totalThreadComments + totalUserProfileComments;
    }

    private Class getEntity(Type type) {
        Map<String, Object> beans = this.applicationContext.getBeansWithAnnotation(CommentType.class);

        for (Object bean : beans.values()) {
            if (bean.getClass().getAnnotation(CommentType.class).type().equals(type)) {
                return bean.getClass();
            }
        }

        return null;
    }

}
