package com.ichop.core.areas.thread.repositories;

import com.ichop.core.areas.thread.domain.entities.Thread;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ThreadRepository extends JpaRepository<Thread,String> {

    List<Thread> findAllByOrderByCreatedOnAsc();
    Thread findThreadById(String id);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Thread AS t\n" +
            "SET t.views = t.views + 1\n" +
            "WHERE t.id = :threadId")
    void increaseViews(@Param(value = "threadId") String threadId);
}
