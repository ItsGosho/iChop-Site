package ichop.service.user;

import ichop.domain.entities.users.User;
import ichop.repository.user.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserBaseServicesImp implements UserBaseServices {

    private static final String EMAIL_PATTERN = "(?:[a-z0-9!#$%&'*+\\=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+\\=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
    private final UserRepository userRepository;

    @Autowired
    public UserBaseServicesImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isEmail(String value) {
        Matcher matcher = Pattern.compile(EMAIL_PATTERN)
                .matcher(value);
        return matcher.find();
    }

    @Override
    public boolean existsByUsername(String username) {
        return this.userRepository.findUserByUsername(username) != null;
    }

    @Override
    public boolean existsByEmail(String email) {
        return this.userRepository.findUserByEmail(email) != null;
    }

    @Override
    public long getTotalUsers() {
        return this.userRepository.findAll().size();
    }

    @Override
    public User getUserByUsername(String username) {
        return this.userRepository.findUserByUsername(username);
    }

    @Override
    public User getUserByEmail(String email) {
        return this.userRepository.findUserByEmail(email);
    }

    @Override
    public void save(User user) {
        this.userRepository.save(user);
    }
}
