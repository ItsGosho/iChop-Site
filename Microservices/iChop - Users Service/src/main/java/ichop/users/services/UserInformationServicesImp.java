package ichop.users.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.users.common.service.AbstractBaseService;
import ichop.users.domain.entities.User;
import ichop.users.domain.entities.UserInformation;
import ichop.users.domain.models.service.UserInformationServiceModel;
import ichop.users.domain.models.service.UserServiceModel;
import ichop.users.repositories.UserInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class UserInformationServicesImp
        extends AbstractBaseService<UserInformation, UserInformationServiceModel, UserInformationRepository>
        implements UserInformationServices {


    @Autowired
    public UserInformationServicesImp(ObjectMapper objectMapper, UserInformationRepository repository) {
        super(objectMapper, repository);
    }


    @Override
    public UserInformationServiceModel update(UserInformationServiceModel model) {

        UserInformationServiceModel information = this.createIfNotPresent(model.getUser());

        try {
            information = super.objectMapper.readerForUpdating(information).readValue(super.objectMapper.writeValueAsString(model));
            super.save(information);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return information;
    }

    @Override
    public UserInformationServiceModel getByUser(UserServiceModel user) {
        User entityUser = this.objectMapper.convertValue(user, User.class);
        UserInformation userInformation = this.repository.findByUser(entityUser);
        return userInformation != null ? this.objectMapper.convertValue(userInformation, UserInformationServiceModel.class) : null;
    }

    @Override
    public UserInformationServiceModel createIfNotPresent(UserServiceModel user) {

        if (!this.hasInformation(user)) {
            UserInformationServiceModel userInformation = new UserInformationServiceModel();
            userInformation.setUser(user);

            return this.save(userInformation, UserInformationServiceModel.class);
        }

        return this.getByUser(user);
    }

    @Override
    public boolean hasInformation(UserServiceModel user) {
        User entityUser = super.objectMapper.convertValue(user, User.class);
        return super.repository.existsByUser(entityUser);
    }


}
