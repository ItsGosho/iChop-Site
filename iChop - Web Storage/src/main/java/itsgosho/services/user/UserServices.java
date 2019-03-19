package itsgosho.services.user;

import java.awt.image.BufferedImage;
import java.io.InputStream;

public interface UserServices {

    void updateAvatar(String username, BufferedImage image);

}
