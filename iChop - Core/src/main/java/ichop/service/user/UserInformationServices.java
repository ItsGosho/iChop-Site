package ichop.service.user;

import ichop.domain.models.binding.user.UserUpdateProfileInformationBindingModel;
import ichop.domain.models.service.user.UserInformationServiceModel;
import ichop.domain.models.service.user.UserServiceModel;

public interface UserInformationServices {

    UserInformationServiceModel update(UserUpdateProfileInformationBindingModel userUpdateProfileInformationBindingModel, UserServiceModel user);

    boolean isUserInformationExistsByUser(UserServiceModel user);

}
