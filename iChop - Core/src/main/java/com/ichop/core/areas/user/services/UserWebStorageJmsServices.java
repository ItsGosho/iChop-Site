package com.ichop.core.areas.user.services;

public interface UserWebStorageJmsServices {

    /*
     *
     * Updates the current user avatar ,by user username.
     * and image converted in binary64 string.
     * In case of not consumer ,nothing will happen.
     *
     * */
    void sendUpdateAvatarRequest(String username, String imageAsBase64String);

}
