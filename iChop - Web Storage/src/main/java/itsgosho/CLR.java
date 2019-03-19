package itsgosho;

import itsgosho.services.dropbox.DropboxServices;
import itsgosho.services.user.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;

@Component
public class CLR implements CommandLineRunner {

    private final DropboxServices dropboxServices;
    private final UserServices userServices;

    @Autowired
    public CLR(DropboxServices dropboxServices, UserServices userServices) {
        this.dropboxServices = dropboxServices;
        this.userServices = userServices;
    }

    @Override
    public void run(String... args) throws Exception {
//        byte[] imageBytes = DatatypeConverter.parseBase64Binary("1");
//        BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imageBytes));
//
//        this.userServices.updateAvatar("itsgosho", bufferedImage);

    }
}
