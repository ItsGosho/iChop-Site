package com.ichop.core.repository.post;

import com.ichop.core.domain.entities.post.Post;
import com.ichop.core.domain.entities.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,String> {

    List<Post> findAllByUser(User user);

}
