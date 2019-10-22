package com.ichop.core.areas.user.web.fillers;

import com.ichop.core.EntityFactory;
import com.ichop.core.areas.user.domain.entities.User;
import com.ichop.core.areas.user.domain.models.binding.UserResetPasswordBindingModelByToken;
import com.ichop.core.areas.user.domain.models.binding.UserResetPasswordBindingModelByUser;
import com.ichop.core.areas.user.repositories.UserRepository;
import com.ichop.core.areas.user.services.UserServices;
import com.ichop.core.areas.user.services.UserServicesImp;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.Principal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class UserBindingModelsFillersIntegrationTests {


    @Autowired
    private UserRepository userRepository;

    private UserServices userServices;
    private EntityFactory entityFactory;
    private ModelMapper modelMapper;

    private UserBindingModelsFillers userBindingModelsFillers;

    @Before
    public void setUp() {
        this.modelMapper = new ModelMapper();
        this.entityFactory = new EntityFactory();

        this.userServices = new UserServicesImp(null, this.modelMapper, this.userRepository, null, null, null, null);
        this.userBindingModelsFillers = new UserBindingModelsFillers(this.userServices);
    }

    @Test
    public void fillByUser_withNullUser_shouldSetNullUser() {
        UserResetPasswordBindingModelByUser bindingModelByUser = new UserResetPasswordBindingModelByUser();
        Principal principal = () -> "notExistingUsername";

        UserResetPasswordBindingModelByUser result = this.userBindingModelsFillers.fill(bindingModelByUser,principal);

        assertNull(result.getUser());
    }

    @Test
    public void fillByUser_withValidData_shouldUser() {
        User user = this.userRepository.save(this.entityFactory.createUser());
        UserResetPasswordBindingModelByUser bindingModelByUser = new UserResetPasswordBindingModelByUser();
        Principal principal = () -> user.getUsername();

        UserResetPasswordBindingModelByUser result = this.userBindingModelsFillers.fill(bindingModelByUser,principal);

        assertNotNull(result.getUser());
    }

    @Test
    public void fillByUser_withValidData_shouldNotChangeTheBindingModel() {
        User user = this.userRepository.save(this.entityFactory.createUser());
        UserResetPasswordBindingModelByUser bindingModelByUser = new UserResetPasswordBindingModelByUser();
        bindingModelByUser.setPassword("password");
        bindingModelByUser.setConfirmPassword("confirmPassword");
        Principal principal = () -> user.getUsername();

        UserResetPasswordBindingModelByUser result = this.userBindingModelsFillers.fill(bindingModelByUser,principal);

        assertNotNull(result.getUser());
        assertEquals(bindingModelByUser.getPassword(),result.getPassword());
        assertEquals(bindingModelByUser.getConfirmPassword(),result.getConfirmPassword());
    }


    @Test
    public void fillByToken_withValidData_shouldNotChangeTheBindingModel() {
        User user = this.userRepository.save(this.entityFactory.createUser());
        UserResetPasswordBindingModelByToken bindingModelByToken = new UserResetPasswordBindingModelByToken();
        bindingModelByToken.setPassword("password");
        bindingModelByToken.setConfirmPassword("confirmPassword");
        Principal principal = () -> user.getUsername();

        UserResetPasswordBindingModelByToken result = this.userBindingModelsFillers.fill(bindingModelByToken,"token",principal);

        assertEquals(bindingModelByToken.getToken(),"token");
        assertEquals(bindingModelByToken.getPassword(),result.getPassword());
        assertEquals(bindingModelByToken.getConfirmPassword(),result.getConfirmPassword());
    }

}
