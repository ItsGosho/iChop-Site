package com.ichop.webstorage.services;

import java.awt.image.BufferedImage;
import java.io.InputStream;

public interface UserDataServices {

    void updateAvatar(String username, String avatarAsBase64);
    byte[] getAvatarAsBase64Array(String username);

}
