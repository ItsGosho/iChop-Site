package ichop.factories.user;


import ichop.domain.entities.users.User;
import ichop.domain.models.binding.user.UserForgottenPasswordBindingModel;
import ichop.domain.models.binding.user.UserRegisterBindingModel;
import ichop.domain.models.binding.user.UserResetPasswordBindingModel;
import ichop.factories.role.UserRoleFactory;
import ichop.service.role.UserRoles;

public class UserFactoryImp implements UserFactory {

    private UserRoleFactory userRoleFactory;

    public UserFactoryImp(UserRoleFactory userRoleFactory){
        this.userRoleFactory = userRoleFactory;
    }

    public User create(String username, String email, String password, UserRoles... userRoles) {

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setAuthorities(this.userRoleFactory.bakeRoles(userRoles));

        return user;
    }

    public UserResetPasswordBindingModel create(String password, String confirmPassword) {

        UserResetPasswordBindingModel userResetPasswordBindingModel = new UserResetPasswordBindingModel();
        userResetPasswordBindingModel.setPassword(password);
        userResetPasswordBindingModel.setPassword(confirmPassword);

        return userResetPasswordBindingModel;
    }

    public UserForgottenPasswordBindingModel create(String usernameOrEmail) {

        UserForgottenPasswordBindingModel userForgottenPasswordBindingModel = new UserForgottenPasswordBindingModel();
        userForgottenPasswordBindingModel.setUsernameOrEmail(usernameOrEmail);

        return userForgottenPasswordBindingModel;
    }

    public UserRegisterBindingModel create(String username, String password, String confirmPassword, String email) {

        UserRegisterBindingModel userRegisterBindingModel = new UserRegisterBindingModel();
        userRegisterBindingModel.setUsername(username);
        userRegisterBindingModel.setPassword(password);
        userRegisterBindingModel.setConfirmPassword(confirmPassword);
        userRegisterBindingModel.setEmail(email);

        return userRegisterBindingModel;
    }
}
