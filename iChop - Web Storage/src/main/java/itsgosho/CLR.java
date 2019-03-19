package itsgosho;

import com.dropbox.core.v2.files.CreateFolderErrorException;
import itsgosho.services.DropboxServices;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

@Component
public class CLR implements CommandLineRunner {

    private final DropboxServices dropboxServices;

    @Autowired
    public CLR(DropboxServices dropboxServices) {
        this.dropboxServices = dropboxServices;
    }

    @Override
    public void run(String... args) throws Exception {


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
