package org.ichop.commons.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.ichop.commons.domain.JmsReplyModel;
import org.ichop.commons.domain.UserReply;
import org.ichop.commons.requesters.UserRequester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserSecurityService implements UserDetailsService {

    private final UserRequester userRequester;
    private final ObjectMapper objectMapper;

    @Autowired
    private UserSecurityService(UserRequester userRequester, ObjectMapper objectMapper) {
        this.userRequester = userRequester;
        this.objectMapper = objectMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        JmsReplyModel replyModel = this.userRequester.findByEmail(username);

        return this.objectMapper.convertValue(replyModel.getData(), UserReply.class);
    }
}