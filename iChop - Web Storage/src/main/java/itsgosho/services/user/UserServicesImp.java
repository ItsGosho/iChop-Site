package itsgosho.services.user;

import itsgosho.services.dropbox.DropboxServices;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

@Service
public class UserServicesImp implements UserServices {

    public static final String USER_AVATAR_FILE_PATH_PATTERN = "/%s/avatar-%s.png";
    private final DropboxServices dropboxServices;

    @Autowired
    public UserServicesImp(DropboxServices dropboxServices) {
        this.dropboxServices = dropboxServices;
    }


    @Override
    public void updateAvatar(String username, BufferedImage image) {
        InputStream inputStream = this.convertBufferedImageToInputStream(image);

        if(this.getSizeInMB(inputStream) > 1.00){
            return;
        }

        if(image.getWidth() > 200 || image.getHeight() > 200){
            return;
        }

        String filePath = String.format(USER_AVATAR_FILE_PATH_PATTERN,username,username);

        this.dropboxServices.deleteFile(filePath);
        this.dropboxServices.createFolder("itsgosho");
        this.dropboxServices.uploadFile(filePath,inputStream);
    }

    @Override
    public byte[] getAvatarAsBase64Array(String username) {

        InputStream inputStream = this.dropboxServices.downloadFile(String.format(USER_AVATAR_FILE_PATH_PATTERN,username,username));

        return this.inputStreamToBase64String(inputStream);
    }

    private byte[] inputStreamToBase64String(InputStream inputStream){

        try {
            byte[] bytes = IOUtils.toByteArray(inputStream);
            return bytes;
        } catch (Exception ignored) { }

        return new byte[0];
    }

    private InputStream convertBufferedImageToInputStream(BufferedImage image){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "png", byteArrayOutputStream);
        } catch (Exception e) {

        }
        return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
    }

    private int getSizeInMB(InputStream inputStream){
        try {
            return inputStream.available()/1048576;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
