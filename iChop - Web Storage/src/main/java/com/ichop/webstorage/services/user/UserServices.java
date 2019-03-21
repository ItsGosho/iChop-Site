package com.ichop.webstorage.services.user;

import java.awt.image.BufferedImage;
import java.io.InputStream;

public interface UserServices {

    void updateAvatar(String username, BufferedImage image);
    byte[] getAvatarAsBase64Array(String username);

}
