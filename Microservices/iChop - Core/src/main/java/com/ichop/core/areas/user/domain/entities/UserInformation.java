package com.ichop.core.areas.user.domain.entities;

import com.ichop.core.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Getter
@Setter
@Entity(name = "UserInformation")
@Table(name = "users_profile_information")
public class UserInformation extends BaseEntity {

    @Column(name = "status_message")
    private String statusMessage;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "about_you")
    private String aboutYou;

    @OneToOne(targetEntity = User.class)
    private User user;

}
