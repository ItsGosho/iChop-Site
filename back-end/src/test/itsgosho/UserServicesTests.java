package itsgosho;

import itsgosho.service.user.UserServices;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@EntityScan("itsgosho")
@ComponentScan("itsgosho")
@ActiveProfiles("test")
public class UserServicesTests {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserServices userServices;

    @Before
    public void setUp() {

    }



//    @Test
//    public void register_whenUserAlreadyExists_shouldReturnFalse() {
//        User user = this.createCustomUser("itsgosho", "123", "bomba@gmail.bg", LocalDateTime.now());
//        this.entityManager.persist(user);
//        this.entityManager.flush();
//
//        UserRegisterBindingModel userRegBindModel = this.createCustomUserRegisterBindingModel("itsgosho", "123","123", "bomba@gmail.bg");
//
//        boolean result = this.userServices.register(userRegBindModel);
//
//        Assert.assertFalse(result);
//    }
//
//    @Test
//    public void register_whenEmailAlreadyExists_shouldReturnFalse() {
//        User user = this.createCustomUser("bomba", "123", "bomba@gmail.bg", LocalDateTime.now());
//        this.entityManager.persist(user);
//        this.entityManager.flush();
//
//        UserRegisterBindingModel userRegBindModel = this.createCustomUserRegisterBindingModel("itsgosho", "123","123", "bomba@gmail.bg");
//        boolean result = this.userServices.register(userRegBindModel);
//
//        Assert.assertFalse(result);
//    }
//
//    @Test
//    public void register_whenUsernameContainsUppercase_shouldResultBeLowercase() {
//        UserRegisterBindingModel userRegBindModel = this.createCustomUserRegisterBindingModel("ItsGosho", "123","123", "bomba@gmail.bg");
//        this.userServices.register(userRegBindModel);
//        User user = this.userServices.getUserByUsername("itsgosho");
//        Assert.assertNotNull(user);
//
//    }
//
//    @Test
//    public void register_whenEmailContainsUppercase_shouldResultBeLowercase() {
//        UserRegisterBindingModel userRegBindModel = this.createCustomUserRegisterBindingModel("itsgosho", "123","123", "BOMBA@GMail.bg");
//        this.userServices.register(userRegBindModel);
//        User user = this.userServices.getUserByEmail("bomba@gmail.bg");
//        Assert.assertNotNull(user);
//
//    }
//
//    @Test
//    public void register_whenUserIsFirstRegistered_shouldHaveRankOwner(){
//        UserRegisterBindingModel userRegBindModel = this.createCustomUserRegisterBindingModel("itsgosho", "123","123", "bomba@gmail.bg");
//        this.userServices.register(userRegBindModel);
//        User user = this.userServices.getUserByUsername("itsgosho");
//
//        Assert.assertEquals("OWNER",this.userServices.getRole(user).getAuthority());
//    }
//
//    //@Note: after the first user of course!
//    @Test
//    public void register_whenUserIsRegistered_shouldHaveRankUser(){
//        UserRegisterBindingModel userRegBindModel = this.createCustomUserRegisterBindingModel("itsgosho", "123","123", "bomba@gmail.bg");
//        UserRegisterBindingModel userRegBindModel2 = this.createCustomUserRegisterBindingModel("bombastiko", "123","123", "bomba2@gmail.bg");
//        this.userServices.register(userRegBindModel);
//        this.userServices.register(userRegBindModel2);
//        User user = this.userServices.getUserByUsername("bombastiko");
//
//        Assert.assertEquals("USER",this.userServices.getRole(user).getAuthority());
//    }
//
//    //------------------------------------------------------------------------------------------------------------------
//
//    @Test
//    public void loadByUsername_whenValidUsernameIsPassed_shouldAcceptItAsUsername(){
//        UserRegisterBindingModel userRegBindModel = this.createCustomUserRegisterBindingModel("itsgosho", "123","123", "bomba@gmail.bg");
//        this.userServices.register(userRegBindModel);
//
//        User user = (User) this.userServices.loadUserByUsername(userRegBindModel.getUsername());
//        Assert.assertNotNull(user);
//    }
//
//    @Test
//    public void loadByUsername_whenValidEmailIsPassed_shouldAcceptItAsEmail(){
//        UserRegisterBindingModel userRegBindModel = this.createCustomUserRegisterBindingModel("itsgosho", "123","123", "bomba@gmail.bg");
//        this.userServices.register(userRegBindModel);
//
//        User user = (User) this.userServices.loadUserByUsername(userRegBindModel.getEmail());
//        Assert.assertNotNull(user);
//    }
//
//    //------------------------------------------------------------------------------------------------------------------
//
//    @Test
//    public void initialAuthorities(){
//        //TODO:!
//    }
//
//
//
//
//
//    private User createCustomUser(String username, String password, String email, LocalDateTime registrationDate) {
//        User user = new User();
//        user.setUsername(username);
//        user.setPassword(password);
//        user.setEmail(email);
//        user.setRegistrationDate(registrationDate);
//        return user;
//    }
//
//    private UserRegisterBindingModel createCustomUserRegisterBindingModel(String username, String password, String confirmPassword, String email) {
//        UserRegisterBindingModel userRegisterBindingModel = new UserRegisterBindingModel();
//        userRegisterBindingModel.setUsername(username);
//        userRegisterBindingModel.setPassword(password);
//        userRegisterBindingModel.setConfirmPassword(confirmPassword);
//        userRegisterBindingModel.setEmail(email);
//        return userRegisterBindingModel;
//    }
}
