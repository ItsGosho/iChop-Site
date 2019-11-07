package ichop.users.domain.entities;

import lombok.Getter;
import lombok.Setter;
import org.ichop.commons.domain.MySQLEntity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Getter
@Setter
@Entity(name = "UserInformation")
@Table(name = "users_information")
public class UserInformation extends MySQLEntity {

    private String statusMessage;
    private LocalDate birthDate;
    private String aboutYou;

    @OneToOne(targetEntity = User.class)
    private User user;

}
