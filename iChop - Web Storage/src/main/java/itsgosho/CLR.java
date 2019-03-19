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

        byte[] imageBytes = DatatypeConverter.parseBase64Binary("iVBORw0KGgoAAAANSUhEUgAAAGQAAABkCAIAAAD/gAIDAAACs0lEQVR4Ae3csWpTARiG4cQcW9tiXSq2gq2UWoU4OTh0chDcvAQX70S8DfEmnCUUwVXRgItFF60KQayhICR6kMI3Nd//QSZ9O/3lvD0nffJnOCGku//iWafwc2F11VZfvn6zTRssLy/ZrNs9Y5s2GI/HNhuNvtumDdbXL9qs9JjsWf6TAKzgiQYLrEAgSNkssAKBIGWzwAoEgpTNAisQCFI2C6xAIEibW71XlXxluuCzNZ/MufC35J1O7VGNjz/bx8bL0BIpAEsWdgLLEikASxZ2AssSKQBLFnYCyxIpAEsWdgLLEikASxZ2AssSKWg0zpzeNHszjwcHt48GQT0zPTh/Z+bx4OB2Z2BrNssSKQBLFnYCyxIpAEsWdgLLEikASxZ2AssSKQBLFnYCyxIpAEsWdgLLEikASxZ2qt5I39i9bc+1teY/0dye5NP+I3uq8fEv27TBvb27Nnt/OLJNG0xfD2zGZlkiBWDJwk5gWSIFYMnCTmBZIgVgycJOYFkiBWDJwk5gWSIFYMnCTmBZIgXdny8f67fTp5WlwgdwT//z9Ejx3rDp+Sd7caF0/1u5or9Y+n/+wz1YwZMLFliBQJCyWWAFAkHKZoEVCAQpmwVWIBCkbBZYgUCQNucWz1byyfS3zcY7D2wz32BSOd27p5Wq8k4BL8OK5EkDFliBQJCyWWAFAkHKZoEVCAQpmwVWIBCkbBZYgUCQNs9HO5V8Y+OyzTZt8TfoDZ/UQl9N+g9tdHjpvm3a4MeR/1ZhXoYVyZMGLLACgSBls8AKBIKUzQIrEAhSNgusQCBI2SywAoEgZbMCrGb32vVK/uHjgc02r1y1TRvM8XuctgrXGw7fFqpOv3/TZmyWJVIAlizsBJYlUgCWLOwEliVSAJYs7ASWJVIAlizsBJYlUgCWLOwEliVS8Ad45EJSKeASrQAAAABJRU5ErkJggg==");
        BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imageBytes));

        this.userServices.updateAvatar("itsgosho",bufferedImage);

    }
}


//        String username = "itsgosho";
//
//        this.dropboxServices.createFolder("/"+username);
//
//        File file = new File("C:\\Users\\Gosho\\Desktop\\dropbox\\src\\main\\resources\\avatar-test.png");
//        InputStream inputStream = new FileInputStream(file);
//        String filePath = "/"+username+"/avatar.png";
//        this.dropboxServices.deleteFile(filePath);
//        this.dropboxServices.uploadFile(filePath,inputStream);
