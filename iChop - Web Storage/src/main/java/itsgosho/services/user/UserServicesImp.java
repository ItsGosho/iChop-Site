package itsgosho.services.user;

import itsgosho.services.dropbox.DropboxServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class UserServicesImp implements UserServices {

    private final DropboxServices dropboxServices;

    @Autowired
    public UserServicesImp(DropboxServices dropboxServices) {
        this.dropboxServices = dropboxServices;
    }


    @Override
    public void updateAvatar(String username, BufferedImage image) {
        InputStream inputStream = this.convertBufferedImageToInputStream(image);

        String filePath = String.format("/%s/avatar-%s.png",username,username);

        this.dropboxServices.deleteFile(filePath);
        this.dropboxServices.createFolder("itsgosho");
        this.dropboxServices.uploadFile(filePath,inputStream);
    }

    private InputStream convertBufferedImageToInputStream(BufferedImage image){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "png", byteArrayOutputStream);
        } catch (Exception e) {

        }
        return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
    }

}
