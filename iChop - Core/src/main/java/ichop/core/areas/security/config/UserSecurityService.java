package ichop.core.areas.security.config;

import ichop.core.areas.user.requester.UserRequester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserSecurityService implements UserDetailsService {

    private final UserRequester userRequester;

    @Autowired
    private UserSecurityService(UserRequester userRequester) {
        this.userRequester = userRequester;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRequester.findByEmail(username);
    }
}