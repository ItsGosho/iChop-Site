package ichop.users.domain.entities;

import ichop.users.common.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@Document("users_information")
public class UserInformation extends BaseEntity {

    private String statusMessage;
    private LocalDate birthDate;
    private String aboutYou;

    @NotNull
    @DBRef
    private User user;

}
