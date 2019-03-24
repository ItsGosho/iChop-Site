package com.ichop.core.components.jms;

import java.util.Map;

public interface JmsServices {


    void sendUpdateAvatarRequest(String username,String imageAsBase64String);

    boolean isPlayerLinkKeyValid(String key);
    Map<String,Object> getPlayerUUIDByLinkKey(String key);

    boolean sendSiteUserToPlayerLinkConnection(String key,String userUsername);

}
