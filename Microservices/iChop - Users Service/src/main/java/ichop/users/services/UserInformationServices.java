package ichop.users.services;

import ichop.users.domain.models.jms.information.UserInformationUpdateRequest;
import ichop.users.domain.models.service.UserInformationServiceModel;
import ichop.users.domain.models.service.UserServiceModel;

public interface UserInformationServices {


    UserInformationServiceModel update(UserInformationUpdateRequest request);

    UserInformationServiceModel getByUser(String username);

    UserInformationServiceModel createIfNotPresent(String username);

    boolean hasInformation(String username);
}
