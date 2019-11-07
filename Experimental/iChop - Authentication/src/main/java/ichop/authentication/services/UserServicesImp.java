package ichop.authentication.services;

import ichop.authentication.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServicesImp implements UserServices {

    private final UserRepository userRepository;

    @Autowired
    public UserServicesImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



}
