package com.ichop.core.areas.post.repositories;

import com.ichop.core.areas.post.domain.entities.Post;
import com.ichop.core.areas.user.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,String> {

    List<Post> findAllByUser(User user);

}
