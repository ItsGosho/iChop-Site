package com.ichop.webstorage.services;

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
public class UserDataServicesImp implements UserDataServices {

    public static final String USER_AVATAR_FILE_PATH_PATTERN = "/%s/avatar-%s.png";
    private final DropboxServices dropboxServices;

    @Autowired
    public UserDataServicesImp(DropboxServices dropboxServices) {
        this.dropboxServices = dropboxServices;
    }


    @Override
    @CacheEvict(allEntries = true)
    public void updateAvatar(String username, String avatarAsBase64) {

        byte[] imageBytes = DatatypeConverter.parseBase64Binary(avatarAsBase64);
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(new ByteArrayInputStream(imageBytes));
        } catch (IOException e) {
            e.printStackTrace();
        }
        InputStream inputStream = this.convertBufferedImageToInputStream(bufferedImage);

        if (this.getSizeInMB(avatarAsBase64) > 1.00) {
            return;
        }

        if (bufferedImage.getWidth() > 200 || bufferedImage.getHeight() > 200) {
            return;
        }

        String filePath = String.format(USER_AVATAR_FILE_PATH_PATTERN, username, username);

        this.dropboxServices.deleteFile(filePath);
        this.dropboxServices.createFolder(username.toLowerCase());
        this.dropboxServices.uploadFile(filePath, inputStream);
    }

    @Cacheable
    public byte[] getAvatarAsBase64Array(String username) {

        InputStream inputStream = this.dropboxServices.downloadFile(String.format(USER_AVATAR_FILE_PATH_PATTERN, username, username));

        return this.inputStreamToBase64String(inputStream);
    }

    private byte[] inputStreamToBase64String(InputStream inputStream) {

        try {
            byte[] bytes = IOUtils.toByteArray(inputStream);
            return bytes;
        } catch (Exception ignored) {
        }

        return new byte[0];
    }

    private InputStream convertBufferedImageToInputStream(BufferedImage image) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "png", byteArrayOutputStream);
        } catch (Exception e) {

        }
        return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
    }

    private Double getSizeInMB(String base64) {
        int y = base64.endsWith("==") ? 2 : 1;
        double size = (((base64.length() * 3) / 4 - y) / 1024.00) / 1024.00;
        return size;
    }


}
