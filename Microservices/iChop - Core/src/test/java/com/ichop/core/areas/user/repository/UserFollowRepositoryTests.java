package com.ichop.core.areas.user.repository;

import com.ichop.core.EntityFactory;
import com.ichop.core.areas.user.domain.entities.User;
import com.ichop.core.areas.user.domain.entities.UserFollow;
import com.ichop.core.areas.user.repositories.UserFollowRepository;
import com.ichop.core.areas.user.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class UserFollowRepositoryTests {


    @Autowired
    private UserFollowRepository userFollowRepository;

    @Autowired
    private UserRepository userRepository;

    private EntityFactory entityFactory;

    @Before
    public void setUp() {
        this.entityFactory = new EntityFactory();
    }

    @Test
    public void findByUserAndHisFollower_withValidData_shouldReturnTrue(){
        User user = this.userRepository.save(this.entityFactory.createUserRANDOM());
        User follower = this.userRepository.save(this.entityFactory.createUserRANDOM());
        this.userRepository.save(user);
        this.userRepository.save(follower);

        UserFollow userFollow = this.entityFactory.createUserFollow(user,follower);
        this.userFollowRepository.save(userFollow);

        UserFollow result = this.userFollowRepository.findByUserAndHisFollower(user,follower);

        assertEquals(result.getUser().getUsername(),user.getUsername());
        assertEquals(result.getFollower().getUsername(),follower.getUsername());
    }

    @Test
    public void isUserAlreadyFollowedUser_withFollowingUser_shouldReturnTrue(){
        User user = this.userRepository.save(this.entityFactory.createUserRANDOM());
        User follower = this.userRepository.save(this.entityFactory.createUserRANDOM());
        this.userRepository.save(user);
        this.userRepository.save(follower);

        UserFollow userFollow = this.entityFactory.createUserFollow(user,follower);
        this.userFollowRepository.save(userFollow);

        boolean result = this.userFollowRepository.isUserAlreadyFollowedUser(follower,user);

        assertTrue(result);
    }

    @Test
    public void isUserAlreadyFollowedUser_withUserNotFollowingUser_shouldReturnFalse(){
        User user = this.userRepository.save(this.entityFactory.createUserRANDOM());
        User follower = this.userRepository.save(this.entityFactory.createUserRANDOM());
        this.userRepository.save(user);
        this.userRepository.save(follower);

        boolean result = this.userFollowRepository.isUserAlreadyFollowedUser(follower,user);

        assertFalse(result);
    }

    @Test
    public void isUserAlreadyFollowedUser_withUserFollowingAnotherUser_shouldReturnFalse(){
        User user = this.userRepository.save(this.entityFactory.createUserRANDOM());
        User follower = this.userRepository.save(this.entityFactory.createUserRANDOM());
        User anotherUser = this.userRepository.save(this.entityFactory.createUserRANDOM());
        this.userRepository.save(user);
        this.userRepository.save(follower);
        this.userRepository.save(anotherUser);

        UserFollow userFollow = this.entityFactory.createUserFollow(anotherUser,user);
        this.userFollowRepository.save(userFollow);

        boolean result = this.userFollowRepository.isUserAlreadyFollowedUser(user,follower);

        assertFalse(result);
    }

    @Test
    public void findUserFollowings_withUserNoFollowings_shouldReturnEmptyList(){
        User user = this.userRepository.save(this.entityFactory.createUserRANDOM());
        this.userRepository.save(user);

        List<UserFollow> result = this.userFollowRepository.findUserFollowings(user);

        assertEquals(result.size(),0);
    }

    @Test
    public void findUserFollowings_withUserFollowing3Users_shouldReturnListOfSize3(){
        User user = this.userRepository.save(this.entityFactory.createUserRANDOM());
        User follower1 = this.userRepository.save(this.entityFactory.createUserRANDOM());
        User follower2 = this.userRepository.save(this.entityFactory.createUserRANDOM());
        User follower3 = this.userRepository.save(this.entityFactory.createUserRANDOM());
        this.userRepository.save(user);
        this.userRepository.save(follower1);
        this.userRepository.save(follower2);
        this.userRepository.save(follower3);

        UserFollow userFollow1 = this.entityFactory.createUserFollow(follower1,user);
        UserFollow userFollow2 = this.entityFactory.createUserFollow(follower2,user);
        UserFollow userFollow3 = this.entityFactory.createUserFollow(follower3,user);
        this.userFollowRepository.save(userFollow1);
        this.userFollowRepository.save(userFollow2);
        this.userFollowRepository.save(userFollow3);

        List<UserFollow> result = this.userFollowRepository.findUserFollowings(user);

        assertEquals(result.size(),3);
    }

    @Test
    public void findUserFollowers_withUserNoFollowers_shouldReturnEmptyList(){
        User user = this.userRepository.save(this.entityFactory.createUserRANDOM());
        this.userRepository.save(user);

        List<UserFollow> result = this.userFollowRepository.findUserFollowers(user);

        assertEquals(result.size(),0);
    }


    @Test
    public void findUserFollowers_withUserFollowedBy3Users_shouldReturnListOfSize3(){
        User user = this.userRepository.save(this.entityFactory.createUserRANDOM());
        User follower1 = this.userRepository.save(this.entityFactory.createUserRANDOM());
        User follower2 = this.userRepository.save(this.entityFactory.createUserRANDOM());
        User follower3 = this.userRepository.save(this.entityFactory.createUserRANDOM());
        this.userRepository.save(user);
        this.userRepository.save(follower1);
        this.userRepository.save(follower2);
        this.userRepository.save(follower3);

        UserFollow userFollow1 = this.entityFactory.createUserFollow(user,follower1);
        UserFollow userFollow2 = this.entityFactory.createUserFollow(user,follower2);
        UserFollow userFollow3 = this.entityFactory.createUserFollow(user,follower3);
        this.userFollowRepository.save(userFollow1);
        this.userFollowRepository.save(userFollow2);
        this.userFollowRepository.save(userFollow3);

        List<UserFollow> result = this.userFollowRepository.findUserFollowers(user);

        assertEquals(result.size(),3);
    }

    @Test
    public void getUserTotalFollowings_withUserFollowinge1Users_shouldReturn1(){
        User user = this.userRepository.save(this.entityFactory.createUserRANDOM());
        User follower = this.userRepository.save(this.entityFactory.createUserRANDOM());
        this.userRepository.save(user);
        this.userRepository.save(follower);

        UserFollow userFollow = this.entityFactory.createUserFollow(follower,user);
        this.userFollowRepository.save(userFollow);

        int result = this.userFollowRepository.getUserTotalFollowings(user);

        assertEquals(result,1);
    }


    @Test
    public void getUserTotalFollowers_withUserHaving3Followers_shouldReturn3(){
        User user = this.userRepository.save(this.entityFactory.createUserRANDOM());
        User follower = this.userRepository.save(this.entityFactory.createUserRANDOM());
        this.userRepository.save(user);
        this.userRepository.save(follower);

        UserFollow userFollow = this.entityFactory.createUserFollow(user,follower);
        this.userFollowRepository.save(userFollow);

        int result = this.userFollowRepository.getUserTotalFollowers(user);

        assertEquals(result,1);
    }

}
