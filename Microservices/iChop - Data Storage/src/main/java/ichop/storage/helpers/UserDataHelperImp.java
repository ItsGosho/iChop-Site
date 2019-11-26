package ichop.storage.helpers;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
@CacheConfig(cacheNames = "users")
public class UserDataHelperImp implements UserDataHelper {

    private static final String USER_AVATAR_FILE_PATH_PATTERN = "/%s/avatar-%s.png";

    private final DropboxHelper dropboxHelper;

    @Autowired
    public UserDataHelperImp(DropboxHelper dropboxHelper) {
        this.dropboxHelper = dropboxHelper;
    }


    @Override
    @CacheEvict(allEntries = true)
    public void updateAvatar(String username, String avatarAsBase64) {

        avatarAsBase64 = avatarAsBase64.substring(avatarAsBase64.lastIndexOf("base64,") + "base64,".length());
        byte[] imageBytes = DatatypeConverter.parseBase64Binary(avatarAsBase64);
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(new ByteArrayInputStream(imageBytes));
        } catch (IOException e) {
            e.printStackTrace();
        }
        InputStream inputStream = this.convertBufferedImageToInputStream(bufferedImage);

        String filePath = String.format(USER_AVATAR_FILE_PATH_PATTERN, username, username);

        this.dropboxHelper.deleteFile(filePath);
        this.dropboxHelper.createFolder(username.toLowerCase());
        this.dropboxHelper.uploadFile(filePath, inputStream);
    }

    @Cacheable
    public byte[] getAvatarAsBase64Array(String username) {

        InputStream inputStream = this.dropboxHelper.downloadFile(String.format(USER_AVATAR_FILE_PATH_PATTERN, username, username));

        return this.inputStreamToBase64String(inputStream);
    }

    private byte[] inputStreamToBase64String(InputStream inputStream) {

        try {
            return IOUtils.toByteArray(inputStream);
        } catch (Exception ignored) {
        }

        return new byte[0];
    }

    private InputStream convertBufferedImageToInputStream(BufferedImage image) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "png", byteArrayOutputStream);
        } catch (Exception ignored) {

        }
        return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
    }

}
