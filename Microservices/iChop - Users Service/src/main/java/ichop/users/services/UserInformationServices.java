package ichop.users.services;

import ichop.users.domain.models.service.UserInformationServiceModel;
import ichop.users.domain.models.service.UserServiceModel;

public interface UserInformationServices {


    UserInformationServiceModel update(UserInformationServiceModel model);

    UserInformationServiceModel getByUser(UserServiceModel user);

    UserInformationServiceModel createIfNotPresent(UserServiceModel user);

    boolean hasInformation(UserServiceModel user);
}
