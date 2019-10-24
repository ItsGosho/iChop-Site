package ichop.storage.helpers;

import com.dropbox.core.v2.files.CreateFolderResult;
import com.dropbox.core.v2.files.FileMetadata;

import java.io.InputStream;

public interface DropboxHelper {

    InputStream downloadFile(String filePath);

    FileMetadata uploadFile(String filePath, InputStream fileStream);

    CreateFolderResult createFolder(String folderPath);

    void deleteFile(String filePath);

}
