package ichop.users.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.users.domain.entities.User;
import ichop.users.domain.entities.UserInformation;
import ichop.users.domain.models.jms.information.UserInformationUpdateRequest;
import ichop.users.domain.models.service.UserInformationServiceModel;
import ichop.users.repositories.UserInformationRepository;
import org.ichop.commons.service.AbstractMySQLBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class UserInformationServicesImp
        extends AbstractMySQLBaseService<UserInformation, UserInformationServiceModel, UserInformationRepository>
        implements UserInformationServices {

    private static final String BIRTHDATE_FORMAT = "d/MM/yyyy";
    private final UserServices userServices;

    @Autowired
    public UserInformationServicesImp(ObjectMapper objectMapper, UserInformationRepository repository, UserServices userServices) {
        super(objectMapper, repository);
        this.userServices = userServices;
    }


    @Override
    public UserInformationServiceModel update(UserInformationUpdateRequest request) {

        UserInformationServiceModel information = this.createIfNotPresent(request.getUsername());

        LocalDate birthDate = null;

        try {
            birthDate = LocalDate.parse(request.getBirthDate(), DateTimeFormatter.ofPattern(BIRTHDATE_FORMAT));
        } catch (Exception ignored) {

        }

        information.setAboutYou(request.getAboutYou() != null ? request.getAboutYou() : information.getAboutYou());
        information.setStatusMessage(request.getStatusMessage() != null ? request.getStatusMessage() : information.getStatusMessage());
        information.setBirthDate(birthDate != null ? birthDate : information.getBirthDate());

        return super.save(information);
    }

    @Override
    public UserInformationServiceModel getByUser(String username) {
        User entityUser = this.objectMapper.convertValue(this.userServices.findByUsername(username), User.class);
        UserInformation userInformation = this.repository.findByUser(entityUser);
        return userInformation != null ? this.objectMapper.convertValue(userInformation, UserInformationServiceModel.class) : null;
    }

    @Override
    public UserInformationServiceModel createIfNotPresent(String username) {

        if (!this.hasInformation(username)) {
            UserInformationServiceModel userInformation = new UserInformationServiceModel();
            userInformation.setUser(this.userServices.findByUsername(username));

            return this.save(userInformation, UserInformationServiceModel.class);
        }

        return this.getByUser(username);
    }

    @Override
    public boolean hasInformation(String username) {
        User entityUser = this.objectMapper.convertValue(this.userServices.findByUsername(username), User.class);
        return super.repository.existsByUser(entityUser);
    }


}
