package ichop.domain.entities.users;

import ichop.domain.entities.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "users_profile_information")
public class UserInformation extends BaseEntity {

    private String statusMessage;
    private String avatarPath;
    private LocalDate birthDate;
    private String aboutYou;

    @OneToOne(optional = false)
    private User user;

}
