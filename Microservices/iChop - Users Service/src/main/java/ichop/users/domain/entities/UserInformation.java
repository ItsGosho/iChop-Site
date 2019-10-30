package ichop.users.domain.entities;

import ichop.users.common.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Getter
@Setter
@Entity(name = "UserInformation")
@Table(name = "users_information")
public class UserInformation extends BaseEntity {

    private String statusMessage;
    private LocalDate birthDate;
    private String aboutYou;

    @OneToOne(targetEntity = User.class)
    private User user;

}
